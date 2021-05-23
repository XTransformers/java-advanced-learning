package com.xtransformers.designpattern.collector.v1;

import lombok.Builder;
import lombok.Data;

/**
 * @author daniel
 * @date 2021-05-23
 */
@Data
@Builder
public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
}
