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
        for (int i = 0; i < cars - 1; i++) {
            if (posiSpeedMap.get(position[i]) <= posiSpeedMap.get(position[i + 1])) {
                res--;
            }
        }
        return res;
    }

}



