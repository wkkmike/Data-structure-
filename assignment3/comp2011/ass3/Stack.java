package comp2011.ass3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Stack {
	// Using an array is stupid, but it suffices for this assignment.
	private TreeNode[] data; 
	private int top;
	
	public Stack() {
		data = new TreeNode[32];
		top = -1;
	}
	
	public void push(TreeNode c) {
		data[++top] = c;
	}
	
	public TreeNode pop() {
		if(isEmpty()) System.out.println("Hey, I'm still empty");
		return data[top--];
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	
	public String toString() {
		return Arrays.stream(data, 0, top + 1).map(TreeNode::toString).collect(Collectors.joining(", "));
	}
}
