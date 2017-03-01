import java.util.Arrays;

/**
 * Created by michael on 2016/11/26.
 */

class TreeNode{
    int data;
    public TreeNode leftChild, rightChild;

    public TreeNode(int data) {
        this.data = data;
    }

    public String toString(){
        return Integer.toString(data);
    }
}


public class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree(){
        root = null;
    }

    public TreeNode insert(int[] a, int start, int end){
        TreeNode t = new TreeNode(a[(start+end)/2]);
        if(start >= end){
            t.leftChild = null;
            t.rightChild = null;
            return t;
        }
        else{
            if(start <= (start + end)/2 -1) t.leftChild = insert(a, start, (start+end)/2-1);
            t.rightChild = insert(a, (start+end)/2+1, end);
            return t;
        }
    }

    public void inorder(TreeNode curRoot){
        if (curRoot == null)
            return;
        inorder(curRoot.leftChild);
        System.out.print(curRoot + ", ");
        inorder(curRoot.rightChild);
    }

    public void postorder(TreeNode curRoot){
        if(curRoot == null) return;;
        postorder(curRoot.leftChild);
        postorder(curRoot.rightChild);
        System.out.print(curRoot + ", ");
    }

    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        System.out.println("Original array: ");
        System.out.println(Arrays.toString(a));
        BinarySearchTree result = new BinarySearchTree();
        result.root = result.insert(a,0,13);
        System.out.print("\nInorder: ");
        result.inorder(result.root);
        System.out.print("\nPostorder: ");
        result.postorder(result.root);
    }
}
