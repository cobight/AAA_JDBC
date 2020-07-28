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

## **自带的四个数据库**

（尽量不要动）

infomation_schema（描述数据库信息的，只存名字，不存信息）

mysql（核心数据库）

performance_schema（提升性能相关的数据库）

test（随便玩，空的）



## CRUD

1. Create：创建

   -- CHARACTER SET:指定数据库采用的字符集,utf8不能写成utf-8
   -- COLLATE:指定数据库字符集的排序规则,utf8的默认排序规则为utf8_general_ci（通过show character set查看）
   
   create database stu [`CHARACTER` `SET` `utf8` `COLLATE` `utf8_general_ci` if not exit] ;
   
2. Retrieve：查询

   show databases;查所有数据库

   show create database stu;查数据库创建语句

   show variables `like` `'%char%'` ;查看数据库编码

3. Update：修改

   -- 修改数据库编码

   alter database stu CHARACTER SET GBK [COLLATE gbk_chinese_ci];

   alter database stu CHARACTER SET utf8 [COLLATE utf8_general_ci];

4. Delete：删除

   [如果存在]删除数据库stu

   `drop` `database` [`if EXISTS]` `stu`;

5. 使用数据库

   - select database();查询当前正在使用的数据库名称

     未使用时为NULL

   - use stu;使用数据库



# 操作数据表

## 数据类型

<img src='https://note.youdao.com/yws/api/personal/file/2FB73125319642268D5539A104D57F51?method=download&shareKey=1e8dff8ca1a96b7743548d0083147796' />

## CRUD

1. Create：创建

   * 创建表

   语法：

   ​	create table 表明(

   ​		列名1 数据类型1 【约束条件】 【default 默认值】，

   ​		...

   ​		列名n 数据类型n 【约束条件】

   ​	)；

   ​	数据库类型：

     		1. int：类型
                   		2. double：小数类型
          * score double(5,2)
           		3. date：日期，只包含年月日，yyyy-MM-dd
           		4. datetime：日期，包含年月日时分秒 yyyy-MM-dd HH:mm:ss
                      		5. timestamp：时间戳类型   包含年月日时分秒    yyyy-MM-dd  HH:mm:ss
          * 如果将来不给这个字段赋值，或赋值为null，则默认使用当前系统时间，来自动赋值。
           		6. varchar：字符串
          * name varcahr(20)

   * 复制表：
     * create table 表名 like 被复制的表名;

2. Retrieve：查询

   * 查询当前数据库中所有表的名称
     * show tables;
   * 查询表结构
     	 * desc 表名

3. Update：修改

   1. 修改表名
      * alter table 表名 rename to 新的表名;
      * rename table 表名 to 新的表名;
   2. 修改表的字符集
      * alter table 表名 character set 字符集名称;
   3. 添加一列
      * alter table 表名 add 列名  数据类型;
   4. 修改【列名称 类型】
      * alter table 表名 change 列名 **新列明** 新数据类型【约束】【default 默认值】;
      * alter table 表名 modify 列名 新数据类型 【约束】【default 默认值】;
   5. 删除列
      * alter table 表名 drop 列名;

   

4. Delete：删除

   * drop table 表名;
   * drop table if exists 表名;

## 约束

​	**主键约束**：primary key（非空且唯一，只能一个字段是主键，主键就是表中记录的唯一标识）
​		alter table ** drop primary key;删
​		alter table ** modify 字段名 int primary key;加
​		alter table ** add CONSTRAINT 别名 PRIMARY key(字段名);通过别名加
​		alter table ** drop primary key;通过别名删
​	**非空约束**：not null
​		alter table ** modify 字段名 varchar(20);删
​		alter table ** modify 字段名 varchar(20) not null;加
​	**唯一约束**：unique(空值null除外,可以重复)
​		alter table ** drop index phone;删
​		alter table ** modify 字段名 varchar(20) unique;加
​		alter table grade add CONSTRAINT 别名 UNIQUE(字段名);通过别名加
​		alter table grade drop index 别名;通过别名删

​	非空唯一两个约束可以连着



​	**外键约束**：foreign key（与外表的某字段关联，此表的本字段数据只能是被引用表的字段中的已存在的值【此表a，b；引用表a，b，c】）

```
create table department(
	id int primary key auto_increment,
	dep_name varchar(20),
	dep_location varchar(20)
);
create table employee(
	id int primary key auto_increment,
	name varchar(20),
	age int,
	dep_id int, -- 可以为null，不能为department的id外的值
	constraint emp_dept_fk foreign key (dep_id) references department(id) -- 起别名emp_dept_fk，设置字段dep_id为外键，引用deparitment表的id字段
);

insert into department values(null,'研发部','广州'),(null,'销售部','深圳');

insert into employee(name,age,dep_id) values('张三',20,1);
insert into employee(name,age,dep_id) values('李四',21,1);
insert into employee(name,age,dep_id) values('王五',20,1);
insert into employee(name,age,dep_id) values('老王',20,2);
insert into employee(name,age,dep_id) values('大王',22,2);
insert into employee(name,age,dep_id) values('小王',18,2);
```

​	alter table employee drop foreign key emp_dept_fk; -- 删除外键
​	alter table employee add constraint emp_dept_fk foreign key (dep_id) references department(id); -- 给dep_id加外键（起别名emp_fk），关联department表的id
​	alter table employee add constraint emp_dept_fk foreign key (dep_id) references department(id) 【on update cascade】【 on delete cascade】; -- 更新删除-级联动，改外键列的值会联动两个table	

## 特殊约束

### enum枚举

alter table 【student】 add 【adders】【 enum("sichuang","shanghai",..........)】;

这里表示在student表中添加一个adders的字段，这个字段里面的值只能是sichuang 或者shanghai 如果添加的一行新的数据不为这两个值则会报错，（如果允许了可以为空，那么让其为空也是可以）


# 操作数据

## CRUD

1. Create：创建

   * INSERT INTO 表名(字段1,字段2) values(值1,值2);

2. Retrieve：查询

   * select * from 表名;
   * select 字段名1,字段名2 from 表名;
   * select 字段名1 as 别名1,字段名2 as 别名2 from 表名;

   

3. Update：修改

   * update 表名 set 字段名=值 【where 范围】;不写范围就是全表

4. Delete：删除

   * delete from ** where time is null;
   * delete from ** where 条件;











