package com.pein.cloud.program.enums;

/**
 * Created by qiuw on 2017/6/29.
 */
public enum ClassifyEnum {
    CHINESE("0"),//语文
    ENGLISH("1"),//英文
    MATH("2");//数学

    private String classify;

    ClassifyEnum(String classify) {
        this.classify = classify;
    }

    public String getClassify() {
        return classify;
    }
}
