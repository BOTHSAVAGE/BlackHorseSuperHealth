package com.bothsavage.service;

import com.bothsavage.entity.Result;

import java.util.Map;

/**
 * 订单操作接口
 */
public interface OrderService {
    public Result order(Map map) throws Exception;
    public Map findById(Integer id) throws Exception;
}
