package com.yanzhen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.UserMapper;
import com.yanzhen.model.User;
import com.yanzhen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
   @Autowired
   private UserMapper userDao;
    @Override
    public PageInfo<User> queryPageUser(int page, int pagesize, User user) {
        PageHelper.startPage(page,pagesize);
        List<User> list=userDao.queryAll(user);
        PageInfo<User> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean saveInfo(User user) {
        int num = userDao.insert(user);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateInfo(User user) {
        int num = userDao.updateByPrimaryKey(user);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        int num = userDao.deleteByPrimaryKey(id);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public User queryById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User queryUserByUserNameAndPwdAndType(String username, String password, String roleName) {
        return userDao.queryUserByUserNameAndPwdAndType(username,password,roleName);
    }

    @Override
    public User queryUserInfoByTypeAndRoleAndDeptId(String type, String role, String deptId) {
        return userDao.queryUserInfoByTypeAndRoleAndDeptId(type,role,deptId);
    }

    @Override
    public int updatePassword(User user) {
        return userDao.updatePassword(user);
    }
}
