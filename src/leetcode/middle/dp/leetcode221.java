package leetcode.middle.dp;/*
 *
 * @Param
 */

import java.util.Arrays;

/**
 ["0","1","0","1","0","0"]
 ["0","0","1","1","0","1"]
 */
public class leetcode221 {
    public int maximalSquare(char[][] matrix) {
        int length = matrix.length,lengthColumn = matrix[0].length;
        int[][][] dp = new int[length][lengthColumn][3];
        //dp[i][j][0]:dp[i][j]左边连续1的个数(包括自己)
        //dp[i][j][1]:dp[i][j]上面连续1的个数
        //dp[i][j][2]:dp[i][j]为右下角店可以组成的最大正方形的边长

        dp[0][0][0] = matrix[0][0] == '1' ? 1:0;
        dp[0][0][1] = dp[0][0][0];
        dp[0][0][2] = dp[0][0][0];
        int max = dp[0][0][0];
        for(int i = 1;i < length;i++){
            if(matrix[i][0] == '1'){
                dp[i][0][0] = 1;
                dp[i][0][1] = dp[i - 1][0][1] + 1;
                dp[i][0][2] = 1;
                max = 1;
            }
        }
        for(int i = 1;i < lengthColumn;i++){
            if(matrix[0][i] == '1'){
                dp[0][i][0] = dp[0][i - 1][0] + 1;
                dp[0][i][1] = 1;
                dp[0][i][2] = 1;
                max = 1;
            }
        }
        for (int i = 1;i < length;i++){
            for (int j = 1;j < lengthColumn;j++){
                if(matrix[i][j] == '0'){
                    continue;
                }
                dp[i][j][0] = dp[i][j - 1][0] + 1;
                dp[i][j][1] = dp[i - 1][j][1] + 1;
                int min = Math.min(dp[i][j][0],dp[i][j][1]);
                if(min > dp[i - 1][j - 1][2]){
                    dp[i][j][2] = dp[i - 1][j - 1][2] + 1;
                    max = Math.max(max,dp[i][j][2]);
                }else{
                    dp[i][j][2] = Math.min(dp[i][j][0],dp[i][j][1]);
                }
            }
        }
        return max*max;
    }


}
