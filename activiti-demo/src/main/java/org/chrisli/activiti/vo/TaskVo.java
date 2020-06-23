package org.chrisli.activiti.vo;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-23]
 */
public class TaskVo extends SysBillsVo {
    private String taskId;
    private String taskName;
    private String taskKey;

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

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }
}
