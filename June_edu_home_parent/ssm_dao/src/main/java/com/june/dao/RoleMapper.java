package com.june.dao;

import com.june.domain.Role;
import com.june.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    /**
     * 角色列表查询&条件查询
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色ID查询关联菜单ID
     * @return：[1,2,3,5]
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 根据roleId清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);

    /**
     * 为角色分配菜单信息
     *  添加操作
     * 向中间表role_menu_relation中添加记录，即在service层封装role_menu_relation实体，再传给dao层添加记录
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 删除角色
     */
    public void deleteRole(Integer roleId);
}
