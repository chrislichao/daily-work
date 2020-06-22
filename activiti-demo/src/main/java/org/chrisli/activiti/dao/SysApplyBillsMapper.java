package org.chrisli.activiti.dao;

import org.chrisli.activiti.domain.SysApplyBillsDO;

import java.util.List;

public interface SysApplyBillsMapper {
    Long insert(SysApplyBillsDO sysApplyBillsDO);

    List<SysApplyBillsDO> selectDynamic(SysApplyBillsDO sysApplyBillsDO);

    List<SysApplyBillsDO> selectDynamicPageQuery(SysApplyBillsDO queryBill);

    Long selectDynamicCount(SysApplyBillsDO queryBill);
}