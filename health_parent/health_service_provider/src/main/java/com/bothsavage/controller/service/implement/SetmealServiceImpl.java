package com.bothsavage.controller.service.implement;

import com.alibaba.dubbo.config.annotation.Service;
import com.bothsavage.controller.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.bothsavage.constant.RedisConstant;
import com.bothsavage.dao.SetmealDao;
import com.bothsavage.entity.PageResult;
import com.bothsavage.entity.QueryPageBean;
import com.bothsavage.pojo.Setmeal;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 体检套餐服务
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService{
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${out_put_path}")
    private String outPutPath;//todo EL表达式别忘了

    //新增套餐信息，同时需要关联检查组
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        this.setSetmealAndCheckgroup(setmealId,checkgroupIds);
        //将图片名称保存到Redis集合中
        String fileName = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,fileName);

        //当添加完成需要重新来生成静态页面
    }

    //用于生成静态页面的通用方法
    public void generateHtml(String templateName,String htmlPageName,Map map){
        //拿到配置对象
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        //声明字符串writer
        Writer out = null;
        try {
            //得到模板
            Template template = configuration.getTemplate(templateName);
            //构建输出流 todo 这里使用的是filewriter 会把重名的覆盖掉
            out = new FileWriter(new File(outPutPath+"/"+htmlPageName));
            //输出文件
            template.process(map,out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    //生成当前方法所需要的静态页面
    public void generateMobileStaticHtml(){
        //在生成静态页面之前需要查询数据
        List<Setmeal> list = setmealDao.findAll();

        //todo 没写完自己去写
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    //根据套餐ID查询套餐详情（套餐基本信息、套餐对应的检查组信息、检查组对应的检查项信息）
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    //查询套餐预约占比数据
    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setmealDao.findSetmealCount();
    }

    //设置套餐和检查组多对多关系，操作t_setmeal_checkgroup
    public void setSetmealAndCheckgroup(Integer setmealId,Integer[] checkgroupIds){
        if(checkgroupIds != null && checkgroupIds.length > 0){
            for (Integer checkgroupId : checkgroupIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("setmealId",setmealId);
                map.put("checkgroupId",checkgroupId);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }
}
