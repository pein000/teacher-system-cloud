package com.pein.cloud.wechat.settings;

import com.alibaba.fastjson.JSON;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义菜单创建接口
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
 *
 * 1 需要进行微信认证
 * 2 url中替换access_token值为最新值
 *
 */
public class ExampleMenuSettings {

	public void testMenu() {
		try {
			String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
					+"P0l4L9g8Z9t_tyCnMRUUMnfxIoCMvCRM1eNXuLCc8xJWl5j8S_jndGldiH9MyQy9Yk1T39vx3JuRCMSIxFvBKx4gGfjHND0GsVz2X-hPANu9CIkSr6RMkXJbMmHPUNz4AOWfACATHP";
			List<WechatMenu> wms = new ArrayList<WechatMenu>();
			WechatMenu wm1 = new WechatMenu();
			WechatMenu wm2 = new WechatMenu();
			WechatMenu wm3 = new WechatMenu();
			WechatMenu wm4= new WechatMenu();

			wm2.setName("我要");
			List<WechatMenu> wm2Sub = new ArrayList<WechatMenu>();
			wm1 = new WechatMenu();
			wm1.setId(2);
			wm1.setName("注册");
			wm1.setType("view");
			wm1.setUrl("https://daikuan.2345.com/register?channel=1041");
			wm2Sub.add(wm1);
			wm1 = new WechatMenu();
			wm1.setId(3);
			wm1.setName("借款");
			wm1.setType("view");
			wm1.setUrl("https://daikuan.2345.com/lauchAPP?auto=true");
			wm2Sub.add(wm1);
			wm1 = new WechatMenu();
			wm1.setId(4);
			wm1.setName("还款");
			wm1.setType("view");
			wm1.setUrl("https://daikuan.2345.com/lauchAPP?auto=true");
			wm2Sub.add(wm1);
			wm1 = new WechatMenu();
			wm1.setId(5);
			wm1.setName("提额");
			wm1.setType("click");
			wm1.setKey("quota");
			wm2Sub.add(wm1);
			wm2.setSub_button(wm2Sub);
			wms.add(wm2);


			wm3.setName("查询");
			List<WechatMenu> wm3Sub = new ArrayList<WechatMenu>();
			wm1 = new WechatMenu();
			wm1.setId(2);
			wm1.setName("额度");
			wm1.setType("view");
			wm1.setUrl("https://daikuan.2345.com/lauchAPP?auto=true");
			wm3Sub.add(wm1);
			wm1 = new WechatMenu();
			wm1.setId(2);
			wm1.setName("明细");
			wm1.setType("view");
			wm1.setUrl("https://daikuan.2345.com/lauchAPP?auto=true");
			wm3Sub.add(wm1);
			wm3.setSub_button(wm3Sub);
			wms.add(wm3);


			wm4.setName("更多服务");
			List<WechatMenu> wm4Sub = new ArrayList<WechatMenu>();
			wm1 = new WechatMenu();
			wm1.setName("微客服");
			wm1.setType("click");
			wm1.setKey("customer_service");
			wm4Sub.add(wm1);


			wm1 = new WechatMenu();
			wm1.setName("活动福利");
			wm1.setType("click");
			wm1.setKey("activity_benefit");
			wm4Sub.add(wm1);

			wm1 = new WechatMenu();
			wm1.setName("下载app");
			wm1.setType("view");
			wm1.setUrl("http://m.daikuan.2345.com/download?channel=1041");
			wm4Sub.add(wm1);

			wm1 = new WechatMenu();
			wm1.setName("贷款流程");
			wm1.setType("click");
			wm1.setKey("loan_process");
			wm4Sub.add(wm1);

			wm1 = new WechatMenu();
			wm1.setName("商务合作");
			wm1.setType("click");
			wm1.setKey("business_cooperation");
			wm4Sub.add(wm1);

			wm4.setSub_button(wm4Sub);
			wms.add(wm4);


			Map<String,List<WechatMenu>> maps = new HashMap<String,List<WechatMenu>>();
			maps.put("button",wms);
			String json = JSON.toJSONString(maps);
			
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			post.addHeader("Content-Type","application/json");
			StringEntity entity = new StringEntity(json,
					ContentType.create("application/json", "utf-8"));
			post.setEntity(entity);
			CloseableHttpResponse resp = client.execute(post);
			int sc = resp.getStatusLine().getStatusCode();
			if(sc>=200&&sc<300) {
				System.out.println(EntityUtils.toString(resp.getEntity()));
			}
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
