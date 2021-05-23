package com.xtransformers.designpattern.collector;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class Metrics {

    private final Map<String, List<Double>> responseTimes = Maps.newHashMap();
    private final Map<String, List<Double>> timestamps = Maps.newHashMap();

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void recordResponseTime(String apiName, double responseTime) {
        responseTimes.putIfAbsent(apiName, Lists.newArrayList());
        responseTimes.get(apiName).add(responseTime);
    }

    public void recordTimestamp(String apiName, double timestamp) {
        timestamps.putIfAbsent(apiName, Lists.newArrayList());
        timestamps.get(apiName).add(timestamp);
    }

    public void startRepeatedReport(long period, TimeUnit timeUnit) {
        executor.scheduleAtFixedRate(() -> {
            Gson gson = new Gson();
            Map<String, Map<String, Double>> stats = Maps.newHashMap();

            responseTimes.forEach((apiName, apiResponseTimes) -> {
                stats.putIfAbsent(apiName, Maps.newHashMap());
                stats.get(apiName).put("max", max(apiResponseTimes));
                stats.get(apiName).put("avg", avg(apiResponseTimes));
            });

            timestamps.forEach((apiName, apiTimestamps) -> {
                stats.putIfAbsent(apiName, Maps.newHashMap());
                stats.get(apiName).put("count", (double) apiTimestamps.size());
            });

            System.out.println(gson.toJson(stats));

        }, 0, period, timeUnit);
    }

    private double max(List<Double> apiResponseTimes) {
        return 0;
    }

    private double avg(List<Double> apiResponseTimes) {
        return 0;
    }
}
