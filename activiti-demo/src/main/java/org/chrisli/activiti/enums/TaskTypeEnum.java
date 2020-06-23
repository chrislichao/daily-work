package org.chrisli.activiti.enums;

public enum TaskTypeEnum {
    SUBMIT_TASK(1, "提交任务","需修改单据状态【草稿单】-【审批中】");

    TaskTypeEnum(Integer value, String name,String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    private Integer value;
    private String name;
    private String description;

    public static TaskTypeEnum getMatchedItemByValue(Integer value) {
        for (TaskTypeEnum billsTypeEnum : TaskTypeEnum.values()) {
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

    public String getDescription() {
        return description;
    }
}