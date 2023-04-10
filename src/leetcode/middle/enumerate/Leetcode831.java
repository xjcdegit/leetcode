package leetcode.middle.enumerate;/*
 *
 * @Param
 */

public class Leetcode831 {
    public static void main(String[] args) {
        String s = maskPII("LeetCode@LeetCode.com");
        System.out.println(s);
    }
    public static String maskPII(String s) {
        String res = "";
        if(s.lastIndexOf('@') != -1){
            s = s.toLowerCase();
            int index = s.lastIndexOf("@");
            res += s.charAt(0) + "*****" + s.charAt(index - 1) +  s.substring(index,s.length()).toLowerCase();
        }else{
            String temp = "";
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if('0' <= c && c <= '9'){
                    temp += c;
                }
            }
            if(temp.length() == 10){
                res = "***-***-" + temp.substring(6);
            }else if(temp.length() == 11){
                res = "+*-***-***-" + temp.substring(7);
            }else if(temp.length() == 12){
                res = "+**-***-***-" + temp.substring(8);
            }else{
                res = "+***-***-***-" + temp.substring(9);
            }
        }
        return res;
    }
}
