package com.zzzyt.rs.util;

import com.zzzyt.rs.util.StringUtil.MyFormatter;

public class HtmlFormatter implements MyFormatter {

	// Thanks to StackOverflow for the idea!
	// https://stackoverflow.com/questions/15452994/what-is-the-alternative-for-string-format-in-gwt
	// http://developer.classpath.org/doc/java/util/Formatter-source.html
	// actually a lightweight implementation of java.util.Formatter
	@Override
	public String format(String fmt, Object... args) {
		final StringBuffer buffer = new StringBuffer();
		int cnt = 0;
		for (int i = 0; i < fmt.length(); i++) {
			if (fmt.charAt(i) != '%') {
				buffer.append(fmt.charAt(i));
			} else {
				char nxt = fmt.charAt(i + 1);
				if (nxt == 'd' || nxt == 's' || nxt == 'c') {
					buffer.append(args[cnt].toString());
					i++;
				} else if (nxt == '%') {
					buffer.append('%');
					i++;
				} else if (nxt == '.') {
					int idd = fmt.indexOf('f', i);
					int dig = Integer.parseInt(fmt.substring(i + 2, idd));
					try {
						double num = Double.parseDouble(args[cnt].toString());
						String tmp;
						if (dig == 0) {
							tmp = Integer.toString((int) Math.round(num));
						} else {
							tmp = Double.toString((Math.round(num * Math.pow(10, dig)) / Math.pow(10, dig)));
							int dot = tmp.lastIndexOf('.');
							if(dot==-1) {
								tmp+='.';
								dot=tmp.length()-1;
							}
							int les = tmp.length() - dot - 1;
							if (les < dig) {
								for (int j = 0; j < dig - les; j++) {
									tmp += '0';
								}
							}
						}

						buffer.append(tmp);
					} catch (Exception e) {
						e.printStackTrace();
					}
					i = idd;
				}
				cnt++;
			}
		}
		return buffer.toString();
	}

	public HtmlFormatter() {
		super();
	}
}
