package leetcode.middle.doublepointer;/*
 *
 * @Param
 */

/**
 * 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
 *
 * 一个子数组指的是原数组中连续的一个子序列。
 *
 * 请你返回满足题目要求的最短子数组的长度。
 */
public class Leetcode1574 {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int j = n - 1;
        while(j > 0 && arr[j - 1] <= arr[j]){
            j--;
        }
        if(j == 0){
            return 0;
        }
        int res = j;
        int i = 0;
        for(i = 0;i < n;i++){
            while(j < n && arr[i] > arr[j]){
                j++;
            }
            res = Math.min(res,j - i - 1);
            if(i + 1 < n&& arr[i + 1] < arr[i]){
                break;
            }
        }
        return res;
    }
}
