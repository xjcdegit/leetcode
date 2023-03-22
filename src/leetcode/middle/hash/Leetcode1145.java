package leetcode.middle.hash;/*
 *
 * @Param
 */


import leetcode.easy.Tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode1145 {
    public static void main(String[] args) {

    }
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode treeNode = root.preOrderSearch(x);
        int left = find(treeNode.left);
        int right = find(treeNode.right);
        int pre = n - left - right;
        int max = Math.max(Math.max(left,right),pre);
        return max > n - max;
    }

    /**
     * 计算root为头节点的二叉树节点个数
     * @param root
     * @return
     */
    public int find(TreeNode root){
        if(root == null){
            return 0;
        }else if(root.left == null && root.right == null){
            return 1;
        }else {
            return find(root.left) + find(root.right) + 1;
        }
    }

    //前序遍历查找
    public TreeNode preOrderSearch(TreeNode root,int no){
        System.out.println("前序遍历次数+1");
        if(root.val == no){//找到了该排名
            return root;
        }
        //定义一个辅助结点
        TreeNode resNode = null;
        //判断当前结点的左节点是否为空，，若不为空，继续向前递归查找
        if(root.left != null){
            resNode = root.left.preOrderSearch(no);
        }
        //判断
        if(resNode != null){//说明我们在左子树中找到了目标结点
            return resNode;
        }
        if(root.right != null){
            resNode = root.right.preOrderSearch(no);
        }
        //在右边不管有没有找到，直接返回resNode
        return resNode;
    }
}
