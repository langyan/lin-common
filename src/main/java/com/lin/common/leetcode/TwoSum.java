package com.lin.common.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // key =
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0, len = nums.length; i < len; i++) {
            int num1 = nums[i];
            int other = target - num1;
            if (map.containsKey(other)) {
                return new int[]{map.get(other), i};
            } else {
                map.put(num1, i);
            }
        }
        return null;

    }

    public static void main(String args[]) {
        TwoSum sum = new TwoSum();
        int[] result = sum.twoSum(new int[]{2, 7, 11, 15}, 9);
        if (result != null) {
            System.out.println(result[0]);
            System.out.println(result[1]);
        }

    }

}
