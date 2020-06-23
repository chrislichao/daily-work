package org.chrisli.activiti.dao;

import org.chrisli.activiti.domain.SysApplyBillsDO;

import java.util.List;

public interface SysApplyBillsMapper {
    Long insert(SysApplyBillsDO sysApplyBillsDO);

    Integer updateDynamic(SysApplyBillsDO sysApplyBillsDO);

    SysApplyBillsDO selectById(Long id);

    List<SysApplyBillsDO> selectDynamic(SysApplyBillsDO sysApplyBillsDO);

    List<SysApplyBillsDO> selectDynamicPageQuery(SysApplyBillsDO sysApplyBillsDO);

    Long selectDynamicCount(SysApplyBillsDO sysApplyBillsDO);
}