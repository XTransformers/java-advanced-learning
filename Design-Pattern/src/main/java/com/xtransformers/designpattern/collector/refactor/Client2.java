package com.xtransformers.designpattern.collector.refactor;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class Client2 {
    public static void main(String[] args) {
        ConsoleReporter consoleReporter = new ConsoleReporter();
        consoleReporter.startRepeatedReport(60, 60);

        List<String> addressList = Lists.newArrayList();
        addressList.add("test@123.com");
        EmailReporter emailReporter = new EmailReporter(addressList);
        emailReporter.startDailyReport();

        // 收集接口访问数据，可以放到 AOP 切面中
        MetricsCollector metricsCollector = new MetricsCollector();
        double timestamp = System.currentTimeMillis();
        double responseTime = new Random().nextDouble();
        metricsCollector.recordRequest(
                RequestInfo.builder()
                        .apiName("register")
                        .timestamp(timestamp)
                        .responseTime(responseTime)
                        .build());
    }
}
