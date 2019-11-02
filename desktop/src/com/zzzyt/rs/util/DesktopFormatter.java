package com.zzzyt.rs.util;

import java.util.Locale;

public class DesktopFormatter implements StringUtil.MyFormatter{
	
	@Override
	public String format(String fmt, Object... args) {
		return String.format(Locale.getDefault(), fmt, args);
	}
	
	public DesktopFormatter() {
		super();
	}
	
}
