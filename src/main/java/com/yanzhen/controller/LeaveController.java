package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.LeaveInfo;
import com.yanzhen.model.TongJi;
import com.yanzhen.model.User;
import com.yanzhen.service.LeaveService;
import com.yanzhen.service.UserService;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private UserService userService;

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;


    @RequestMapping("/queryLeaveAll")
    public JsonObject queryLeaveAll(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "15")Integer limit,
                                   LeaveInfo leaveInfo,HttpSession session){
        JsonObject jsonObject=new JsonObject();
        User user= (User) session.getAttribute("user");
        if(user.getRoleName().equals("学生")){
            leaveInfo.setUserId(user.getId());
        }
        PageInfo<LeaveInfo> pageInfo = leaveService.queryPageLeave(page, limit, leaveInfo);
        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());
        return  jsonObject;
    }


    @RequestMapping("/saveInfo")
    public R saveInfo(@RequestBody LeaveInfo leaveInfo, HttpServletRequest request){
        //获取登录账号userid
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            leaveInfo.setUserId(user.getId());
            leaveInfo.setState(0);//未提交
        }
        boolean b = leaveService.saveInfo(leaveInfo);
        if(b){
            return R.ok();
        }
        return R.fail("添加失败");
    }


    /**
     * 提交信息操作
     */
    @RequestMapping("/startApply")
    public R saveSumit(HttpServletRequest request,String leaveId){
        //获取到登录用户
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        //获取用户的
        Integer userId=user.getId();
        //获取当前用户的班主任 系主任  院长
        Map<String,Object> variables=new HashMap<String,Object>();
        variables.put("leaveId",leaveId);
        //根据id查询请假天数
        LeaveInfo info=leaveService.queryByID(Integer.parseInt(leaveId));
        variables.put("day",info.getLeaveDays());
        variables.put("student",user.getUsername());
        //根据当前登录账号获取办班主任
        User user1=userService.queryUserInfoByTypeAndRoleAndDeptId("3","班主任",user.getDeptId().toString());
        variables.put("teacher",user1.getUsername());
        //系主任
        User user2=userService.queryUserInfoByTypeAndRoleAndDeptId("2","系主任",user.getDeptId().toString());
        variables.put("manager",user2.getUsername());

        //院长
        User user3=userService.queryUserInfoByTypeAndRoleAndDeptId("1","院长",user.getDeptId().toString());
        variables.put("yz",user3.getUsername());

        //启动流程
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myLeave",variables);
        //根据流程实例的id查询任务
        Task task=taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        taskService.complete(task.getId());

        //修改提交状态请假
        info.setState(1);
        info.setUserId(user.getId());
        info.setProcessinstanceid(processInstance.getProcessInstanceId());
        int num=leaveService.updateInfo(info);
        if(num>0){
            return R.ok();
        }
        return R.fail("提交失败");
    }


    /**
     * 删除请假申请
     */
    @RequestMapping("/deleteByIds")
    public R deleteByIds(Integer id){
       int num=leaveService.deleteById(id);
       if(num>0){
           return R.ok();
       }
       return R.fail("失败");
    }


    /**
     * 前端统计分析接口
     */
    @RequestMapping("/queryTongJi")
    public List<TongJi> queryTongJi(){
        List<TongJi> list= leaveService.queryTongjiCounts();
        return list;
    }



}
