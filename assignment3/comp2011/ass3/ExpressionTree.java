package comp2011.ass3;

class TreeNode {
    public String toString() {
        return "";
    }
}

public class ExpressionTree {
    public TreeNode root;

    public void inorder() {inorder(root);}
    public void inorder(TreeNode r) {    }

    public void preorder() {preorder(root);}
    public void preorder(TreeNode r) {    }

    public void postorder() {postorder(root);}
    public void postorder(TreeNode r) {    }
    
    public int size() { 
    	return -1;
    }
    
    public int recSize() { return recSize(root); } 
    public int recSize(TreeNode node) { 
    	return -1;
    }
}
