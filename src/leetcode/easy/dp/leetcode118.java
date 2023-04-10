package leetcode.easy.dp;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.List;

public class leetcode118 {

    static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
//        List<List<Integer>> generate = generate(5);

    }

    public static List<List<Integer>> generate(int numRows) {
        int[][] dp = new int[numRows][numRows];
        for(int i = 0;i < numRows;i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for(int i = 1;i < numRows;i++){
            for(int j = 1;j < numRows;j++){
               dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        for(int i = 0;i < numRows;i++){
            List<Integer> temp = new ArrayList<>();
            for(int j = 0;j <= i;j++){
                temp.add(dp[i][j]);
            }
            result.add(new ArrayList<>(temp));
        }
        return result;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        int[][] dp = new int[triangle.size()][triangle.size()];
        int m = 0,n = 0;
        for(List<Integer> list1:triangle) {
            for(Integer i:list1) {
                dp[m][n++] = i;
            }
            m++;
            n = 0;
        }
        for(int i = 1;i < dp.length;i++) {
            for(int j = 0;j <= i;j++) {
                if(j == 0) {
                    dp[i][j] += dp[i - 1][j];
                }else if(j == i){
                    dp[i][j] += dp[i - 1][j - 1];
                } else {
                    dp[i][j] += Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
        }
        for(int i = 0;i < dp.length;i++) {
            min = Math.min(dp[dp.length - 1][i], min);
        }
        return min;
    }
}
