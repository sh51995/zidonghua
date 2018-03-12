package ui_app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
	public static String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		return df.format(new Date()).toString();// new Date()为获取当前系统时间
	}
	
	public static void main(String[] args) {
		System.out.println(getTime());
	}
}
