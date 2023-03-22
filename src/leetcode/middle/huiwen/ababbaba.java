package leetcode.middle.huiwen;/*
 *
 * @Param
 */

import java.math.BigInteger;

import java.util.Scanner;

public class ababbaba {


    static int[] time = {31,28,31,30,31,30,31,31,30,31,30,31};
    public static void main(String[] args) {



        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        String s = scan.next();
        int start = Integer.parseInt(s.substring(0,4));
        for(int i = start + 1;i <= 9999;i++){
            if(ishuiwen(i)){
                System.out.println(i + daozhuan(i));
                for (int j = i; j <= 9221; j++) {
                    if(j / 100 == j % 100 && ishuiwen(j)){
                        System.out.println(j + daozhuan(j));
                        break;
                    }
                }
                break;
            }
        }
        scan.close();
    }

    public static String daozhuan(int left){
        String temp = "";
        while(left > 0){
            temp += left%10;
            left /= 10;
        }
        return temp.toString();
    }

    public static boolean ishuiwen(int left){


        int temp = 0;
        while(left > 0){
            temp = temp*10 + left%10;
            left /= 10;
        }
        int month = temp/100,day = temp % 100;
        if(month > 12 || month == 0){
            return false;
        }else if(left % 4 == 0 && month == 2 && day == 29){
            return true;
        } else if(time[month - 1] < day){
            return false;
        }else{
            return true;
        }
    }




}
