package com.june.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.june.dao.ResourceMapper;
import com.june.domain.Resource;
import com.june.domain.ResourceVo;
import com.june.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override//把返回值List<Resource>改成PageInfo<Resource>
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo) {
        //分页查询
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());

        List<Resource> allResourceByPage = resourceMapper.findAllResourceByPage(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(allResourceByPage);

        return pageInfo;
    }
}
