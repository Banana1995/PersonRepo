---
title: ARTS打卡第四周
date: 2019-04-27 23:05:39
categories: "Java基础"
tags: "ARTS"
---

# ARTS第五周



## Algorithm

> 9. 回文数

```java
class Solution {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int rightToLeft =0;
        int te =0;
        int y = x;
        while(y!=0){
            te = y%10;
            rightToLeft = rightToLeft*10+te;
            y=y/10;
        }
        if(x==rightToLeft){
            return true;
        }
            return false;
    }
}
```

刚好昨晚刷题刷到了整数反转的题目，这里顺手一上来就想到了使用整数反转的方式来做。果然效果很不错。对于整数溢出的问题其实可以不用担心，因为回文数字肯定不会溢出。

> 14. 最长公共前缀

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length<=0){
            return "";
        }
        StringBuilder res = new StringBuilder("");
        for(int j=0;;j++){
            boolean flag =true;
            char publicChar = ' ';
            if(strs[0]!=null && strs[0].length()>=j+1){
                publicChar = strs[0].charAt(j);
            }else{
                break;
            }
           for(int i =0;i<strs.length;i++){
                String te = strs[i];
                if(te!=null &&  te.length()>=j+1 && publicChar==te.charAt(j)){
                    continue;
                }else{
                    flag=false;
                    break;
                }
            }
            if(flag ){
                res.append(publicChar);
            }else{
                break;
            }
            
        }
        return res.toString();
        
    }
}
```

最开始写的时候没有考虑到String获取长度的是length()方法与数组的length调用方式是不同的，导致编译未通过。后来测试案例又未通过`""`的校验。测试了一番之后才知道`""`的长度为0，在调用length()方法后，应该判断的条件是字符串的长度大于数组下标+1的值。

我的这个算法的最坏的时间复杂度为O(m*n)，m为数组的长度，n为公共前缀的字符数。查看了题解后发现与题解中的算法二思路相同，但是代码实现没有它的简单。







