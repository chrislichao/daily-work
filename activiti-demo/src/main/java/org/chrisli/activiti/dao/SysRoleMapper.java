package org.chrisli.activiti.dao;

import org.chrisli.activiti.domain.SysRoleDO;

import java.util.List;

public interface SysRoleMapper {
    Long insert(SysRoleDO sysRoleDO);

    List<SysRoleDO> selectDynamic(SysRoleDO sysRoleDO);

    List<SysRoleDO> selectDynamicPageQuery(SysRoleDO sysRoleDO);

    Long selectDynamicCount(SysRoleDO sysRoleDO);
}