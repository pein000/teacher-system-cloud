package com.pein.cloud.wechat.settings;

/**
 * Created by qiuw on 2017/6/22.
 */

import com.alibaba.fastjson.JSON;
import com.pein.cloud.wechat.constants.WechatConstants;
import com.pein.cloud.wechat.utils.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巧巧公众号菜单设置
 * 调用微信自定义菜单创建接口
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
 * @author qiuwei
 * @create 2017-06-22 11:06
 **/
public class QiaoQiaoMenuSettings {

    private static final Logger log = LoggerFactory.getLogger(QiaoQiaoMenuSettings.class);

    public static void execute() {
        List<WechatMenu> menuList = new ArrayList<>();
        WechatMenu indexMenu = new WechatMenu();
        indexMenu.setId(0);
        indexMenu.setName("商城主页");
        indexMenu.setType("view");
        indexMenu.setUrl("http://139.196.176.138:8082/one-shop-website/index");
        menuList.add(indexMenu);

        WechatMenu introduceMenu = new WechatMenu();
        introduceMenu.setId(1);
        introduceMenu.setName("介绍");
        introduceMenu.setType("click");
        introduceMenu.setKey("introduce");
        menuList.add(introduceMenu);

        WechatMenu connectMeMenu = new WechatMenu();
        connectMeMenu.setId(2);
        connectMeMenu.setName("我");
        connectMeMenu.setType("click");
        connectMeMenu.setKey("connectMe");
        menuList.add(connectMeMenu);

        Map<String,List<WechatMenu>> maps = new HashMap<String,List<WechatMenu>>();
        maps.put("button",menuList);
        String json = JSON.toJSONString(maps);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(WechatConstants.MENU_SETTINGS_URL);
        post.addHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(json,
                ContentType.create("application/json", "utf-8"));
        post.setEntity(entity);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sc = resp.getStatusLine().getStatusCode();
        if(sc>=200&&sc<300) {
            try {
                log.info("LOGGER00070: 设置菜单项成功：{}。",EntityUtils.toString(resp.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String setClickMenu(InputMessage inputMessage) {
        String messageType = inputMessage.getMsgType();
        if ("event".equals(messageType)) {
            String eventKey = inputMessage.getEventKey();
            if ("introduce".equals(eventKey)) {
                InputMessage message = new InputMessage();
                message.setCreateTime(System.currentTimeMillis());
                message.setFromUserName(inputMessage.getFromUserName());
                message.setToUserName(inputMessage.getToUserName());
                message.setMsgType("text");
                message.setContent("欢迎来到巧巧玩具城！");
                return SerializeXmlUtil.textMessageToXml(message);
            }
            if ("connectMe".equals(eventKey)) {
                InputMessage message = new InputMessage();
                message.setCreateTime(System.currentTimeMillis());
                message.setFromUserName(inputMessage.getFromUserName());
                message.setToUserName(inputMessage.getToUserName());
                message.setMsgType("text");
                message.setContent("我们的联系方式：\n 18521563200");
                return SerializeXmlUtil.textMessageToXml(message);
            }
        }
        return "success";
    }

//    public static void main(String[] args) {
//        execute();
//    }

}
