package leetcode.middle.recursion.backtrack;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_Offer38 {
    List<String> temp = new ArrayList<>();
    boolean[] loop;
    public String[] permutation(String s) {
        int n = s.length();
        loop = new boolean[n];
        char[] chs = s.toCharArray();
        Arrays.sort(chs);
        StringBuffer sbf = new StringBuffer();
        backtrack(chs,0,n,sbf);
        int size = temp.size();
        String[] strs = new String[size];
        for(int i = 0;i < size;i++){
            strs[i] = temp.get(i);
        }
        return strs;
    }

    public void backtrack(char[] chs,int index,int n,StringBuffer sbf){
        if(index == n){
            temp.add(new String(sbf));
            return;
        }

        for(int i = 0;i < n;i++){
            if(loop[i] || (i > 0 && chs[i - 1] == chs[i] && !loop[i - 1]) ){
                continue;
            }
            loop[i] = true;
            sbf.append(chs[i]);
            backtrack(chs,index + 1,n,sbf);
            loop[i] = false;
            sbf.deleteCharAt(sbf.length() - 1);
        }
    }
}
