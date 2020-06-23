package org.chrisli.activiti.dao;

import org.chrisli.activiti.domain.SysUserDO;

import java.util.List;

public interface SysUserMapper {
    Long insert(SysUserDO sysUserDO);

    List<SysUserDO> selectDynamic(SysUserDO sysUserDO);

    List<SysUserDO> selectDynamicPageQuery(SysUserDO sysUserDO);

    Long selectDynamicCount(SysUserDO sysUserDO);
}