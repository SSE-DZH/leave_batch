package com.yanzhen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.DeptInfoMapper;
import com.yanzhen.model.DeptInfo;
import com.yanzhen.model.Node;
import com.yanzhen.service.DeptService;
import com.yanzhen.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptInfoMapper deptDao;
    @Override
    public PageInfo<DeptInfo> queryDeptAll(int page, int pageSize, DeptInfo info) {
        PageHelper.startPage(page,pageSize);
        List<DeptInfo> list=deptDao.queryDeptAll(info);
        PageInfo<DeptInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean saveInfo(DeptInfo info) {
        int num= deptDao.insert(info);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        DeptInfo info=deptDao.selectByPrimaryKey(id);
        int num=0;
        if(info.getType()==2){//院系
            List<DeptInfo> list=deptDao.queryByFID(id);
            for(DeptInfo d:list){
               num+= deptDao.deleteByPrimaryKey(d.getId());
            }
            num+= deptDao.deleteByPrimaryKey(id);
        }else{
            num+= deptDao.deleteByPrimaryKey(id);//如果是班级
        }
       if(num>0){
           return true;
       }
       return  false;
    }

    @Override
    public boolean updateInfo(DeptInfo info) {
        int num= deptDao.updateByPrimaryKey(info);
        if(num>0){
            return true;
        }
        return  false;
    }

    @Override
    public List<Node> queryDeptTree() {
        List<Node> list = deptDao.queryDeptTree();
        TreeUtil util=new TreeUtil();
        List<Node> treeList=util.build(list);
        return treeList;
    }


}
