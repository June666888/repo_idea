package com.june.dao;

import com.june.domain.Course;
import com.june.domain.CourseVO;
import com.june.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    /**
     * 多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     * 新建课程信息
     * （保存操作不需要返回值）
     */
    public void saveCourse(Course course);

    /**
     * 新建讲师信息
     */
    public void saveTeacher(Teacher teacher);

    /**
     * 回显课程信息（根据id查询对应的课程信息及关联的讲师信息）
     * @return：只有CourseVO既可以封装课程信息，也可以封装讲师信息
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 更新课程信息
     * @param course：传递封装好的Course对象
     */
    public void updateCourse(Course course);

    /**
     * 更新讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     */
    public void updateCourseStatus(Course course);
}
