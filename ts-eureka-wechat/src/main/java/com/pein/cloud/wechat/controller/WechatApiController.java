package com.pein.cloud.wechat.controller;


import com.pein.cloud.wechat.properties.ConfigProperties;
import com.pein.cloud.wechat.utils.*;
import com.thoughtworks.xstream.XStream;
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

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class WechatApiController {

    private static final Logger logger = LoggerFactory.getLogger(WechatApiController.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ConfigProperties configProperties;


    @ResponseBody
    @RequestMapping(value = "/weixinapi/signature", method = RequestMethod.GET)
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
    @RequestMapping(value = "/weixinapi/signature", method = RequestMethod.POST)
    public String signaturep(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return acceptMessage(request, response);
    }

    private String acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("LOG00150: user begin to send message.");
        ServletInputStream in = request.getInputStream();
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.processAnnotations(OutputMessage.class);
        xs.alias("xml", InputMessage.class);
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());
        String servername = inputMsg.getToUserName();// 服务端
        String custermname = inputMsg.getFromUserName();// 客户端
        long createTime = inputMsg.getCreateTime() * 1000;// 接收时间 微信时间为秒，兼容本系统时间毫秒
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
        // 取得消息类型
        String msgType = inputMsg.getMsgType();
        logger.info("LOG00160: user to send message. name = {}, content = {}, msgType = {}. ", custermname, inputMsg.getContent(), msgType);
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
            Map map = new HashMap();
            map.put("video", "客户发送的是一段视频");
            map.put("voice", "客户发送的是一段音频");
            map.put("location", "客户发送的是位置信息");
            if (msgType.equals("event")) {
                if (inputMsg.getEvent().equals("subscribe")) {
                    InputMessage msg = new InputMessage();
                    msg.setCreateTime(System.currentTimeMillis());
                    msg.setFromUserName(servername);
                    msg.setToUserName(custermname);
                    msg.setMsgType("text");
                    msg.setContent("[耶]Hi~我是发财妹，欢迎关注2345贷款王！\n" +
                            "/:coffee贷款咨询：回复【客服】，即刻开始咨询！");
                    return SerializeXmlUtil.textMessageToXml(msg);
                }
                if (inputMsg.getEventKey().equals("loan")) {
                    ImageMessage imageMessage = new ImageMessage();
                    imageMessage.setMsgType("image");
                    imageMessage.setFromUserName(servername);
                    imageMessage.setToUserName(custermname);
                    imageMessage.setCreateTime(System.currentTimeMillis());
                    Image image = new Image();
                    image.setMediaId("9itazfs86vVMnx4rjKckKcs9_dmer9tfxK-4Eye8sJzfKHypU5x5Pc0RtfIgUH23");
                    imageMessage.setImage(image);
                    return MessageUtil.imageMessageToXml(imageMessage);
                } else if (inputMsg.getEventKey().equals("quota")) {

                    String content = "2345贷款王每月都会对优质客户进行提额，按时还款，借用次数越多，提额机会越大！\n" +
                            "现邀请好友可抽奖提额，500元、1000元提额奖励任你拿！\n" +
                            "邀请方式：登录APP>>点击下方“免息”>>点击“立即邀请”>>选择分享方式>>点击“分享”\n" +
                            "点击http://t.cn/RVRjgdX立即邀请好>>";
                    inputMsg.setContent(content);
                    inputMsg.setFromUserName(servername);
                    inputMsg.setToUserName(custermname);
                    inputMsg.setMsgType("text");
                    return SerializeXmlUtil.textMessageToXml(inputMsg);
                } else if (inputMsg.getEventKey().equals("customer_service")) {

                    String content = "欢迎使用2345贷款王！微信人工客服工作时间周一到周日9:00-20:00。\n" +
                            "由于咨询量很大，无法及时给您答复，请回复以下数字了解相关问题： \n" +
                            "\n" +
                            "注册登录问题：1             \n" +
                            "实名认证问题：2             \n" +
                            "银行卡问题：3                   \n" +
                            "基本信息问题：4              \n" +
                            "影像资料问题：5                \n" +
                            "申请审核问题：6               \n" +
                            "修改注销账号：7              \n" +
                            "借款问题：8                    \n" +
                            "还款问题：9                   \n" +
                            "邀请好友：10";
                    inputMsg.setContent(content);
                    inputMsg.setFromUserName(servername);
                    inputMsg.setToUserName(custermname);
                    inputMsg.setMsgType("text");
                    return SerializeXmlUtil.textMessageToXml(inputMsg);
                } else if (inputMsg.getEventKey().equals("activity_benefit")) {

                    String content = "邀请好友抽提额奖励，送免息券！\n" +
                            "现邀请好友开户成功，即可参与提额抽奖，500、1000元提额奖励等你拿！还有免息券赠送，多邀多得，上不封顶！\n" +
                            "\n" +
                            "\n" +
                            "【邀请方式】登录app--点击下方“免息”--点击“立即邀请”--选择分享方式--确定“分享”。点击http://t.cn/RVRjgdX立即邀请吧！";
                    inputMsg.setContent(content);
                    inputMsg.setFromUserName(servername);
                    inputMsg.setToUserName(custermname);
                    inputMsg.setMsgType("text");
                    return SerializeXmlUtil.textMessageToXml(inputMsg);
                } else if (inputMsg.getEventKey().equals("loan_process")) {
                    String content = "一、申请条件\n" +
                            "\n" +
                            "1、申请人年龄须在20周岁（含）至45周岁之间\n" +
                            "\n" +
                            "2、申请人所在户籍或居住地不限（新疆、西藏除外）\n" +
                            "\n" +
                            "3、申请人须收入稳定但工作内容不限（学生暂不能申请）\n" +
                            "\n" +
                            "4、无不良征信记录，在其他银行没有任何逾期记录\n" +
                            "\n" +
                            "二、借款流程\n" +
                            "\n" +
                            "下载贷款王APP—注册—提交资料—等待审核通过—提现到银行卡（额度500-5000元）\n" +
                            "\n" +
                            "三、还款流程\n" +
                            "打开贷款王APP，直接点击立即还款即可。";
                    inputMsg.setContent(content);
                    inputMsg.setFromUserName(servername);
                    inputMsg.setToUserName(custermname);
                    inputMsg.setMsgType("text");
                    return SerializeXmlUtil.textMessageToXml(inputMsg);
                } else if (inputMsg.getEventKey().equals("business_cooperation")) {
                    String content = "联系人邮箱：\n" +
                            "chenggq@2345.com \n" +
                            "zhangyn@2345.com\n" +
                            "欢迎进行合作洽谈!/:share";
                    inputMsg.setContent(content);
                    inputMsg.setFromUserName(servername);
                    inputMsg.setToUserName(custermname);
                    inputMsg.setMsgType("text");
                    return SerializeXmlUtil.textMessageToXml(inputMsg);
                }
                return "success";
            }
            taskExecutor.execute(() -> {

            });
        }
        return "success";
    }

}
