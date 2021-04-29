package com.xtransformers.chapter12;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.TimeZone;

public class FormatDemo2 {

    @Test
    public void test1() {
        LocalDate date = LocalDate.now();
        String dateStr1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String dateStr2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        // 20210426
        System.out.println(dateStr1);
        // 2021-04-26
        System.out.println(dateStr2);

        LocalDate date1 = LocalDate.parse("20210426", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2021-04-26", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date1);
        System.out.println(date2);
    }

    @Test
    public void test2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.now();
        // 26/04/2021
        System.out.println(date.format(formatter));
        // 2021-04-26
        System.out.println(LocalDate.parse("26/04/2021", formatter));
    }

    @Test
    public void test3() {
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        // 26. aprile 2021
        System.out.println(LocalDate.now().format(italianFormatter));

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        // 26. aprile 2021
        System.out.println(LocalDate.now().format(formatter));
    }

}
