package com.xtransformers.designpattern.collector.v1;

import lombok.Builder;
import lombok.Data;

/**
 * @author daniel
 * @date 2021-05-23
 */
@Data
@Builder
public class RequestInfo {

    private String apiName;

    private Double responseTime;

    private Double timestamp;
}
