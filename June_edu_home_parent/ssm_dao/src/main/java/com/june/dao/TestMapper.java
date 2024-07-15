package com.june.dao;

import com.june.domain.Test;

import java.util.List;

public interface TestMapper {
    /**
     * 对test表进行查询所有
     *      因为ssm_dao依赖了ssm_domain，所以可以直接使用ssm_domain里定义的Test实体类
     */
    public List<Test> findAllTest();
}
