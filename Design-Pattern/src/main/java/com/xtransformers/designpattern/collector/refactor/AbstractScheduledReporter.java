package com.xtransformers.designpattern.collector.refactor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-05-23
 */
public abstract class AbstractScheduledReporter {

    private static final long MAX_STAT_DURATION_IN_MILLIS = 10 * 60 * 1000;

    protected MetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected StatViewer statViewer;

    public AbstractScheduledReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer statViewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.statViewer = statViewer;
    }

    protected void doStatAndReport(long startInMillis, long endInMillis) {
        Map<String, RequestStat> stats = doStat(startInMillis, endInMillis);
        // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
        statViewer.output(stats, startInMillis, endInMillis);
    }

    private Map<String, RequestStat> doStat(long startInMillis, long endInMillis) {
        Map<String, List<RequestStat>> segmentStats = Maps.newHashMap();
        long segmentStartTimeInMillis = startInMillis;
        while (segmentStartTimeInMillis < endInMillis) {
            long segmentEndTimeInMillis = startInMillis + MAX_STAT_DURATION_IN_MILLIS;
            if (segmentEndTimeInMillis > endInMillis) {
                segmentEndTimeInMillis = endInMillis;
            }
            // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
            Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(segmentStartTimeInMillis, segmentEndTimeInMillis);
            if (CollectionUtils.isEmpty(requestInfos)) {
                continue;
            }
            // 第2个代码逻辑：根据原始数据，计算得到统计数据；
            Map<String, RequestStat> segmentStat = aggregator.aggregate(requestInfos, segmentEndTimeInMillis - segmentStartTimeInMillis);
            addStat(segmentStats, segmentStat);
            segmentStartTimeInMillis += MAX_STAT_DURATION_IN_MILLIS;
        }

        long durationInMillis = endInMillis - startInMillis;
        return aggregateStat(segmentStats, durationInMillis);
    }

    protected void addStat(Map<String, List<RequestStat>> segmentStats, Map<String, RequestStat> segmentStat) {
        segmentStat.forEach((apiName, requestStat) -> {
            List<RequestStat> requestStats = segmentStats.putIfAbsent(apiName, Lists.newArrayList());
            if (requestStat != null) {
                requestStats.add(requestStat);
            }
        });
    }

    private Map<String, RequestStat> aggregateStat(Map<String, List<RequestStat>> segmentStats, long durationInMillis) {
        Map<String, RequestStat> result = Maps.newHashMap();
        segmentStats.forEach((apiName, apiStates) -> {
            double maxResponseTime = apiStates.stream()
                    .mapToDouble(RequestStat::getMaxResponseTime).max().orElse(Double.MIN_VALUE);
            double minResponseTime = apiStates.stream()
                    .mapToDouble(RequestStat::getMinResponseTime).min().orElse(Double.MAX_VALUE);
            double sumResponseTime = apiStates.stream()
                    .mapToDouble(requestStat -> requestStat.getCount() * requestStat.getAvgResponseTime()).sum();
            long count = apiStates.stream().mapToLong(RequestStat::getCount).sum();
            double avgResponseTime = sumResponseTime / count;
            long tps = count / durationInMillis * 1000;
            RequestStat build = RequestStat.builder()
                    .maxResponseTime(maxResponseTime)
                    .minResponseTime(minResponseTime)
                    .avgResponseTime(avgResponseTime)
                    .count(count)
                    .tps(tps)
                    .build();
            result.put(apiName, build);
        });
        return result;
    }
}
