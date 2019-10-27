package com.zzzyt.rs.util;

public class StringUtil {
	//thanks to StackOverflow!
	//https://stackoverflow.com/questions/15452994/what-is-the-alternative-for-string-format-in-gwt
	public static String format(final String format, final Object... args) {
	    final StringBuffer buffer= new StringBuffer();
	    int cnt=0;
	    for (int i= 0; i< format.length(); i++) {
	        if(format.charAt(i)!='%') {
	        	buffer.append(format.charAt(i));
	        }
	        else {
	        	char nxt=format.charAt(i+1);
	        	if(nxt=='d'||nxt=='s'||nxt=='c') {
	        		buffer.append(args[cnt].toString());
	        		i++;
	        	}
	        	else if(nxt=='%') {
	        		buffer.append('%');
	        		i++;
	        	}
	        	else if(nxt=='.') {
	        		int idd=format.indexOf('f', i);
	        		int dig=Integer.parseInt(format.substring(i+2,idd));
	        		try {
	        			double num=Double.parseDouble(args[cnt].toString());
	        			String tmp;
	        			if(dig==0) {
	        				tmp=Integer.toString((int)Math.round(num));
	        			}
	        			else {
	        				tmp=Double.toString((Math.round(num*Math.pow(10, dig))/Math.pow(10, dig)));
	        				int dot=tmp.lastIndexOf('.');
		        			if(dot!=-1) {
		        				int les=tmp.length()-dot-1;
		        				if(les<dig) {
		        					for(int j=0;j<dig-les;j++) {
		        						tmp+='0';
		        					}
		        				}
		        			}
	        			}
	        			
	        			buffer.append(tmp);
	        		}
	        		catch(Exception e) {
	        			e.printStackTrace();
	        		}
	        		i=idd;
	        	}
	        	cnt++;
	        }
	    }
	    return buffer.toString();
	 }
}
