package com.bothsavage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bothsavage.service.OrderService;
import com.bothsavage.service.SetmealService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml"})
public class ReportControllerTest {
    @Reference
    private SetmealService setmealService;
    @Reference
    private OrderService orderService;

    @Test
    public void getMemberReport() {
        List<Map<String,Object>> setmealCount = setmealService.findSetmealCount();
        System.out.println(setmealCount);
    }

    @Test
    public void findOrderById() {
        try {
            Map byId = orderService.findById(17);
            System.out.println(byId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void findAll() {
        try {
            List all = orderService.findAll();
            System.out.println(all);
            System.out.println(all.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}