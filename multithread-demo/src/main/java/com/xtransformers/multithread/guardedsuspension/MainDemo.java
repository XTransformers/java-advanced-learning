package com.xtransformers.multithread.guardedsuspension;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-10-12
 */
public class MainDemo {

    public static void main(String[] args) throws InterruptedException {
        MainDemo mainDemo = new MainDemo();
        new Thread(() -> {
            Response response = mainDemo.handleWebReq();
        }).start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> {
            // 这里返回时保证 id 一致即可
            Message msg = new Message("123", "{mq response message}");
            mainDemo.onMessage(msg);
        }).start();
    }

    /**
     * 处理浏览器发来的请求
     *
     * @return 响应对象实例
     */
    Response handleWebReq() {
        String id = "123";
        // 创建消息
        Message msg = new Message(id, "{send to mq message}");
        // 创建实例
        GuardedObject<Message> go = GuardedObject.create(id);
        // 发送消息
        send(msg);
        // 等待MQ消息
        Message r = go.get(Objects::nonNull, msg.id);
        System.out.println("receive " + r);
        return new Response();
    }

    /**
     * 发送消息
     *
     * @param message 消息对象实例
     */
    private void send(Message message) {
        System.out.println("MainDemo.send " + message);
    }

    /**
     * MQ 消息返回后会调用该方法
     * 该方法执行线程不同于发送消息的线程
     *
     * @param message 消息对象实例
     */
    void onMessage(Message message) {
        // 唤醒等待的线程
        GuardedObject.fireEvent(message.id, message);
    }


}

class Message {
    String id;
    String content;

    Message(String id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", msg='" + content + '\'' +
                '}';
    }
}

class Response {

}
