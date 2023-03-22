package leetcode.hard.dp;/*
 *
 * @Param
 */

public class Leetcode1326 {
    public int minTaps(int n, int[] ranges) {
        int[] land = new int[n + 1];
        for(int i = 0;i <= n;i++){
            int l = Math.max(0,i - ranges[i]);//确定下标为i的水龙头能喷洒的范围
            int r = Math.min(n,i + ranges[i]);
            for(int j = l;j < r;j++){//注意：这里j 必须小于 r，如果land[j] == j，可能让下面while出现死循环
                //若land[j] == 0说明 i水龙头只能浇到这一片花园
                //若不为0，land[j]代表了能够达到的最远花园下标
                land[j] = Math.max(land[j], r);
            }
        }
        int cur = 0,num = 0;
        while(cur < n){
            if(land[cur] == 0){
                return -1;
            }
            cur = land[cur];
            num++;
        }
        return num;
    }
}
