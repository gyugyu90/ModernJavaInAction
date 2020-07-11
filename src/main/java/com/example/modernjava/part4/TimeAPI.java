package com.example.modernjava.part4;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class TimeAPI {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.of(2020, 7, 4);
        int year = localDate.getYear();           // 2020
        Month month = localDate.getMonth();       // JULY
        int day = localDate.getDayOfMonth();      // 4
        DayOfWeek dow = localDate.getDayOfWeek(); // SATURDAY
        int len = localDate.lengthOfMonth();      // 31 (7월의 일 수)
        boolean leap = localDate.isLeapYear();    // false 윤년 여부

        int yearChronoField = localDate.get(ChronoField.YEAR);
        int monthChronoField = localDate.get(ChronoField.MONTH_OF_YEAR);
        int dayChronoField = localDate.get(ChronoField.DAY_OF_MONTH);


        LocalTime localTime = LocalTime.of(13, 45, 20); // 13:45:20
        int hour = localTime.getHour();     // 13
        int minute = localTime.getMinute(); // 45
        int second = localTime.getSecond(); // 20

        LocalDateTime dt1 = LocalDateTime.of(2020, Month.JULY, 4, 21, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(localDate, localTime);
        LocalDateTime dt3 = localDate.atTime(13, 45, 20);
        LocalDateTime dt4 = localDate.atTime(localTime);
        LocalDateTime dt5 = localTime.atDate(localDate);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMins = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

        // formatting();
        // parsing();
        makeFormatter();
    }

    public void changeLocalDate() {
        LocalDate date1 = LocalDate.of(2020, 7, 4); // 2020-07-04
        LocalDate date2 = date1.withYear(2011); // 2011-07-04
        LocalDate date3 = date2.withDayOfMonth(25); // 2011-07-25
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2); // 2011-02-25
    }

    public void changeLocalDateRelatively() {
        LocalDate date1 = LocalDate.of(2020, 7, 4); // 2020-07-04
        LocalDate date2 = date1.plusWeeks(1); // 2020-07-11
        LocalDate date3 = date2.minusYears(6); // 2014-07-11
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS); // 2015-01-11
    }

    public static void formatting() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE); // 20140318
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2014-03-18
        System.out.println(s1);
        System.out.println(s2);
    }

    public static void parsing() {
        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date1);
        System.out.println(date2);
    }

    public static void makeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        String formattedDate = date1.format(formatter);
        LocalDate date2 = LocalDate.parse(formattedDate, formatter);
        System.out.println(formattedDate);
    }

    public static void useTimeZone() {

        ZoneId romeZone = ZoneId.of("Europe/Rome");
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);

    }


}
