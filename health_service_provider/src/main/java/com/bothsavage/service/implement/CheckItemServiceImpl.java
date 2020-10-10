package com.bothsavage.service.implement;


import com.alibaba.dubbo.config.annotation.Service;
import com.bothsavage.dao.CheckItemDao;
import com.bothsavage.entity.PageResult;
import com.bothsavage.entity.QueryPageBean;
import com.bothsavage.pojo.CheckItem;
import com.bothsavage.service.CheckItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)//TODO （如果有事务，那么要指定接口）dubbo注册，这里使用到的是dubbo的注解
@Transactional //todo 可能涉及到事务
public class CheckItemServiceImpl implements CheckItemService {
    //注入DAO对象
    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //检查项分页查询
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件完成


        PageHelper.startPage(currentPage,pageSize);//todo 分页助手（第几页，行数）
        //select * from t_checkitem limit 0,10
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();//todo 分页助手api

        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }

    //根据ID删除检查项
    @Override
    public void deleteById(Integer id) {
        //判断当前检查项是否已经关联到检查组
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项已经被关联到检查组，不允许删除
            new RuntimeException();
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
