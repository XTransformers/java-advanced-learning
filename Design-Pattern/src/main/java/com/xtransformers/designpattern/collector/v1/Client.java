package com.xtransformers.designpattern.collector.v1;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class Client {
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();

        ConsoleReporter consoleReporter = new ConsoleReporter(storage);
        consoleReporter.startRepeatedReport(60, 60);

        EmailReporter emailReporter = new EmailReporter(storage);
        emailReporter.addAddress("xxx@126.com");
        emailReporter.startDailyReport();

        MetricsCollector metricsCollector = new MetricsCollector(storage);
        metricsCollector.recordRequest(
                RequestInfo.builder()
                        .apiName("register")
                        .timestamp(123D)
                        .responseTime(10123D)
                        .build());
    }
}
