package leetcode.easy.Tree;/*
 *
 * @Param
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    //编写 二叉树 前序遍历的方法：先输出 父节点 ，再遍历左子树和右子树
    public void PreOrder(){
        System.out.println(this);//先输出父结点
        //递归向左子树遍历
        if(this.left != null) {
            this.left.PreOrder();
        }
        //递归向右子树遍历
        if(this.right != null){
            this.right.PreOrder();
        }

    }
    //中序遍历：先遍历左子树， 再输出父节点，再遍历右子树
    public void infixOrder(){
        //遍历左子树
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //遍历右子树
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历：先遍历左子树、右子树，最后输出父节点
    public void curOrder(){
        if(this.left != null){
            this.left.curOrder();
        }
        if(this.right != null){
            this.right.curOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public TreeNode  preOrderSearch(int no){
        System.out.println("前序遍历次数+1");
        if(this.val == no){//找到了该排名
            return this;
        }
        //定义一个辅助结点
         TreeNode resNode = null;
        //判断当前结点的左节点是否为空，，若不为空，继续向前递归查找
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        //判断
        if(resNode != null){//说明我们在左子树中找到了目标结点
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        //在右边不管有没有找到，直接返回resNode
        return resNode;
    }
    //中序遍历查找
    public TreeNode  infixOrderSearch(int no){
         TreeNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){//说明在左子树中找到了
            return resNode;
        }
        System.out.println("中序遍历次数+1");
        if(this.val == no){
            return this;
        }
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public TreeNode  postOrderSearch(int no){
         TreeNode resNode = null;
        if(this.left != null){//遍历左子树
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){//左子树中找到
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        System.out.println("后序遍历查找次数+1");
        //如果左右子树都没有找到，比较该no与跟节点的no
        if(this.val == no){
            return this;
        }
        return resNode;
    }
}