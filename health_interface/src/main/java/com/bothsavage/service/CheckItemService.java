package com.bothsavage.service;

import com.bothsavage.entity.PageResult;
import com.bothsavage.entity.QueryPageBean;
import com.bothsavage.pojo.CheckItem;

import java.util.List;

/**
 * 检查项操作接口
 **/
public interface CheckItemService {
    void add(CheckItem checkitem);
    PageResult pageQuery(QueryPageBean queryPageBean);
    void deleteById(Integer id);
    void edit(CheckItem checkitem);
    CheckItem findById(Integer id);
    List<CheckItem> findAll();
}