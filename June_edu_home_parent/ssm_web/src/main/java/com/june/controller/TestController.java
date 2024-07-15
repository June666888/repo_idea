package com.june.controller;

import com.june.domain.Test;
import com.june.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //@Controller+@ResponseBody（与json有关）
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/findAllTest")
    public List<Test> findAllTest(){
        List<Test> allTest = testService.findAllTest();
        return allTest;
    }
}
