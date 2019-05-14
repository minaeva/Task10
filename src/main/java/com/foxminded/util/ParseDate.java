package com.foxminded.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParseDate {

    public static LocalDate stringToLocalDate(String date) throws DateTimeParseException {
        if ((date == null) || (date.isEmpty())) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
