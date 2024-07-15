package com.june.service;

import com.june.domain.Test;

import java.util.List;

public interface TestService {
    /**
     * 对Test表进行查询所有
     */
    public List<Test> findAllTest();
}
