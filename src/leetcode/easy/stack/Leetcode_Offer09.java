package leetcode.easy.stack;/*
 *
 * @Param
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode_Offer09 {
    Deque<Integer> instack;
    Deque<Integer> outstack;
    public Leetcode_Offer09() {
        instack = new ArrayDeque<>();
        outstack = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        instack.push(value);
    }

    public int deleteHead() {
        if (outstack.isEmpty()){
           if(instack.isEmpty()){
               return -1;
           }
           //将instack中的数据一次传入到outstack中
            inToOut();
        }
        return outstack.pop();
    }
    public void inToOut(){
        while(!instack.isEmpty()){
            outstack.push(instack.pop());
        }
    }
}
