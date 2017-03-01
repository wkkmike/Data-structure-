package comp2011.ass3;
import java.util.StringTokenizer;

import comp2011.ass3a.ExpressionTree;

public class Postfix {
    public static boolean isOperator(char c) {
        return ((c == '+') || (c == '-') || (c == '*') || (c == '/'));
    }
    
    public static boolean isWhitespace(char c) {
        return ((c == ' ') || (c == '\n') || (c == '\t') || (c == '\r'));
    }

    public static ExpressionTree buildTree(String postfix){
    	return null;
    }

    public static void main(String[] args) {
    	ExpressionTree tree = buildTree("5 2 1 - - 6 5 / +");
    	tree.inorder();
    	System.out.println(tree.size());
    	System.out.println(tree.recSize());
    	// (((5)-((2)-(1)))+((6)/(5)))
    	// ((5-(2-1))+(6/5))
    	// 5-(2-1) + 6/5
    }
}
