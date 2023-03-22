package leetcode.middle.greed;/*
 *
 * @Param
 */

/**
 * 字母板上的路径
 */
public class Leetcode1138 {
    public String alphabetBoardPath(String target) {
        StringBuilder res = new StringBuilder();
        char pre = 'a';
        char next = pre;
        for(int i = 0 ;i < target.length();i++){
            next = target.charAt(i);
            res.append(jump(pre,next));
            res.append('!');
            pre = next;
        }
        return res.toString();
    }

    String jump(char pre,char next){
        StringBuilder sb = new StringBuilder();
        int preIndex0 = (pre - 'a')/5,preIndex1 = (pre - 'a')%5;
        int nextIndex0 = (next - 'a')/5,nextIndex1 = (next - 'a')%5;

        int UD = nextIndex0 - preIndex0,LR = nextIndex1 - preIndex1;
        //上左下右
        while(UD < 0){
            sb.append('U');
            UD++;
        }
        while(LR < 0){
            sb.append('L');
            LR++;
        }
        while(UD > 0){
            sb.append('D');
            UD--;
        }
        while(LR > 0){
            sb.append('R');
            LR--;
        }


        return sb.toString();
    }
}
