package com.bothsavage.service;

import com.bothsavage.entity.PageResult;
import com.bothsavage.entity.QueryPageBean;
import com.bothsavage.entity.Result;

import java.util.List;
import java.util.Map;

/**
 * 订单操作接口
 */
public interface OrderService {
     Result order(Map map) throws Exception;
     Map findById(Integer id) throws Exception;
     List<Map> findAll() throws Exception;

    PageResult pageQuery(QueryPageBean queryPageBean);
}
