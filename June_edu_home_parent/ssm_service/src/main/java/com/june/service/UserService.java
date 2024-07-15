package com.june.service;

import com.github.pagehelper.PageInfo;
import com.june.domain.*;

import java.util.List;

public interface UserService {
    /**
     * 用户分页&多条件组合查询
     */
    public PageInfo<User> findAllUserByPage(UserVo userVo);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(int id, String status);

    /**
     * 用户登录方法
     *  根据用户名查询具体的用户信息
     * @param user：参数封装了从前台页面传递过来的user信息
     */
    public User login(User user) throws Exception;

    /**
     * 根据用户id查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 为用户分配角色
     */
    public void userContextRole(UserVo userVo);

    /**
     * 获取用户权限，动态菜单显示
     * @param id：在controller中，判断完access_token后，会从session中取出之前登录的用户id，调用service方法时，把这个用户id进行传递
     * @return：表示对该对象的封装，在service层中完成。也可以在service层中直接返回一个Map，返回给controller层，在controller层中封装ResponseResult对象
     */
    public ResponseResult getUserPermissions(Integer id);

}
