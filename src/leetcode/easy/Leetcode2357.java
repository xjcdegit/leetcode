package leetcode.easy;/*
 *
 * @Param
 */

import java.util.HashSet;
import java.util.Set;

public class Leetcode2357 {
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int num = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] > 0){
                if(set.add(nums[i])){
                    num++;
                }
            }
        }
        return num;
    }
}
