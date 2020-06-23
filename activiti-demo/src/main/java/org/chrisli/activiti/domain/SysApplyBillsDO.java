package org.chrisli.activiti.domain;

import com.sun.xml.internal.rngom.parse.host.Base;

import java.util.Date;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class SysApplyBillsDO extends BaseDO {
    private Long id;
    private Integer billsType;
    private Integer billsStatus;
    private String billsCode;
    private String processDefinitionId;
    private String deploymentId;
    private String processInstanceId;
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

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
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
