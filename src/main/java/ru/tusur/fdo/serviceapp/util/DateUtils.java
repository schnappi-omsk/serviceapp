package ru.tusur.fdo.serviceapp.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.util
 * by Oleg Alekseev
 * 24.05.14.
 */
public class DateUtils {

    public static Date fromLocalDate(LocalDate source) {
        Instant instant = source.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    public static LocalDate toLocalDate(Date source) {
        Instant instant = Instant.ofEpochMilli(source.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    public static String stringFromLocalDate(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static LocalDate localFromSqlDate(java.sql.Date date){
        Date mediator = new Date(date.getTime());
        return toLocalDate(mediator);
    }

    public static java.sql.Date sqlDateFromLocal(LocalDate source){
        return new java.sql.Date(fromLocalDate(source).getTime());
    }

    public static LocalDate localFromString(String source) {
        return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}
