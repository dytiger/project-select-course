package org.forten.course.dto.vo;

public class CourseForShow {
    private int id;
    private String name;
    private String teacher;
    private String teachTime;
    private int credit;

    public CourseForShow() {
    }

    public CourseForShow(int id, String name, String teacher, String teachTime, int credit) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.teachTime = teachTime;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(String teachTime) {
        this.teachTime = teachTime;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "CourseForShow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", teachTime='" + teachTime + '\'' +
                ", credit=" + credit +
                '}';
    }
}
