package com.pein.cloud.wechat.controller;


import com.pein.cloud.wechat.parse.WechatParser;
import com.pein.cloud.wechat.properties.ConfigProperties;
import com.pein.cloud.wechat.settings.QiaoQiaoMenuSettings;
import com.pein.cloud.wechat.utils.InputMessage;
import com.pein.cloud.wechat.utils.SerializeXmlUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
public class WechatApiController {

    private static final Logger logger = LoggerFactory.getLogger(WechatApiController.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ConfigProperties configProperties;


    @ResponseBody
    @RequestMapping(value = "/signature", method = RequestMethod.GET)
    public String signature(HttpServletRequest request) throws IOException {
        logger.info("to signature weixin token. ");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        List<String> list = new LinkedList<>();
        list.add(configProperties.getToken());
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        String messge = DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2));
        logger.info("end to signature weixin token. message = {}, echostr = {}. ", messge, echostr);
        return messge.equals(signature) ? echostr : null;
    }

    @ResponseBody
    @RequestMapping(value = "/signature", method = RequestMethod.POST)
    public String signature(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return acceptMessage(request, response);
    }

    private String acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("LOG00150: user begin to send message.");
        InputMessage inputMsg = WechatParser.toMessage(request);
        String serverName = inputMsg.getToUserName();// 服务端
        String customerName = inputMsg.getFromUserName();// 客户端
        long createTime = inputMsg.getCreateTime() * 1000;// 接收时间 微信时间为秒，兼容本系统时间毫秒
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
        // 取得消息类型
        String msgType = inputMsg.getMsgType();
        logger.info("LOG00160: user to send message. name = {}, content = {}, msgType = {}. ", customerName, inputMsg.getContent(), msgType);
        // 根据消息类型获取对应的消息内容
        if (msgType.equals("text")) {
            taskExecutor.execute(() -> {
                try {
                    logger.info("LOG00170: background thread begin to execute send message");

                    logger.info("LOG00180: background thread end to send message");
                } catch (Exception e) {
                    logger.error("LOG00220: background thread execute error {}", ExceptionUtils.getFullStackTrace(e));
                }
            });
        } else if (msgType.equals("image")) {
            taskExecutor.execute(() -> {

                logger.info("LOG00180: background thread end to send message");
            });
        } else {
            if (msgType.equals("event")) {
                if (inputMsg.getEvent().equals("subscribe")) {
                    InputMessage msg = new InputMessage();
                    msg.setCreateTime(System.currentTimeMillis());
                    msg.setFromUserName(serverName);
                    msg.setToUserName(customerName);
                    msg.setMsgType("text");
                    msg.setContent("[耶]Hi~我是巧巧玩具城主！\n欢迎新伙伴加入");
                    return SerializeXmlUtil.textMessageToXml(msg);
                }
            return QiaoQiaoMenuSettings.setClickMenu(inputMsg);
            }
        }
        return "success";
    }

}
