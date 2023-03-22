package leetcode.middle.dp;/*
 *
 * @Param
 */

public class Leetcode_Offer14 {


    public int cuttingRope(int n) {
        if(n <= 3){
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 2;
        dp[3] = 3;
        for(int i = 4;i <= n;i++){
            for(int j = 2;j <= i/2;j++){
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }

    public int cuttingRope2(int n) {
        if(n <= 3) return n - 1;
        long res=1L;
        int p=(int)1e9+7;
        //贪心算法，优先切三，其次切二
        while(n>4){
            res=res*3%p;
            n-=3;
        }
        //出来循环只有三种情况，分别是n=2、3、4
        return (int)(res*n%p);
    }


}
