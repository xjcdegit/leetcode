package leetcode.middle.dp;/*
 *
 * @Param
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 */
public class Leetcode300 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        int[] dp = new int[n];
        int res = 0;
        for(int i = 0;i < n;i++){
            dp[i] = 1;
            for(int j = 0;j < i;j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
