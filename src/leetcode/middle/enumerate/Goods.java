package leetcode.middle.enumerate;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 现在，小蓝有n 箱货物要摆放在仓库，每箱货物都是规则的正方体。小蓝规定了长、宽、高三个互相垂直的方向，每箱货物的边都必须严格平行于长、宽、高。
 * 小蓝希望所有的货物最终摆成一个大的长方体。即在长、宽、高的方向上分别堆 L W H
 * 满足 L*W*H = n
 */
public class Goods {
    public static void main(String[] args) {
        long num = 2021041820210418L;
        //找出num所有的因子并存入list中去
        List<Long> list = new ArrayList<>();

        for(long i = 1;i <= Math.sqrt(num);i++){
            if(num % i == 0){
                list.add(i);
                long n = num/i;
                //防止导入两次Math.sqrt(num)
                if(n != i){
                    //导入较大因子，因为较小因子之后会遍历到然后加入到list中
                    list.add(n);
                }
            }
        }

        //list中含有num的所有因子 ~ 128个
        System.out.println(list.size());
        int count = 0;
        //然后三次for循环所有的因子即可
        for (long i: list){
            for (long j: list){
                for (long k: list){
                    if(i*j*k == num){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);

    }
}
