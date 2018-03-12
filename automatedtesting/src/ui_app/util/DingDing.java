package ui_app.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import util.httpclient.HTTPClient;

public class DingDing {
	public static void send(String send){
		Map<String, String> header=new HashMap<String,String>();
		  header.put("Content-Type", "application/json;charset=UTF-8");
		  JSONObject jb=new JSONObject();
		  jb.elementOpt("msgtype", "text");
		  JSONObject text=new JSONObject();
		  text.element("content", send);
		  jb.element("text", text);
		  String body=jb.toString();
		  System.out.println(body);
		  HTTPClient.dopost("https://oapi.dingtalk.com/robot/send?access_token=044f2152f97f00db9da29be77c598ec829a47bcb7bc2696a5df42730d2bd1f2e", header, body);
	}
	
	
	public static void sendMarkDown(String file){
		System.out.println(file);
		Map<String, String> header=new HashMap<String,String>();
		  header.put("Content-Type", "application/json;charset=UTF-8");
		  JSONObject jb=new JSONObject();
		  jb.elementOpt("msgtype", "markdown");
		  JSONObject text=new JSONObject();
		  text.element("title", "屏幕截图");
		  text.element("text", "![]("+file+")");
		  System.out.println(text.toString());
		  jb.element("markdown", text);
		  String body=jb.toString();
		  System.out.println(body);
		  HTTPClient.dopost("https://oapi.dingtalk.com/robot/send?access_token=044f2152f97f00db9da29be77c598ec829a47bcb7bc2696a5df42730d2bd1f2e", header, body);
	}
	 public static void main(String[] args) throws MalformedURLException {
//		 File file=new File("E:/workspace-sts-3.8.3.RELEASE/automatedtesting/屏幕截图1515139397720.jpg");
//		 URL imgUrl = new URL("file:///" + file.getPath());
//		 System.out.println(imgUrl);
//		 sendMarkDown(imgUrl.toString());
	}
}
