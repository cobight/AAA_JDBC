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

## 备份与还原 

CMD控制台

备份：mysqldump -u root -p schooldb student > D:BackupName.sql
还原：mysql -u root -p schooldb < D:BackupName.sql

mysql控制台

还原：数据库重建，use 数据库，source D:/***.sql

# 操作数据表

## 数据类型

<span>
<img src='http://cobight.cn/image-20200727230558451.png' /> 
</span>

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
     * create table 表名 like 被复制的表名;创建表，包括约束条件
     * CREATE TABLE 表名1 SELECT * FROM 表名2;创建表，除了约束，表的基本定义与内容全copy来
   * Truncate 重置表：
     * Truncate table语句用来删除/截断表里的所有数据
     * 和delete删除所有表数据在逻辑上含义相同，但性能更快
     * 类似执行了drop table和create table两个语句

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
​		alter table ** drop primary key ;省略删
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

### enum枚举  数据类型

alter table 【student】 add 【adders】【 enum("sichuang","shanghai",..........)】;

这里表示在student表中添加一个adders的字段，这个字段里面的值只能是sichuang 或者shanghai 如果添加的一行新的数据不为这两个值则会报错，（如果允许了可以为空，那么让其为空也是可以）


# 操作数据

## 操作语句

1. Insert：插入

   * INSERT 【INTO】 【表名(字段1,字段2)】 values(值1,值2);
   * INSERT into 表名1(字段1,字段2) select (字段1,字段2) from 表名2;搬指定列的数据
   * INSERT into 表名1 select * from 表名2;除了约束，全搬来

2. Select：查询

   * select * from 表名;
   * select 字段名1,字段名2 from 表名;
   * select 字段名1 as 别名1,字段名2 as 别名2 from 表名;


3. Update：修改

   * update 表名 set 字段名1=值1,字段名2=值2... 【where 范围】;不写范围就是全表

4. Delete：删除

   * delete from ** where time is null;
   * delete from ** where 条件;





# 排序

单列：order by 字段 asc升/desc降

多列：SELECT * FROM StudentInfo ORDER BY Age DESC,Birthday ASC; 

# 获取数量

limit position，length

# 去重

distingct 字段名



# 匹配

where

name like a__, -- 一个下划线一个字符
name like a%, -- 一个百分号多个字符

# 分组

group  by





聚合函数：排除null值计算
	count数量    select cout(ifnull(english,0)) from cj;
	max,min,xum(**,**)
	avg平均值
分组查询：(查询内容要么  查询字段，要么聚合函数)
	select sex , avg(math),count(id) from cj group by sex;
分页查询：limit起始位置0，显示数量
	select * from cj limit 0,1;

## 特殊方法支持

concat拼接

select concat（s.name,'---'.s.gender） 姓名,caoncat（s.province，‘>’，s.city） 地址 from studentinfo s;

select s.name,length(s.name) '长度',char_length(s.name) 个数 from studentinfo s;



***\*字符串函数\****

| ***\*函数名\**** | ***\*示例\****                                              | ***\*函数功能\****                                           |
| ---------------- | ----------------------------------------------------------- | ------------------------------------------------------------ |
| Concat           | concat(s1,s2....sn)concat(‘hello’,‘AAA’)返回 helloAAA       | 把传入的参数连接成一个字符串。如果有值为null，则最终返回null。忽略null值则使用concat_ws函数。 |
| Length           | length('hello world')结果为11length('加油中国')结果返回为12 | 返回任何数据类型的字节数                                     |
| Char_length      | Char_length('中国') 返回为2                                 | 返回字符个数                                                 |
| Upper            | upper('abcd')返回为ABCD                                     | 将字符串转为大写                                             |
| Ltrim            | Ltrim(‘   abc’)返回为’abc’                                  | 去除字符串左边的空格                                         |
| Rtrim            | Rtrim(‘abc  ’)返回为’abc’                                   | 去除字符串右边的空格                                         |
| Replace          | Replace('abcccd','c','x')返回为’abxxxd’                     | 将abcccd中c替换为x                                           |
| Locate           | Locate('a','helloaaa')返回 6                                | 返回子串 a  在字符串 helloaaa 第一个出现的位置，不存在则返回 0 |
| Substring        | substring('Johnson',5,3)返回为‘son’                         | 从第5个位置开始截取长度为3的字符串                           |



****日期函数\****

| ***\*函数名\****      | ***\*示例\****                                               | ***\*函数功能\****                                           |
| --------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Now                   | now()返回系统当前时间                                        | 返回系统当前时间                                             |
| TIMESTAMPDIFF         | 语法:TIMESTAMPDIFF(unit,datetime_expr1,datetime_expr2)例：SELECT  TIMESTAMPDIFF(MONTH,'2009-09-01','2009-10-01'); 返回 1 | 返回日期或日期时间表达式之间的整数差unit可以是： MICROSECOND(microseconds), SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, QUARTER, YEAR. |
| DateAdd               | DATE_ADD('1998-01-02', interval 1 YEAR)返回 1999-01-02       | 想日期指定部分添加数字，其中：YEAR表示是年，month表示月，day表是日Interval--时间间隔 |
| Dayofweek             | dayofweek('2008-08-08‘)返回 6dayofweek：（1 = Sunday, 2 = Monday, ..., 7 = Saturday） | 返回一周中的位置                                             |
| YEARWEEK(date[,mode]) | select yearweek('2008-8-8'); 返回200831mode:0-7              | 获取年份和周数                                               |
| DateDiff              | select datediff('2008-08-08', '2008-08-01'); 结果返回为  7   | 两个日期相减 date1 - date2，返回天数                         |
| Date                  | date('2008-09-10 07:15:30'); 返回 2008-09-10                 | 以字符串形式返回某个日期部分。                               |

 

select date('2008-09-10 07:15:30');     **--** **日期**  **2008-09-10**

select time('2008-09-10 07:15:30');     **--** **时间****07:15:30.123456**

select year('2008-09-10 07:15:30');     **--** **年**  **2008**

select quarter('2008-09-10 07:15:30');   **--** **季度** **3**

select month('2008-09-10 07:15:30');    **--** **月**  **9**

select week('2008-09-10 07:15:30');     **--** **一年中的第几周** **36**

select day('2008-09-10 07:15:30');     **--** **日**   **10**

select hour('2008-09-10 07:15:30');     **--** **小时**  **7**

select minute('2008-09-10 07:15:30');    **--** **分钟**  **15**

select second('2008-09-10 07:15:30');    **--** **秒**   **30**



***\*数学函数\****

| ***\*函数名\**** | ***\*示例\****                | ***\*函数功能\****                       |
| ---------------- | ----------------------------- | ---------------------------------------- |
| Abs              | Abs(-1)返回为”1”              | 求绝对值                                 |
| Ceiling          | Ceiling(24.1)结果返回25       | 大于24.1的最小整数                       |
| Floor            | Floor(24.1)结果返回为24       | 小于24.1的最大整数                       |
| Power            | Power(2,3)结果返回为“8”       | 计算2的3次方                             |
| Round            | Round(68.32,1)结果返回为68.30 | 返回一个数字，舍入到指定的长度或精度     |
| Sign             | Sign(123)结果返回为1          | 返回数值的符号，正负零分别返回1， -1， 0 |
| Sqrt             | Sqrt(16)结果返回为“4”         | 开平方                                   |
| PI               | PI() 返回3.141593             | 圆周率                                   |
| Rand([x])        | Rand()/Rand(x)                | 返回一个随机浮点值                       |



***\*系统函数及其它\****

| ***\*函数名\****   | ***\*示例\****                                   | ***\*函数功能\****                                  |
| ------------------ | ------------------------------------------------ | --------------------------------------------------- |
| Convert            | convert(1234,char(4))结果返回为”1234”            | 数据类型转换函数，将1234数字类型转换为char类型      |
| VERSION            | Version()                                        | 返回数据库的版本号                                  |
| LAST_INSERT_ID     | LAST_INSERT_ID()                                 | 返回最后生成的AUTO_INCREMENT值                      |
| ifnull(expr,value) | select  ifnull(city,'未知') from studentInfo     | 如果表达式的值非空，则返回表达式的值，否则返回value |
| PASSWORD/MD5       | PASSWORD(str)/MD5(str)                           | 对数据加密                                          |
|                    | select user,host from mysql.user返回计算机的名字 | 返回当前用户登录的计算机名字                        |
|                    | SELECT Current_User 返回当前用户的登录名         | 返回当前用户的名字                                  |



# 代码实现

## 初级版本

```java
public class beforeclass {
    public static String url = "jdbc:mysql://127.0.0.1:3306/studb?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
    //用户名
    public static String user = "root";
    //密码
    public static String pwd = "root";
    //驱动类的名称
    public static String diverName = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        add();

        update();
        del();
        selectall();
    }
    public static Connection connect(){
        Connection con=null;
        try {
            Class.forName(diverName);
            con= DriverManager.getConnection(url,user,pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void close(Connection con, Statement st, ResultSet rs){
        try {
            if (null!=con)con.close();
            if (null!=st)st.close();
            if (null!=rs)rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void add(){
        Connection con=null;
        Statement st=null;
        con=connect();
        try {
            st=con.createStatement();
            String sql="insert into gradetb(gradename,remark) values('老三','rty')";
            int result=st.executeUpdate(sql);
            System.out.println(result>0?"添加成功:"+result:"添加失败:"+result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con,st,null);
        }
    }
    public static void del(){
        Connection con=null;
        Statement st=null;
        con=connect();
        try {
            st=con.createStatement();
            String sql="delete from gradetb where remark='rty'";
            int result=st.executeUpdate(sql);
            System.out.println(result>0?"删除成功:"+result:"删除失败:"+result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con,st,null);
        }
    }
    public static void update(){
        Connection con=null;
        Statement st=null;
        con=connect();
        try {
            st=con.createStatement();
            String sql="update gradetb set gradename='老八' where gradename='老三'";
            int result=st.executeUpdate(sql);
            System.out.println(result>0?"修改成功:"+result:"修改失败:"+result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con,st,null);
        }
    }
    public static void selectall(){
        Connection con=null;
        Statement st=null;
        con=connect();
        try {
            st=con.createStatement();
            String sql="select * from gradetb";
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                String name=rs.getString(1);
                String re=rs.getString(2);
                System.out.println(name+"-->"+re);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con,st,null);
        }
    }
}
```



jdbcTemplte











