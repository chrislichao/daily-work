package org.chrisli.activiti.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.chrisli.activiti.utils.DateUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public class ResponseBaseVo<T> implements Serializable {
    private String message;
    private String code;
    private String timestamp;
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T datas;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object requestValue;

    protected ResponseBaseVo(boolean success, String code, String message, T datas) {
        this.message = message;
        this.code = code;
        this.success = success;
        this.datas = datas;
        this.timestamp = DateUtils.getCurTimestamp();
    }

    protected ResponseBaseVo(boolean success, String code, String message, T datas, Object requestValue) {
        this.message = message;
        this.code = code;
        this.success = success;
        this.datas = datas;
        this.timestamp = DateUtils.getCurTimestamp();
    }

    public ResponseBaseVo() {
    }

    public String getCode() {
        return this.isSuccess() ? "0" : this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getDatas() {
        return this.datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public Object getRequestValue() {
        return this.requestValue;
    }

    public void setRequestValue(Object requestValue) {
        this.requestValue = requestValue;
    }

    public String getMessage() {
        return StringUtils.isEmpty(this.message) ? (this.isSuccess() ? "请求成功" : this.message) : this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseBaseVo ok() {
        return new ResponseBaseVo(true, (String)null, (String)null, (Object)null);
    }

    public static <T> ResponseBaseVo<T> ok(T datas) {
        return new ResponseBaseVo(true, (String)null, (String)null, datas);
    }

    public static ResponseBaseVo fail(String errorCode, String message) {
        return new ResponseBaseVo(false, errorCode, message, (Object)null);
    }

    public static <T> ResponseBaseVo<T> fail(String errorCode, String message, T datas) {
        return new ResponseBaseVo(false, errorCode, message, datas);
    }

    public static ResponseBaseVo fail(CodeMsg codeMsg) {
        return new ResponseBaseVo(false, codeMsg.getCode(), codeMsg.getMsg(), (Object)null);
    }

    public static <T> ResponseBaseVo<T> fail(CodeMsg codeMsg, T datas) {
        return new ResponseBaseVo(false, codeMsg.getCode(), codeMsg.getMsg(), datas);
    }

    public static ResponseBaseVo ok(CodeMsg codeMsg) {
        return new ResponseBaseVo(true, codeMsg.getCode(), codeMsg.getMsg(), (Object)null);
    }

    public static <T> ResponseBaseVo<T> ok(CodeMsg codeMsg, T datas) {
        return new ResponseBaseVo(true, codeMsg.getCode(), codeMsg.getMsg(), datas);
    }
}