package leetcode.middle.greed;/*
 *
 * @Param
 */

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Leetcode1144 {
    public int movesToMakeZigzag(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int num1 = 0,temp = 0;
        for(int i = 0;i < nums.length;i += 2){
            if(i>0 && i < nums.length - 1){
                min = Math.min(nums[i - 1],nums[i + 1]);
            }else if(i == 0){
                min = nums[i + 1];
            }else{
                min = nums[i - 1];
            }
            temp = nums[i] - min + 1;
            num1 += temp > 0?temp:0;
        }
        min = Integer.MAX_VALUE;
        int num2 = 0;
        for(int i = 1;i < nums.length;i += 2){
            if(i < nums.length - 1){
                min = Math.min(nums[i - 1],nums[i +1]);
            }else{
                min = nums[i - 1];
            }
            temp = nums[i] - min + 1;
            num2 += temp > 0?temp:0;
        }
        //System.out.println(num1 + " "+num2);
        return Math.min(num1,num2);
    }
}
