package org.chrisli.activiti.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseDO implements Serializable {

    private int start;
    /**
     * 起始页
     */
    private int startPage = 1;
    /**
     * 每页记录数
     */
    private int pageSize = 20;
    /**
     * 排序策略
     */
    private List<OrderPolicyDO> orderPolicyList = new ArrayList<>();

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        if (startPage < 0 || pageSize < 0) {
            return 0;
        } else {
            return ((startPage - 1) * pageSize);
        }
    }

    public BaseDO addOrderPolicy(OrderPolicyDO orderPolicyDO) {
        this.orderPolicyList.add(orderPolicyDO);
        return this;
    }

    public List<OrderPolicyDO> getOrderPolicyList() {
        return orderPolicyList;
    }
}