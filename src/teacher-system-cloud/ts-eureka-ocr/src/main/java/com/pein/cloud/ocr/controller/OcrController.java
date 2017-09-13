package com.pein.cloud.ocr.controller;

import com.pein.cloud.common.OcrRequest;
import com.pein.cloud.common.OcrResponse;
import com.pein.cloud.ocr.service.OcrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

/**
 * Created by qiuwei on 2017/2/27.
 */
@RestController
@RequestMapping("/ocr")
public class OcrController {

    private static Logger LOGGER = LoggerFactory.getLogger(OcrController.class);

    @Autowired
    private OcrService ocrService;

    @Autowired
    private Executor fixExecutor;

    @RequestMapping("/identify")
    public OcrResponse identify(@RequestBody OcrRequest ocrRequest) {
        LOGGER.info("begin to identify. ocrRequest : {}. ", ocrRequest);
        fixExecutor.execute(()->{
            long begin = System.currentTimeMillis();
            String content = ocrService.identify(ocrRequest.getOrigin(), ocrRequest.getDest());
            long end = System.currentTimeMillis();
            LOGGER.info("end to identify. content = {},time = {}ms.", content, end - begin);
        });
        return new OcrResponse(ocrRequest.getOrigin(), ocrRequest.getDest());
    }
}
