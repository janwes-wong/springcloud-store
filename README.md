# **Spring Cloud eureka**

## **spring cloud微服务simple project**

### eureka  服务注册与发现
- 本案例中的eureka注册中心地址：http://localhost:8761
### apollo   服务配置中心
- apollo-adminservice地址：http://localhsot:8090
- apollo-configservice地址：http://localhost:8080
- apollo-portal地址：http://localhost:8070
### openfeign 服务调用
### Hystrix   服务降级
- 官方已对netfilx hystrix停止更新
- Hystrix Dashboard 地址：http://localhost:9001/hystrix
### swagger2 + knife4j  接口文档技术

## 携程Apollo配置中心的使用

### 配置方式一

- **step 1：** IDEA clone Github上的代码 https://github.com/ctripcorp/apollo.git；

- **step 2：** 在clone完成后的apollo项目中，找到scripts/sql文件路径下的apolloconfigdb.sql和apolloportaldb.sql的sql脚本，在Windows本地环境或Linux服务器下的MySQL数据库执行sql脚本即可；

- **step 3：** 在apollo项目中找到scripts目录下的build.bat或build.sh

Windows系统环境下修改build.bat脚本文件

```bat
# 修改后状态 ApolloConfigDB数据库
rem apollo config db info
set apollo_config_db_url=jdbc:mysql://localhost:3306/ApolloConfigDB?characterEncoding=utf8
set apollo_config_db_username=root
set apollo_config_db_password=root

# 修改后状态 ApolloPortalDB数据库
rem apollo portal db info
set apollo_portal_db_url=jdbc:mysql://localhost:3306/ApolloPortalDB?characterEncoding=utf8
set apollo_portal_db_username=root
set apollo_portal_db_password=root

# 修改后状态 meta server addresses
rem meta server url, different environments should have different meta server addresses
set dev_meta=http://localhost:8080
set fat_meta=http://localhost:8080
set uat_meta=http://localhost:8080
set pro_meta=http://localhost:8080
```

更改完成后，执行build.bat脚本文件 cmd命令执行

Linux系统环境下修改build.sh脚本文件

```sh
# 修改后状态 ApolloConfigDB数据库
rem apollo config db info
set apollo_config_db_url=jdbc:mysql://localhost:3306/ApolloConfigDB?characterEncoding=utf8
set apollo_config_db_username=root
set apollo_config_db_password=root

# 修改后状态 ApolloPortalDB数据库
rem apollo portal db info
set apollo_portal_db_url=jdbc:mysql://localhost:3306/ApolloPortalDB?characterEncoding=utf8
set apollo_portal_db_username=root
set apollo_portal_db_password=root

# 修改后状态 meta server addresses
rem meta server url, different environments should have different meta server addresses
set dev_meta=http://localhost:8080
set fat_meta=http://localhost:8080
set uat_meta=http://localhost:8080
set pro_meta=http://localhost:8080
```

更改完成后，执行build.sh脚本文件 ./build.sh

- **step 4：** 在Windows系统环境下，分别找到执行完build.bat脚本文件后的apollo-adminservice.zip、apollo-	configservice.zip和apollo-portal.zip，并分别解压，分别对apollo-adminservice.jar、apollo-configservice.jar和apollo-portal.jar执行java -jar xxx.jar命令，先执行apollo-adminservice.jar以此类推；

  ​		Linux系统环境下，将3个压缩包分别上传到服务器并解压执行

### 配置方式二

也可以通过该apollo github官方下载地址 https://github.com/ctripcorp/apollo/releases 下载官方已打包好的压缩包



apollo-portal界面

http://localhost:8070

默认登录用户名：apollo

默认登录密码：admin



后续待更新.....