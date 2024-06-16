package com.yanzhen.controller;

import com.yanzhen.model.RoleInfo;
import com.yanzhen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleAll")
    public List<RoleInfo> queryRoleAll(){
         return roleService.queryRoleAll();
    }
}
