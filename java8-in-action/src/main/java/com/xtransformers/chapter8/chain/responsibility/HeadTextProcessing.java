package com.xtransformers.chapter8.chain.responsibility;

public class HeadTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}
