package com.xtransformers.reactor;

import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;

import java.time.Duration;

public class MyReactiveLibraryTest {

    private MyReactiveLibrary library;

    @Before
    public void setup() {
        library = new MyReactiveLibrary();
    }

    @Test
    public void alphabet5() {
        StepVerifier.create(library.alphabet5('x'))
                .expectNext("x", "y", "z")
                .expectComplete()
                .verify();
    }

    @Test
    public void withDelay() {
        Duration testDuration = StepVerifier.withVirtualTime(
                () -> library.withDelay("foo", 30))
                .expectSubscription()
                .thenAwait(Duration.ofSeconds(10))
                .expectNoEvent(Duration.ofSeconds(10))
                .thenAwait(Duration.ofSeconds(10))
                .expectNext("foo")
                .expectComplete()
                .verify();
        System.out.println(testDuration.toMillis() + "ms");
    }
}