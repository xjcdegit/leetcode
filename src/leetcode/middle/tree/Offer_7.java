package leetcode.middle.tree;/*
 *
 * @Param
 */

import leetcode.easy.Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Offer_7 {
    private Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<Integer,Integer>();
        for(int i = 0; i < n; i++){
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder,inorder,0, n - 1, 0 , n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder,
                                int preorder_left, int preorder_right, int inorder_left, int inorder_right){
        if(preorder_left >= preorder_right){
            return null;
        }
        int inorder_root = indexMap.get(preorder[preorder_left]);//获取该子树的根节点
        TreeNode root = new TreeNode(preorder[preorder_left]);

        int Tree_num = inorder_root - inorder_left;//获取该左子树的节点个数
        root.left = myBuildTree(preorder,inorder,preorder_left + 1,preorder_left + Tree_num
           ,inorder_left,inorder_left + Tree_num - 1);
        root.right = myBuildTree(preorder,inorder,preorder_left + Tree_num + 1,preorder_right
                , inorder_root + 1,inorder_right);

        return root;
    }
}
