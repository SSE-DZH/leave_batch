package com.yanzhen.service;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.DeptInfo;
import com.yanzhen.model.Node;

import java.util.List;

public interface DeptService {
    PageInfo<DeptInfo> queryDeptAll(int page,int pageSize,DeptInfo info);

    /**
     * 添加
     */
    boolean saveInfo(DeptInfo info);

    /**
     * 删除
     */
    boolean deleteById(Integer id);

    /**
     * 修改
     */
    boolean updateInfo(DeptInfo info);


    /**
     * 查询树
     */
    List<Node> queryDeptTree();



}
