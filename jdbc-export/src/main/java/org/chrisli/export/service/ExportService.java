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
import java.util.stream.Collectors;

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

    private static String WORD_NAME_FORMAT = "%s-%s数据库表结构.doc";

    private static String TEMPLATE_FILE_NAME_FORMAT = "导出模板-%s.ftl";

    public void exportTableColumn(String projectName, String tableSchema, String tableSchemaDesc, String type) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<TableVo> tableVoList = exportMapper.getTableVoList(tableSchema);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tableSchema", tableSchema);
        tableVoList = tableVoList.stream().filter((item) -> !item.getName().startsWith("ACT_")).collect(Collectors.toList());
        for (TableVo tableVo : tableVoList) {
            paramMap.put("tableName", tableVo.getName());
            List<ColumnVo> columnVoList = exportMapper.getColumnVoList(paramMap);
            tableVo.setColumnList(columnVoList);
        }
        dataMap.put("tableList", tableVoList);
        String wordName = String.format(WORD_NAME_FORMAT, projectName, tableSchemaDesc);
        String templateFileName = String.format(TEMPLATE_FILE_NAME_FORMAT, type);
        WordUtil.createWord(dataMap, templateFileName, "C:/ChrisLi", wordName);
    }
}