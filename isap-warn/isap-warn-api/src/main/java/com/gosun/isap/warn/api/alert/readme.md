# 目录说明

## Alert 模块下的目录
### model
model 文件夹用于存放封装的数据类

#### 子目录
- enumeration ： 存放枚举类
- statistics ： 存放用于数据统计的类

#### 通用类
- ResponseData ： RESTful api 统一返回数据格式 

### rest

存放 RESTful Service，在 RESTful service 中对输入参数进行校验，对内部 service 进行组合，返回的数据为 ResponseData 类型。

rest 层要实现的功能
- 参数校验
- 用户鉴权
- 超级用户的判断

### service
存放内部 service ，完成数据的封装，组合数据库操作。  

> **注意事项**  
service 内部最好利用 SqlMapper 直接实现业务，使用其他 service 时，要防止循环注入。  
GuardService UserService PermissionService 为了防止重名 Service 的出现，定义了 bean 的名称，自动注入时要注意，使用 @Qualifier 指定。

#### 通用类
- ServiceId ：用于存放 ServiceId 常量
- AlertCommonService ：封装其他模块接口，提供基础服务
- UserService ：封装用户模块接口，提供涉及到用户信息的一些服务
- PermissionService ：封装权限模块接口，提供鉴权服务
- service 层封装两套数据，一是获取全部数据，二是根据用户（不包括超级用户）获取数据。

### to
存放数据传输过程中用到的 bean

### aop
存放 aop 的实现

### export
存放导出功能的实现

#### 导出功能的设计

- 通过 xml 文件进行导出设置
```xml
<table index="0"  handler="com.gosun.isap.warn.impl.alert.export.handler.QuestionSuspectAfterHandler">
    <body row="3" column="1">
        <column>areaName</column>
        <column>communityName</column>
        <column>alertCreateTime</column>
        <column>questionSuspectTime</column>
        <column>guardInfo</column>
        <column>picture</column>
        <column>confirm</column>
    </body>
    <template resource="alert/template/confirm_question_suspect_template.xls" index="0"/>
    <style>
        <date>yyyy-MM-dd HH:mm:ss</date>
    </style>
</table>
```
- TableSetting 与 xml 文件对应，保存配置信息
- ExportHelper 负责初始化文档，根据 TableSetting 写入 header 数据 ，写入 body 数据，并做写入后的处理，此外，还负责将文档输出到 outputStream
- AfterWriteDataHandler 负责数据写入后的处理工作，包括合并单元格等操作，通过 handler 属性指定相关类给 table 标签
- ColumnHandler 负责写入 body 数据时的数据转换，通过 handler 属性指定相关类给 body 标签
- ExportStreamingOutput 负责构造下载文件的 Response ，为 ExportHelper 提供 outputStream

提供 rest 接口时，为保证接口数据与文档数据一致，MapConverter 负责将原始数据转换成文档所用数据。

#### 导出功能的相关资源
相关资源包括 setting template 两种，资源会存放到两个位置，打包时会打包到 `conf/alert` 文件夹下；同时会在 warn 模块 resource 下的 `alert` 文件夹下会有一份。

ConfigUtil 提供了获取 setting 以及 template 输入流的方式。

### util
存放模块使用的工具类

## dao 模块下的目录

### mapper.alert
用于存放 mapper 文件，mapper 类存放在 main/java 下面，mapper.xml 存放在 main/resources 下面

#### base 文件夹
存在由 mybatis generator 自动生成的 mapper 文件

#### 自定义数据类型

- Interval ：查询区间，开区间，存在一个上限值和一个下限值
- OrderByBuilder ：用于构建 order by list
- SqlLimit ：用于分页，使用 SqlLimit 查询列表必须有 count 与之相对应

### po.alert
用于存放 PO 类，用于存放查询组合的数据

#### base 文件夹
存在由 mybatis generator 自动生成的 po 文件

#### bean 文件夹
简单的通用 java bean

## 存在测试数据的文件

- **rest service : init**
- **CheckPermissionAspect ：checkPermission**
- **AlertRestService : create**
