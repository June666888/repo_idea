package com.june.dao;

import com.june.domain.Resource;
import com.june.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {
    /**
     * 资源分页&多条件查询
     */
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
