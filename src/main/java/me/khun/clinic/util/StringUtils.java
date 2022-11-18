package me.khun.clinic.util;

public class StringUtils {

	public static boolean isNullOrBlank(String s) {
		return s == null || s.isBlank();
	}
	
	public static boolean notNullOrBlank(String s) {
		return !isNullOrBlank(s);
	}
	
	public static boolean isEmail(String str) {
		return true;
	}
	
	public static boolean isPhone(String str) {
		return true;
	}
	
	public static boolean containsIgnoreCase(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return false;
		}
		
		return str1.toLowerCase().contains(str2.toLowerCase());
	}
	
	public static Long parseLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
