package com.june.service.impl;

import com.june.dao.RoleMapper;
import com.june.domain.Role;
import com.june.domain.RoleMenuVo;
import com.june.domain.Role_menu_relation;
import com.june.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //1.清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());//这个方法需要一个roleId，这个roleId会在前台页面传递后封装到RoleMenuVo实体对象中

        //2.为角色分配菜单
        //遍历RoleMenuVo实体对象的menuIdList，遍历出来的每一个值都是在页面中选中的菜单id
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //补充信息
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            //封装数据：获取role_menu_relation对象的属性，来完成添加操作
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        //调用 根据roleId清空中间表的关联关系 方法
        roleMapper.deleteRoleContextMenu(roleId);

        roleMapper.deleteRole(roleId);
    }
}
