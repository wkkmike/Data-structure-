
public class CustomerList {
	private CustomerNode head;  //firstNode
	private CustomerNode tail;  //lastNode

	public CustomerList () {
		head = tail = null; 
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void insertAtFront(String a) {
		CustomerNode newNode = new CustomerNode(a);
        newNode.next = head;
        head = newNode;
        if (tail == null) tail = head;
	}

	public void insertAtEnd(String a) {
        if (head == null) {
            insertAtFront(a);
            return;
        }
        CustomerNode newNode = new CustomerNode(a);
        tail.next = newNode;
        tail = tail.next;
	}

	public void insertAfter(CustomerNode node, String a) {
		CustomerNode newNode = new CustomerNode(a);
		if (node == null) {
			System.out.println("Oops...");
			return;
		}
		newNode.next = node.next;
		node.next = newNode;
	}

	public CustomerNode search(String a) {
		CustomerNode cur = head;
		while(cur != null && cur.name != a) cur = cur.next;
	    return cur;
	}

	public String deleteFirst() {
        if (head == null) {
            System.out.println("Oops...");
			return "no such element";
        }
		CustomerNode deleted = head;
        String name = head.name;
        head = head.next;
        deleted.next = null;
        if (head == null) tail = null;
        return name;
	}

	public String deleteLast() {
		if (head == tail) return deleteFirst();
		CustomerNode cur = head;
		while(cur.next != tail) cur = cur.next;
        String data = tail.name;
        tail = cur;
        tail.next = null;
        return data;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		CustomerNode cur = head;
		while(cur != null) { 
			sb.append(cur.name);
			if (cur.next != null) sb.append(", ");
			cur = cur.next;
		}
	    return sb.toString();
	}
	
	public void reverse(){
		if(head == tail) return;
		tail = head;
		CustomerNode temp = null;
		while(head.next != null){
			CustomerNode imm = head.next;
			head.next = temp;
			temp = head;
			head = imm;
		}
		head.next = temp;
	}
	
	public static void main(String[] args){
		CustomerList list = new CustomerList();
		list.insertAtEnd("a");
		list.insertAtEnd("b");
		list.insertAtEnd("c");
		list.insertAtEnd("d");
		list.insertAtEnd("e");
		list.insertAtEnd("f");
		list.insertAtEnd("g");
		list.insertAtEnd("h");
		list.insertAtEnd("i");
		list.insertAtEnd("j");
		list.reverse();
		System.out.println(list.toString());
	}
}
