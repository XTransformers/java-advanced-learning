package com.xtransformers.chapter12;

import org.junit.Test;

import java.time.*;
import java.time.chrono.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

public class ZoneIdDemo {

    @Test
    public void test4() {
        // Asia/Shanghai
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println(zoneId);

        System.out.println(ZoneId.getAvailableZoneIds());

        // 为时间点添加时区信息
        ZoneId romeZoneId = ZoneId.of("Europe/Rome");
        LocalDate date = LocalDate.of(2021, Month.APRIL, 26);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZoneId);
        // 2021-04-26T00:00+02:00[Europe/Rome]
        System.out.println(zdt1);

        LocalDateTime datetime = LocalDateTime.of(2021, 4, 26, 23, 49, 01);
        ZonedDateTime zdt2 = datetime.atZone(romeZoneId);
        // 2021-04-26T23:49:01+02:00[Europe/Rome]
        System.out.println(zdt2);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZoneId);
        // 2021-04-26T17:50:25.638+02:00[Europe/Rome]
        System.out.println(zdt3);

        // 2021-04-26T23:51:27.705+08:00[Asia/Shanghai]
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()));

        // LocalDateTime = LocalDate + LocalTime
        // ZonedDateTime = LocalDateTime + ZonedId
    }

    @Test
    public void test5() {
        LocalDateTime dateTime = LocalDateTime.of(2021, Month.APRIL, 26, 23, 53, 59);
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        // Z
        System.out.println(ZoneOffset.UTC);
        // 2021-04-26T23:53:59Z
        System.out.println(instant);

        ZoneId romeZone = ZoneId.of("Europe/Rome");
        instant = Instant.now();
        LocalDateTime dt = LocalDateTime.ofInstant(instant, romeZone);
        // 2021-04-28T16:21:22.746
        System.out.println(dt);
    }

    @Test
    public void test6() {
        // 没有考虑日光时的影响
        ZoneOffset newYorkZoneOffset = ZoneOffset.of("-05:00");
        LocalDateTime ldt = LocalDateTime.of(2021, Month.MARCH, 28, 22, 23, 15);
        // ISO8601 历法系统
        // ISO-8601日历系统是世界文明日历系统的事实标准。
        OffsetDateTime offsetDateTime = OffsetDateTime.of(ldt, newYorkZoneOffset);
        // 2021-04-28T22:23:15-05:00
        System.out.println(offsetDateTime);
    }

    // 非 ISO 历法系统
    @Test
    public void test7() {
        LocalDate localDate = LocalDate.of(2021, Month.APRIL, 28);
        JapaneseDate japaneseDate = JapaneseDate.from(localDate);
        // Japanese Heisei 33-04-28
        System.out.println(japaneseDate);

        Chronology chronology = Chronology.ofLocale(Locale.ENGLISH);
        ChronoLocalDate chronoLocalDate = chronology.dateNow();
        // 2021-04-28
        System.out.println(chronoLocalDate);
    }

    // 伊斯兰教日历
    @Test
    public void test8() {
        // 计算当前伊斯兰年中斋月的起始和终止日期
        // 取得当前的Hijrah日期，紧接着对其进行修正，得到斋月的第一天，即第9个月天，即第9个月
        LocalDate ld = LocalDate.of(2014, 1, 3);
        HijrahDate from = HijrahDate.from(ld);
        HijrahDate ramadanDate =
                // HijrahDate.now()
                from
                        .with(ChronoField.DAY_OF_MONTH, 1)
                        .with(ChronoField.MONTH_OF_YEAR, 9);
        // Ramadan starts on 2014-06-28 and ends on 2014-07-27
        // Ramadan starts on 2021-04-13 and ends on 2021-05-12
        System.out.println("Ramadan starts on "
                + IsoChronology.INSTANCE.date(ramadanDate)
                + " and ends on "
                + IsoChronology.INSTANCE.date(ramadanDate.with(TemporalAdjusters.lastDayOfMonth())));
    }

}
