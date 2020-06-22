package org.chrisli.activiti.request;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class UserRequest extends BasePageRequest {
    private Long userId;
    private Integer billsType;
    private Integer billsStatus;

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

    public Integer getBillsStatus() {
        return billsStatus;
    }

    public void setBillsStatus(Integer billsStatus) {
        this.billsStatus = billsStatus;
    }
}
