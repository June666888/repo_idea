package com.june.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.june.dao.UserMapper;
import com.june.domain.*;
import com.june.service.UserService;
import com.june.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);

        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id,status);
    }

    @Override
    public User login(User user) throws Exception {
        User user2 = userMapper.login(user);//从数据库中查出来的user2，包含了密文密码

        if (user2!=null&& Md5.verify(user.getPassword(),"june",user2.getPassword())){//这个user是从前台传递过来的
            return user2;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //1.根据用户id清空中间表的关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        //2.为用户分配角色（重新建立关联关系）
        for (Integer roleId : userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);

            //补充信息
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            //向中间表插入数据
            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        //1.根据用户id查询关联的角色信息
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        //2.获取角色id，保存到List集合中
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());//只需要用到角色信息的id属性，角色与菜单/资源的关系都是维护在中间表的
        }

        //3.根据角色id查询父菜单
        if (roleIds.isEmpty()) {
            // 如果roleIds为空，抛出异常或返回错误响应
            return new ResponseResult(false, 400, "用户没有关联的角色", null);
        }
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);//传递的就是封装好的roleIds了

        //4.再对父菜单关联的子菜单进行关联查询、封装
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());//传递的是父菜单的id值

            //要把查询出来的子菜单存放到Menu实体的subMenuList属性上
            menu.setSubMenuList(subMenu);
        }

        //5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //6.封装数据并进行返回
        //这一步要看接口文档
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);//parentMenu已经在前面被封装好了
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }
}
