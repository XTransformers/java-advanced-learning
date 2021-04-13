package com.xtransformers.chapter8.chain.responsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Client {
    public static void main(String[] args) {
        ProcessingObject<String> processingObject1 = new HeadTextProcessing();
        ProcessingObject<String> processingObject2 = new SpellCheckerProcessing();
        processingObject1.setSuccessor(processingObject2);
        String result = processingObject1.handle("Aren't labdas really sexy?!!");
        // From Raoul, Mario and Alan: Aren't lambda really sexy?!!
        System.out.println(result);

        // 使用 Lambda

        UnaryOperator<String> headerProcessing = text -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = text -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }
}
