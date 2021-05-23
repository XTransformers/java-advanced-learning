package com.xtransformers.designpattern.collector.refactor;

import java.util.List;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class RedisMetricsStorage implements MetricsStorage {

    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startInMillis, long endInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startInMillis, long endInMillis) {
        return null;
    }
}
