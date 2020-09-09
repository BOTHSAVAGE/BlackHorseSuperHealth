package com.bothsavage.service;

import com.bothsavage.entity.PageResult;
import com.bothsavage.entity.QueryPageBean;
import com.bothsavage.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public List<Setmeal> findAll();
    public Setmeal findById(int id);
    public List<Map<String,Object>> findSetmealCount();
}