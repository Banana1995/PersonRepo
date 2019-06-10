# ARTS

## Review

 continue reading 《effective java》

- Item 14 : consider implement comparable

  > 1. By implement comparable, you allow you class to interoperate with all of many generic algorithms and collection implementations that depends on this interface.
  > 2. If  a field does not implement comparable or you need a nonstandard ordering , use a Comparator instead.
  > 3. Use of the relational operators < and > in compareTo methods is
  >    verbose and error-prone and no longer recommended


## Tips







##Share

处理mysql插入记录重复的几种方式

- 使用`insert ignore`

> Use the **INSERT IGNORE** command rather than the **INSERT** command. If a record doesn't duplicate an existing record, then MySQL inserts it as usual. If the record is a duplicate, then the **IGNORE** keyword tells MySQL to discard it silently without generating an error.
>
> 当记录不存在是，mysql会正常插入；当记录已经存在时，`ignore`关键字会告诉mysql静默的将数据丢掉而不报出错误。下面的sql语句将不会报错：
>
> ```sql
> mysql> INSERT IGNORE INTO person_tbl (last_name, first_name)
>    -> VALUES( 'Jay', 'Thomas');
> Query OK, 1 row affected (0.00 sec)
> 
> mysql> INSERT IGNORE INTO person_tbl (last_name, first_name)
>    -> VALUES( 'Jay', 'Thomas');
> Query OK, 0 rows affected (0.00 sec)
> ```

- 使用`replace` 

> Use the **REPLACE** command rather than the INSERT command. If the record is new, it is inserted just as with INSERT. If it is a duplicate, the new record replaces the old one.
>
> 使用`replace`命令来插入时，当记录不存在，则会正常插入。若已经存在，则会用新的记录代替原先的记录（先删再插）。示例如下：
>
> ```sql
> mysql> REPLACE INTO person_tbl (last_name, first_name)
>    -> VALUES( 'Ajay', 'Kumar');
> Query OK, 1 row affected (0.00 sec)
> 
> mysql> REPLACE INTO person_tbl (last_name, first_name)
>    -> VALUES( 'Ajay', 'Kumar');
> Query OK, 2 rows affected (0.00 sec)
> ```

- ***当处理重复插入记录时，应该在`insert ignore`和`replace`中选择一个，`insert ignore`会保留第一次插入的记录，而`replace`则保留的是最后的一条记录。*** 
- 使用`insert into...on duplicate key update id=id`

> 这里使用`id=id`来在更新时做无用操作来完成在key重复时什么都不做。

