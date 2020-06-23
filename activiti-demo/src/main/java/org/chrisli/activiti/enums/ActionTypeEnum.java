package org.chrisli.activiti.enums;

public enum ActionTypeEnum {
    APPROVE(1, "同意"),
    REJECT(0,"拒绝");

    ActionTypeEnum(Integer value,  String name) {
        this.value = value;
        this.name = name;
    }

    private Integer value;
    private String name;

    public static ActionTypeEnum getMatchedItemByValue(Integer value) {
        for (ActionTypeEnum billsTypeEnum : ActionTypeEnum.values()) {
            if (billsTypeEnum.getValue().equals(value)) {
                return billsTypeEnum;
            }
        }
        throw new RuntimeException("无效的操作类型：" + value);
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}