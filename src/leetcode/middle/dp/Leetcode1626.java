package leetcode.middle.dp;/*
 *
 * @Param
 */

import java.util.Arrays;

/**
 * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。
 * 如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
 * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
 */
public class Leetcode1626 {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] bodies = new int[n][2];//
        for (int i = 0; i < n; i++) {
            bodies[i][0] = scores[i];
            bodies[i][1] = ages[i];
        }
        //按照成绩升序，成绩一样按照年龄升序
        Arrays.sort(bodies,(a,b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        //记录到i位置最大的分数
        int[] dp = new int[n];
        int res = 0;
        for(int i = 0;i < n;i++){
            for(int j = i - 1;j >= 0;j--){
                //j球员的年龄得分小于i球员的年龄得分，那么可以直接加上j球员的得分
                //那么到i球员截至 的最大得分情况 = Math.max(dp[i],dp[j])
                if(bodies[j][1] <= bodies[i][1]){
                    dp[i] = Math.max(dp[i],dp[j]);
                }
            }
            //加上i球员自己本身的得分
            dp[i] += bodies[i][0];
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
