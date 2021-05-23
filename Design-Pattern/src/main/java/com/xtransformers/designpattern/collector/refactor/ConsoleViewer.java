package com.xtransformers.designpattern.collector.refactor;

import com.google.gson.Gson;

import java.util.Map;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class ConsoleViewer implements StatViewer {
    @Override
    public void output(Map<String, RequestStat> requestStats, long startInMillis, long endInMillis) {
        System.out.println("Time span [" + startInMillis + ", " + endInMillis + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}
