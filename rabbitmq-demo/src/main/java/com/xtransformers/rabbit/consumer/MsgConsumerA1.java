package com.xtransformers.rabbit.consumer;

import com.xtransformers.rabbit.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 一个生产者，多个消费者，并且他们的分发是负载均衡的。
 */
@Component
@RabbitListener(queues = RabbitmqConfig.QUEUE_A)
@Slf4j
public class MsgConsumerA1 {

    @RabbitHandler
    public void handler(String msg) {
        log.info("接收队列 A 中内容 " + msg);
    }

}
