package com.pein.cloud.common;

/**
 *
 * @author qiuwei
 * @date 2017/2/27
 */
public class OcrRequest {

    private String origin;

    private String dest;

    public OcrRequest() {
    }

    public OcrRequest(String origin, String dest) {
        this.origin = origin;
        this.dest = dest;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "OcrRequest{" +
                "origin='" + origin + '\'' +
                ", dest='" + dest + '\'' +
                '}';
    }
}
