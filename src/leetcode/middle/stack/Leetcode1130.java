package leetcode.middle.stack;/*
 *
 * @Param
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调栈
 */
public class Leetcode1130 {
    public int mctFromLeafValues(int[] arr) {
        Deque<Integer> stack = new LinkedList<>();
        stack.offerLast(Integer.MAX_VALUE);
        int res = 0;
        for(int n:arr){
            while(n > stack.peekLast()){
                res += stack.pollLast() * Math.min(n, stack.peekLast());
            }
            stack.offerLast(n);
        }
        //此时这个栈一定是降序的
        while(stack.size() > 2){
            res += stack.pollLast() * stack.peekLast();
        }
        return res;
    }
}
