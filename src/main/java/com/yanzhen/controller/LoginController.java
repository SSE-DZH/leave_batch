package com.yanzhen.controller;

import com.yanzhen.model.User;
import com.yanzhen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/loginIn")
    public Map loginIn(User user , HttpServletRequest request){
        Map map=new HashMap();
        HttpSession session=request.getSession();
        User u= userService.queryUserByUserNameAndPwdAndType(user.getUsername(),user.getPassword(),user.getRoleName());
        if(u!=null){
             session.setAttribute("user",u);
             map.put("code",200);
             map.put("user",user);
             map.put("username",u.getUsername());
             return map;
        }else{
            map.put("code",400);
            map.put("msg","用户名或密码错误");
            return map;
        }

    }

}
