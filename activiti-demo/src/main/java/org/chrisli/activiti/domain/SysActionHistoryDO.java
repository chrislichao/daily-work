package org.chrisli.activiti.domain;

import java.util.Date;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class SysActionHistoryDO extends BaseDO {
    private Long id;
    private Long billsId;
    private String taskId;
    private String taskName;
    private String taskDefinitionKey;
    private Integer actionType;
    private String actionComment;
    private Long actionBy;
    private Date actionOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillsId() {
        return billsId;
    }

    public void setBillsId(Long billsId) {
        this.billsId = billsId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getActionComment() {
        return actionComment;
    }

    public void setActionComment(String actionComment) {
        this.actionComment = actionComment;
    }

    public Long getActionBy() {
        return actionBy;
    }

    public void setActionBy(Long actionBy) {
        this.actionBy = actionBy;
    }

    public Date getActionOn() {
        return actionOn;
    }

    public void setActionOn(Date actionOn) {
        this.actionOn = actionOn;
    }
}
