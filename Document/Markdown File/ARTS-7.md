---
title: ARTS打卡第七周
date: 2019-05-26 23:46:39
categories: "Java基础"
tags: "ARTS"
---

# ARTS第七周

本次ARTS主要包括：Excel列名称算法题；读完《Java并发编程艺术》整书的思维导图；VIM编程技巧关于上下移动一行和向上或向下查询当前游标下的单词等操作；

上周没有打卡，最近的重心都放在读书上，没有仔细的做算法题。

<!--more-->



## Algorithm

> 168.Excel表列名称

```java

```



## Review

 continue reading 《effective java》

- Item 12 : Always override toString

  > 1. Providing  a good toString implementation makes your class much more pleasant to use and makes systems using the class easier to debug.
  > 2. When practical, the toString method should return all insteresting information contained in the  object.
  > 3. Whether or not you decide to specify the format , you should clearly document you intentions.
  > 4. Whether or not you specify the format, provide programmatic access to the information contained in the value returned by toString.

- Item 13 : Override clone judiciously

  > 1. Though the specification doesn't say it , in practice , a class implementing Cloneable is expected to provide a properly functioning public clone method. And the class and all of its superclass should obey a complex , unenforceable,thinly docemented protocol to achieve that.
  > 2. You can use `throw new CloneNotSupportedException();` for degenerate clone implementation.
  > 3. To recap, all classes that implement Cloneable should override clone with a public method whose return type is the class itself. This method should first call `super.clone`, then fix any fields tha need fixing. Typically, this means copying any mutable objects that comprise the internal "deep structure" of the object and replacing the clone's references to these objects with references to their copies.
  > 4. A better approach to object copying is to provide a ***copy constructor*** or ***copy factory***. A notable exception to this rule is arrays, which are best copied with the clone method.

## Tips

vim编程技巧：

使用`#`可以自动向上查询当前游标下的单词；

使用`*`可以自动向下查询当前游标下的单词；

使用`ctrl`+`e`可以向上翻滚一行；

使用`ctrl`+`y`可以自动向下翻滚一行；

使用`zz`可以将当前行移动到屏幕中央。

## Share

本周阅读完了《Java并发编程艺术》这本书，将其全部的思维导图贴出：

![](ARTS-7\Java并发编程的艺术.png)



