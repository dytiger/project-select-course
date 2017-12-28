package org.forten.course.dto.qo;

public class AdminCourseQo {
    private String name;
    private int credit;
    private int pageNo;
    private int pageSize;

    public AdminCourseQo() {
        this.pageNo = 1;
        this.pageSize = 3;
        this.credit = 0;
    }

    public AdminCourseQo(String name, int credit) {
        this();
        this.name = name;
        this.credit = credit;
    }

    public AdminCourseQo(String name, int credit, int pageNo, int pageSize) {
        this.name = name;
        this.credit = credit;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "AdminCourseQo{" +
                "name='" + name + '\'' +
                ", credit=" + credit +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
