package com.june.service;

import com.june.domain.Role;
import com.june.domain.RoleMenuVo;
import com.june.domain.Role_menu_relation;

import java.util.List;

public interface RoleService {
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
     * 为角色分配菜单信息
     * @param roleMenuVo：controller接收到请求参数后，会创建一个Vo对象，接收roleId和menuIdList，controller层调用service层时，也是传递的这个Vo对象
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     */
    public void deleteRole(Integer roleId);
}
