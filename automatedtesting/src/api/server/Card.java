package api.server;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import api.util.Config;
import util.httpclient.HTTPClient;
import util.jiami.RSAUtils;
import util.mysql.entity.model.UserDevice;

public class Card {
	static Logger log=Logger.getLogger(Card.class);
	static String GL_CLIENT_VER=Config.getCfronURL("GL_CLIENT_VER");
	static String GL_TIMESTAMP = "1496726854";//时间戳
	static String GL_CLIENT_ID="glLive.app.ios";
	static String ContentType="application/json;charset=UTF-8";
	private static String url=Config.getCfronURL("cfronurl")+"/lifeAPI/payment/fft/card/?type=0";
	public static String getCard(String token,String cookie,UserDevice userdevice){
		String  GL_SALT_MD5_KEY =token+userdevice.getDevice_id() +GL_CLIENT_ID + GL_CLIENT_VER + GL_TIMESTAMP;
		  String sign=null;
	try {
		  try {
				sign = RSAUtils.sign(GL_SALT_MD5_KEY.getBytes());
				sign=URLEncoder.encode(sign, "utf-8");
			} catch (Exception e) {
				log.error("=====获取签名失败："+e.getStackTrace());
				
			}
		  Map<String, String> header=new HashMap<String,String>();
		  header.put("GL_CLIENT_ID",GL_CLIENT_ID);
		  header.put("GL_DEVICE_ID", userdevice.getDevice_id());
		  header.put("GL_CLIENT_VER", GL_CLIENT_VER);
		  header.put("GL_TIMESTAMP", GL_TIMESTAMP);
		  header.put("Content-Type", ContentType);
		  header.put("GL_TOKEN", token);
		  header.put("Cookie", cookie);
		  header.put("GL_REQ_SIGN",sign);
		 String result=HTTPClient.doget(url, header);	
		 return result;
		} catch (Exception e) {
			log.error(e.getStackTrace());
			return null;
		}
	}
}
