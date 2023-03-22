package leetcode.middle.tree;/*
 *
 * @Param
 */

import leetcode.easy.Tree.TreeNode;

//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
public class Leetcode_Offer26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(B == null){
            return false;
        }
        return Preselect(A,B);
    }

    //中序查找
    public boolean Preselect(TreeNode A,TreeNode B){
        if(A == null){
            return false;
        }
        if(A.val == B.val){
            if(judge(A,B)){
                // System.out.println(B.val);
                return true;
            }
        }
        //Preselect(A.left,B)：判断A的左子树中是否存在 B节点树
        //Preselect(A.left,B)：判断A的右子树中是否存在 B节点树
        return Preselect(A.left,B) || Preselect(A.right,B);
    }

    //判断以A为根节点的树是否包含B节点为根节点的树
    public boolean judge(TreeNode A,TreeNode B){
        if(B == null){
            return true;
        }
        if(A == null){
            return false;
        }


        if(B.val == A.val){
            return judge(A.left,B.left) && judge(A.right,B.right);
        }else{
            return false;
        }
    }
}
