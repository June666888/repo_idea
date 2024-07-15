package com.june.controller;

import com.june.domain.Course;
import com.june.domain.CourseVO;
import com.june.domain.ResponseResult;
import com.june.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController //组合注解：@Controller+@ResponseBody（与json有关）
@RequestMapping("/course") //请求映射路径
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition") //根据接口文档编写
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){ //前台发送过来的是json串，封装到VO实体中的话要使用注解
        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);//把前台传递过来的请求参数封装到VO实体中

        //200不是固定的，是后台和前台的一个协定
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);//list不能直接返回，要整成前台响应的json格式

        return responseResult;//转化为json响应给前台
    }

    /**
     * 课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断接收到的上传文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");//C:\apache-tomcat-8.5.55-windows-x64\apache-tomcat-8.5.55\webapps\ssm_web\

        //只想要前面部分
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));//C:\apache-tomcat-8.5.55-windows-x64\apache-tomcat-8.5.55\webapps\

        //3.获取原文件名  如：june.jpg
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名  peach.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传  tomcat的webapp下的upload文件夹
        String uploadPath=substring+"upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建目录："+filePath);
        }

        //图片就进行了真正上传
        file.transferTo(filePath);

        //6.将文件名和文件路径返回，进行响应
        Map<String,String> map=new HashMap<>();
        map.put("fileName",newFileName);
        //"filePath": "http://localhost:8080/upload/1597112871741.JPG"
        map.put("filePath","http://localhost:70/upload/"+newFileName);

        ResponseResult responseResult=new ResponseResult(true,200,"图片上传成功",map);

        return responseResult;
    }

    /**
     * 新建课程信息及讲师信息
     * 新增课程信息和修改课程信息要写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if (courseVO.getId()==null){//新增操作
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);//保存操作，后台无需向前台返回数据
            return responseResult;
        }else {//修改操作
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    /**
     * 根据id查询具体的课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){//GET请求不需要加上@RequestBody注解，@RequestBody是获取到请求体中的内容封装为实体/集合
        CourseVO courseVO = courseService.findCourseById(id);//封装了课程信息和讲师信息的对象

        ResponseResult responseResult = new ResponseResult(true, 200, "根据id查询课程信息成功", courseVO);

        return responseResult;
    }

    /**
     * 修改课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        //调用service，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id,status);

        //响应数据
        Map<String, Object> map=new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);

        return responseResult;
    }
}
