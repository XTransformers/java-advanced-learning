package com.xtransformers.designpattern.collector.refactor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class ConsoleReporter extends AbstractScheduledReporter {

    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer statViewer) {
        super(metricsStorage, aggregator, statViewer);
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    // 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(() -> {
            long durationInMillis = durationInSeconds * 1000;
            long endInMillis = System.currentTimeMillis();
            long startInMillis = endInMillis - durationInMillis;
            doStatAndReport(startInMillis, durationInMillis);
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}
