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

- 交叉连接

得出的是两张表的笛卡尔积。

- 自连接

```sql
SELECT a.*, b.* FROM table_1 a,table_1 b WHERE a.name = b.name 
```

同一张表的两个别名 然后通过某一字段进行关联。

## 主键外键



## 子查询

### `on`与`where`条件的区别 



## 数据库事务隔离



## 参考文章

[参考文章一](http://www.cnblogs.com/wangwanchao/p/5314964.html)

[参考文章二](http://www.cnblogs.com/chiangchou/p/mysql-3.html)

[参考文章三](https://my.oschina.net/jun24bryant/blog/787375)



