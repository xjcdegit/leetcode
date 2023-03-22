package leetcode.middle.dp;/*
 *
 * @Param
 */

public class poj2796 {
    
    public static void main(String[] args) {
        int[] a = {3,1,6,4,5};
        int i = dpOfMax(a);
        System.out.println(i);
    }
    public static int dpOfMax(int[] a){
        int length = a.length;
        int[][][] dp = new int[length][length][2];
        for(int i = 0;i < length;i++){
            dp[i][i][0] = a[i];//记录i~j的最小元素
            dp[i][i][1] = a[i];//记录i~j的元素和
        }
        int max = dp[0][0][0];//结果

        for(int i = 0;i < length - 1;i++){
            for (int j = i + 1;j < length;j++){
                dp[i][j][0] = Math.min(dp[i][j - 1][0],dp[j][j][0]);
                dp[i][j][1] = dp[i][j - 1][1] + dp[j][j][1];
                max = Math.max(max,dp[i][j][0] * dp[i][j][1]);
            }
        }
        return max;
    }

}
