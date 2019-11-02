package com.zzzyt.rs.util;

public class StringUtil {
	public static MyFormatter formatter;
	
	public static String format(String fmt, Object... args) {
		if(formatter==null) {
			throw new RuntimeException("Internal formatter not assigned!");
		}
		return formatter.format(fmt, args);
	}
	
	public interface MyFormatter{
		public String format(String fmt, Object... args);
	}
}
