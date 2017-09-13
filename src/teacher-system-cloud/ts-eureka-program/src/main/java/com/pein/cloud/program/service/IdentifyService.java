package com.pein.cloud.program.service;

/**
 * Created by qiuw on 2017/6/28.
 */

import com.pein.cloud.program.domain.TeacherOcrPaper;
import com.pein.cloud.program.domain.TeacherOcrStore;
import com.pein.cloud.program.enums.ClassifyEnum;
import com.pein.cloud.program.enums.StoreTypeEnum;
import com.pein.cloud.program.mapper.PaperMapper;
import com.pein.cloud.program.mapper.StoreMapper;
import com.pein.cloud.program.vo.PaperVo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 在线识别服务
 *
 * @author qiuwei
 * @create 2017-06-28 19:55
 **/
@Service
public class IdentifyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentifyService.class);

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private PaperMapper paperMapper;

    public String storeNative(MultipartFile file) {
        String url = "D:\\data\\" + file.getOriginalFilename();
        LOGGER.info("to store file by native. url = {}.", url);
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = file.getInputStream();
            fileOutputStream = new FileOutputStream(new File(url));
            byte[] bytes = new byte[inputStream.available()];
            while (inputStream.read(bytes) > 0) {
                fileOutputStream.write(bytes);
            }
            return url;
        } catch (IOException e) {
            LOGGER.error("write to native file error. {}.", ExceptionUtils.getFullStackTrace(e));
            return null;
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
    }

    @Transactional
    public TeacherOcrPaper save(PaperVo paperVo){
        try {
            TeacherOcrPaper teacherOcrPaper = this.buildPaper(paperVo.getName(), paperVo.getDescription());
            paperMapper.insert(teacherOcrPaper);
            String[] urls = paperVo.getUrls();
            if (urls != null && urls.length > 0) {
                for (String url : urls) {
                    TeacherOcrStore teacherOcrStore = this.buildStore(url, teacherOcrPaper.getId());
                    storeMapper.insert(teacherOcrStore);
                }
            }
            return teacherOcrPaper;
        }catch (Exception e){
            return null;
        }
    }

    private TeacherOcrStore buildStore(String url, int subjectId) {
        TeacherOcrStore teacherOcrStore = new TeacherOcrStore();
        teacherOcrStore.setUrl(url);
        teacherOcrStore.setSubjectId(subjectId);
        teacherOcrStore.setStoreType(StoreTypeEnum.NATIVE.getType());
        teacherOcrStore.setCreateTime(new Timestamp(new Date().getTime()));
        teacherOcrStore.setUpdateTime(new Timestamp(new Date().getTime()));
        return teacherOcrStore;
    }

    private TeacherOcrPaper buildPaper(String name, String description){
        TeacherOcrPaper teacherOcrPaper = new TeacherOcrPaper();
        teacherOcrPaper.setName(name);
        teacherOcrPaper.setClassify(ClassifyEnum.CHINESE.getClassify());
        teacherOcrPaper.setDescription(description);
        teacherOcrPaper.setCreateTime(new Timestamp(new Date().getTime()));
        teacherOcrPaper.setUpdateTime(new Timestamp(new Date().getTime()));
        return teacherOcrPaper;
    }


}
