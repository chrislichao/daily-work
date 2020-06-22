package org.chrisli.activiti.enums;

public enum OrderPolicyEnum {
    ASC("asc"),
    DESC("desc");

    OrderPolicyEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}