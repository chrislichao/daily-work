package org.chrisli.activiti.service;

import org.activiti.engine.repository.ProcessDefinition;
import org.chrisli.activiti.dao.SysApplyBillsMapper;
import org.chrisli.activiti.dao.SysUserMapper;
import org.chrisli.activiti.domain.SysApplyBillsDO;
import org.chrisli.activiti.enums.BillsStatusEnum;
import org.chrisli.activiti.enums.BillsTypeEnum;
import org.chrisli.activiti.utils.DateUtils;
import org.chrisli.activiti.vo.SysBillsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SystemService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysApplyBillsMapper sysApplyBillsMapper;

    private String generateBillsCode(BillsTypeEnum billsTypeEnum) {
        return billsTypeEnum.getBillsCodePreffix() + "-" + DateUtils.getCurTimestampSerial();
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
        sysBillsVo.setCreateByName("User:" + userId);
        sysBillsVo.setCreateOn(new Date());
        return sysBillsVo;
    }
}