package com.yanzhen.service.impl;

import com.yanzhen.dao.RoleInfoMapper;
import com.yanzhen.model.RoleInfo;
import com.yanzhen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleInfoMapper roleDao;
    @Override
    public List<RoleInfo> queryRoleAll() {
        return roleDao.queryRoleAll();
    }
}
