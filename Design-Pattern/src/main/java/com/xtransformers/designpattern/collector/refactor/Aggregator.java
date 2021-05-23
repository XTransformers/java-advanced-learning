package com.xtransformers.designpattern.collector.refactor;

import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class Aggregator {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> result = Maps.newHashMap();
        requestInfos.forEach((apiName, requestInfoList) -> {
            RequestStat requestStat = doAggregate(requestInfoList, durationInMillis);
            result.put(apiName, requestStat);
        });
        return result;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = requestInfos.stream()
                .map(RequestInfo::getResponseTime)
                .collect(Collectors.toList());

        return RequestStat.builder()
                .maxResponseTime(max(respTimes))
                .minResponseTime(min(respTimes))
                .avgResponseTime(avg(respTimes))
                .p999ResponseTime(percentile999(respTimes))
                .p99ResponseTime(percentile99(respTimes))
                .count(respTimes.size())
                .tps(tps(respTimes.size(), durationInMillis / 1000))
                .build();
    }

    private double max(List<Double> respTimes) {
        return respTimes.stream().mapToDouble(d -> d)
                .max()
                .orElse(-1);
    }

    private double min(List<Double> respTimes) {
        return respTimes.stream().mapToDouble(d -> d)
                .min()
                .orElse(-1);
    }

    private double avg(List<Double> respTimes) {
        return respTimes.stream().mapToDouble(d -> d)
                .average()
                .orElse(-1);
    }

    private double percentile999(List<Double> respTimes) {
        int idx999 = (int) (respTimes.size() * 0.999);
        respTimes.sort(Comparator.naturalOrder());
        return respTimes.get(idx999);
    }

    private double percentile99(List<Double> respTimes) {
        int idx99 = (int) (respTimes.size() * 0.99);
        respTimes.sort(Comparator.naturalOrder());
        return respTimes.get(idx99);
    }

    private long tps(int count, long durationInSeconds) {
        return count / durationInSeconds;
    }
}
