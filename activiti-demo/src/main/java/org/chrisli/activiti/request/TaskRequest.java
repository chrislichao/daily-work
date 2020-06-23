package org.chrisli.activiti.request;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class TaskRequest extends BasePageRequest {
    private Long userId;
    private Long billsId;
    private String taskId;
    private Integer actionType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }
}
