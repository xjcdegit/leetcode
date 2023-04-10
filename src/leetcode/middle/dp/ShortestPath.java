package leetcode.middle.dp;/*
 *
 * @Param
 */

import java.util.Arrays;
import java.util.Scanner;

public class ShortestPath {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        long[] dp = new long[2022];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 2; i <= 2021; i++) {
            for (int j = -1;j > -22;j--){
                if(i + j < 1){
                    break;
                }
                dp[i] = Math.min(dp[i + j] + getMin(i,i+j),dp[i]);
            }
        }
//        for (int i = 1; i < dp.length; i++) {
//            System.out.println(dp[i]);
//        }
        System.out.println(dp[2021]);
        scan.close();
    }

    //获取两个数的最小公倍数
    public static int getMin(int m,int n){
        int a = m,b = n;
        int temp = a;
        while(temp != 0){
            temp = a % b;
            a = b;
            b = temp;
        }
        //System.out.println(m*n/a);
        return (m/a)*n;
    }



}
