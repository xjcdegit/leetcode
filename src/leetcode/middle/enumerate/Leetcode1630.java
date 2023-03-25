package leetcode.middle.enumerate;/*
 *
 * @Param
 */

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 如果一个数列由至少两个元素组成，且每两个连续元素之间的差值都相同，那么这个序列就是 等差数列 。更正式地，数列 s 是等差数列，
 * 只需要满足：对于每个有效的 i ， s[i+1] - s[i] == s[1] - s[0] 都成立。

 */
public class Leetcode1630 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long l1 = scanner.nextLong();
        int[] nums = {4,6,5,9,3,7};
        int[] l = {0,0,2},r = {2,3,5};
        new Leetcode1630().checkArithmeticSubarrays(nums,l,r);
    }
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> list = new ArrayList();

        for(int i = 0;i < n;i++){
             if(r[i] - l[i] < 2){
                 list.add(true);
             }else {
                 int[] a = nums.clone();
                 list.add(judge(a,l[i],r[i]));
             }
        }
        return list;
    }

    public Boolean judge(int[] arrs,int startIndex,int endIndex){
        Arrays.sort(arrs, startIndex, endIndex + 1);
        for (int i = startIndex; i <=endIndex; i++) {
            System.out.println(arrs[i]);
        }
        System.out.println();

        int temp = arrs[startIndex + 1] - arrs[startIndex];
        for(int i = startIndex + 1;i < endIndex;i++){
            if(arrs[i + 1] - arrs[i] != temp){
                //System.out.println(arrs[i]);
                return false;
            }
        }
        return true;
    }
}