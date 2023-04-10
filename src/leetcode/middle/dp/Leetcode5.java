package leetcode.middle.dp;/*
 *
 * @Param
 */

import java.util.*;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class Leetcode5 {

    /**
     * 方法二：动态规划
     */
    public String longestPalindromeByDP(String s){

        int length = s.length();
        //dp[i][j]:指s字符串i~j是否为回文子串
        boolean[][] dp = new boolean[length][length];
        // 初始化：所有长度为 1 的子串都是回文串
        for(int i = 0;i < length;i++){
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        int maxNum = 1,begin = 0;
        //递推开始
        //L:指的是回文串的长度
        for(int L = 2;L <= length;L++){
            //l:回文子串左边界下标
            for(int l = 0;l < length;l++){
                //r:回文子串的右边界下标
                int r = l + L - 1;
                //数组越界，直接退出
                if(r >= length){
                    break;
                }

                if(chars[l] != chars[r]){
                    dp[l][r] = false;
                }else{
                    if(r - l < 3){//一定是回文串
                        dp[l][r] = true;
                    }else{
                        dp[l][r] = dp[l + 1][r - 1];
                    }
                }

                //确定i~j属于回文串 且长度大于目前已知的最长
                if(dp[l][r] && L > maxNum){
                    maxNum = L;
                    begin = l;
                }
            }
        }
        return s.substring(begin,begin + maxNum + 1);
    }

    /**
     * 方法一：中心扩散法
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 1){
            return "";
        }
        int start = 0,end = 0;
        for(int i = 0;i < s.length();i++){
            int len1 = expandAroundCenter(s,i,i);
            int len2 = expandAroundCenter(s,i,i + 1);
            int len = Math.max(len1,len2);
            if(len > end - start){
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start,end + 1);
    }

    /**
     * 判断以left和right为中心的回文数的长度
     * @param s
     * @param left
     * @param right
     * @return
     */
    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

}
