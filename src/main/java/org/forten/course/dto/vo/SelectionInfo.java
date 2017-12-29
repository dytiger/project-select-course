package org.forten.course.dto.vo;

import org.forten.utils.collection.CollectionUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SelectionInfo {
    private int courseId;
    private String courseName;
    private int amount;
    private List<String> nameList;

    public SelectionInfo() {
    }

    public SelectionInfo(int courseId, String courseName, int amount, List<String> nameList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.amount = amount;
        this.nameList = nameList;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public String getNames(){
        String s = null;
        if(CollectionUtil.isEmpty(nameList)){
            s = "";
        }else{
            s = nameList.stream().collect(Collectors.joining("、"));
        }
        return s;
    }

    @Override
    public String toString() {
        return "SelectionInfo{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", amount=" + amount +
                ", nameList=" + nameList +
                '}';
    }
}
