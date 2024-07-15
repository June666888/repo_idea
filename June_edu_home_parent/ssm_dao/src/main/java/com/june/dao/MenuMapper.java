package com.june.dao;

import com.june.domain.Menu;

import java.util.List;

public interface MenuMapper {
    /**
     * 查询所有父子菜单列表
     * 要把查询出的父级菜单封装到Menu实体中，把查询出的与父级菜单关联的子级菜单封装到Menu实体中的subMenuList属性上
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 菜单列表查询
     * 与上面的方法不同，这里不需要父子级关联关系
     */
    public List<Menu> findAllMenu();

    /**
     * 根据id查询菜单信息
     */
    public Menu findMenuById(Integer id);
}
