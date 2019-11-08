package Interview_4;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {


    //求一棵二叉树的镜像
    public TreeNode MakeMirror(TreeNode root) {
        if(root == null){
            return null;
        }
        if(root.left == null && root.right == null){
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        MakeMirror(root.left);
        MakeMirror(root.right);
        return root;
    }

    //层序遍历一棵二叉树
    public void levelOrder(TreeNode root) {
        if(root == null){
            return;
        }
        //创建队列辅助进行层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        //先插入root
        queue.offer(root);
        while(!queue.isEmpty()){
            //循环取队首元素
            TreeNode cur = queue.poll();
            System.out.print(cur.val + " ");
            //将队首元素的左右子树的根节点插入队列
            if(cur.left != null){
                queue.offer(cur.left);
            }
            if(cur.right != null){
                queue.offer(cur.right);
            }
        }
    }

    public static TreeNode build() {
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(2);
        TreeNode C = new TreeNode(3);
        TreeNode D = new TreeNode(4);
        TreeNode E = new TreeNode(5);
        TreeNode F = new TreeNode(6);
        TreeNode G = new TreeNode(7);
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        //E.left = G;
        //C.right = F;
        return A;
    }

    public boolean isComplete(TreeNode root) {
        if(root == null){
            return true;
        }
        if(root.left == null && root.right == null){
            return true;
        }

        //设置第一阶段标记位
        boolean needNoChild = false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();

            if(!needNoChild){
                if(cur.left != null && cur.right != null){
                    //阶段1 每个节点都有左右子树
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }else if(cur.left == null && cur.right != null){
                    //特殊节点 只有右子树 不是完全二叉树
                    return false;
                }else if(cur.left != null && cur.right == null){
                    //特殊临界节点 只有左子树 开启阶段2
                    queue.offer(cur.left);
                    needNoChild = true;
                }else{
                    //特殊临界节点 没有子树 开启阶段2
                    needNoChild = true;
                }
            }else{ // 阶段2 剩余节点没有子树
                if(cur.left != null || cur.right != null){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = build();
        Solution binaryTree = new Solution();
        binaryTree.levelOrder(root);
        System.out.println();
        System.out.println(binaryTree.isComplete(root));
    }
}
