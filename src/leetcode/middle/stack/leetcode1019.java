package leetcode.middle.stack;/*
 *
 * @Param
 */

import leetcode.middle.link.entity.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个长度为 n 的链表 head
 *
 * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，
 * 设置 answer[i] = 0 。
 */
public class leetcode1019 {
    //单调栈
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();//用于记录结果
        Deque<int[]> deque = new ArrayDeque<>();//使用单调栈
        ListNode node = head;
        int index = -1;
        while(node != null){
            index++;
            list.add(0);//先填充0，后续有更大的值就会进行覆盖
            //栈不为空：说明之前有节点还没有找到更大的值，需要与这一个节点进行对比
            while(!deque.isEmpty() && deque.peek()[0] < node.val){
                //队列中第二个元素就是对应节点的下标
                list.set(deque.pop()[1],node.val);
            }
            //把该结点的值以及对应的下标一起存入栈中
            deque.push(new int[]{node.val,index});
            node = node.next;
        }
        int size = list.size();
        int[] res = new int[size];
        for(int i = 0;i < size;i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
