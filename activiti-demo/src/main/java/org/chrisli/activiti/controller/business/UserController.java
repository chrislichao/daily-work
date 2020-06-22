package org.chrisli.activiti.controller.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.collections4.CollectionUtils;
import org.chrisli.activiti.enums.BillsTypeEnum;
import org.chrisli.activiti.request.UserRequest;
import org.chrisli.activiti.service.SystemService;
import org.chrisli.activiti.view.RequestBaseVo;
import org.chrisli.activiti.view.ResponseBaseVo;
import org.chrisli.activiti.vo.SysBillsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ObjectMapper objectMapper;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private SystemService systemService;

    /**
     * [创建申请草稿单]
     *
     * @author Chris Li[黎超]
     * @create [2020/6/22]
     */
    @RequestMapping(value = "/createDraftBill")
    public ResponseBaseVo<SysBillsVo> createDraftBill(@RequestBody RequestBaseVo<UserRequest> requestVo) {
        UserRequest userRequest = requestVo.getParam();
        BillsTypeEnum billsTypeEnum = BillsTypeEnum.getMatchedItemByValue(userRequest.getBillsType());
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionKey(billsTypeEnum.getProcDefKey()).orderByProcessDefinitionVersion().desc().list();
        if (CollectionUtils.isEmpty(processDefinitionList)) {
            return ResponseBaseVo.fail("XXXXX", "单据流程未定义");
        }
        // 获取申请单对应的最新的流程定义
        ProcessDefinition processDefinition = processDefinitionList.get(0);
        // 创建申请单
        SysBillsVo sysBillsVo = systemService.createDraftBill(userRequest.getUserId(), billsTypeEnum, processDefinition);
        Map<String, Object> valusMap = new HashMap<>();
        valusMap.put("user", userRequest.getUserId().toString());
        // 启动审批流程
        runtimeService.startProcessInstanceById(processDefinition.getId(), sysBillsVo.getId().toString(), valusMap);
        return ResponseBaseVo.ok(sysBillsVo);
    }

    /**
     * [提交申请单]
     *
     * @author Chris Li[黎超]
     * @create [2020/6/22]
     */
    @RequestMapping(value = "/applyBill")
    public ResponseBaseVo<SysBillsVo> applyBill(@RequestBody RequestBaseVo<UserRequest> requestVo) {
        return ResponseBaseVo.ok();
    }
}