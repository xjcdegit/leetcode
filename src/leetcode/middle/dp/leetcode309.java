package leetcode.middle.dp;/*
 *
 * @Param
 */

/**
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class leetcode309 {
    //我们目前持有一支股票，对应的「累计最大收益」记为f[i][0]
    //我们目前不持有任何股票，并且处于冷冻期中，对应的f[i][1]
    //我们目前不持有任何股票，并且不处于冷冻期中，对应的f[i][2]
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[][] f = new int[length][3];
        f[0][0] -= prices[0];
        for(int i = 1;i < length;i++){
            //i天持有一支股票：i-i天持有一支股票 或者 i-1天没有股票，i天买了一支股票
            f[i][0] = Math.max(f[i - 1][0], f[i-1][2] - prices[i]);
            //
            f[i][1] = f[i - 1][0] + prices[i];

            //
            f[i][2] = Math.max(f[i - 1][1],f[i - 1][2]);
        }
        return Math.max(f[length - 1][0],Math.max(f[length - 1][1],f[length - 1][2]));
    }
}
