package org.chrisli.activiti.dao;

import org.chrisli.activiti.domain.SysActionHistoryDO;

import java.util.List;

public interface SysActionHistoryMapper {
    Long insert(SysActionHistoryDO sysActionHistoryDO);

    SysActionHistoryDO selectById(Long id);

    List<SysActionHistoryDO> selectDynamic(SysActionHistoryDO sysActionHistoryDO);

    List<SysActionHistoryDO> selectDynamicPageQuery(SysActionHistoryDO sysActionHistoryDO);

    Long selectDynamicCount(SysActionHistoryDO sysActionHistoryDO);
}