package com.xtransformers.designpattern.collector.refactor;

import java.util.List;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-05-23
 */
public interface MetricsStorage {

    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startInMillis, long endInMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startInMillis, long endInMillis);
}
