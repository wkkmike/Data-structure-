public class IntQueue {
	private int[] data;
	private int rear;
	
	public IntQueue() {
		data = new int[16];
		rear = -1;
	}

	public void enqueue(int e) {
		data[++rear] = e;
	}
	
	public int dequeue() {
		int a = data[0];
		for (int i = 0; i < rear; i++) 
			data[i] = data[i+1]; 
		rear--;
		return a;
	}
	
	public boolean isEmpty() {
		return rear < 0;
	}
}
