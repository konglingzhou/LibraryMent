package com.cqeec.util;

import java.text.DecimalFormat;
import java.util.Calendar;

public class ValidateUtil {

	
	public static String StringTodouble(String number) {
		Double d = new Double(-1.00);
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			d = new Double(number);
			return df.format(d);
		} catch (Exception e) {
			return new String("err");
		}
	}
	
	
	// date 格式严格按照yyyy-mm-dd，例如2018-12-15
	public static boolean StringToDate(String date) {
		boolean ret = true;
		try {
			int year = new Integer(date.substring(0, 4)).intValue();
			int month = new Integer(date.substring(5, 7)).intValue();
			int day = new Integer(date.substring(8)).intValue();
			Calendar cal = Calendar.getInstance();
			cal.setLenient(false); // 允许严格检查日期格式
			cal.set(year, month - 1, day);
			cal.getTime();
		} catch (Exception e) {
			ret = false;
		}
		return ret;
	}

	
    public static boolean StringToInt(String number) {
    	try {
    		Integer.valueOf(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
    }
}
