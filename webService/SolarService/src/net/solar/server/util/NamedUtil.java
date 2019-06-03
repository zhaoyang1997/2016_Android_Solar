package net.solar.server.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NamedUtil {
	
	public static String name(){
	
		Format format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String date = format.format(new Date());
		System.out.println(date+"0"+"solar");
		return date+"0"+"solar";
	}
	

}
