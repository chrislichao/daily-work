package org.chrisli.export.model;

import java.util.List;

/**
 * [表信息]
 *
 * @author Administrator[黎超]
 * @create [2017-04-12]
 */
public class TableVo {
    private String name;
    private String remark;
    private List<ColumnVo> columnList;

    public List<ColumnVo> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnVo> columnList) {
        this.columnList = columnList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
