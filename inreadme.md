# 注意
* visible是否显示dialog
   支持.sync修饰符
   .sysc相当于把一个v-bind变成v-model
* 去axios官网去学
* jdbc:mysql://localhost:3306/superhealth?characterEncoding=UTF-8 解决乱码问题
* axios的get最好使用？去传递参数
* 多对多要用中间表，这个回来复盘的时候好好记一下



# 页面静态化

前后端分离会造成数据库很大的访问压力

有些数据不会经常更新

技术：freemarker ， thymleaf



目的：优化效率，提高seo



问题：目前代码里面只是使用js来渲染，难道要写个页面重新渲染吗



问题：

* 什么时候来生成静态页面
  * 当套餐数据发生改变的时候（增，删，改）
* 生成到什么位置
  *  在开发阶段可以生成到项目目录中，上线后可以将文件生成到移动端系统运行的tomcat中
* 生成几个静态页面
  * 有需求就可以
* 以空间去换时间的技术





# 网页静态化技术与缓存技术比较

共同点：都可以减小数据库访问的压力

区别：

缓存技术适用于小规模的数据。以及一些经常变动的数据

网页静态化技术适用于大规模但是变化不太频繁的数据







# 权限控制 写入cookie

需要用到response对象

因为鉴权总的来说必须要使用cookie

![1600135692338](C:\Users\Administrator\AppData\Local\Temp\1600135692338.png)







之前是用session去存储我们所有的用户信息 



但是session有一个很严重的问题， 就是session不能再集群的时候去用   



所以使用redis+cookie 去模拟





登陆成功后，再redis中存储登录信息而不使用session，原因是session再集群中表现不佳





password一般不能存储明文：md5容易暴力破解（一般加salt或者整个几十次）





# 权限控制 

认证：让系统知道你是谁

授权：指定当前的用户可以操作哪些功能





权限模块数据模型

用户表：t_user

权限表：t_permission

角色表：t_role

菜单表：t_menu

用户角色关系表：

角色权限关系表

![1600136739733](C:\Users\Administrator\AppData\Local\Temp\1600136739733.png)

认证过程：

只需要用户表就可以了，在用户登录时可以查询用户表校验，判断登录凭证是否正确

授权过程：

用户必须完成认证之后才可以进行授权，根据用户查角色，根据角色查菜单（用户能够看到哪些菜单），根据用户的角色查询对应的权限，确定了用户拥有哪些权限











![1600137584061](C:\Users\Administrator\AppData\Local\Temp\1600137584061.png)





自己指向自己的外键





# spring security

还有一个就是shiro 框架



pring security严格遵守上面的表的设计

![1600138614445](C:\Users\Administrator\AppData\Local\Temp\1600138614445.png)![1600138666894](C:\Users\Administrator\AppData\Local\Temp\1600138666894.png)



# 存在问题 如何做到第三方登录









csrf 过滤器要关闭 ，如果使用自定义的登录页面 







这个默认官方的

![1600141490487](C:\Users\Administrator\AppData\Local\Temp\1600141490487.png)

在点击登录提交的时候，还会提交_csrf的值



如果没有这个就视为恶意攻击，返回403



去spring security官网查看







这里的认证就很迷，他是用你的username去找到一个用户，然后用查询出来的用户去比较传过来的密码（可以选择加密方式）





在前面加大括号{noop}，代表的是没有经过加密的



![1600149030327](C:\Users\Administrator\AppData\Local\Temp\1600149030327.png)









静态的代码块的使用



开启spring注解使用

context:annotation-config





j加密算法一般的三大类：

对称加密：

​	信息传输加密

rsa飞对称加密：

​	不可逆



http传递都是明文



msdn网站   很厉害![1600151004785](C:\Users\Administrator\AppData\Local\Temp\1600151004785.png)



cmd5.com 很厉害

加盐的方式，方法是不确定的





# 存在的问题 

bancken里面怎么去注入这个securityservice

因为这securityservice 是存在 与dubbo服务里面的

怎么通过标签去获取到



权限控制的本质就是认证和授权



![1600153486372](C:\Users\Administrator\AppData\Local\Temp\1600153486372.png)







# 一共就是四张主表 



# spring security 认为嵌套的页面不安全 

![1600158209174](C:\Users\Administrator\AppData\Local\Temp\1600158209174.png)



直接拒绝请求

![1600158406210](C:\Users\Administrator\AppData\Local\Temp\1600158406210.png)



redis的用法
1.缓存
2.模拟集群的session使用 


如果对任何一个个体的依赖性过高，那么稳定性必然下降

（缓存穿透，缓存雪崩，缓存击穿）

缓存穿透：查询一个数据库一定不存在的数据
如果主键自增，且主键大于1，如果恶意用户一直传-1，那么就会穿透压垮数据库

措施：查询对象为空也放入缓存，设置60s


缓存雪崩：某一时间大面积的数据失效（周期性的压力波峰）

缓存击穿：非常热点的数据失效，瞬间的大并发击穿缓存 


redis集群方案

主从复制，哨兵，redis cluster

主从复制（主节点一个，从节点多个）
    原则：master数据slave有，slave有的master不一定有
    读写分离：master写，slave读
    
哨兵模式（sentinel）
高可用是分布式架构必须考虑的因素
1.单点系统是高可用的大敌，应该尽量避免单点
2.通过架构设计来保证系统的高可用，其核心准则是冗余
3.实现自动故障转移

哨兵是用于监控redis集群master状态的工具。其本身也是一个独立运行进程
是redis的高可用解决方案

哨兵可以监视一个或者多个redis master服务，以及这些master服务的所有从服务
当某个master服务下线的时候，自动将该master下的某个从服务升级为master服务 
哨兵也可以集群

redis内置集群cluster
redis cluster是redis的内置集群
redis cluster是无中心点的集群架构，依靠gossip协议协同自动化修复集群状态

去中心化，去中间键。集群的每个节点都是平等的关系
每个节点都保存各自的数据和整个集群的状态
每个节点都和其他所有的节点连接，而且这些连接保持活跃
这样就保证了我们只需要连接集群的任意一个节点，就可以获取到其他节点的数据

但是怎么分配数据呢？
使用hashslot（hash槽）来分配，默认分配了16384个slot
数据进来就会CRC16(key)%16384
比如三个节点，就平分


但是cluster也是单点模式
也要加主从（master写，slave读以及备份）