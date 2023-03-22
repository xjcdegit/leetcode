package leetcode.middle.tree;/*
 *
 * @Param
 */


/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class Leetcode_Offer36 {
    Node pre,head;
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node node){
        if(node == null){
            return;
        }
        dfs(node.left);
        if(pre != null){
            pre.right = node;
        }else{
            head = node;
        }
        node.left = pre;
        pre = node;
        dfs(node.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
