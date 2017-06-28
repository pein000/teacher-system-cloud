package com.pein.cloud.wechat.parse;

/**
 * Created by qiuw on 2017/6/22.
 */

import com.pein.cloud.wechat.utils.InputMessage;
import com.pein.cloud.wechat.utils.OutputMessage;
import com.pein.cloud.wechat.utils.SerializeXmlUtil;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 微信解析
 *
 * @author qiuwei
 * @create 2017-06-22 11:39
 **/
public class WechatParser {

    private static final Logger log = LoggerFactory.getLogger(WechatParser.class);


    public static InputMessage toMessage(HttpServletRequest request) {
        ServletInputStream in = null;
        try {
            in = request.getInputStream();
            XStream xs = SerializeXmlUtil.createXstream();
            xs.processAnnotations(InputMessage.class);
            xs.processAnnotations(OutputMessage.class);
            xs.alias("xml", InputMessage.class);
            StringBuilder xmlMsg = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = in.read(b)) != -1; ) {
                xmlMsg.append(new String(b, 0, n, "UTF-8"));
            }
            InputMessage inputMessage = (InputMessage) xs.fromXML(xmlMsg.toString());
            return inputMessage;
        } catch (IOException e) {
            log.error("LOGGER00040:  parse wechat to message error. {}.", ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
