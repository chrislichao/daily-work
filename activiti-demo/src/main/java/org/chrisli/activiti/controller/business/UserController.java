package org.chrisli.activiti.controller.business;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections4.CollectionUtils;
import org.chrisli.activiti.domain.SysApplyBillsDO;
import org.chrisli.activiti.domain.SysRoleDO;
import org.chrisli.activiti.enums.ActionTypeEnum;
import org.chrisli.activiti.enums.BillsTypeEnum;
import org.chrisli.activiti.request.TaskRequest;
import org.chrisli.activiti.request.UserRequest;
import org.chrisli.activiti.service.SystemService;
import org.chrisli.activiti.view.Page;
import org.chrisli.activiti.view.RequestBaseVo;
import org.chrisli.activiti.view.ResponseBaseVo;
import org.chrisli.activiti.vo.SysBillsVo;
import org.chrisli.activiti.vo.TaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-16]
 */
@RestController
@RequestMapping("user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SystemService systemService;

    /**
     * [用户分页查询申请单]
     *
     * @author Chris Li[黎超]
     * @create [2020/6/22]
     */
    @RequestMapping(value = "/getSysBillsVoPageList")
    public ResponseBaseVo<Page<SysBillsVo>> getBillPageList(@RequestBody RequestBaseVo<UserRequest> requestVo) {
        UserRequest request = requestVo.getParam();
        if (request == null) {
            return ResponseBaseVo.fail("XXXX", "XXXX");
        }
        logger.info("用户分页查询申请单,参数:{}", JSON.toJSONString(request));
        if (request.getStartPage() <= 0 || request.getPageSize() <= 0) {
            return ResponseBaseVo.fail("XXXX", "XXXX");
        }
        if (request.getUserId() == null) {
            return ResponseBaseVo.fail("xxxxx", "用户不允许为空！");
        }
        Page<SysBillsVo> page = systemService.getSysBillsVoPageList(request);
        return ResponseBaseVo.ok(page);
    }

    /**
     * [创建申请草稿单]
     *
     * @author Chris Li[黎超]
     * @create [2020/6/22]
     */
    @RequestMapping(value = "/createDraftBill")
    public ResponseBaseVo<SysBillsVo> createDraftBill(@RequestBody RequestBaseVo<UserRequest> requestVo) {
        UserRequest request = requestVo.getParam();
        if (request == null) {
            return ResponseBaseVo.fail("XXXX", "XXXX");
        }
        if (request.getUserId() == null) {
            return ResponseBaseVo.fail("xxxxx", "用户不允许为空！");
        }
        if (request.getBillsType() == null) {
            return ResponseBaseVo.fail("xxxxx", "申请单类型不允许为空！");
        }
        BillsTypeEnum billsTypeEnum = BillsTypeEnum.getMatchedItemByValue(request.getBillsType());
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionKey(billsTypeEnum.getProcDefKey()).orderByProcessDefinitionVersion().desc().list();
        if (CollectionUtils.isEmpty(processDefinitionList)) {
            return ResponseBaseVo.fail("XXXXX", "单据流程未定义");
        }
        // 获取申请单对应的最新的流程定义
        ProcessDefinition processDefinition = processDefinitionList.get(0);
        // 创建申请单
        SysBillsVo sysBillsVo = systemService.createDraftBill(request.getUserId(), billsTypeEnum, processDefinition);
        Map<String, Object> valusMap = new HashMap<>();
        valusMap.put("user", request.getUserId().toString());
        // 启动审批流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), sysBillsVo.getId().toString(), valusMap);
        boolean result = systemService.setProcessInstanceId(sysBillsVo.getId(), processInstance.getId());
        if (!result) {
            return ResponseBaseVo.fail("xxxxx", "单据创建失败！");
        }
        return ResponseBaseVo.ok(sysBillsVo);
    }

    /**
     * [查询我的待办任务]
     *
     * @author Chris Li[黎超]
     * @create [2020/6/22]
     */
    @RequestMapping(value = "/getMyTaskList")
    public ResponseBaseVo<List<TaskVo>> getMyTaskList(@RequestBody RequestBaseVo<TaskRequest> requestVo) {
        TaskRequest request = requestVo.getParam();
        if (request == null) {
            return ResponseBaseVo.fail("XXXX", "XXXX");
        }
        Long userId = request.getUserId();
        if (userId == null) {
            return ResponseBaseVo.fail("xxxxx", "用户不允许为空！");
        }
        // 获取当前用户的角色
        List<SysRoleDO> sysRoleDOList = systemService.getRoleListByUserId(userId);
        List<String> roleCodeList = sysRoleDOList.stream().map(item -> "RodeCode:" + item.getCode()).collect(Collectors.toList());

        // 当前存在两种任务:用户任务,角色任务
        List<Task> userTaskList = taskService.createTaskQuery().taskAssignee(userId.toString()).list();
        for (String roleCode : roleCodeList) {
            List<Task> roleTaskList = taskService.createTaskQuery().taskAssignee(roleCode).list();
            userTaskList.addAll(roleTaskList);
        }
        List<TaskVo> myTaskList = userTaskList.stream().map(item -> {
            String processInstanceId = item.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            String businessKey = processInstance.getBusinessKey();
            SysBillsVo sysBillsVo = systemService.getSysBillsVoById(Long.valueOf(businessKey));

            TaskVo taskVo = new TaskVo();
            taskVo.setTaskId(item.getId());
            taskVo.setTaskKey(item.getTaskDefinitionKey());
            taskVo.setTaskName(item.getName());
            BeanUtils.copyProperties(sysBillsVo, taskVo);
            return taskVo;
        }).collect(Collectors.toList());
        return ResponseBaseVo.ok(myTaskList);
    }

    /**
     * [完成任务]
     *
     * @author Chris Li[黎超]
     * @create [2020/6/22]
     */
    @RequestMapping(value = "/completeTask")
    public ResponseBaseVo<SysBillsVo> completeTask(@RequestBody RequestBaseVo<TaskRequest> requestVo) {
        TaskRequest request = requestVo.getParam();
        if (request == null) {
            return ResponseBaseVo.fail("XXXX", "XXXX");
        }
        Long userId = request.getUserId();
        if (userId == null) {
            return ResponseBaseVo.fail("xxxxx", "用户不允许为空！");
        }
        if (request.getBillsId() == null) {
            return ResponseBaseVo.fail("xxxxx", "申请单不允许为空！");
        }
        if (request.getTaskId() == null) {
            return ResponseBaseVo.fail("xxxxx", "任务不允许为空！");
        }
        if (request.getActionType() == null) {
            return ResponseBaseVo.fail("xxxxx", "操作类型不允许为空！");
        }
        ActionTypeEnum actionTypeEnum = ActionTypeEnum.getMatchedItemByValue(request.getActionType());
        SysApplyBillsDO sysApplyBillsDO = systemService.getSysApplyBillsDOById(request.getBillsId());
        if (sysApplyBillsDO == null) {
            return ResponseBaseVo.fail("xxxxx", "当前操作的申请单不存在！");
        }
        Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
        if (task == null) {
            return ResponseBaseVo.fail("xxxx", "当前任务不存在！");
        }
        // 校验权限
        boolean result = systemService.validateAuth(userId, sysApplyBillsDO, task);
        if (!result) {
            return ResponseBaseVo.fail("xxxx", "您无权操作此申请单！");
        }
        Map<String, Object> actionMap = new HashMap<>();
        actionMap.put("actionType", actionTypeEnum.getValue());
        taskService.complete(task.getId(), actionMap);
        return ResponseBaseVo.ok();
    }
}