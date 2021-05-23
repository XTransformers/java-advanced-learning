package com.xtransformers.designpattern.collector.refactor;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmailReporterTest {

    @Test
    public void trimTimeFieldsToZeroOfNextDay() {
        MetricsStorage metricsStorage = Mockito.mock(MetricsStorage.class);
        Aggregator aggregator = Mockito.mock(Aggregator.class);
        StatViewer statViewer = Mockito.mock(StatViewer.class);
        EmailReporter emailReporter = new EmailReporter(metricsStorage, aggregator, statViewer);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 5 - 1, 23, 14, 46, 03);
        Date date = calendar.getTime();
        Date trimTimeFieldsToZeroOfNextDay = emailReporter.trimTimeFieldsToZeroOfNextDay(date);
        assertNotNull(trimTimeFieldsToZeroOfNextDay);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2021, 5 - 1, 24, 0, 0, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        assertEquals(sdf.format(calendar2.getTime()), sdf.format(trimTimeFieldsToZeroOfNextDay));
    }
}