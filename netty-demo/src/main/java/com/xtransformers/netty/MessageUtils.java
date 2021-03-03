package com.xtransformers.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MessageUtils {

    /**
     * 将 BaseMessage 消息写入ByteBuf
     *
     * @param baseMessage BaseMessage 对象
     * @return ByteBuf 对象
     * @throws UnsupportedEncodingException
     */
    public static ByteBuf getByteBuf(BaseMessage baseMessage) throws UnsupportedEncodingException {
        byte[] bytes = JSON.toJSONString(baseMessage).getBytes(StandardCharsets.UTF_8);
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    /**
     * 从 ByteBuf 中获取信息，使用 UTF-8 编码后
     * 解析为BaseMessage的系统消息格式
     *
     * @param byteBuf ByteBuf 对象
     * @return
     */
    public static BaseMessage getBaseMessage(ByteBuf byteBuf) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String messageStr = new String(bytes, StandardCharsets.UTF_8);
        BaseMessage baseMessage = JSON.parseObject(messageStr, BaseMessage.class);
        baseMessage.setReceiveTime(new Date());
        return baseMessage;
    }

}
