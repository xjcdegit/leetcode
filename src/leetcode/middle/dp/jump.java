package leetcode.middle.dp;/*
 *
 * @Param
 */

import java.util.Scanner;

public class jump {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] temp = new int[m][n];
        for(int i = 0;i < m;i++){
            for (int j = 0;j < n;j++){
                temp[i][j] = scanner.nextInt();
            }
        }
        int t1 = 0,t2 = 0;
        for (int i = 0;i < m;i++){
            for (int j = 0;j < n;j++){
                if(i == j && i == 0){
                    continue;
                }
                temp[i][j] = jisuan(temp,i,j);
            }
        }
        /*for (int i = 0;i < m;i++){
            for (int j = 0;j < n;j++){

                System.out.print(temp[i][j] + " ");
            }
            System.out.println();
        }*/
        System.out.println(temp[m-1][n-1]);
    }

    private static int jisuan(int[][] temp, int i, int j) {
        int max = Integer.MIN_VALUE;
        for (int t1 = 0;t1 <= 3;t1++){
            for (int t2 = 0;t2 <= 3 - t1;t2++){
                if(t1 > i || t2 > j){
                    continue;
                }
                if(t1 == t2 && t1 == 0){
                    continue;
                }
                max = Math.max(max,temp[i - t1][j - t2]);
            }
        }
        return max + temp[i][j];
    }
}
