package com.bothsavage.dao;

import com.bothsavage.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
     void add(CheckGroup checkGroup);
     void setCheckGroupAndCheckItem(Map map);
     Page<CheckGroup> findByCondition(String queryString);
     CheckGroup findById(Integer id);
     List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
     void edit(CheckGroup checkGroup);
     void deleteAssocication(Integer id);
     List<CheckGroup> findAll();
     void deleteById(Integer id);
}
