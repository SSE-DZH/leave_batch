package com.yanzhen.service;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.LeaveInfo;
import com.yanzhen.model.TongJi;
import com.yanzhen.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveService {

    /**
     * 分页查询请假列表
     */
    PageInfo<LeaveInfo> queryPageLeave(int page, int pagesize, LeaveInfo info);

    /**
     * 添加方法
     */
    boolean saveInfo(LeaveInfo leaveInfo);


    /**
     * 根据id查询对象
     */
    LeaveInfo queryByID(Integer id);


    /**
     * 修改方法
     */
    int updateInfo(LeaveInfo info);

    /**
     * 删除
     */
    int deleteById(Integer id);


    /**
     * 根据流程id获取请假记录信息
     */
    LeaveInfo getLeaveInfoTaskId( String proId);

    /**
     * 根据所在的班级进行统计
     * @return
     */
    List<TongJi> queryTongjiCounts();


}
