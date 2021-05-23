package com.xtransformers.designpattern.collector.v1;

import java.util.Comparator;
import java.util.List;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class Aggregator {

    public static RequestStat aggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        double maxResponseTime = Double.MAX_VALUE;
        double minResponseTime = Double.MIN_VALUE;
        double avgResponseTime = -1;
        double p999ResponseTime = -1;
        double p99ResponseTime = -1;
        double sumResponseTime = -1;
        long count = 0;

        for (RequestInfo requestInfo : requestInfos) {
            count++;
            double responseTime = requestInfo.getResponseTime();
            if (responseTime > maxResponseTime) {
                maxResponseTime = responseTime;
            }
            if (responseTime < minResponseTime) {
                minResponseTime = responseTime;
            }
            sumResponseTime += responseTime;
        }
        if (count != 0) {
            avgResponseTime = sumResponseTime / count;
        }
        long tps = count / durationInMillis * 1000;

        requestInfos.sort(Comparator.comparing(RequestInfo::getResponseTime));
        int idxP999 = (int) (count * 0.999);
        int idxP99 = (int) (count * 0.99);
        if (count != 0) {
            p999ResponseTime = requestInfos.get(idxP999).getResponseTime();
            p99ResponseTime = requestInfos.get(idxP99).getResponseTime();
        }

        return RequestStat.builder()
                .maxResponseTime(maxResponseTime)
                .minResponseTime(minResponseTime)
                .avgResponseTime(avgResponseTime)
                .p999ResponseTime(p999ResponseTime)
                .p99ResponseTime(p99ResponseTime)
                .count(count)
                .tps(tps)
                .build();
    }
}
