package org.chrisli.activiti.dao;

import org.chrisli.activiti.domain.SysUserRoleDO;

import java.util.List;

public interface SysUserRoleMapper {
    Long insert(SysUserRoleDO sysUserRoleDO);

    List<SysUserRoleDO> selectDynamic(SysUserRoleDO sysUserRoleDO);

    List<SysUserRoleDO> selectDynamicPageQuery(SysUserRoleDO sysUserRoleDO);

    Long selectDynamicCount(SysUserRoleDO sysUserRoleDO);
}