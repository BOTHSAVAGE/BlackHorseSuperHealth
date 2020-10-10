# 项目原型
• 名称：传智健康管理系统
• 技术栈：Vue+SSM+Dubbo+ZooKeeper+Redis+MySql+Spring Security
• 功能架构图：
# 项目目录
## common
• constant包
• 存放项目常量
• entity包
• 抽取出来的实体类(查询条件，通用返回对象，页码返回对象)
• pojo包
• 业务中抽取出来的所有的pojo
• utils包
• 项目中所用到的工具类（日期，MD5加密，Excel操作，七牛云，短信工具，验证码工具）
> 该模块一般为项目的通用模块，把抽取到的对象都放在这里面作为其他模块的依赖来使用