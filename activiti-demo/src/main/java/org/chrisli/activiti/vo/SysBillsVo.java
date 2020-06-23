package org.chrisli.activiti.vo;

import java.util.Date;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class SysBillsVo {
    private Long billsId;
    private String billsTypeName;
    private String billsStatusName;
    private String billsCode;
    private String createdByName;
    private Date createdOn;

    public Long getBillsId() {
        return billsId;
    }

    public void setBillsId(Long billsId) {
        this.billsId = billsId;
    }

    public String getBillsTypeName() {
        return billsTypeName;
    }

    public void setBillsTypeName(String billsTypeName) {
        this.billsTypeName = billsTypeName;
    }

    public String getBillsStatusName() {
        return billsStatusName;
    }

    public void setBillsStatusName(String billsStatusName) {
        this.billsStatusName = billsStatusName;
    }

    public String getBillsCode() {
        return billsCode;
    }

    public void setBillsCode(String billsCode) {
        this.billsCode = billsCode;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
