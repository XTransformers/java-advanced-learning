package com.xtransformers.chapter8;

import java.util.Arrays;
import java.util.List;

public class Debugging2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 4);
        numbers.stream().map(Debugging2::divideByZero).forEach(System.out::println);
    }

    public static int divideByZero(int n) {
        return n / 0;
    }

    /**
     * Exception in thread "main" java.lang.ArithmeticException: / by zero
     * 	at com.xtransformers.chapter8.Debugging2.divideByZero(Debugging2.java:13)
     * 	at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
     * 	at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
     * 	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
     * 	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
     * 	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
     * 	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
     * 	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
     * 	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
     * 	at com.xtransformers.chapter8.Debugging2.main(Debugging2.java:9)
     */
}
