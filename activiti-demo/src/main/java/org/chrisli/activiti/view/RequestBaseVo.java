package org.chrisli.activiti.view;

import javax.validation.Valid;
import java.io.Serializable;

public class RequestBaseVo<T> implements Serializable {
    private String appKey;
    private String sign;
    private String timestamp;
    private String format;
    private String accessToken;
    @Valid
    private T param;
    private ClientInfo clientInfo;

    public RequestBaseVo() {
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public T getParam() {
        return this.param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }
}
