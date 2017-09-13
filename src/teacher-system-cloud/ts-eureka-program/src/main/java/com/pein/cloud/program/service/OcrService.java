package com.pein.cloud.program.service;

/**
 * Created by qiuw on 2017/7/10.
 */

import com.alibaba.fastjson.JSON;
import com.pein.cloud.common.OcrRequest;
import com.pein.cloud.common.OcrResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author qiuwei
 * @create 2017-07-10 19:11
 **/
@Service
public class OcrService {

    private static final Logger log = LoggerFactory.getLogger(OcrService.class);

    @Autowired
    private RestTemplate restTemplate;

    public void identify(String origin, String dest) {
        String url = "http://ts-eureka-zuul/eureka-zuul/ts-eureka-ocr/ocr/identify";
        OcrRequest ocrRequest = new OcrRequest(origin, dest);
        OcrResponse ocrResponse = restTemplate.postForObject(url, ocrRequest, OcrResponse.class);
        log.info("end to identify. ocrResponse : {}.", JSON.toJSONString(ocrResponse));
    }
}
