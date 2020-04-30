package com.arch.ccc.storminaction;


//Note:
//
//
// 0 <= N <= 10 ^ 4
// 0 < target <= 10 ^ 6
// 0 < speed[i] <= 10 ^ 6
// 0 <= position[i] < target
// All initial positions are different.

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Fleet {

    public int carFleet(int target, int[] position, int[] speed) {
        int cars = position.length;
        if (cars <= 1) {
            return cars;
        }
        Map<Integer, Double> posiSpeedMap = new TreeMap<>();
        for (int i = 0; i < cars; i++) {
            int distence = target - position[i];
            double time =(double) distence / (double) speed[i] ;
            posiSpeedMap.put(position[i], time);
        }
        //from farest to earliest car
        Arrays.sort(position);
        int res = cars;
        for (int i = cars - 1; i >0 ; i--) {
            if (posiSpeedMap.get(position[i]) >= posiSpeedMap.get(position[i - 1])) {
                position[i - 1] = position[i];
                res--;
            }
        }
        return res;
    }



     public double getPow(double x, long n) {
         if (n == 0) {
             return 1.0;
         }
         long beta = ((n>>31)^n)-(n>>31);
         long p = beta / 2;
         double value = 1;
         if((beta&1)!=0){
            value=  x*getPow(x*x,p);
         }else{
             value = getPow(x*x,p);
         }
         return n >> 31 == 0 ? value : 1 / value;
     }

}



