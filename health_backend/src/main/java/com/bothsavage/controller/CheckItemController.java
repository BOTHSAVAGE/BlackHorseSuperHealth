package com.bothsavage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bothsavage.constant.MessageConstant;
import com.bothsavage.service.CheckItemService;
import com.bothsavage.entity.PageResult;
import com.bothsavage.entity.QueryPageBean;
import com.bothsavage.entity.Result;
import com.bothsavage.pojo.CheckItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查项管理
 */

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference//查找服务 todo 这个是在dubbo去找
    private CheckItemService checkItemService;

    //新增检查项
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){//todo RequestBody 解析json为对象
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return  new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    //检查项分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(queryPageBean);
        return pageResult;
    }

    //删除检查项
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkItemService.deleteById(id);
        }
        catch (Exception e){
            //服务调用失败
            e.printStackTrace();
            if(e.getMessage().contains("DataIntegrityViolationException")){
                return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL_RELATION);
            }
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return  new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //编辑检查项
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try{
            checkItemService.edit(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return  new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            CheckItem checkItem = checkItemService.findById(id);
            return  new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<CheckItem> list = checkItemService.findAll();
            return  new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
