package org.forten.course.bo;

import org.forten.BaseTest;
import org.forten.course.dto.vo.CourseForShow;
import org.forten.course.dto.vo.SelectionInfo;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class CourseBoTest extends BaseTest {
    @Resource
    private CourseBo bo;

    @Test
    public void testQuerySelectionInfo() throws Exception {
        List<SelectionInfo> list = bo.querySelectionInfo();
        assertNotNull(list);
        assertEquals(9, list.size());

        list.forEach(System.out::println);

        list.forEach((e) -> System.out.println(e.getNames()));
    }

    @Test
    public void testQuerySelectedCourse(){
        List<CourseForShow> list = bo.querySelectedCourse(2);
        assertNotNull(list);
        assertEquals(4,list.size());

        list.forEach(System.out::println);
    }

    @Test
    public void testQueryNotSelectedCourse(){
        List<CourseForShow> list = bo.queryNotSelectedCourse(2);
        assertNotNull(list);
        assertEquals(5,list.size());

        list.forEach(System.out::println);
    }
}