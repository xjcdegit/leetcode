package leetcode.middle.stack;/*
 *
 * @Param
 */

import leetcode.easy.Tree.TreeNode;

import java.util.*;

public class Leetcode_Offer31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Map<Integer,Integer> map = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0 ;i < pushed.length;i++){
            map.put(pushed[i],i);
        }
        int popIndex = 0,pushIndex = 0;
        while(popIndex < popped.length){
            for(;pushIndex <= map.get(popped[popIndex]);pushIndex++){
                stack.push(pushed[pushIndex]);
            }
            if(stack.pop() != popped[popIndex]){
                return false;
            }
            popIndex++;
        }
        return true;
    }


}
