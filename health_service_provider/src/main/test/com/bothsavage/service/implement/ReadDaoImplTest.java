package com.bothsavage.service.implement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-free.xml"})
public class ReadDaoImplTest {

    @Value("${out_put_path}")
    private String outPutPath;

    @Test
    public void getListTest(){
        System.out.println(outPutPath);
    }  
}