package leetcode.middle.doublepointer;/*
 *
 * @Param
 */

/**
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 *
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 *
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 *
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 *
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 */
public class leetcode443 {
    public int compress(char[] chars) {
        int res = 1,num = 1,index = 1;
        for (int i = 1;i < chars.length;i++){
            num = 1;
            //遍历重复元素
            while(i < chars.length && chars[i] == chars[i - 1]){
                num++;
                i++;
            }
            //如果有重复元素，则将重复个数更新到chars数组中
            if(num > 1) {
                char[] chars1 = String.valueOf(num).toCharArray();
                for (int j = 0; j < chars1.length; j++) {
                    chars[index++] = chars1[j];
                }
            }

            //计算出重复个数所占的位数，添加到res中
            res += getNum(num);
            if(i < chars.length){
                res++;
                chars[index++] = chars[i];
            }
        }
        return res;
    }

    public int getNum(int num){
        if(num == 1){
            return 0;
        }
        int temp = 0;
        while(num > 0){
            num /= 10;
            temp++;
        }
        return temp;
    }
}
