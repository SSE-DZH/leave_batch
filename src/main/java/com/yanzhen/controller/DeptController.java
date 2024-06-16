package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.DeptInfo;
import com.yanzhen.service.DeptService;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/queryAll")
    public JsonObject queryAll(DeptInfo info ,
                        @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "15") Integer pageSize){
        JsonObject object=new JsonObject();
        PageInfo<DeptInfo> pageInfo=deptService.queryDeptAll(page,100,info);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;

    }

    /**
     * 添加实现
     */
    @RequestMapping("/saveInfo")
    public R saveInfo(@RequestBody  DeptInfo deptInfo){
         deptInfo.setType(deptInfo.getType()+1);
         boolean bs= deptService.saveInfo(deptInfo);
         if(bs){
             return R.ok();
         }
         return R.fail("添加失败");
    }

    /**
     * 修改实现
     */
    @RequestMapping("/updateInfo")
    public R updateInfo(@RequestBody  DeptInfo deptInfo){
        boolean bs= deptService.updateInfo(deptInfo);
        if(bs){
            return R.ok();
        }
        return R.fail("修改失败");
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteById")
    public R deleteById(int id){
        boolean bs=deptService.deleteById(id);
        if(bs){
            return R.ok();
        }
        return R.fail("删除失败");
    }


    @RequestMapping("/queryDeptTree")
    public List queryDeptTree(){
      return deptService.queryDeptTree();
    }

}
