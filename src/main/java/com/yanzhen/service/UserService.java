package com.yanzhen.service;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.User;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {

    /**
     * 分页查询用户列表
     */
    PageInfo<User> queryPageUser(int page,int pagesize,User user);

    /**
     * 添加方法
     */
    boolean saveInfo(User user);

    /**
     * 修改
     */
    boolean updateInfo(User user);

    /**
     * 删除
     */
    boolean deleteById(Integer id);


    /**
     * 根据id查询记录对象
     */
    User queryById(Integer id);


    /**
     * 登录验证 根据用户名 密码以及角色验证
     * @param username 用户名称
     * @param password 密码
     * @param roleName 角色名称
     * @return
     */
    User queryUserByUserNameAndPwdAndType(String username,
                                           String password,
                                          String roleName);

    /**
     * 根用户的类型 角色  组织结构的id 获取用户对象
     */
    User queryUserInfoByTypeAndRoleAndDeptId(String type
                             ,String role,String deptId);


    //修改密码
    int updatePassword(User user);

}
