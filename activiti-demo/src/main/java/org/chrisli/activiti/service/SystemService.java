package org.chrisli.activiti.service;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.collections4.CollectionUtils;
import org.chrisli.activiti.dao.*;
import org.chrisli.activiti.domain.*;
import org.chrisli.activiti.enums.*;
import org.chrisli.activiti.request.UserRequest;
import org.chrisli.activiti.utils.DateUtils;
import org.chrisli.activiti.view.Page;
import org.chrisli.activiti.vo.SysBillsActionVo;
import org.chrisli.activiti.vo.SysBillsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysApplyBillsMapper sysApplyBillsMapper;

    @Autowired
    private SysActionHistoryMapper sysActionHistoryMapper;

    private String generateBillsCode(BillsTypeEnum billsTypeEnum) {
        return billsTypeEnum.getBillsCodePreffix() + "-" + DateUtils.getCurTimestampSerial();
    }

    public Page<SysBillsVo> getSysBillsVoPageList(UserRequest request) {
        Page<SysBillsVo> page = new Page<>();
        SysApplyBillsDO queryBill = new SysApplyBillsDO();
        if (request.getBillsType() != null) {
            queryBill.setBillsType(request.getBillsType());
        }
        if (request.getBillsStatus() != null) {
            queryBill.setBillsStatus(request.getBillsStatus());
        }
        queryBill.setCreatedBy(request.getUserId());
        queryBill.setStartPage(request.getStartPage());
        queryBill.setPageSize(request.getPageSize());
        queryBill.addOrderPolicy(new OrderPolicyDO(OrderColumnEnum.CREATED_ON, OrderPolicyEnum.DESC));

        List<SysApplyBillsDO> sysApplyBillsDOList = sysApplyBillsMapper.selectDynamicPageQuery(queryBill);
        if (CollectionUtils.isEmpty(sysApplyBillsDOList)) {
            page.setList(new ArrayList<>());
            page.setTotalCount(0);
            return page;
        }
        List<SysBillsVo> sysBillsVoList = sysApplyBillsDOList.stream().map(item -> {
            SysBillsVo sysBillsVo = new SysBillsVo();
            BeanUtils.copyProperties(item, sysBillsVo);
            sysBillsVo.setBillsId(item.getId());
            sysBillsVo.setCreatedByName("user:" + item.getCreatedBy());
            sysBillsVo.setBillsTypeName(BillsTypeEnum.getMatchedItemByValue(item.getBillsType()).getName());
            sysBillsVo.setBillsStatusName(BillsStatusEnum.getMatchedItemByValue(item.getBillsStatus()).getName());
            return sysBillsVo;
        }).collect(Collectors.toList());

        Long totalCount = sysApplyBillsMapper.selectDynamicCount(queryBill);
        page.setList(sysBillsVoList);
        page.setTotalCount(totalCount.intValue());
        return page;
    }

    public SysApplyBillsDO getSysApplyBillsDOById(Long sysBillsId) {
        return sysApplyBillsMapper.selectById(sysBillsId);
    }

    public SysBillsVo getSysBillsVoById(Long sysBillsId) {
        SysApplyBillsDO sysApplyBillsDO = sysApplyBillsMapper.selectById(sysBillsId);
        SysBillsVo sysBillsVo = new SysBillsVo();
        BeanUtils.copyProperties(sysApplyBillsDO, sysBillsVo);
        sysBillsVo.setBillsId(sysApplyBillsDO.getId());
        sysBillsVo.setCreatedByName("user:" + sysApplyBillsDO.getCreatedBy());
        sysBillsVo.setBillsTypeName(BillsTypeEnum.getMatchedItemByValue(sysApplyBillsDO.getBillsType()).getName());
        sysBillsVo.setBillsStatusName(BillsStatusEnum.getMatchedItemByValue(sysApplyBillsDO.getBillsStatus()).getName());
        return sysBillsVo;
    }

    public SysBillsVo createDraftBill(Long userId, BillsTypeEnum billsTypeEnum, ProcessDefinition processDefinition) {
        SysApplyBillsDO sysApplyBillsDO = new SysApplyBillsDO();
        sysApplyBillsDO.setBillsType(billsTypeEnum.getValue());
        sysApplyBillsDO.setBillsStatus(BillsStatusEnum.DRAFT.getValue());
        sysApplyBillsDO.setBillsCode(generateBillsCode(billsTypeEnum));
        sysApplyBillsDO.setProcessDefinitionId(processDefinition.getId());
        sysApplyBillsDO.setDeploymentId(processDefinition.getDeploymentId());
        sysApplyBillsDO.setCreatedBy(userId);
        sysApplyBillsMapper.insert(sysApplyBillsDO);
        SysBillsVo sysBillsVo = new SysBillsVo();
        BeanUtils.copyProperties(sysApplyBillsDO, sysBillsVo);
        sysBillsVo.setBillsId(sysApplyBillsDO.getId());
        sysBillsVo.setBillsTypeName(billsTypeEnum.getName());
        sysBillsVo.setBillsStatusName(BillsStatusEnum.DRAFT.getName());
        sysBillsVo.setCreatedByName("User:" + userId);
        sysBillsVo.setCreatedOn(new Date());
        SysActionHistoryDO sysActionHistoryDO = new SysActionHistoryDO();
        sysActionHistoryDO.setBillsId(sysApplyBillsDO.getId());
        sysActionHistoryDO.setActionType(ActionTypeEnum.APPROVE.getValue());
        sysActionHistoryDO.setActionComment("创建草稿单");
        sysActionHistoryDO.setActionBy(userId);
        sysActionHistoryMapper.insert(sysActionHistoryDO);
        return sysBillsVo;
    }

    public boolean setProcessInstanceId(Long billsId, String processInstanceId) {
        SysApplyBillsDO sysApplyBillsDO = new SysApplyBillsDO();
        sysApplyBillsDO.setId(billsId);
        sysApplyBillsDO.setProcessInstanceId(processInstanceId);
        Integer effectedCount = sysApplyBillsMapper.updateDynamic(sysApplyBillsDO);
        return effectedCount.intValue() == 1;
    }

    public List<SysRoleDO> getRoleListByUserId(Long userId) {
        SysUserRoleDO sysUserRoleDO = new SysUserRoleDO();
        sysUserRoleDO.setUserId(userId);
        List<SysUserRoleDO> sysUserRoleDOList = sysUserRoleMapper.selectDynamic(sysUserRoleDO);
        List<Long> roleIdList = sysUserRoleDOList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toList());
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setRoleIdList(roleIdList);
        return sysRoleMapper.selectDynamic(sysRoleDO);
    }

    public boolean validateAuth(Long userId, SysApplyBillsDO sysApplyBillsDO, Task task) {
        if (!sysApplyBillsDO.getProcessDefinitionId().equals(task.getProcessDefinitionId()) || !sysApplyBillsDO.getProcessInstanceId().equals(task.getProcessInstanceId())) {
            return false;
        }
        String assignee = task.getAssignee();
        if (assignee.startsWith("RoleCode:")) {
            String needRole = assignee.substring(9);
            List<SysRoleDO> sysRoleDOList = getRoleListByUserId(userId);
            for (SysRoleDO sysRoleDO : sysRoleDOList) {
                if (needRole.equalsIgnoreCase(sysRoleDO.getCode())) {
                    return true;
                }
            }
            return false;
        }
        return assignee.equals(userId.toString());
    }

    public void addActionHistory(Long userId, Long billsId, ActionTypeEnum actionTypeEnum, Task task, String actionComment) {
        SysActionHistoryDO sysActionHistoryDO = new SysActionHistoryDO();
        sysActionHistoryDO.setBillsId(billsId);
        sysActionHistoryDO.setActionType(actionTypeEnum.getValue());
        sysActionHistoryDO.setActionComment(actionComment);
        sysActionHistoryDO.setTaskId(task.getId());
        sysActionHistoryDO.setTaskName(task.getName());
        sysActionHistoryDO.setTaskDefinitionKey(task.getTaskDefinitionKey());
        sysActionHistoryDO.setActionBy(userId);
        sysActionHistoryMapper.insert(sysActionHistoryDO);
    }

    public void updateBillsStatus(Long billsId, BillsStatusEnum toStatus) {
        SysApplyBillsDO sysApplyBillsDO = new SysApplyBillsDO();
        sysApplyBillsDO.setId(billsId);
        sysApplyBillsDO.setBillsStatus(toStatus.getValue());
        sysApplyBillsMapper.updateDynamic(sysApplyBillsDO);
    }

    public List<SysBillsActionVo> getBillsActionHistory(Long billsId) {
        SysActionHistoryDO sysActionHistoryDO = new SysActionHistoryDO();
        sysActionHistoryDO.setBillsId(billsId);
        sysActionHistoryDO.addOrderPolicy(new OrderPolicyDO(OrderColumnEnum.ACTION_ON, OrderPolicyEnum.DESC));
        List<SysActionHistoryDO> sysActionHistoryDOList = sysActionHistoryMapper.selectDynamic(sysActionHistoryDO);
        List<SysBillsActionVo> sysBillsActionVoList = sysActionHistoryDOList.stream().map(item -> {
            SysBillsActionVo sysBillsActionVo = new SysBillsActionVo();
            BeanUtils.copyProperties(item, sysBillsActionVo);
            sysBillsActionVo.setActionTypeName(ActionTypeEnum.getMatchedItemByValue(item.getActionType()).getName());
            sysBillsActionVo.setActionByName(item.getActionBy().toString());
            return sysBillsActionVo;
        }).collect(Collectors.toList());
        return sysBillsActionVoList;
    }
}