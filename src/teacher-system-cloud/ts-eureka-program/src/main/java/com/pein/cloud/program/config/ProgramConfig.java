package com.pein.cloud.program.config;

/**
 * Created by qiuw on 2017/9/22.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author qiuwei
 * @create 2017-09-22 10:35
 **/
@Component
@ConfigurationProperties
@RefreshScope //spring cloud bus配置fresh时，其他类使用此类时将刷新数据
public class ProgramConfig {

    @Value("${program.hello}")
    private String helloProgram;

    public String getHelloProgram() {
        return helloProgram;
    }
}
