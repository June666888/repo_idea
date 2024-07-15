package com.june.controller;

import com.github.pagehelper.PageInfo;
import com.june.domain.PromotionAd;
import com.june.domain.PromotionAdVO;
import com.june.domain.ResponseResult;
import com.june.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllAdByPage(promotionAdVO);

        return new ResponseResult(true, 200, "广告分页查询成功", pageInfo);
    }

    /**
     * 图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
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
     * 广告状态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(int id, int status) {
        promotionAdService.updatePromotionAdStatus(id,status);

        //数据响应
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult=new ResponseResult(true,200,"广告动态上下线成功",map);

        return responseResult;
    }

    /**
     * 新建&修改广告
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if (promotionAd.getId()==null){
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新增广告成功",null);
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"修改广告成功",null);
        }
    }

    /**
     * 回显广告信息
     * （根据id查询广告信息）
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        return new ResponseResult(true,200,"查询广告信息成功",promotionAd);
    }
}
