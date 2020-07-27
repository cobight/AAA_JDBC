# AAA_JDBC
 数据库

## 什么是SQL

Structured Query Language:结构化查询语言

其实就是定义了操作所有关系型数据库的规则。每一种数据库操作的方式不一样的地方，成为"方言"。

## SQL通用语法
1. SQL  语句可以单行或多行书写，以分号结尾。
2. 可使用空格和缩进来增强语句的可读性。
3. MySQL数据库的SQL语句不区分大小写，关键字建议使用大写
4. 3种注释
   * 单行注释：-- 注释内容  或  #注释内容
   * 多行注释：/* 注释 */

## SQL分类

1. DDL（Data Definition Language）数据定义语言

   ​	用来定义数据库对象：数据库，表，列等。

   ​	关键字：create，drop，alter等。

2. DML（Data Manipulation Language）数据操作语言

   ​	用来对数据库中表的数据进行增删改。

   ​	关键字：insert，delete，update等。

3. DQL（Data Query Language）数据查询语言

   ​	用来查询数据库中表的记录（数据）。

   ​	关键字：select，where等。

4. DCL（Data Control Language）数据控制语言

   ​	用来定义数据库的访问权限和安全级别，及创建用户。

   ​	关键字：grant，revoke等。

# 操作数据库

**自带的四个数据库**（尽量不要动）

infomation_schema（描述数据库信息的，只存名字，不存信息）

mysql（核心数据库）

performance_schema（提升性能相关的数据库）

test（随便玩，空的）



CRUD

1. Create：创建

   -- CHARACTER SET:指定数据库采用的字符集,utf8不能写成utf-8
   -- COLLATE:指定数据库字符集的排序规则,utf8的默认排序规则为utf8_general_ci（通过show character set查看）
   
   create database stu [`CHARACTER` `SET` `utf8` `COLLATE` `utf8_general_ci`];
   
2. Retrieve：查询

   show databases;查所有数据库

   show create database stu;查数据库创建语句

   show variables `like` `'%char%'` ;查看数据库编码

3. Update：修改

   -- 修改数据库编码

   alter database dbtest CHARACTER SET GBK COLLATE gbk_chinese_ci;

   alter database dbtest CHARACTER SET utf8 COLLATE utf8_general_ci;

4. Delete：删除

   [如果存在]删除数据库stu

   `drop` `database` [`if EXISTS]` `stu`;








