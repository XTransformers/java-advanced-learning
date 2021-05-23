package com.xtransformers.designpattern.collector.refactor;

import java.util.List;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-05-23
 */
public abstract class AbstractScheduledReporter {

    protected MetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected StatViewer statViewer;

    public AbstractScheduledReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer statViewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.statViewer = statViewer;
    }

    protected void doStatAndReport(long startInMillis, long endInMillis) {
        // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
        long durationInMillis = endInMillis - startInMillis;
        Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startInMillis, endInMillis);
        // 第2个代码逻辑：根据原始数据，计算得到统计数据；
        Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, durationInMillis);
        // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
        statViewer.output(stats, startInMillis, endInMillis);
    }
}
