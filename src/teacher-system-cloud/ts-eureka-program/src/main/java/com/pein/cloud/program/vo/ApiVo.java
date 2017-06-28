package com.pein.cloud.program.vo;

/**
 * Created by qiuw on 2017/6/28.
 */

/**
 * vo对象
 *
 * @author qiuwei
 * @create 2017-06-28 16:00
 **/
public class ApiVo {

    private boolean success = false;
    private String errorCode;
    private String errorMessage;
    private String data;

    public ApiVo() {
    }

    public ApiVo(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
