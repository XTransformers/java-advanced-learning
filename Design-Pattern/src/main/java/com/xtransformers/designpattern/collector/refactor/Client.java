package com.xtransformers.designpattern.collector.refactor;

import java.util.Random;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class Client {
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        // 定时触发统计并将结果显示到终端
        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60, 60);

        // 定时触发统计并将结果输出到邮件
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addAddress("xxx@126.com");
        EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        // 收集接口访问数据，可以放到 AOP 切面中
        MetricsCollector metricsCollector = new MetricsCollector(storage, 20);
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
