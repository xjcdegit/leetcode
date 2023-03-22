package leetcode.middle.BFS;/*
 *
 * @Param
 */

import leetcode.easy.Tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode_Offer32 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        boolean isOrder = true;
        Queue<TreeNode> queue = new LinkedList<>();
        //堆栈  pop  push
        //队列  add  poll
        queue.add(root);
        List<List<Integer>> res = new LinkedList<>();

        while(!queue.isEmpty()){
            Deque<Integer> temp = new LinkedList<>();
            int size = queue.size();
            for(int i = 0;i < size;i++){
                TreeNode cur = queue.poll();
                if(isOrder){
                    temp.offerLast(cur.val);
                }else{
                    temp.offerFirst(cur.val);
                }
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            isOrder = !isOrder;
            res.add(new LinkedList<>(temp));
        }
        return res;
    }
}
