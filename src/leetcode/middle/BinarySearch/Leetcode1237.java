package leetcode.middle.BinarySearch;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.List;

public class Leetcode1237 {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        /*for(int x = 1;x <= 1000;x++){
            for(int y = 1;y <= 1000;y++){
                if(customfunction.f(x, y) == z){
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(y);
                    res.add(new ArrayList<>(list));
                    break;
                }
            }
        }*/
        for(int x = 1;x < BinaryRow(customfunction,z) ;x++){
            int y = BinaryColumb(customfunction,x,z);
            if(y != 0){
                List<Integer> list = new ArrayList<>();
                list.add(x);
                list.add(y);
                res.add(new ArrayList<>(list));
            }
        }
        return res;
    }

    public int BinaryRow(CustomFunction customfunction,int z){
        int up = 1,down = 1000;
        while(up < down){
            int mid = (down - up)/2 + up;
            if(customfunction.f(mid,1) <= z){
                up = mid + 1;
            }else{
                down = mid;
            }
        }
        return down;
    }

    public int BinaryColumb(CustomFunction customfunction,int x,int z){
        int left = 1,right = 1000;
        while(left < right){
            int mid = (right - left + 1)/2 + left;
            if(customfunction.f(x,mid) > z){
                right = mid - 1;
            }else{
                left = mid;
            }
        }
        if(customfunction.f(x,left) == z){
            return left;
        }else{
            return 0;
        }
    }

     // This is the custom function interface.
     // You should not implement it, or speculate about its implementation
     class CustomFunction {
          // Returns f(x, y) for any given positive integers x and y.
          // Note that f(x, y) is increasing with respect to both x and y.
          // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
          public int f(int x, int y){
              return x + y;
          }
      };
}
