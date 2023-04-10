package leetcode.easy.dp;/*
 *
 * @Param
 */

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

import java.util.*;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */
public class leetcode139 {
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3};
        int[] int2 = Arrays.copyOf(ints,3);
        int2[1] = 10;
        for (int i = 0;i < 3;i++){
            System.out.print(ints[i] + " ");
        }
//        List<String> list = new ArrayList<>();

    }
    public boolean wordBreak(String s, List<String> wordDict) {

        for(String str:wordDict) {
            set.add(str);
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1;i <= s.length();i++){
            for (int j = 0;j < i;j++){
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
