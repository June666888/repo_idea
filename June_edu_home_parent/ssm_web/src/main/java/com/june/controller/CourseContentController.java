package com.june.controller;

import com.june.domain.Course;
import com.june.domain.CourseLesson;
import com.june.domain.CourseSection;
import com.june.domain.ResponseResult;
import com.june.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        //调用service
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时内容查询成功", list);

        return responseResult;
    }


    /**
     * 回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);

        return responseResult;
    }

/*    *//**
     * 新增章节信息
     * 把新增和修改写在一个方法里，通过对获取到的id进行判断，来决定执行新增操作还是更新操作
     *//*
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        courseContentService.saveSection(courseSection);
        return new ResponseResult(true,200,"新增章节成功",null);
    }*/

    /**
     * 新增&修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        //判断是否携带了章节id
        if (courseSection.getId()==null){//新增
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true,200,"新增章节成功",null);
        }else {//修改
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"修改章节成功",null);
        }
    }

    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id,status);

        //数据响应
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true, 200, "修改章节状态成功", map);
    }

    /**
     * 新建课时信息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
        //判断是否携带了课时id
        if (courseLesson.getId()==null){//新增
            courseContentService.saveLesson(courseLesson);
            return new ResponseResult(true,200,"新增课时成功",null);
        }else {//修改
            courseContentService.updateLesson(courseLesson);
            return new ResponseResult(true,200,"修改课时成功",null);
        }
    }
}
