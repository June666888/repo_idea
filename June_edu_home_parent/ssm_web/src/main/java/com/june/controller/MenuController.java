package com.june.controller;

import com.june.domain.Menu;
import com.june.domain.ResponseResult;
import com.june.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 菜单列表查询
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true,200,"查询所有菜单信息成功",allMenu);
    }

/*    *//**
     * 回显菜单信息
     *//*
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        //根据id判断当前是更新还是添加操作 判断id是否为-1
        if (id==-1){//添加
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据  由接口文档知要利用Map
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);//添加 回显信息中不需要查询menu信息
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加回显成功",map);
        }else {//修改 回显menu的所有信息
            Menu menu = menuService.findMenuById(id);

            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据  由接口文档知要利用Map
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);//这里的区别
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"修改回显成功",map);
        }
    }*/
    /**
     * 回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        //根据id判断当前是更新还是添加操作 判断id是否为-1
        if (id==-1){//添加
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加回显成功",map);
        }else {//修改
            Menu menu = menuService.findMenuById(id);

            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"修改回显成功",map);
        }
    }
}
