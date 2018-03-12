package util.httpclient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HTTPClient {
	
	static Logger log=Logger.getLogger(HTTPClient.class);
	/*
	 * @type： 请求类型(暂定post,get)
	 * @url：请求地址
	 * @header:请求头信息
	 * @body： 请求体
	 * 发送请求，获取返回httpresponse
	 */
	public static HttpResponse httpResponse(String type,String url,Map<String, String> header,String body){
		
		log.info("=====进入httpResponse");
		log.info("=====参数打印，type： "+type+"==url: "+url+"==header:  "+header.toString()+"==body:  "+body);
		//定义响应体HttpResponse
		HttpResponse httpresponse=null;
		
		HttpClient httpclient= new DefaultHttpClient();
		
		//判断请求类型post
		if (("post").equals(type)){
			//定义post请求
			HttpPost httppost=new HttpPost(url);
			//判断参数请求头是否为空，非空就解析
			log.info("=====判断头信息是否为空："+header.isEmpty());
			if(!header.isEmpty()){
				log.info("======添加请求头信息");
				for (String key : header.keySet()) {
				//逐项添加请求头
				log.info(key+":"+header.get(key));
				httppost.addHeader(key, header.get(key));
				}
			}
			System.out.println("======判断实体长度"+body.length());
			//根据请求体参数body长度判断是否为空
			if(body.length() > 0){
				HttpEntity entity=new StringEntity(body, "UTF-8");
				log.info(body);
				//塞参数如请求体
				httppost.setEntity(entity);
			}
		
			try {
				//发送请求获取响应
				 httpresponse=httpclient.execute(httppost);
			} catch (Exception e) {
				log.error(e);
			}
		}
		//当类型为get请求
		else if("get".equals(type)){
			HttpGet httpget=new HttpGet(url);
			log.info("=====判断头信息是否为空："+header.isEmpty());
			if(!header.isEmpty()){
				log.info("======添加请求头信息");
				for (String key : header.keySet()) {
				httpget.addHeader(key, header.get(key));
				}
			}
			
			try {
				 httpresponse=httpclient.execute(httpget);
			} catch (Exception e) {
				log.error(e);
			}
		}
		System.out.println(httpresponse.getStatusLine());
		return httpresponse;
	}
	
	
	
	/**
	 * 解析返回，获取响应码
	 * @param type
	 * @param url
	 * @param header
	 * @param body
	 * @return
	 */
	public static int getCode(String type,String url,Map<String, String> header,String body){
		int result=0;
		try {
			result=httpResponse(type,url, header,body).getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据参数集合getHeader，获取各项响应头
	 * @param type
	 * @param url
	 * @param header
	 * @param body
	 * @param getHeader
	 * @return
	 */
	public static Map<String,String> getHeader(String type,String url,Map<String, String> header,String body,List<String> getHeader){
		Map<String,String> map=new HashMap<String,String>();
		try {
			HttpResponse httpresponse=httpResponse(type,url,header,body);
			log.info("=====响应码："+httpresponse.getStatusLine());
			if(getHeader.size()>0){
				for(int i=0;i<getHeader.size();i++){
					map.put(getHeader.get(i), httpresponse.getFirstHeader(getHeader.get(i)).getValue());
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return map;
	}
	/**
	 * post请求获取响应头
	 * @param url
	 * @param header
	 * @param body
	 * @param getHeader
	 * @return
	 */
	public static Map<String, String> getPostHeader(String url,Map<String, String> header,String body,List<String> getHeader){
		Map<String,String> map=new HashMap<String,String>();
		try {
			HttpResponse httpresponse=httpResponse("post",url,header,body);
			log.info("=====响应码："+httpresponse.getStatusLine());
			if(getHeader.size()>0){
				for(int i=0;i<getHeader.size();i++){
					map.put(getHeader.get(i), httpresponse.getFirstHeader(getHeader.get(i)).getValue());
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return map;
		
	}
	/**
	 * get请求获取响应头
	 * @param url
	 * @param header
	 * @param body
	 * @param getHeader
	 * @return
	 */
	public static Map<String, String> getGetHeader(String url,Map<String, String> header,String body,List<String> getHeader){
		Map<String,String> map=new HashMap<String,String>();
		try {
			HttpResponse httpresponse=httpResponse("get",url,header,body);
			log.info("=====响应码："+httpresponse.getStatusLine());
			if(getHeader.size()>0){
				for(int i=0;i<getHeader.size();i++){
					map.put(getHeader.get(i), httpresponse.getFirstHeader(getHeader.get(i)).getValue());
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return map;
		
	}
	
	/**
	 * 请求
	 * @param url
	 * @param header
	 * @param body
	 * @return
	 */
	public static String dopost(String url,Map<String, String> header,String body){
		String result=null;
		try {
			HttpResponse httpresponse=httpResponse("post", url, header, body);
			if(httpresponse.getStatusLine().getReasonPhrase().equals("OK")){
				log.info("=====响应码为："+httpresponse.getStatusLine());	
				HttpEntity entity=httpresponse.getEntity();
				String stringentity=EntityUtils.toString(entity);
				log.info("=====转换后的实体："+stringentity);
				result=stringentity;
			}else{
				log.error("=====响应码为："+httpresponse.getStatusLine());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	
	public static Map<String, String> post(String url,Map<String, String> header,String body,List<String> getHeader){
		log.info("=====进入post");
		Map<String,String> map=new HashMap<String,String>();
		try {
			
			HttpResponse httpresponse=httpResponse("post",url,header,body);
			if(httpresponse.getStatusLine().getReasonPhrase().equals("OK")){
			log.info("=====响应码为："+httpresponse.getStatusLine());	
			HttpEntity entity=httpresponse.getEntity();
			String stringentity=EntityUtils.toString(entity);
			log.info("=====转换后的实体："+stringentity);
			
			map.put("body", stringentity);
			if(getHeader.size()>0){
				for(int i=0;i<getHeader.size();i++){
					map.put(getHeader.get(i), httpresponse.getFirstHeader(getHeader.get(i)).getValue());
				}
			}
			}else{
			log.error("=====响应码："+httpresponse.getStatusLine());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return map;
		
	}
	
	public static String doget(String url,Map<String, String> header){
		String result=null;
		try {
			HttpResponse httpresponse=httpResponse("get", url, header, null);
			if(httpresponse.getStatusLine().getReasonPhrase().equals("OK")){
				log.info("=====响应码为："+httpresponse.getStatusLine());	
				HttpEntity entity=httpresponse.getEntity();
				String stringentity=EntityUtils.toString(entity);
				log.info("=====转换后的实体："+stringentity);
				result=stringentity;
			}else{
				log.error("=====响应码为："+httpresponse.getStatusLine());
			}
			
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}
	public static void main(String[] args) {
		
	  try {
	
			Map<String, String> map= new HashMap<>();
			System.out.println(httpResponse("get", "http://baidu.com/", map, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}

