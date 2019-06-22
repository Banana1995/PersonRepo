# ARTS

## Algorithm

> 78.颜色分类

```java
class Solution {
    public void sortColors(int[] nums) {
        recurisionFastOrder(nums,0,nums.length-1);
    }
    
    private void recurisionFastOrder(int[] nums,int start,int end){
        if(start>=end){
            return;
        }
        int pivot =  nums[end];
        int i=start;
        // int j =start;
        for(int j = start;j<end;j++){
            //j指向的数字小于中枢点时 则需要与i交换，同时i往后移，保证从i到j都是大于中枢点的值
            if(nums[j]<pivot){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        int temp = nums[i];
        nums[i] = pivot;
        nums[end] = temp;
        recurisionFastOrder(nums,start,i-1);
        recurisionFastOrder(nums,i+1,end);
    }
}
```

我得解法与题解给的不同，用的是快排的算法，快排的递推公式为：`f(p,r)=f(p,q-1)+f(q+1,r)`.其中的q为中枢点。将整个排序的数据分为两部分，[p,q-1]为小于q的部分，[q+1,r]为大于q的部分。剩下的就是确定中枢点的位置了。快排算法是使用两个指针i和j，一个从头开始遍历，当遇到比尾部的值小的时候，则将两个指针的值交换，并将两个指针都往后移一步。这样保持从i到j之间的区间一直都是大于尾部的值的。一次遍历完成后，将i指针的值与尾部的值交换，i点则为中枢点。

## Review

 continue reading 《effective java》

- Item 14 : consider implement comparable

  > 1. 


## Tips







##Share

> 

