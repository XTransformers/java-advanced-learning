package com.xtransformers.designpattern.collector.refactor;

import com.google.common.annotations.VisibleForTesting;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class EmailReporter extends AbstractScheduledReporter {

    private static final long DAY_HOURS_IN_SECONDS = 86400L;

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer statViewer) {
        super(metricsStorage, aggregator, statViewer);
    }

    public void startDailyReport() {
        Date firstTime = trimTimeFieldsToZeroOfNextDay(new Date());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endInMillis = System.currentTimeMillis();
                long startInMillis = endInMillis - durationInMillis;
                doStatAndReport(startInMillis, durationInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }

    @VisibleForTesting
    protected Date trimTimeFieldsToZeroOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
