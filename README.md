# urban-tool

[城市综合服务管理平台](https://github.com/liukaiK/urban)通用的一些工具类，框架的封装，目前包含单点登录CAS的封装，工具类的封装，分部署文件Minio，应用维护对接SDK。



## 模块说明

- urban-common 包含常用的工具类。

- urban-app-mtce-spring-boot-starter 与应用维护对接的starter。

- urban-cas-spring-boot-starter 与单点登录对接的starter。
- urban-minio-spring-boot-starter 与Minio集成的starter。



## 使用说明

1. urban-common 在pom.xml增加依赖
   ```xml
   <dependency>
       <groupId>com.unicom.urban</groupId>
       <artifactId>urban-common</artifactId>
       <version>${urban.tool.version}</version>
   </dependency>
   ```

2. urban-app-mtce-spring-boot-starter 在pom.xml增加依赖
   ```xml
   <dependency>
       <groupId>com.unicom.urban</groupId>
       <artifactId>urban-app-mtce-spring-boot-starter</artifactId>
       <version>${urban.tool.version}</version>
   </dependency>
   ```

   `application.yml`增加如下配置

   ```yaml
   urban:
     app-mtce:
       url: https://127.0.0.1/api/v1/
       app-key: xxxxxxxx
       app-secret: xxxxxxxxxxx
   ```

3. urban-cas-spring-boot-starter 在pom.xml增加如下依赖
   ```xml
   <dependency>
       <groupId>com.unicom.urban</groupId>
       <artifactId>urban-cas-spring-boot-starter</artifactId>
       <version>${urban.tool.version}</version>
   </dependency>
   ```

   `application.yml`增加如下配置

   ```yaml
   urban:
     cas:
       enable: true
       service: http://127.0.0.1:9331/dev-api/login/cas
       logout-url: http://127.0.0.1:8780/cas/logout
       ticket-url: http://127.0.0.1:8780/cas
       success-url: http://127.0.0.1:9331/#/dashboard
   ```

4. urban-minio-spring-boot-starter在pom.xml增加如下依赖
   ```xml
   <dependency>
       <groupId>com.unicom.urban</groupId>
       <artifactId>urban-minio-spring-boot-starter</artifactId>
   </dependency>
   ```

   `application.yml`增加如下配置

   ```yaml
   urban:
     minio:
       endpoint: 127.0.0.1
       access-key: xxxxxxx
       secret-key: xxxxxxxxxxxxx
   ```

   




## 安装

1. `mvn clean package`

   

未完待续。。。。

