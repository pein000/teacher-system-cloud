package com.pein.cloud.program.enums;

/**
 * Created by qiuw on 2017/6/29.
 */
public enum StoreTypeEnum {
    NATIVE(0),//本地文件系统
    FTP(1),//FTP系统
    FASTDFS(2),//fastdfs系统
    HDFS(3);//hdfs系统

    private int type;

    StoreTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
