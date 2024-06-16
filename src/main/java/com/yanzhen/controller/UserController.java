package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.User;
import com.yanzhen.service.UserService;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryUserAll")
    public JsonObject queryUserAll(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "15")Integer limit,
                                   User user){
        JsonObject jsonObject=new JsonObject();
        PageInfo<User> pageInfo = userService.queryPageUser(page, limit, user);
        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());
        return  jsonObject;
    }


    @RequestMapping("/queryUserAll2")
    public JsonObject queryUserAll2(HttpSession session){
        User user= (User) session.getAttribute("user");
        PageInfo<User> pageInfo=userService.queryPageUser(1, 1, user);
        JsonObject jsonObject=new JsonObject();
        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());

        return  jsonObject;
    }





    /**
     * 新增接口
     */
    @RequestMapping("/saveInfo")
    public R saveInfo(@RequestBody  User user){
        user.setPassword("1");
        boolean b = userService.saveInfo(user);
        if(b){
            return R.ok();
        }
        return R.fail("添加失败");
    }

    /**
     * 修改接口
     */
    @RequestMapping("/updateInfo")
    public R updateInfo(@RequestBody  User user){
        boolean b = userService.updateInfo(user);
        if(b){
            return R.ok();
        }
        return R.fail("修改失败");
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){
        //ids 转成字符串数组
        List<String> strings = Arrays.asList(ids.split(","));
        for(String id:strings){
            userService.deleteById(Integer.parseInt(id));
        }
        return R.ok();
    }


    /**
     * 修改密码
     */
    @RequestMapping("/updatePassword")
    public R updatePassword(@RequestBody User user){
        //调用service接口实现修改密码
      int num= userService.updatePassword(user);
      if(num>0){
          return R.ok();
      }
      return R.fail("修改失败");
    }


}
