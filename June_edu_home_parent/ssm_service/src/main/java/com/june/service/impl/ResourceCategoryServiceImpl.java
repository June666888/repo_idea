package com.june.service.impl;

import com.june.dao.ResourceCategoryMapper;
import com.june.domain.ResourceCategory;
import com.june.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        return allResourceCategory;
    }
}
