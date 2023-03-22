package leetcode.middle.dp;/*
 *
 * @Param
 */

public class Leetcode1140 {
    public int stoneGameII(int[] piles) {
        int len = piles.length,sum = 0;
        int[][] dp = new int[len][len + 1];
        //int[i][j] i：指此时从第i块石堆开始拉取
        //j：指此时的M值
        for (int i = len - 1;i >= 0;i--){
            sum += piles[i];
            for(int M = 1; M <= len;M++){
                if(i + 2 * M >= len){
                    dp[i][M] = sum;
                }else{
                    for (int j = 1; j <= 2 * M; j++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + j][Math.max(M,j)]);
                    }
                }
            }
        }
        return dp[0][1];
    }
}
