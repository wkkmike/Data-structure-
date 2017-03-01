
public class CustomerQueue {
	private CustomerList queue;
	public CustomerQueue(){
		queue = new CustomerList();
	}
	
	public void enqueue(String a){
		queue.insertAtEnd(a);
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public String dequeue(){
		if(isEmpty()) return "The queue is Empty";
		String name = queue.deleteFirst();
		return name;
	}
	
	public CustomerQueue[] split(int k){
		CustomerQueue[] Queues = new CustomerQueue[k];
		for(int i = 0; i<k; i++) Queues[i] = new CustomerQueue();
		int i = 0;
		while(!isEmpty()){
			Queues[i % k].enqueue(this.dequeue());
			i++;
		}
		return Queues;
	}
	
	public static void main(String[] ars){
		CustomerQueue test = new CustomerQueue();
		test.enqueue("a");
		test.enqueue("b");
		test.enqueue("c");
		test.enqueue("d");
		test.enqueue("e");
		test.enqueue("f");
		test.enqueue("g");
		test.dequeue();
		System.out.println(test.queue.toString());
		CustomerQueue[] test2 = test.split(2);
		System.out.println(test2[1].queue.toString());
		System.out.println(test2[0].queue.toString());
	}
}
