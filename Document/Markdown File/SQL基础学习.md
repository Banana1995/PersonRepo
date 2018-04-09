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

## 



## 子查询

### 1.where子查询

### 2.from子查询

### 3.exists子查询

### 4.any，in子查询

### 5.`on`与`where`条件的区别 

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



## 主键、外键、唯一性索引

- 主键是指当前表的唯一性约束；主键列不允许为空；为表定义主键会自动创建主键索引；主键的列可以作为外键；一个表只能有一个主键；
- 外键是对当前表关联到其他表的字段的约束，这个字段的值必须存在于其他表中才能在当前表中插入；
- 唯一性索引本质上是个索引，只是加了个唯一性的约束；唯一性索引的列允许为空，但是只要存在数据值就必须是唯一的；唯一性索引的列不允许作为外键；一个表可以有多个唯一性索引。

当一个字段的值可以为空，但是业务上又可以作为一个唯一性标识的时候，就可以设置为唯一性索引。



## 数据库事务隔离



## 参考文章

[参考文章一](http://www.cnblogs.com/wangwanchao/p/5314964.html)

[参考文章二](http://www.cnblogs.com/chiangchou/p/mysql-3.html)

[参考文章三](http://my.oschina.net/jun24bryant/blog/787375)

[参考文章四](http://www.cnblogs.com/hjwublog/p/5952296.html)

[参考文章五](http://www.oraok.com/oracle/oracle-foreign-key.html)



