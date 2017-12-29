package org.forten.course.bo;

import org.forten.HibernateDao;
import org.forten.course.dto.ro.MessageForEasyUI;
import org.forten.course.dto.ro.PagedRoForEasyUI;
import org.forten.course.dto.vo.CourseForShow;
import org.forten.course.entity.Course;
import org.forten.dto.Message;
import org.forten.dto.PageInfo;
import org.forten.utils.common.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseBo")
public class CourseBo {
    @Resource
    private HibernateDao dao;

    @Transactional
    public PagedRoForEasyUI<CourseForShow> queryAll(int pageNo, int pageSize) {
        String countHql = "SELECT count(id) FROM Course";
        String hql = "SELECT new org.forten.course.dto.vo.CourseForShow(id,name,teacher,teachTime,credit) FROM Course ORDER BY id DESC";
        long count = dao.findOneBy(countHql);
        if (count == 0) {
            return new PagedRoForEasyUI<>(new ArrayList<>(), count);
        }
        PageInfo pageInfo = PageInfo.getInstance(pageNo, pageSize, (int) count);

        List<CourseForShow> list = dao.findBy(hql, new HashMap<>(0), pageInfo.getFirst(), pageInfo.getPageSize());
        return new PagedRoForEasyUI<>(list, count);
    }

    @Transactional(readOnly = true)
    public PagedRoForEasyUI<CourseForShow> queryBy(String name, int credit, int page, int rows) {
        Map<String, Object> params = new HashMap<>();
        String countHql = "SELECT count(id) FROM Course WHERE 1=1 ";
        String hql = "SELECT new org.forten.course.dto.vo.CourseForShow(id,name,teacher,teachTime,credit) FROM Course WHERE 1=1 ";

        if (StringUtil.hasText(name)) {
            countHql = countHql + "AND name LIKE :n ";
            hql = hql + "AND name LIKE :n ";
            params.put("n", "%" + name + "%");
        }
        if (credit > 0) {
            countHql = countHql + "AND credit LIKE :c ";
            hql = hql + "AND credit LIKE :c ";
            params.put("c", credit);
        }

        long count = dao.findOneBy(countHql, params);
        if (count == 0) {
            return new PagedRoForEasyUI<>(new ArrayList<>(), 0);
        }

        hql = hql + "ORDER BY id DESC";

        PageInfo pageInfo = PageInfo.getInstance(page, rows, (int) count);

        List<CourseForShow> dataList = dao.findBy(hql, params, pageInfo.getFirst(), rows);

        return new PagedRoForEasyUI<>(dataList, count);
    }

    @Transactional
    public MessageForEasyUI doSave(Course course) {
        try {
            dao.save(course);
            return new MessageForEasyUI(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageForEasyUI(false, "保存失败");
        }
    }

    @Transactional
    public Message doDelete(int... ids) {
        try {
            for (int id : ids) {
                dao.delete(Course.class, id);
            }
            return Message.info("成功删除了"+ids.length+"条数据");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("删除失败");
        }
    }

    @Transactional
    public Message doUpdate(CourseForShow vo){
        try {
            Course course = dao.loadById(Course.class, vo.getId());
            BeanUtils.copyProperties(vo, course);
            return Message.info("数据更新成功");
        }catch(Exception e){
            e.printStackTrace();
            return Message.error("数据更新失败");
        }
    }
}
