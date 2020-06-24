package org.chrisli.activiti.vo;

import org.chrisli.activiti.domain.BaseDO;

import java.util.Date;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class SysBillsActionVo {
    private String taskId;
    private String taskName;
    private String taskDefinitionKey;
    private String actionTypeName;
    private String actionComment;
    private String actionByName;
    private Date actionOn;

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

    public String getActionTypeName() {
        return actionTypeName;
    }

    public void setActionTypeName(String actionTypeName) {
        this.actionTypeName = actionTypeName;
    }

    public String getActionComment() {
        return actionComment;
    }

    public void setActionComment(String actionComment) {
        this.actionComment = actionComment;
    }

    public String getActionByName() {
        return actionByName;
    }

    public void setActionByName(String actionByName) {
        this.actionByName = actionByName;
    }

    public Date getActionOn() {
        return actionOn;
    }

    public void setActionOn(Date actionOn) {
        this.actionOn = actionOn;
    }
}
