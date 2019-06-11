package net.solar.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	
	public static int[] getDate(){
		String[] strNow = new SimpleDateFormat("yyyy-MM-dd").
				format(new Date()).toString().split("-");
		Integer year = Integer.parseInt(strNow[0]);
		Integer month = Integer.parseInt(strNow[1]);
		Integer day = Integer.parseInt(strNow[2]);
		
		int[] date = {year,month,day};
		return date;
	}


}
