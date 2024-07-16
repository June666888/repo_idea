package com.june.dao;

import com.june.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 用户分页&多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(@Param("id") int id, @Param("status") String status);

    /**
     * 用户登录方法
     *  根据用户名查询具体的用户信息
     * @param user：参数封装了从前台页面传递过来的user信息
     */
    public User login(User user);

    /**
     * 1.根据用户id查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 根据用户id清空中间表的关联关系
     */
    public void deleteUserContextRole(Integer userId);

    /**
     * 为用户分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /**
     * 2.根据角色id查询父菜单
     * menu表中parent_id=-1的
     * @param ids：一个用户可以关联多个角色，这个参数代表了多个角色的id值（通过动态sql的foreach来遍历list集合，进行多值查询）
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 3.对父菜单关联的子菜单进行关联查询
     * 根据pid，查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /**
     * 4.获取资源信息
     * 用户与资源没用直接关系，是根据角色信息进行资源获取
     * @param ids：一个用户可以关联多个角色，这个参数代表了多个角色的id值（通过动态sql的foreach来遍历list集合，进行多值查询）
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

    public List<Resource> findResourceByRoleId2(List<Integer> ids);

    public void test11();
    public void test21();
    public void test31();
    public void test41();
    public void test51();
    public void test61();
    public void test71();
}
