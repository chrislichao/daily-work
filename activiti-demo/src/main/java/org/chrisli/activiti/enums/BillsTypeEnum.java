package org.chrisli.activiti.enums;

public enum BillsTypeEnum {
    FINANCING_ORDER(1, "FinancingOrderProcess","FO", "融资订单");

    BillsTypeEnum(Integer value, String procDefKey, String billsCodePreffix,String name) {
        this.value = value;
        this.procDefKey = procDefKey;
        this.billsCodePreffix = billsCodePreffix;
        this.name = name;
    }

    private Integer value;
    private String procDefKey;
    private String billsCodePreffix;
    private String name;

    public static BillsTypeEnum getMatchedItemByValue(Integer value) {
        for (BillsTypeEnum billsTypeEnum : BillsTypeEnum.values()) {
            if (billsTypeEnum.getValue().equals(value)) {
                return billsTypeEnum;
            }
        }
        throw new RuntimeException("无效的申请单类型：" + value);
    }

    public Integer getValue() {
        return value;
    }

    public String getProcDefKey() {
        return procDefKey;
    }

    public String getBillsCodePreffix() {
        return billsCodePreffix;
    }

    public String getName() {
        return name;
    }
}