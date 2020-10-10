# 项目原型
* 名称：传智健康管理系统

* 技术栈：Vue+SSM+Dubbo+ZooKeeper+Redis+MySql+Spring Security

* 功能架构图：

  ![1](https://cdn.nlark.com/yuque/0/2020/png/2548612/1602307379757-7a624f5c-cbe2-4e3f-acd6-731d56d504ee.png)
# 项目目录
## Common
* constant包
    * 存放项目常量
* entity包
    * 抽取出来的实体类(查询条件，通用返回对象，页码返回对象)
* pojo包
    * 业务中抽取出来的所有的pojo
* utils包
    * 项目中所用到的工具类（日期，MD5加密，Excel操作，七牛云，短信工具，验证码工具）
> 该模块一般为项目的通用模块，把抽取到的对象都放在这里面作为其他模块的依赖来使用

## Interface

* service包
  * 定义所有服务的接口规范

> 该模块用于定义所有服务的接口规范。在RPC中使用的时候，就可以依赖同一个Interface模块，根据接口去找实现类

## Service Provider

### src

- service.implement
  - 各种实现类
- dao
  - mybatis的服务接口

### resources

* spring*.xml
  * DruidDataSource，sqlSessionFactory（PageHelper），MapperScannerConfigurer
  * freemarkerConfig
  * jedisPoolConfig，jedisPool
  * dubbo
  * transactionManager
* mybatis的映射文件

### webapp

* ftl模板
  * 页面静态化的模板
* web.xml
  * ContextLoaderListener
  * 编码过滤器

> 架构是spring和mybatis整合放在tomcat服务器上，然后将服务发布出去

