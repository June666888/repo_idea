package com.june.service.impl;

import com.june.dao.TestMapper;
import com.june.domain.Test;
import com.june.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //生成该类实例存到IOC容器中，以方便Web层调用
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAllTest() {

        List<Test> allTest = testMapper.findAllTest();
        return allTest;
    }
}
