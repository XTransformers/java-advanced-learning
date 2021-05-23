package com.xtransformers.designpattern.collector.refactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class ConsoleReporter {

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer statViewer;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer statViewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.statViewer = statViewer;
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    // 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(() -> {
            long durationInMillis = durationInSeconds * 1000;
            long endInMillis = System.currentTimeMillis();
            long startInMillis = endInMillis - durationInMillis;

            // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
            Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startInMillis, endInMillis);
            // 第2个代码逻辑：根据原始数据，计算得到统计数据；
            Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, durationInMillis);
            // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
            statViewer.output(stats, startInMillis, endInMillis);
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}
