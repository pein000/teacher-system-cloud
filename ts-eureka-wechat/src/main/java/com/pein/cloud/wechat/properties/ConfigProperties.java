package com.pein.cloud.wechat.properties;/**
 * Created by qiuw on 2017/3/30.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author qiuwei
 * @create 2017-03-30 17:12
 **/
@Component
@PropertySource("${config.properties}")
@ConfigurationProperties
public class ConfigProperties {

    @Value("${weixin.app.id}")
    private String appId;

    @Value("${weixin.secret}")
    private String secret;

    @Value("${weixin.token}")
    private String token;

    public String getAppId() {
        return appId;
    }

    public String getSecret() {
        return secret;
    }

    public String getToken() {
        return token;
    }
}
