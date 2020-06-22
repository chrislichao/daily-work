package org.chrisli.activiti.enums;

public enum OrderColumnEnum {
    ID("id"),
    CREATED_ON("created_on");

    OrderColumnEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}