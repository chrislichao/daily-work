package org.chrisli.activiti.vo;

import java.util.Date;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class SysBillsVo {
    private Long id;
    private String billsTypeName;
    private String billsStatusName;
    private String billsCode;
    private String createByName;
    private Date createOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
