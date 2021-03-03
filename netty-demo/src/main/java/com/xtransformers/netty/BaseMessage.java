package com.xtransformers.netty;

import java.util.Date;

/**
 * 定义通用消息格式BaseMessage
 */
public class BaseMessage {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 接收时间
     */
    private Date receiveTime;
    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 消息流水号
     */
    private int messageId;

    public BaseMessage(int messageId, String messageContent, Date createTime) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
