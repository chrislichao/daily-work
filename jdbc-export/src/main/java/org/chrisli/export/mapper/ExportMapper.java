package org.chrisli.export.mapper;

import org.chrisli.export.model.ColumnVo;
import org.chrisli.export.model.TableVo;

import java.util.List;
import java.util.Map;

/**
 * [导出mapper]
 *
 * @author Administrator[黎超]
 * @create [2017-04-12]
 */
public interface ExportMapper {
    List<TableVo> getTableVoList(String tableSchema);
    List<ColumnVo> getColumnVoList(Map<String, Object> paramMap);
}
