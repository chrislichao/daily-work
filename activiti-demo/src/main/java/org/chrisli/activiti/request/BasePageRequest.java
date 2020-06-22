package org.chrisli.activiti.request;

import java.io.Serializable;

public class BasePageRequest implements Serializable {
    /**
     * 起始页
     */
    private int startPage = 1;
    /**
     * 每页记录数
     */
    private int pageSize = 20;
    /**
     * 起始记录行
     */
    private int start;

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        if (startPage < 0 || pageSize < 0) {
            start = 0;
        } else {
            start = ((startPage - 1) * pageSize);
        }
        return start;
    }
}