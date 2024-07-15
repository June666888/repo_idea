package com.june.service.impl;

import com.june.dao.CourseMapper;
import com.june.domain.Course;
import com.june.domain.CourseVO;
import com.june.domain.Teacher;
import com.june.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> list = courseMapper.findCourseByCondition(courseVO);
        return list;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course=new Course();

        //把CourseVO中名称相同的属性封装到Course中
        BeanUtils.copyProperties(course,courseVO);

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程
        courseMapper.saveCourse(course);//一旦这句代码执行完成，Course实体就有对应的id值了

        //获取新插入数据的id值
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);//外键

        //保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {
        CourseVO courseVO = courseMapper.findCourseById(id);

        return courseVO;
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course=new Course();
        BeanUtils.copyProperties(course,courseVO);

        //补全信息
        Date date=new Date();
        course.setUpdateTime(date);

        //更新课程信息
        courseMapper.updateCourse(course);

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        //补全信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        //更新讲师信息
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int courseId, int status) {
        //1.封装数据
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        //2.调用mapper
        courseMapper.updateCourseStatus(course);
    }
}