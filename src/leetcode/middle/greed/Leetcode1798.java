package leetcode.middle.greed;/*
 *
 * @Param
 */

import java.util.Arrays;
import java.util.Collections;

/**
 * 贪心算法：
 *
 * 我们可以构建[0,x]，新增一个y，就可以构建[y, x + y]
 * 如果y < x+1 就可以构建[0, x + y]
 */
public class Leetcode1798 {

    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int res = 1;
        for(int temp:coins){
            if(temp > res){
                return res;
            }
            res += temp;
        }
        return res;
    }
}
