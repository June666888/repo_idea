package com.june.service;

import com.june.domain.Course;
import com.june.domain.CourseLesson;
import com.june.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    /**
     * 根据课程id查询关联的章节信息及章节信息关联的课时信息方法
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 回显章节对应的课程信息
     * （根据课程id查询课程信息）
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 新增章节信息
     */
    public void saveSection(CourseSection courseSection);

    /**
     * 修改章节信息
     */
    public void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     */
    public void updateSectionStatus(int id,int status);

    /**
     * 新建课时信息
     */
    public void saveLesson(CourseLesson courseLesson);

    /**
     * 修改课时信息
     */
    public void updateLesson(CourseLesson courseLesson);
}
