package util.httpclient;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;
import util.jiami.MD5Util;

public class Test {
	static String ContentType="application/json;charset=UTF-8";
	@org.testng.annotations.Test
	public void test(){
		String url="http://test.rm-tech.com.cn/platform/cloudplatform/api/trade.html";
//		String sig="amount=0.04&amountSettle=&body=扫描订单&channel=wx_pub&currency=CNY&description=扫描订单&details=[{amount=0.02&innerOrderNo="
//				+ "2017090001&virAccNo=0}&{amount=0.02&innerOrderNo=2017090002&virAccNo=0}]&mchNo=100120000000011&"
//				+ "notifyUrl=http://test.com&openId=123456&mchOrderNo=2017090001&subject=&timeExpire=&timePaid=&timeSettle=&"
//				+ "tradeType=cs.pay.submit&version=2.0";
		String sig="amount=0.04&body=扫描订单&channel=wx_pub&currency=CNY&description=扫描订单&details=[{amount=0.02&innerOrderNo="
				+ "2017090001&virAccNo=0}&{amount=0.02&innerOrderNo=2017090002&virAccNo=0}]&extra={notifyUrl=http://test.com&openId=123456}&mchNo=100120000000011&"
				+ "mchOrderNo=2017090001&"
				+ "tradeType=cs.pay.submit&version=2.0";
		String sign=sig+"&key=91be991a7491481ab43a89657a780b69";
		System.out.println(sign);
		sign=MD5Util.toMd5(sign);
		System.out.println(sign);
		Map<String, String> header=new HashMap<String,String>();
		String body="{\"amount\": \"0.04\",\"body\":\"扫描订单\",\"channel\":\"wx_pub\",\"amountSettle\":\"\",\"timeExpire\":\"\",\"timeSettle\":\"\","
				+ "\"subJect\": \"\",\"currency\":\"CNY\",\"description\":\"扫描订单\",\"mchNo\":\"100120000000011\",\"mchOrderNo\":\"2017090001\","
				+ "\"timePaid\":\"\",\"tradeType\":\"cs.pay.submit\",\"version\":\"2.0\",\"details\":[{\"innerOrder\":\"2017090001\","
				+ "\"virAccNo\":\"0\",\"amount\":\"0.02\"},{\"innerOrder\":\"2017090002\",\"virAccNo\":\"0\",\"amount\":\"0.02\"}],"
				+ "\"extra\":{\"openId\":\"123456\",\"notifyUrl\":\"http://test.com\"},\"sign\":\""+sign+"\"}";
		System.out.println(body);
		String result=HTTPClient.dopost(url, header, body);
		System.out.println(result);
	}
//public static void main(String[] args) {
////	String url="http://test.rm-tech.com.cn/platform/cloudplatform/api/trade.html";
////	Map<String, String> header=new HashMap<String,String>();
////	String body="{\"tradeType\":\"cs.trade.check\",\"version\":\"2.0\",\"mchNo\":\"100120000000011\",\"checkDate\":\"2017-09-13\","
////			+ "\"sign\":\"FC920D0F67AF680C70525E2C833FEC59\"}";
////	String result=HTTPClient.dopost(url, header, body);
////	System.out.println(result);
//	
//	String url="http://test.rm-tech.com.cn/platform/cloudplatform/api/trade.html";
//	Map<String, String> header=new HashMap<String,String>();
//	String body="{\"tradeType\":\"cs.refund.submit\",\"version\":\"2.0\",\"mchNo\":\"100120000000011\",\"mchOrderNo\":\"123\","
//			+ "\"mchRefundNo\":\"123\",\"refundAmt\":0.05,\"sign\":\"FC920D0F67AF680C70525E2C833FEC59\"}";
//	String result=HTTPClient.dopost(url, header, body);
//	System.out.println(result);
//	
////	
////	String url="http://test.rm-tech.com.cn/platform/jspay.html?token_id=123456";
////	Map<String, String> header=new HashMap<String,String>();
////	String result=HTTPClient.doget(url, header);
////	System.out.println(result);
////	
//	
//}	
	
	 public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower)  
	   {  
	       String buff = "";  
	       Map<String, String> tmpMap = paraMap;  
	       try  
	       {  
	           List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());  
	           // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）  
	           Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()  
	           {  
	  
	               @Override  
	               public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)  
	               {  
	                   return (o1.getKey()).toString().compareTo(o2.getKey());  
	               }  
	           });  
	           // 构造URL 键值对的格式  
	           StringBuilder buf = new StringBuilder();  
	           for (Map.Entry<String, String> item : infoIds)  
	           {  
	               if (StringUtils.isNotBlank(item.getKey()))  
	               {  
	                   String key = item.getKey();  
	                   String val = item.getValue();  
	                   if (urlEncode)  
	                   {  
	                       val = URLEncoder.encode(val, "utf-8");  
	                   }  
	                   if (keyToLower)  
	                   {  
	                       buf.append(key.toLowerCase() + "=" + val);  
	                   } else  
	                   {  
	                       buf.append(key + "=" + val);  
	                   }  
	                   buf.append("&");  
	               }  
	  
	           }  
	           buff = buf.toString();  
	           if (buff.isEmpty() == false)  
	           {  
	               buff = buff.substring(0, buff.length() - 1);  
	           }  
	       } catch (Exception e)  
	       {  
	          return null;  
	       }  
	       return buff;  
	   }  
	
	
	public static void main(String[] args) {
		String url="http://test.rm-tech.com.cn/platform/cloudplatform/api/trade.html";
		Map<String, String> header=new HashMap<String,String>();
//	//	String sign="beginTime=2017-09-12 09:08:05&endTime=2017-09-12 15:12:13&mchNo=100120000000011&mchOrderNo=55&pageNum=1&tradeType=cs.trade.query&version=2.0&key=91be991a7491481ab43a89657a780b69";
//		Map<String, String> paraMap=new HashMap<String,String>();
//		paraMap.put("tradeType", "cs.trade.query");paraMap.put("version","2.0");paraMap.put("mchNo", "100120000000011");
//	//	paraMap.put("mchOrderNo", "55");
//		paraMap.put("beginTime", "2017-09-10 09:08:05");paraMap.put("endTime", "2017-09-12 15:12:13");
//		paraMap.put("pageNum", "1");
//		String sign=formatUrlMap(paraMap,false,false);
//		sign=sign+"&key=91be991a7491481ab43a89657a780b69";
//		System.out.println(sign);
//		sign=MD5Util.toMd5(sign);
//		System.out.println(sign);
//		JSONObject jo=new JSONObject();
//		jo.element("tradeType", "cs.trade.query");jo.element("version", "2.0");jo.element("mchNo", "100120000000011");
//	//	jo.element("mchOrderNo", "55");
//		jo.element("beginTime", "2017-09-10 09:08:05");jo.element("endTime", "2017-09-12 15:12:13");
//		jo.element("pageNum", "1"); jo.element("sign", sign);
//		String body=jo.toString();
//		String result=HTTPClient.dopost(url, header, body);
//		System.out.println(result);
		
		
		
		
		
		
		
//		Map<String, String> paraMap=new HashMap<String,String>();
//		paraMap.put("tradeType", "cs.refund.submit");paraMap.put("version","2.0");paraMap.put("mchNo", "100120000000011");
//		paraMap.put("cpOrderNo", "10012000000001117091114155180226");
//		paraMap.put("mchRefundNo", "555");paraMap.put("refundAmt", "0.02");
//		String sign=formatUrlMap(paraMap,false,false);
//		sign=sign+"&key=91be991a7491481ab43a89657a780b69";
//		System.out.println(sign);
//		sign=MD5Util.toMd5(sign);
//		System.out.println(sign);
//		JSONObject jo=new JSONObject();
//		jo.element("tradeType", "cs.refund.submit");jo.element("version", "2.0");jo.element("mchNo", "100120000000011");
//		jo.element("cpOrderNo", "10012000000001117091114155180226");
//		jo.element("mchRefundNo", "555");jo.element("refundAmt", "0.02");
//		jo.element("sign", sign);
//		String body=jo.toString();
//		String result=HTTPClient.dopost(url, header, body);
//		System.out.println(result);
		
		
		
		
		Map<String, String> paraMap=new HashMap<String,String>();
		paraMap.put("tradeType", "cs.refund.query");paraMap.put("version","2.0");paraMap.put("mchNo", "100120000000011");
		paraMap.put("cpOrderNo", "10012000000001117091114155180226");
		paraMap.put("mchRefundNo", "555");
		String sign=formatUrlMap(paraMap,false,false);
		sign=sign+"&key=91be991a7491481ab43a89657a780b69";
		System.out.println(sign);
		sign=MD5Util.toMd5(sign);
		System.out.println(sign);
		JSONObject jo=new JSONObject();
		jo.element("tradeType", "cs.refund.query");jo.element("version", "2.0");jo.element("mchNo", "100120000000011");
		jo.element("cpOrderNo", "10012000000001117091114155180226");
		jo.element("mchRefundNo", "555");
		jo.element("sign", sign);
		String body=jo.toString();
		String result=HTTPClient.dopost(url, header, body);
		System.out.println(result);
		
		
	}

}
