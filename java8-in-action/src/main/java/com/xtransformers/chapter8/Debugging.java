package com.xtransformers.chapter8;

import java.util.Arrays;
import java.util.List;

public class Debugging {
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(5, 10), null);
        points.stream().map(point -> point.getX()).forEach(System.out::println);
    }
    /**
     * Exception in thread "main" java.lang.NullPointerException
     * 	at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
     * 	at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
     * 	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
     * 	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
     * 	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
     * 	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
     * 	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
     * 	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
     * 	at com.xtransformers.chapter8.Debugging.main(Debugging.java:9)
     */
    /**
     * Exception in thread "main" java.lang.NullPointerException
     * 	at com.xtransformers.chapter8.Debugging.lambda$main$0(Debugging.java:9)
     * 	at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
     * 	at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
     * 	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
     * 	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
     * 	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
     * 	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
     * 	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
     * 	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
     * 	at com.xtransformers.chapter8.Debugging.main(Debugging.java:9)
     */
}
