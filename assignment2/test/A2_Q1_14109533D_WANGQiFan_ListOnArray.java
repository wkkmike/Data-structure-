
import java.util.Arrays;
import java.util.stream.Collectors;

public class ListOnArray {
	private int[] data;
	
	public ListOnArray () {
		data = new int[16];
		data[0] = 0;
		data[15] = 1;
		data[13] = 0;
		for(int i = 2; i <= 12; i += 2){
			data[i] = i+1;
		}
	}
	
	public boolean isEmpty() {
		if(data[0] != 0)
		return false;
		else return true;
	}
	
	public void insertAtFront(int x) {
		if(isEmpty()){
			data[0] = 1;
			data[1] = x;
			data[15] = data[2];
			data[2] = 0;
		}
		else{
			data[data[15]] = x;
			int temp = data[15];
			data[15] = data[temp + 1];
			data[temp + 1] = data[0];
			data[0] = temp;
		}
	}
	
	public void insertAtTail(int x) {
		if(isEmpty()) insertAtFront(x);
		int i = data[0];
		while(data[i + 1] != 0) i = data[i + 1];
		data[i+1] = data[15];
		data[data[15]] = x;
		int temp = data[data[15] + 1];
		data[data[15] + 1] = 0;
		data[15] = temp;
	}
	
	public int deleteFront() {
		if(isEmpty()){
			System.out.println("the list is empty, you can't delete");
			return 0;
		}
		else{
			int temp = data[0];
			data[0] = data[data[0] + 1];
			int datah = data[data[0]];
			data[temp] = 0;
			data[temp + 1] = data[15];
			data[15] = temp;
			if(data[0] == data[15])data[0] = 0;
			return datah;
		}
	}
	
	public int deleteTail() {
		if(isEmpty()){
			System.out.println("the list is empty, you can't delete");
			return 0;
		}
		else{
			if(data[data[0] + 1] == 0){
				int x = deleteFront();
				return x;
			}
			int last2 = data[0];
			int last = data[data[0] + 1];
			while(data[last + 1] != 0){
				last2 = last;
				last = data[last + 1];
			}
			data[last2 + 1] = 0;
			int datah = data[last];
			data[last] = 0;
			data[last + 1] = data[15];
			data[15] = last;
			return datah;
		}
	}
	
	public int delete(int x) {
		if(isEmpty()){
			System.out.println("the list is empty");
			return 0;
		}
		if(data[data[0]] == x){
			deleteFront();
			return x;
		}
		int last2 = data[0];
		int last = data[data[0] + 1];
		while(data[last + 1] != 0 && data[last] != x){
			last2 = last;
			last = data[last + 1];
		}
		if(data[last] != x){
			System.out.println("no such element");
			return 0;
		}
		data[last2 + 1] = data[last + 1];
		data[last] = 0;
		data[last + 1] = data[15];
		data[15] = last;
		return x;
	}

	// this method should print out the numbers in the list in order
	// for example, after the demonstration, it should be "75, 99, 38, 69, 87"
	public String toString() {
		if(isEmpty()){
			return "empty list";
		}
		int ele = data[0];
		StringBuffer sb = new StringBuffer("");
		do{
			sb.append(data[ele] + ", ");
			ele = data[ele + 1];
		} while(ele != 0);
		String str = sb.toString();
		return str;
	}
	
	public static void main(String[] args) {
		// A good idea is to print out the content of the array after each step.
		// System.out.println( Arrays.stream(list.data).mapToObj(Integer::toString).collect(Collectors.joining(", ")) );
		// WARNING: this output should be different from the toString method.
		ListOnArray list = new ListOnArray();
		list.insertAtFront(75);
		list.insertAtTail(99);
		list.insertAtTail(85);
		list.insertAtTail(38);
		list.insertAtTail(49);
		list.insertAtTail(69);
		list.delete(85);
		list.delete(49);
		list.insertAtTail(87);
		System.out.println(list.toString());
	}
}