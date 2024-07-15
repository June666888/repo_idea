package com.june.controller;

import com.june.domain.*;
import com.june.service.MenuService;
import com.june.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 角色列表查询&条件查询
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询所有角色成功",allRole);
    }

    @Autowired
    private MenuService menuService;

    /**
     * 分配菜单的第一个接口：查询所有父子菜单列表
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        //查询所有父级子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);//父级菜单的parent_id都为-1
        //注意：虽然传递的是-1，第一次请求是查询所有父级菜单，但在封装的过程中会再次发起请求，再去执行第二次sql语句，把该父级菜单所关联的子级菜单也进行查询

        //响应数据（看接口文档的要求，要方便树形展示）
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"查询所有的父子级菜单成功",map);
    }

    /**
     * 分配菜单的第二个接口：根据角色ID查询关联菜单ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"查询角色关联的菜单信息成功",menuByRoleId);
    }

    /**
     * 分配菜单的第三个接口：为角色分配菜单信息
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"为角色分配菜单信息成功",null);
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功",null);
    }
}
