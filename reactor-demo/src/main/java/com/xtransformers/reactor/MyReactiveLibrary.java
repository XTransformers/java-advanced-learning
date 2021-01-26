package com.xtransformers.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MyReactiveLibrary {

    public Flux<String> alphabet5(char from) {
        return Flux.range((int) from, 5)
                // 不加这一句，如果传入 x，会返回 x y z { | ，也就是一定会返回5个元素
                .take(Math.min(5, 'z' - from + 1))
                .map(i -> "" + (char) i.intValue());
    }

    public Mono<String> withDelay(String value, int delaySeconds) {
        return Mono.just(value)
                .delaySubscription(Duration.ofSeconds(delaySeconds));
    }

}
