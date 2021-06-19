package com.xtransformers.circutebreaker;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class CircuitBreakerTest {

    @Test
    public void testSuccess() {
        CircuitBreaker cb = new CircuitBreaker(new Config());
        String bookName = cb.run(
                () -> "deep in String cloud",
                t -> "boom"
        );
        assertEquals("deep in String cloud", bookName);
    }

    @Test
    public void testFailuer() {
        RestTemplate restTemplate = new RestTemplate();
        CircuitBreaker cb = new CircuitBreaker(new Config());
        String result = cb.run(
                () -> restTemplate.getForObject("https://httpbin.org/status/500", String.class),
                t -> "boom"
        );
        assertEquals("boom", result);
    }

    @Test
    public void testOpen() {
        Config config = new Config();
//        config.setFailureTimeInterval(100 * 1000);
        CircuitBreaker cb = new CircuitBreaker(config);
        RestTemplate restTemplate = new RestTemplate();
        int degradeCount = 0;

        for (int i = 0; i < 10; i++) {
            String result = cb.run(
                    () -> restTemplate.getForObject("https://httpbin.org/status/500", String.class),
//                    () -> {
//                        int a = 3 / 0;
//                        return "";
//                    },
                    t -> {
                        if (t instanceof DegradeException) {
                            return "degrade";
                        }
                        return "boom";
                    }
            );
            if ("degrade".equals(result)) {
                degradeCount++;
            }
        }
        assertEquals(5, degradeCount);
    }

}