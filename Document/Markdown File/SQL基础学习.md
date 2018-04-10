# SQL基础学习

## 四种join区别

join的语法格式：

```sql
select table1.colum,table2.column 
from table1 
inner|left|right|full join table2 
on table1.column1 = table2.column2
```

`on`子句用来指定连接条件。

- 内连接 inner join（当只写join时默认为内连接）

内连接的结果集中只显示符合`on`子句连接条件的数据。即两个表的数据按照`on`子句连接条件过滤后的交集。不符合条件的数据不显示出来。

- 外连接

1. 左外连接 left outer join （通常省略outer）

左外连接的结果集中包括`join`左边表的所有数据（即table1中的所有数据）和右边表中符合`on`条件的数据，不符合条件的显示为`null`。 

2. 右外连接 right outer join（通常省略outer）

右外连接的结果集中包括`join`右边表的所有数据（即table2中的所有数据）和左边表中符合`on`条件的数据，不符合条件的显示为`null`。 

3. 完全外连接 full outer join（通常省略outer）

完全外连接将显示两个表的所有数据，`join`左边表没有的数据用`null`表示，右边表没有的用`null`表示。

- 交叉连接 cross join

```sql
select * from table1 cross join table2
```

`cross join`没有`on`连接条件，得出的是两张表的笛卡尔积。什么是笛卡尔积？如下图所示：

![](/pic/微信截图_20180408203207.png)

- 自连接

```sql
SELECT a.*, b.* FROM table_1 a,table_1 b WHERE a.name = b.name 
```

同一张表的两个别名 然后通过某一字段进行关联。

## `on`与`where`条件的区别

`where`子查询是在内层查询的结果上作为外层查询的比较条件。当`on`与`where`一起使用的时候，`where`子查询会在`on`连接得出的结果上进行条件过滤。 当使用内连接的时候，`on`会作为连接条件 过滤掉不符合条件的结果。当不使用左连接或者右连接的时候，`on`只是作为连接条件，左表或右表的数据会全部输出。而`where`则是作为过滤条件，结果集中不会显示出`where`过滤掉的数据。因此，如下两种sql的执行结果是不一样的：

```sql
select a.column,b.column from table1 a left join table2 b on a.tenant_id = b.tenant_id where a.no != 123
#此sql将过滤掉a.no = 123的数据记录
```

```sql
select a.column,b.column from table1 a left join table2 b on a.tenant_id = b.tenant_id and a.no != 123
#sql将可以得出a.no = 123的数据记录
```

所以一般建议，在`on`里面只放连接条件，在`where`中使用过滤条件。

> 数据库在通过连接两张或多张表来返回记录时，都会生成一张中间的临时表，然后再将这张临时表返回给用户。
>
> 在使用left join时，on和where条件的区别如下：
>
> 1、 on条件是在生成临时表时使用的条件，它不管on中的条件是否为真，都会返回左边表中的记录。
>
> 2、where条件是在临时表生成好后，再对临时表进行过滤的条件。这时已经没有left join的含义（必须返回左边表的记录）了，条件不为真的就全部过滤掉。





## 子查询

> 子查询其实就是指嵌入到其他语句中的select语句，也称其为嵌套查询

### 1.where子查询

> 将子查询的结果作为外层查询的比较条件。

```sql
select ename,sal,deptno from emp where sal = (select min(sal) from dept );
```

### 2.from子查询

> 将子查询结果看做一个视图，需要使用别名，供外层查询进行再次查询。

```sql
select ename,sal,deptno from (select ename,sal,deptno,managername from emp) emp2 group by deptno;
```

### 3.exists子查询

> 把外层sql的结果拿到内层sql去测试，如果成立，则改行取出。内层即`exists`后面的查询语句。

```sql
SELECT c.cat_id,c.cat_name FROM category c WHERE EXISTS (SELECT 1 FROM goods g WHERE g.cat_id = c.cat_id);
```

注意，`exists`内部的是`g.cat_id = c.cat_id` ，其实相当于是个关联。返回结果集为真的数据。

### 4.any、all子查询

any运算符必须与单行比较运算符结合使用，并且返回行只要匹配子查询的任何一个结果即可。

`>any`表示大于any集合中的最小的，`<any`表示小于any集合中最大的，`=any`表示`in`的意义。

```sql
select empno,ename,job,sal from emp where deptno sal > any(select sal from emp where deptno =10);
```

all运算符必须与单行运算符结合使用，并且返回行必须匹配所有子查询结果。

`>all`表示大于最大的，`<all`表示小于最小的 

```sql
select empno,ename,sal from emp where sal > all(select sal from emp where deptno = 10)
```

###5.union、union all 

> UNION:并集，合并两个操作的结果，去掉重复的部分 
> UNION ALL:并集，合并两个操作的结果，保留重复的部分



## 主键、外键、唯一性索引

- 主键是指当前表的唯一性约束；主键列不允许为空；为表定义主键会自动创建主键索引；主键的列可以作为外键；一个表只能有一个主键；
- 外键是对当前表关联到其他表的字段的约束，这个字段的值必须存在于其他表中才能在当前表中插入；
- 唯一性索引本质上是个索引，只是加了个唯一性的约束；唯一性索引的列允许为空，但是只要存在数据值就必须是唯一的；唯一性索引的列不允许作为外键；一个表可以有多个唯一性索引。

当一个字段的值可以为空，但是业务上又可以作为一个唯一性标识的时候，就可以设置为唯一性索引。

## 函数

### 1. 分组函数

group by 

```sql
select deptno,max(sal) from emp group by deptno;
#查询出公司种薪资最高的部门
```

> 使用group by 的规律：出现在select列表中的字段，如果没有出现在组函数中，则必须出现在group by子句中。

having

```sql
select avg(sal), deptno from emp group by deptno having avg(sal) > 2000;
#按照部门分组，过滤分组后平均薪资大于2000的部门信息
```

having语句的作用是对分组之后的数据进行过滤数据，where 语句的作用是分组之前过滤数据。

>  从emp这张表里把平均工资和部门编号列出来，并且过滤掉大写是A的名字 把剩下的数据按照部门编号进行分组，分组之后的平均薪水必须大于2000，按照部门编号的倒序排列。
>
> 分析：分组，过滤，排序；过滤又包含分组前过滤(where)和分组后过滤(having)。编写顺序：分组前过滤(where) > 分组（group by） > 分组后过滤(having) > 排序（order by）
>
> ```sql
> select avg(sal),deptno,ename from emp  where ename not like '%A%' group by deptno,ename having avg(sal) > 2000 order by deptno desc;
> ```

### 2. 字符函数

1. substr(char,m,n) --从字符的第m位开始向后截取n位。
2. upper(char) : 将字符转为大写
3. lower(char) : 将字符转为小写
4. length(char) : 字符长度
5. replace(char,oldChar,newChar) : 字符替换,用newChar替换oldChar
6. to_char(char,’yy’) : 将字符以什么的形式展现

## 数据库事务隔离



## 参考文章

[参考文章一](http://www.cnblogs.com/wangwanchao/p/5314964.html)

[参考文章二](http://www.cnblogs.com/chiangchou/p/mysql-3.html)

[参考文章三](http://my.oschina.net/jun24bryant/blog/787375)

[参考文章四](http://www.cnblogs.com/hjwublog/p/5952296.html)

[参考文章五](http://www.oraok.com/oracle/oracle-foreign-key.html)



