package com.pein.cloud.program.controller;


import com.alibaba.fastjson.JSON;
import com.pein.cloud.program.build.Builder;
import com.pein.cloud.program.domain.TeacherOcrPaper;
import com.pein.cloud.program.service.IdentifyService;
import com.pein.cloud.program.service.OcrService;
import com.pein.cloud.program.vo.ApiVo;
import com.pein.cloud.program.vo.PaperVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/")
public class IdentifyController {

    private Logger LOGGER = LoggerFactory.getLogger(IdentifyController.class);

    @Autowired
    private IdentifyService identifyService;

    @Autowired
    private OcrService ocrService;

    @RequestMapping("upload")
    public ApiVo upload(MultipartFile file, String phone) {
        LOGGER.info("upload file : phone = {}.", phone);
        String url = identifyService.storeNative(file);
        LOGGER.info("end to upload file : url = {}.", url);
        if (StringUtils.isEmpty(url)) {
            return Builder.build();
        }
        return Builder.build(url);
    }

    @RequestMapping("save_paper")
    public ApiVo savePaper(String name, String description, String[] urls) {
        PaperVo paper = new PaperVo(name, description, urls);
        LOGGER.info("begin to save paper. paperVo = {}.", JSON.toJSONString(paper));
        TeacherOcrPaper teacherOcrPaper = identifyService.save(paper);
        if (teacherOcrPaper == null) {
            return Builder.build();
        }
        ocrService.identify(urls[0],"d://data/dest/123123.jpg");
        LOGGER.info("end to save paper. teacherOcrPaper : {}.", JSON.toJSONString(teacherOcrPaper));
        return Builder.build(JSON.toJSONString(teacherOcrPaper));
    }

}
