import java.util.Arrays;
import java.util.stream.Collectors;

public class IntStack {
	private int[] data;
	private int top;
	
	public IntStack() {
		data = new int[64];
		top = -1;
	}
	
	public void push(int c) {
		data[++top] = c;
	}
	
	public int pop() {
		if(isEmpty()) System.out.println("Hey, I'm still empty");
		return data[top--];
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	
	public String toString() {
		return Arrays.stream(data, 0, top + 1).mapToObj(Integer::toString).collect(Collectors.joining(", "));
	}
}
