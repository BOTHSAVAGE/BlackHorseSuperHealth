package com.bothsavage.dao;

import com.bothsavage.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

//todo 通过动态代理的方式，产生的实现类
public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);
    public long findCountByCheckItemId(Integer id);
    public void deleteById(Integer id);
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);
    public List<CheckItem> findAll();
}
