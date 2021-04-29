package com.xtransformers.chapter12;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;

public class FormatDemo {

    // 直观的方式修改
    @Test
    public void test1() {
        // 2021-04-26
        LocalDate date1 = LocalDate.of(2021, 4, 26);
        // 2011-04-26
        LocalDate date2 = date1.withYear(2011);
        // 2021-04-25
        LocalDate date3 = date1.withDayOfMonth(25);
        // 2021-09-26
        LocalDate date4 = date1.with(ChronoField.MONTH_OF_YEAR, 9);
    }

    // 相对的方式修改
    @Test
    public void test2() {
        // 2021-04-26
        LocalDate date1 = LocalDate.of(2021, 4, 26);
        // 2021-05-03
        LocalDate date2 = date1.plusWeeks(1);
        // 2018-05-03
        LocalDate date3 = date2.minusYears(3);
        // 2018-11-03
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);

        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
    }

    @Test
    public void test3() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        // 会创建一个新的 LocalDate 实例，不过我们并没有将这个新创建的值赋给任何的变量
        date.withYear(2011);
        // 2016-09-08
        System.out.println(date);
    }

    @Test
    public void test4() {
        LocalDate date = LocalDate.of(2021, 4, 26);
        // 2021-05-01
        LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        // 2021-04-30
        LocalDate date2 = date.with(TemporalAdjusters.lastDayOfMonth());

        System.out.println(date1);
        System.out.println(date2);
    }

    // 下一个工作日
    public class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

    @Test
    public void test5() {
        LocalDate date = LocalDate.now();
        // 2021-04-26
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        // 2021-04-27
        System.out.println(date);
    }

    @Test
    public void test6() {
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek dayOfWeek = temporal.getDayOfWeek();
            int dayToAdd = 1;
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        LocalDate date = LocalDate.of(2021, 4, 30);
        date = date.with(nextWorkingDay);
        // 2021-05-03
        System.out.println(date);
    }
}

