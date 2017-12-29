package org.forten.course.action;

import org.forten.course.bo.CourseBo;
import org.forten.course.dto.qo.AdminCourseQo;
import org.forten.course.dto.ro.MessageForEasyUI;
import org.forten.course.dto.ro.PagedRoForEasyUI;
import org.forten.course.dto.vo.CourseForShow;
import org.forten.course.dto.vo.SelectionInfo;
import org.forten.course.entity.Course;
import org.forten.dto.Message;
import org.forten.dto.PagedRo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CourseAction {
    @Resource
    private CourseBo bo;

    @RequestMapping("/admin/list")
    public @ResponseBody
    PagedRoForEasyUI<CourseForShow> list(String name, @RequestParam(defaultValue = "0") int credit,
                                         @RequestParam(name = "page", defaultValue = "1") int pageNo,
                                         @RequestParam(name = "rows", defaultValue = "3") int pageSize) {
        return bo.queryBy(name, credit, pageNo, pageSize);
    }

    /*
     * 因为Request中easyui的分页参数分别称为page和rows，对应我们的pageNo和pageSize
     * 所以在后台方法的参数列表中使用了@RequestParam的name属性，把前台参数名与后台参数名进行了绑定
     * 还使用了此注解的defaultValue为参数设置了默认值
     *
     * 当返回数据时，easyui除了需要数据集合以外，还需要符合查询条件的总数据量，
     * 所以我们把总数据量和数据集合封装在一个PagedRoForEasyUI<T>类型的对象中返回
     * 并且，描述总数据量的变量必须叫total，描述数据集合的变量必须叫rows
     */
    @RequestMapping("/admin/listAll")
    public @ResponseBody
    PagedRoForEasyUI<CourseForShow> list(@RequestParam(name = "page", defaultValue = "1") int pageNo,
                                         @RequestParam(name = "rows", defaultValue = "3") int pageSize) {
        return bo.queryAll(pageNo, pageSize);
    }

    @RequestMapping("/admin/save")
    public @ResponseBody
    MessageForEasyUI save(@RequestBody Course course) {
        return bo.doSave(course);
    }

    @RequestMapping("/admin/delete")
    public @ResponseBody
    Message delete(@RequestBody int... ids) {
        return bo.doDelete(ids);
    }

    @RequestMapping("/admin/update")
    public @ResponseBody
    Message update(@RequestBody CourseForShow vo) {
        return bo.doUpdate(vo);
    }

    @RequestMapping("/admin/getSelectionInfo")
    public @ResponseBody
    List<SelectionInfo> getSelectionInfo() {
        return bo.querySelectionInfo();
    }

}
