package com.pein.cloud.program.vo;

/**
 * Created by qiuw on 2017/6/29.
 */

/**
 * @author qiuwei
 * @create 2017-06-29 13:13
 **/
public class PaperVo {

    private String name;

    private String description;

    private String urls[];


    public PaperVo() {
    }

    public PaperVo(String name, String description, String[] urls) {
        this.name = name;
        this.description = description;
        this.urls = urls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }
}
