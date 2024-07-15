package com.june.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class UserVo {
    private Integer currentPage;
    private Integer pageSize;

    //多条件查询：用户名（手机号）
    private String username;//对应数据库表中的name字段

    //注册起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")//指定前台传递的日期字符串的格式
    private Date startCreateTime;

    //注册结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    //请求体中有roleIdList和userId，添加这两个属性，接收由前台发送过来的请求参数
    private List<Integer> roleIdList;//要和接口文档的对应上
    private Integer userId;

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
