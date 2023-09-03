# zadmin后台管理系统

zadmin前端基于[vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)二次开发，后端基于spring-boot2.7、Mybatis-Plus、Spring Security、RBAC模型实现的前后端分离管理系统

## 已实现功能

- 使用Spring Security结合Jwt作为项目安全框架
- 实现菜单动态路由，后端可配置化，支持多级菜单，并做到了按钮级权限控制
- 自定义权限注解，支持接口级别的功能权限与数据权限，可自定义操作
- 菜单、部门的树形展示

## 后端项目结构

- `zadmin-com`为系统实体类、接口请求返回参数、自定义异常、枚举类、工具类等模块
- `zadmin-dal`为系统数据库交互模块
- `zadmin-service`项目业务service模块
- `zadmin-web`系统核心启动类模块，controller、配置文件、拦截器等

[前端地址](https://github.com/z2yTvT/wall-front)