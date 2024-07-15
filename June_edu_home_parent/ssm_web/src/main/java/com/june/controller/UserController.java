package com.june.controller;

import com.github.pagehelper.PageInfo;
import com.june.domain.ResponseResult;
import com.june.domain.Role;
import com.june.domain.User;
import com.june.domain.UserVo;
import com.june.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户分页&多条件组合查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        return new ResponseResult(true,200,"分页多条件查询成功",pageInfo);
    }

    /**
     * 修改用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id,String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status="DISABLE";
        }else {
            status="ENABLE";
        }

        userService.updateUserStatus(id,status);

        return new ResponseResult(true,200,"修改用户状态成功",status);
    }

     /**
     * 用户登录方法
     *  根据用户名查询具体的用户信息
     * @param user：参数封装了从前台页面传递过来的user信息
     */
    @RequestMapping("/login")
    //GET请求，不用加上@RequestBody
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {//前台传递的是用户名和密码，那么可以直接使用一个User对象来接收
        User user1 = userService.login(user);
        if (user1!=null){
            //1.保存用户id及access_token到session中
            //当用户下一次发送请求时，会携带一个请求头，就是生成的access_token的值。获取access_token的值以及从session中取出access_token的值做对比，一致的话才能允许进一步操作
            HttpSession session = request.getSession();

            //用随机值生成一个access_token，保证不重复
            String access_token= UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            //2.将查询出来的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());

            //将查询出来的user1，也存到map中，响应给前台
            //原因：在前台进行登出功能时，需要用到user1对象的其他信息
            map.put("user",user1);

            //根据接口文档，state改为1
            return new ResponseResult(true,1,"登录成功",map);
        }else {
            return new ResponseResult(true,400,"用户名密码错误",null);
        }
    }

    /**
     * 根据用户id查询关联的角色信息
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(int id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,400,"分配角色回显成功",roleList);
    }

    /**
     * 为用户分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,400,"为用户分配角色成功",null);
    }

    /**
     * 获取用户权限，动态菜单显示
     * @param request：不需要声明请求参数。根据需求分析知，前台发送的请求没有拼接任何的请求参数
     * @return：返回值还是ResponseResult
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        //2.获取session中的token
        //之前的登录功能，把token存放到session中时用到的key值就是access_token，这里就是再进行获取
        String session_token = (String)request.getSession().getAttribute("access_token");

        //3.判断token是否一致
        if (header_token.equals(session_token)){//是同一个用户
            //获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            //调用service进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        }else {
            return new ResponseResult(false,400,"获取用户权限信息失败",null);
        }
    }

}
