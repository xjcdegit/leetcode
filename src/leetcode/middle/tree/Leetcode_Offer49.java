package leetcode.middle.tree;/*
 *
 * @Param
 */

import leetcode.easy.Tree.TreeNode;

/*给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
每条从根节点到叶节点的路径都代表一个数字：
例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。
叶节点 是指没有子节点的节点。*/
public class Leetcode_Offer49 {
    public int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return root.val;
        }else if(root.left == null){
            root.right.val += root.val*10;
            return sumNumbers(root.right);
        }else if(root.right == null){
            root.left.val += root.val*10;
            return sumNumbers(root.left);
        }else{
            root.left.val += root.val*10;
            root.right.val += root.val*10;
            return sumNumbers(root.left) + sumNumbers(root.right);
        }
    }
}
