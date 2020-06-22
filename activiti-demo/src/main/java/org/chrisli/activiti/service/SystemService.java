package org.chrisli.activiti.service;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.collections4.CollectionUtils;
import org.chrisli.activiti.dao.SysApplyBillsMapper;
import org.chrisli.activiti.dao.SysUserMapper;
import org.chrisli.activiti.domain.OrderPolicyDO;
import org.chrisli.activiti.domain.SysApplyBillsDO;
import org.chrisli.activiti.enums.BillsStatusEnum;
import org.chrisli.activiti.enums.BillsTypeEnum;
import org.chrisli.activiti.enums.OrderColumnEnum;
import org.chrisli.activiti.enums.OrderPolicyEnum;
import org.chrisli.activiti.request.UserRequest;
import org.chrisli.activiti.utils.DateUtils;
import org.chrisli.activiti.view.Page;
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
    private SysApplyBillsMapper sysApplyBillsMapper;

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

    public SysBillsVo createDraftBill(Long userId, BillsTypeEnum billsTypeEnum, ProcessDefinition processDefinition) {
        SysApplyBillsDO sysApplyBillsDO = new SysApplyBillsDO();
        sysApplyBillsDO.setBillsType(billsTypeEnum.getValue());
        sysApplyBillsDO.setBillsStatus(BillsStatusEnum.DRAFT.getValue());
        sysApplyBillsDO.setBillsCode(generateBillsCode(billsTypeEnum));
        sysApplyBillsDO.setProdefId(processDefinition.getId());
        sysApplyBillsDO.setDeploymentId(processDefinition.getDeploymentId());
        sysApplyBillsDO.setCreatedBy(userId);
        sysApplyBillsMapper.insert(sysApplyBillsDO);
        SysBillsVo sysBillsVo = new SysBillsVo();
        BeanUtils.copyProperties(sysApplyBillsDO, sysBillsVo);
        sysBillsVo.setBillsTypeName(billsTypeEnum.getName());
        sysBillsVo.setBillsStatusName(BillsStatusEnum.DRAFT.getName());
        sysBillsVo.setCreatedByName("User:" + userId);
        sysBillsVo.setCreatedOn(new Date());
        return sysBillsVo;
    }

}