package org.chrisli.activiti.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页和排序参数及分页查询结果.
 */
public class Page<T> implements Serializable {
    private int totalCount = -1;

    private List<T> list = null;

    public Page() {
    }

    /**
     * 页内的数据列表
     */
    public List<T> getList() {
        if (totalCount == -1) {
            return new ArrayList<T>();
        }
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 总记录数量
     */
    public int getTotalCount() {
        return totalCount < 0 ? 0 : totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}