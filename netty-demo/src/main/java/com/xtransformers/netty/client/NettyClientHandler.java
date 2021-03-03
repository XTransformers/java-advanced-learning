package com.xtransformers.netty.client;

import com.alibaba.fastjson.JSON;
import com.xtransformers.netty.BaseMessage;
import com.xtransformers.netty.MessageUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接创建后，Netty会自动调用channelActive方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建一条消息，发送给服务端
        BaseMessage baseMessage = new BaseMessage(0, "message from client", new Date());
        ByteBuf byteBuf = MessageUtils.getByteBuf(baseMessage);
        ctx.writeAndFlush(byteBuf);
        log.info("send a message to server:" + JSON.toJSONString(baseMessage));
    }

    /**
     * 读取服务端的消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        BaseMessage baseMessage = MessageUtils.getBaseMessage(byteBuf);
        log.info("receive a message from server : " + JSON.toJSONString(baseMessage));
    }
}
