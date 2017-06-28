package com.pein.cloud.program.controller;


import com.pein.cloud.program.build.Builder;
import com.pein.cloud.program.vo.ApiVo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/")
public class IdentifyController {

    private Logger LOGGER = LoggerFactory.getLogger(IdentifyController.class);


    @RequestMapping("upload")
    public ApiVo upload(MultipartFile file, String phone) {
        LOGGER.info("upload file : phone = {}.", phone);
        String url = "D:\\data\\" + file.getOriginalFilename();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = file.getInputStream();
            fileOutputStream = new FileOutputStream(new File(url));
            byte[] bytes = new byte[inputStream.available()];
            while (inputStream.read(bytes) > 0) {
                fileOutputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("close inputStream or fileOutputStream error. {}. ", ExceptionUtils.getFullStackTrace(e));
            }
        }
        return Builder.build(url);
    }

}
