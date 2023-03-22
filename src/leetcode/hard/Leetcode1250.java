package leetcode.hard;/*
 *
 * @Param
 */

/**
 * 裴蜀定理
 */
public class Leetcode1250 {
    /**
     只要满足子集内所有数的最大公约数为1
     */
    public boolean isGoodArray(int[] nums) {
        int part = nums[0];
        for(int i = 1;i < nums.length;i++){
            part = gcd(part,nums[i]);
            if(part == 1){
                break;
            }
        }
        return part == 1;
    }

    //求num1和num2的最大公约数
    public int gcd(int num1,int num2){
        int temp;
        while(num2 != 0){
            temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }
}
