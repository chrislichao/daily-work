package org.chrisli.activiti.domain;

import java.util.Date;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class SysApplyBillsDO {
    private Long id;
    private Integer billsType;
    private Integer billsStatus;
    private String billsCode;
    private String prodefId;
    private String deploymentId;
    private Long createdBy;
    private Date createdOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBillsCode() {
        return billsCode;
    }

    public void setBillsCode(String billsCode) {
        this.billsCode = billsCode;
    }

    public String getProdefId() {
        return prodefId;
    }

    public void setProdefId(String prodefId) {
        this.prodefId = prodefId;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
