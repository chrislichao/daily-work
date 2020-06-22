package org.chrisli.activiti.request;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class UserRequest {
    private Long userId;
    private Integer billsType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getBillsType() {
        return billsType;
    }

    public void setBillsType(Integer billsType) {
        this.billsType = billsType;
    }
}
