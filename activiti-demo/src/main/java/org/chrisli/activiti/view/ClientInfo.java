package org.chrisli.activiti.view;

import java.io.Serializable;

public class ClientInfo implements Serializable {
    private String deviceModel;
    private String deviceSystemVersion;
    private String deviceNetType;
    private String deviceScreenW;
    private String deviceScreenH;
    private String platform;
    private String appVersion;
    private String uniqueIdentifier;
    private String clientRequestTime;
    private String isJailreak;
    private String browserInfo;
    private String origin;

    public ClientInfo() {
    }

    public String getBrowserInfo() {
        return this.browserInfo;
    }

    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceSystemVersion() {
        return this.deviceSystemVersion;
    }

    public void setDeviceSystemVersion(String deviceSystemVersion) {
        this.deviceSystemVersion = deviceSystemVersion;
    }

    public String getDeviceNetType() {
        return this.deviceNetType;
    }

    public void setDeviceNetType(String deviceNetType) {
        this.deviceNetType = deviceNetType;
    }

    public String getDeviceScreenW() {
        return this.deviceScreenW;
    }

    public void setDeviceScreenW(String deviceScreenW) {
        this.deviceScreenW = deviceScreenW;
    }

    public String getDeviceScreenH() {
        return this.deviceScreenH;
    }

    public void setDeviceScreenH(String deviceScreenH) {
        this.deviceScreenH = deviceScreenH;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getUniqueIdentifier() {
        return this.uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getClientRequestTime() {
        return this.clientRequestTime;
    }

    public void setClientRequestTime(String clientRequestTime) {
        this.clientRequestTime = clientRequestTime;
    }

    public String getIsJailreak() {
        return this.isJailreak;
    }

    public void setIsJailreak(String isJailreak) {
        this.isJailreak = isJailreak;
    }
}
