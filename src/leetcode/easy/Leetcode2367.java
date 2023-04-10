package leetcode.easy;/*
 *
 * @Param
 */

public class Leetcode2367 {
    public int arithmeticTriplets(int[] nums, int diff) {
        int length = nums.length;
        int j = 1,k = 2;
        int res = 0;
        for (int i = 0;i < length;i++){
            while(nums[j] - nums[i] < diff && j < length - 1){
                j++;
            }
            if(j < length - 1 && nums[j] - nums[i] == diff){
                k = j + 1;
                while(k < length && nums[k] - nums[j] < diff ){
                    k++;
                }
                if(k < length && nums[k] - nums[j] == diff){
                    res++;
                }
            }
        }
        return res;
    }
}
