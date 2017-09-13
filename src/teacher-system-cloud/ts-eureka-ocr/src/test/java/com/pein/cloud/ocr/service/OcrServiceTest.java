package com.pein.cloud.ocr.service;

import com.pein.cloud.ocr.EurekaOrcApplication;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;


/**
 * Created by qiuw on 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EurekaOrcApplication.class)
@WebAppConfiguration
public class OcrServiceTest {

    private static final Logger log = LoggerFactory.getLogger(OcrServiceTest.class);
    @Autowired
    private OcrService ocrService;

    private String origin = "D:\\data\\tmp_425969294o6zAJs6JGyY6fzZvR-_M_9J1cLSI450a99eae6f95fe8326b934317f9164b.jpg";

    private String dest = "d://data/dest/123123.jpg";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void identify() throws Exception {
        String content = ocrService.identify(origin, dest);
        System.out.println(content);
        Assert.assertNotNull(content);
    }

}