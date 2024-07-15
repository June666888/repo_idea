package com.june.service;

import com.github.pagehelper.PageInfo;
import com.june.domain.Resource;
import com.june.domain.ResourceVo;

import java.util.List;

public interface ResourceService {
    /**
     * 资源分页&多条件查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
