# 注意
* visible是否显示dialog
   支持.sync修饰符
   .sysc相当于把一个v-bind变成v-model
* 去axios官网去学
* jdbc:mysql://localhost:3306/superhealth?characterEncoding=UTF-8 解决乱码问题





# 完善文件上传

自动上传存在的问题，如果用户只上传了图片，没有最终保存套餐信息到我们的数据库

这时候图片就成为了垃圾图片。用redis来保存图片



* 当用户上传图片以后，将图片名称保存到一个redis的集合，例如setmealPicResource
* 当用户添加套餐的时候，将图片名称存放到另外一个集合，如setmealPicDbResource
* 计算差值，结果就是垃圾图片的名称集合，清理就行了



这个可以使用定时任务来完成

quartz：在企业商业级用的特别的多  









# POI

apache的一套工具类，用于操作office文档

一般用来操作excel



批量导入

​	通过excel往后台导入数据

​	基于日历空间的修改

> 这里用于预约设置，就是设置每一天的体检预约最大数量 