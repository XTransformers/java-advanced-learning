package com.xtransformers.rabbit;

import com.xtransformers.rabbit.producer.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

    @Autowired
    private MsgProducer msgProducer;

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            for (int i = 0; i < 100; i++) {
                msgProducer.sendMsg("message::" + i);
            }
        };
    }

}
