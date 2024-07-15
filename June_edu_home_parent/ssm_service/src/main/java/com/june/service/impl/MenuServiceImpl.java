package com.june.service.impl;

import com.june.dao.MenuMapper;
import com.june.domain.Menu;
import com.june.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);//这个查询结果不仅有父级菜单信息，还有父级菜单信息所关联的子级菜单信息

        return subMenuListByPid;
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    @Override
    public Menu findMenuById(Integer id) {
        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }
}
