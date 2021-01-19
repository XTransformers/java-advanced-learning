package com.xtransformers.rabbit.consumer;

import com.xtransformers.rabbit.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitmqConfig.QUEUE_B)
@Slf4j
public class MsgConsumerB {

    @RabbitHandler
    public void handler(String msg) {
        log.info("接收队列 B 中内容 " + msg);
    }

}
