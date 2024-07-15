package com.june.controller;

import com.github.pagehelper.PageInfo;
import com.june.domain.Resource;
import com.june.domain.ResourceVo;
import com.june.domain.ResponseResult;
import com.june.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    public ResourceService resourceService;

    /**
     * 资源分页&多条件查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVo);

        return new ResponseResult(true,200,"资源分页多条件查询成功",pageInfo);
    }
}
