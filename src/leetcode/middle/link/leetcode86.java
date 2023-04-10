package leetcode.middle.link;/*
 *
 * @Param
 */

import leetcode.middle.link.entity.ListNode;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
public class leetcode86 {
    public ListNode partition(ListNode head, int x) {
        ListNode smallhead = new ListNode(0),bighead = new ListNode(1);
        ListNode smallNode = smallhead,bigNode = bighead;
        smallhead.next = head;
        ListNode node = head;
        while(node != null){
            if(node.val < x){
                smallNode.next = node;
                smallNode = smallNode.next;
                node = node.next;
                smallNode.next = null;
            }else{
                bigNode.next = node;
                bigNode = bigNode.next;
                node = node.next;
                bigNode.next = null;
            }
        }
        smallNode.next = bighead.next;
        return smallhead.next;
    }
}
