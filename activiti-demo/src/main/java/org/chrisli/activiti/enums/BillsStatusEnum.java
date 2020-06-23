package org.chrisli.activiti.enums;

public enum BillsStatusEnum {
    DRAFT(0, "草稿单"),
    ING(1, "审批中"),
    COMPLETE(2, "已完成"),
    GIVE_UP(3, "已放弃");

    BillsStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    private Integer value;
    private String name;

    public static BillsStatusEnum getMatchedItemByValue(Integer value) {
        for (BillsStatusEnum billsTypeEnum : BillsStatusEnum.values()) {
            if (billsTypeEnum.getValue().equals(value)) {
                return billsTypeEnum;
            }
        }
        throw new RuntimeException("无效的单据状态：" + value);
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}