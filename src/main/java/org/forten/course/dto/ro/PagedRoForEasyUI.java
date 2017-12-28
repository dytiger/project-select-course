package org.forten.course.dto.ro;

import org.forten.utils.collection.CollectionUtil;

import java.util.Collection;

public class PagedRoForEasyUI<T> {
    private Collection<T> rows;
    private long total;

    public PagedRoForEasyUI() {
    }

    public PagedRoForEasyUI(Collection<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isEmptyData(){
        return CollectionUtil.isEmpty(rows);
    }

    @Override
    public String toString() {
        return "PagedRoForEasyUI{" +
                "rows=" + rows +
                ", total=" + total +
                '}';
    }
}
