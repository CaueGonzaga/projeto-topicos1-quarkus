package dev.cauesouza.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormate {
    public static LocalDateTime convertStringToDate(String date) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDateTime dateTime = LocalDate.parse(date, parser).atStartOfDay();
        return dateTime;
    }

    public static String convertDateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dateString = dateTime.format(formatter);
        return dateString;
    }
}