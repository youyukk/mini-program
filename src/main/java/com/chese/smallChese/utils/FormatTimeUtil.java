package com.chese.smallChese.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTimeUtil {
	
	public static String getTimestamp(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = format.format(new Date());
		
		return timestamp;
	}

}
