package com.bothsavage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bothsavage.entity.PageResult;
import com.bothsavage.entity.QueryPageBean;
import com.bothsavage.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 体检预约处理
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;



    //检查项分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = orderService.pageQuery(queryPageBean);
        return pageResult;
    }


}
