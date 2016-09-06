package cn.examen.domain.commons;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class Page<T> {
    private int pageSize;
    private int pageCount;
    private List<T> pageData;

    private Page(){ super(); }

    public Page(int totalRecord, int pageSize, List<T> pageData) {
        this.pageSize = pageSize;
        this.pageCount = (int)Math.ceil(totalRecord/(double)this.pageSize);
        this.pageData = pageData;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}
