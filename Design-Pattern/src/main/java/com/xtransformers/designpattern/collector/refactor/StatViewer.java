package com.xtransformers.designpattern.collector.refactor;

import java.util.Map;

/**
 * @author daniel
 * @date 2021-05-23
 */
public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startInMillis, long endInMillis);
}
