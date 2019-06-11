package net.solar.server.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NamedUtil {
	
	public static String name(){
	
		Format format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String date = format.format(new Date());
		System.out.println("s"+date);
		return "s"+date;

	}
	

}
