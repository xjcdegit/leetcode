package leetcode.resursion;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.List;

public class Queen8 {
    private final static int MAX = 8;
    int[] arr = new int[MAX];
    static List<int[]> res = new ArrayList<int[]>();
    int num = 0;

    public static void main(String[] args) {
        new Queen8().check(0);
        System.out.println(res.size());
    }

    public void check(int n){//放置第 n+1 个皇后
        if(n >= MAX){
            res.add(arr);
            print(arr);
            return;
        }
        for(int i = 0;i < MAX;i++){
            arr[n] = i;
            if(judge(n)){
                //开始分配下一个皇后
                check(n + 1);
            }
        }

    }

    public void print(int[] arr){
        System.out.print(++num + ":");
        for(int i = 0;i < arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public boolean judge(int n){//判断第n-1个皇后是否成立
        for(int i = 0;i < n;i++) {
            if(arr[n] == arr[i] || Math.abs(i - n) == Math.abs(arr[i] - arr[n])){
                return false;
            }
        }
        return true;
    }
}
