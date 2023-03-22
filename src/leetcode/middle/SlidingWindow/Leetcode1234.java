package leetcode.middle.SlidingWindow;/*
 *
 * @Param
 */

/**
 * 替换子串得到平衡字符串
 */
public class Leetcode1234 {
    public static void main(String[] args) {
        System.out.println(-4%5);
    }
    public int balancedString(String s) {
        int res = s.length(),part = res/4;
        int[] num = new int[26];
        for(int i = 0;i < s.length();i++){
            num[ind(s.charAt(i))]++;
        }
        for(int l = 0, r = 0;l < s.length();l++){
            while(r < s.length() && !jump(num,part)){
                num[ind(s.charAt(r))]--;
                r++;
            }
            if(!jump(num,part)){
                return res;
            }
            res = Math.min(res,r - l);
            num[ind(s.charAt(l))]++;

        }
        return res;
    }

    public int ind(char c){
        return c - 'A';
    }

    public boolean jump(int[] num,int part){
        if(num[ind('Q')]>part ||num[ind('W')]>part ||num[ind('E')]>part ||num[ind('R')]>part){
            return false;
        }
        return true;
    }


}
