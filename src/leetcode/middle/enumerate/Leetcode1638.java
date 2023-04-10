package leetcode.middle.enumerate;/*
 *
 * @Param
 */

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1638 {


    public int countSubstrings(String s, String t) {
        int m = s.length(),n = t.length();
        int res = 0;
        //枚举
        //i：字符串s的起始位置
        //j：字符串t的起始位置
        //k：两字符串起始位置往后的长度
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                //不同的字符子树
                int diff = 0;
                for(int k = 0;i + k < m && j + k < n;k++){
                    if(s.charAt(i + k) != t.charAt(j + k)){
                        diff++;
                    }
                    if(diff > 1){
                        break;
                    }else if(diff == 1){
                        res++;
                    }
                }
            }
        }
        return res;
    }


}
