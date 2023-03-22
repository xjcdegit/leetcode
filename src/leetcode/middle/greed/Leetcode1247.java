package leetcode.middle.greed;/*
 *
 * @Param
 */

public class Leetcode1247 {
    public int minimumSwap(String s1, String s2) {
        if(s1.length() != s2.length()){
            return -1;
        }
        int num_XY = 0,num_YX = 0;
        for(int i = 0;i < s1.length();i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 == c2){
                continue;
            }else if(c1 == 'x'){
                num_XY++;
            }else{
                num_YX++;
            }
        }
        int res = 0;
        res += num_XY/2;
        res += num_YX/2;

        num_XY %= 2;
        num_YX %= 2;
        if(num_XY == num_YX){
            return res + num_XY + num_YX;
        }else{
            return -1;
        }
    }
}
