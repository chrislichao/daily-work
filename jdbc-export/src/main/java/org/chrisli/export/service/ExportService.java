package org.chrisli.export.service;

import org.chrisli.export.mapper.ExportMapper;
import org.chrisli.export.model.ColumnVo;
import org.chrisli.export.model.TableVo;
import org.chrisli.export.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [导出服务]
 *
 * @author Administrator[黎超]
 * @create [2017-04-12]
 */
@Service
public class ExportService {
    @Autowired
    private ExportMapper exportMapper;

    public void exportTableColumn() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<TableVo> tableVoList = exportMapper.getTableVoList();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        for (TableVo tableVo : tableVoList) {
            paramMap.put("tableName", tableVo.getName());
            List<ColumnVo> columnVoList = exportMapper.getColumnVoList(paramMap);
            tableVo.setColumnList(columnVoList);
        }
        dataMap.put("tableList", tableVoList);
        WordUtil.createWord(dataMap, "导出模板.ftl", "D:/", "中台数据库表结构.doc");
    }
}
