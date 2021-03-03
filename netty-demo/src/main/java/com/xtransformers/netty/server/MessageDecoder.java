package com.xtransformers.netty.server;

import com.alibaba.fastjson.JSON;
import com.xtransformers.netty.BaseMessage;
import com.xtransformers.netty.MessageUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
public class MessageDecoder extends ChannelInboundHandlerAdapter {

    /**
     * 连接注册 触发事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channel register");
        super.channelRegistered(ctx);
    }

    /**
     * 覆写 channelRead 方法并接收客户端上报的消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收客户端发送的消息
        ByteBuf receivedByteBuf = (ByteBuf) msg;
        // 消息解码
        BaseMessage receivedBaseMessage = MessageUtils.getBaseMessage(receivedByteBuf);
        log.info("receive a message from client : " + JSON.toJSONString(receivedBaseMessage));

        try {
            // 定义回复消息体
            BaseMessage responseMessage = new BaseMessage(receivedBaseMessage.getMessageId() + 1,
                    "message from server", new Date());
            // 消息编码
            ByteBuf responseByteBuf = MessageUtils.getByteBuf(responseMessage);
            ctx.writeAndFlush(responseByteBuf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接异常 触发事件
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("channel exception");
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 连接断开 触发事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("channel remove");
        super.handlerRemoved(ctx);
    }
}
