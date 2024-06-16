package com.yanzhen.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/***
 * 树结构节点
 */
public class Node {
    private Integer id;
    private Integer parentId;
    private String name;
    private Boolean open=false;//默认折叠
    List<Node> children= new ArrayList<>();;
    private Boolean checked=false;//是否选中

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public Boolean getOpen() {
        return open;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Boolean getChecked() {
        return checked;
    }
}
