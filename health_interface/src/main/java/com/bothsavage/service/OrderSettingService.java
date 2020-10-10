package com.bothsavage.service;

import com.bothsavage.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * 预定操作接口
 */
public interface OrderSettingService {
    public void add(List<OrderSetting> data);
    public List<Map> getOrderSettingByMonth(String date);
    public void editNumberByDate(OrderSetting orderSetting);
}
