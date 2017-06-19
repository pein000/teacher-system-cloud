package com.pein.cloud.wechat.utils;

import com.pein.cloud.wechat.task.WechatTokenTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WeixinConstants {
	public static String send_customer_msg_url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

	public static String get_user_info_url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	public static ConcurrentHashMap<String,String> map=new ConcurrentHashMap<String,String>();

	public static void putToken(String key,String value){
		map.put(key, value);
	}

	@Autowired
	public WechatTokenTask wechatTokenTask;

	public static WechatTokenTask staticWechatTokenTask;

	@PostConstruct
	public void init() {
		 staticWechatTokenTask = wechatTokenTask;
	}
	public static String getToken(String key){
		if(map.isEmpty()){
			try {
				staticWechatTokenTask.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map.get(key);
	}

}
