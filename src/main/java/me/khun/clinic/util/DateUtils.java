package me.khun.clinic.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

	public static LocalDate parseLocalDate(String date, String pattern) {
		if (date == null) {
			return null;
		}
		
		try {
			return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
		} catch (DateTimeParseException e) {
			return null;
		}
	}
}
