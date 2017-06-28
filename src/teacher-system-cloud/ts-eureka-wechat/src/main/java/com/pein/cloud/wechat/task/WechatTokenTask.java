package com.pein.cloud.wechat.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pein.cloud.wechat.properties.ConfigProperties;
import com.pein.cloud.wechat.utils.WeixinConstants;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class WechatTokenTask {

	private static final Logger log = LoggerFactory.getLogger(WechatTokenTask.class);
	@Autowired
	private ConfigProperties configProperties;

	private final static long ONE_HOUR = 60 * 60 * 1500;

	@Scheduled(initialDelay=1,fixedDelay=ONE_HOUR) // 每1小时执行一次
	public void execute() throws Exception {
		log.info("刷新微信AppId、secret. appId = {}, secret = {}. ", configProperties.getAppId(), configProperties.getSecret());
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + configProperties.getAppId()
				+ "&secret=" + configProperties.getSecret());
		CloseableHttpResponse response = httpClient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject demoJson = JSON.parseObject(jsonStr);
		String token = demoJson.getString("access_token");
		log.info("LOGGER00060: 最新access_token : {}.", token);
		WeixinConstants.putToken("token", token);
	}
}
