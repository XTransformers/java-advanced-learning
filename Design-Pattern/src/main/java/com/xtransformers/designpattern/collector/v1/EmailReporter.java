package com.xtransformers.designpattern.collector.v1;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class EmailReporter {

    private static final long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private EmailSender emailSender;
    private List<String> toAddresses = Lists.newArrayList();

    public EmailReporter(MetricsStorage metricsStorage) {
        this(metricsStorage, new EmailSender());
    }

    public EmailReporter(MetricsStorage metricsStorage, EmailSender emailSender) {
        this.metricsStorage = metricsStorage;
        this.emailSender = emailSender;
    }

    public void addAddress(String email) {
        toAddresses.add(email);
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endInMillis = System.currentTimeMillis();
                long startInMillis = endInMillis - durationInMillis;

                // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startInMillis, endInMillis);

                // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                Map<String, RequestStat> stats = Maps.newHashMap();
                requestInfos.forEach((apiName, requestInfoList) -> {
                    RequestStat requestStat = Aggregator.aggregate(requestInfoList, durationInMillis);
                    stats.put(apiName, requestStat);
                });

                // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
                // TODO: 格式化为html格式，并且发送邮件
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }
}
