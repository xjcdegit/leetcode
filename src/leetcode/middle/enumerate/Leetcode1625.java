package leetcode.middle.enumerate;/*
 *
 * @Param
 */

import java.util.Scanner;

/**
 * 枚举
 * 给你一个字符串 s 以及两个整数 a 和 b 。其中，字符串 s 的长度为偶数，且仅由数字 0 到 9 组成。
 *
 * 你可以在 s 上按任意顺序多次执行下面两个操作之一：
 *
 * 累加：将  a 加到 s 中所有下标为奇数的元素上（下标从 0 开始）。数字一旦超过 9 就会变成 0，如此循环往复。例如，s = "3456" 且 a = 5，则执行此操作后 s 变成 "3951"。
 * 轮转：将 s 向右轮转 b 位。例如，s = "3456" 且 b = 1，则执行此操作后 s 变成 "6345"。
 * 请你返回在 s 上执行上述操作任意次后可以得到的 字典序最小 的字符串。
 *
 * 如果两个字符串长度相同，那么字符串 a 字典序比字符串 b 小可以这样定义：在 a 和 b 出现不同的第一个位置上，字符串 a 中的字符出现在字母表中的时间早于 b 中的对应字符。
 * 例如，"0158” 字典序比 "0190" 小，因为不同的第一个位置是在第三个字符，显然 '5' 出现在 '9' 之前。
 */
public class Leetcode1625 {
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        boolean[] booleans = new boolean[n];//用于判断是否已经轮转一整圈
        String res = s;
        s += s;//s加倍方便计算轮转后的字符串

        for (int i = 0; !booleans[i]; i = (i + b)%n) {
            booleans[i] = true;//指定以i为起点的情况已经遍历过
            for(int j = 0;j < 10;j++){//一个字符串最多累加9次，加10就和原始字符串相同了
                int limit = b % 2 == 0?0:9;//偶数下标元素的累加次数
                //判断b是否为偶数
                //是偶数：那么只对计数位置的元素进行累加 limit为0
                //不是偶数：那么还要对偶数位置的元素也进行累加 limit为9
                for (int k = 0;k <= limit;k++){
                    char[] c = s.substring(i,i + n).toCharArray();
                    //对计数下标元素累加，一定要加满9次，找最小情况
                    for(int m = 1;m < n;m+=2){
                        c[m] = (char) ('0' + (c[m] - '0' + j*a)%10);
                    }
                    //对偶数下标元素累加
                    for (int m = 0;m < n;m+=2){
                        //注意：这里需要使用k来累加，因为如果b为偶数，k等于0，那么就可以实现元素保持不变
                        c[m] = (char) ('0' + (c[m] - '0' + k*a)%10);
                    }
                    String strs = new String(c);
                    //比较变化前后字符串的字典序
                    if(res.compareTo(strs) > 0){
                        res = strs;
                    }
                }

            }
        }
        return res;
    }



}
