package org.chrisli.activiti.domain;

import org.chrisli.activiti.enums.OrderColumnEnum;
import org.chrisli.activiti.enums.OrderPolicyEnum;

import java.io.Serializable;

public class OrderPolicyDO implements Serializable {

    public OrderPolicyDO(OrderColumnEnum column) {
        this.column = column.getValue();
        this.policy = OrderPolicyEnum.ASC.getValue();
    }

    public OrderPolicyDO(OrderColumnEnum column, OrderPolicyEnum policy) {
        this.column = column.getValue();
        this.policy = policy.getValue();
    }

    /**
     * 字段
     */
    private String column;
    /**
     * 策略
     */
    private String policy;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
}