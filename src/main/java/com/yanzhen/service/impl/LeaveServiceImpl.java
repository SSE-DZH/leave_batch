package com.yanzhen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.LeaveInfoMapper;
import com.yanzhen.model.LeaveInfo;
import com.yanzhen.model.TongJi;
import com.yanzhen.model.User;
import com.yanzhen.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveInfoMapper leaveInfoDao;

    @Override
    public PageInfo<LeaveInfo> queryPageLeave(int page, int pagesize, LeaveInfo info) {
        PageHelper.startPage(page,pagesize);
        List<LeaveInfo> list=leaveInfoDao.queryLeaveAll(info);
        PageInfo<LeaveInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean saveInfo(LeaveInfo leaveInfo) {
        int num= leaveInfoDao.insert(leaveInfo);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public LeaveInfo queryByID(Integer id) {
        return leaveInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateInfo(LeaveInfo info) {
        return leaveInfoDao.updateByPrimaryKey(info);
    }

    @Override
    public int deleteById(Integer id) {
        return leaveInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public LeaveInfo getLeaveInfoTaskId(String proId) {
        return leaveInfoDao.getLeaveInfoTaskId(proId);
    }

    @Override
    public List<TongJi> queryTongjiCounts() {
        return leaveInfoDao.queryTongjiCounts();
    }


}
