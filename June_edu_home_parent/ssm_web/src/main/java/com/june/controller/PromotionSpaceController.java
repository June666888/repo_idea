package com.june.controller;

import com.june.domain.PromotionSpace;
import com.june.domain.ResponseResult;
import com.june.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /**
     * 广告位列表查询
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有广告位成功", allPromotionSpace);
        return responseResult;
    }

    /**
     * 添加&修改广告位
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId()==null){//添加
            promotionSpaceService.savePromotionSpace(promotionSpace);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加广告位成功", null);
            return responseResult;
        }else {//修改
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改广告位名称成功", null);
            return responseResult;
        }
    }

    /**
     * 回显广告位名称
     * 根据id查询广告位信息
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询具体广告位成功", promotionSpace);
        return responseResult;
    }
}
