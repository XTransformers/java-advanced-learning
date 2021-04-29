package com.xtransformers.chapter12;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateDemo {

    // Java 1.0
    @Test
    public void test1() {
        Date date = new Date(114, 2, 18);
        // Central Europe Time/CET
        // Tue Mar 18 00:00:00 CST 2014
        System.out.println(date);
    }

    @Test
    public void test2() {
        LocalDate date = LocalDate.of(2021, 4, 25);
        date = LocalDate.now();
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leapYear = date.isLeapYear();
        /**
         * 2021
         * APRIL
         * 25
         * SUNDAY
         * 30
         * false
         */
    }

    @Test
    public void test3() {
        LocalDate date = LocalDate.now();
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_YEAR);
    }

    @Test
    public void test4() {
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
        System.out.println(time.getNano());
        /**
         * 7
         * 55
         * 28
         * 305000000
         */
    }

    public void test5() {
        LocalDate date = LocalDate.parse("2021-04-25");
        LocalTime time = LocalTime.parse("07:57:15");

        LocalDateTime ldt = LocalDateTime.of(2021, Month.APRIL, 25, 8, 01, 02);
        ldt = LocalDateTime.of(date, time);
        ldt = date.atTime(time);
        ldt = date.atTime(8, 2, 37);
        ldt = time.atDate(date);

        LocalDate localDate = ldt.toLocalDate();
        LocalTime localTime = ldt.toLocalTime();

    }

    @Test
    public void test6() {
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000);
        Instant.ofEpochSecond(4, -1_000_000_000);
        // 1 s = 1000 ms
        // 1 ms = 1_000_000 ns

        // 2021-04-25T00:11:26.126Z
        Instant now = Instant.now();
        System.out.println(now);

        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfYear
        System.out.println(now.get(ChronoField.DAY_OF_YEAR));
    }

    @Test
    public void test7() {
        LocalTime time1 = LocalTime.parse("06:00:00");
        LocalTime time2 = LocalTime.now();
        Duration duration1 = Duration.between(time1, time2);
        System.out.println(duration1);

        LocalDate date1 = LocalDate.of(2021, 4, 25);
//        LocalDate date2 = LocalDate.now();
        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit: Seconds
        // A Duration measures an amount of time using time-based values (seconds, nanoseconds).
        // A Period uses date-based values (years, months, days).
//        Duration duration2 = Duration.between(date2, date1);
//        System.out.println(duration2);

        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        LocalDateTime dateTime2 = LocalDateTime.now();
        Duration duration2 = Duration.between(dateTime1, dateTime2);
        System.out.println(duration2);

        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(5);
        Duration duration3 = Duration.between(instant1, instant2);
        System.out.println(duration3);
        /**
         * PT1H27M59.315S
         * PT25H27M59.321S
         * PT2S
         */
        // P1D
        Period period = Period.between(LocalDate.of(2021, 4, 25),
                LocalDate.now());
        System.out.println(period);
    }

    @Test
    public void test8() {
        Duration threeMinutes = Duration.ofMinutes(3);
        threeMinutes = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }
}
