package leetcode.KMP;/*
 *
 * @Param
 */

public class utils {
    public static int[] getNext(String str){
        char[] chs = str.toCharArray();
        int length = chs.length;
        int[] next = new int[length];
        int part_length = 0;//最长共同前后缀
        int i = 1;//主字符串的指针
        int index = 1;//next字符串内的指针
        while(i < length){
            if(chs[i] == chs[part_length]){
                i++;
                part_length++;
                next[index++] = part_length;
            }else{
                if(part_length == 0){
                    next[index++] = 0;
                    i++;
                }else{
                    part_length = next[part_length];
                }
            }
        }
        return next;
    }
}
