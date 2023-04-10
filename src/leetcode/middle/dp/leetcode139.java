package leetcode.middle.dp;/*
 *
 * @Param
 */

import java.util.*;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */
public class leetcode139 {


    int max = 0;
    static int res = 0;
    static char[][] chs = new char[10][10];
    static int[][] ints;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),k = sc.nextInt(),res = 0;
        int[] arr = new int[n];
        long sum = 0;
        int[] tar = new int[n];
        for (int i = 0;i < n;i++){
            arr[i] = sc.nextInt();
            sum += arr[i];
            tar[(int)(sum%k)]++;
        }
        res += tar[0];

        for(int i = 0;i < n;i++){
            res += tar[i]*(tar[i]-1)/2;
        }
        System.out.println(res);
    }

    public static void dfs(int m,int n){
        if(m < 0 || m >= 10 || n < 0 || n >= 10){
            res++;
            return;
        }
        if(ints[m][n] == 1){
            return;
        }
        ints[m][n] = 1;
        if(chs[m][n] == 'L'){
            dfs(m,n-1);
        }else if(chs[m][n] == 'R'){
            dfs(m,n + 1);
        }else if(chs[m][n] == 'U'){
            dfs(m - 1,n);
        }else{
            dfs(m + 1,n);
        }
    }
    public int maxmiumScore(int[] cards, int cnt) {
        huisu(cards,cnt,0,0,0);
        return max;
    }

    public void huisu(int[] cards,int cnt,int index,int currentcnt,int score){
        if(currentcnt == cnt){
            if(score % 2 == 0){
                max = Math.max(max,score);
            }
            return;
        }

        for(int i = index;i < cards.length - (cnt - currentcnt) + 1;i++){
            score += cards[i];
            huisu(cards,cnt,i + 1,currentcnt + 1,score);
            //回溯
            score -= cards[i];
        }
    }

    Set<String> set = new HashSet<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        for(String str:wordDict) {
            set.add(str);
        }
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;//字符串为空时为true
        //i：到第i个字母是否满足
        for(int i = 1;i <= length;i++) {
            //设置起始位置，查找 j~(i-1)是否存在
            for(int j = 0;j < i;j++) {
                if(dp[j] && set.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }
}
