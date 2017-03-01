package comp2011.ass3;

class Node {
	int data;
	Node next;

	public Node(int a) {data = a; next = null;}
}

public class List {
	private Node head;  //firstNode
	
	public List () {
		head = null; 
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void insertAtFront(int a) {
		Node node = new Node(a);
		node.next = head;
		head = node;
	}

	public void insertAtEnd(int a) {
		Node node = new Node(a);
		// this check cannot be omitted
		// otherwise you get NullPointerException on an empty list.
		if (head == null) {
			insertAtFront(a);
			return;
		}
		Node cur = head;
		while(cur.next != null) {
			cur = cur.next;
		}
		cur.next = node;
	}

	public void insertAfter(Node node, int a) {
		Node newNode = new Node(a);
		if (node == null) {
			System.out.println("Oops...");
			return;
		}
		newNode.next = node.next;
		node.next = newNode;
	}

	public Node search(int a) {
		Node cur = head;
		while(cur != null && cur.data != a) cur = cur.next;
	    return cur;
	}

	public int deleteFirst() {
		if (head == null) {
			System.out.println("Oops...");
			return -1;
		}
		Node node = head;
		head = head.next;
		node.next = null;
	    return node.data;
	}

	public int deleteLast() {
		if (head == null || head.next == null) 
			return deleteFirst();
		Node second = head;
		Node cur = second.next;
		// when the thile loop finishes,
		// cur points to the last node.
		while(cur.next != null) {
			second = cur;
			cur = cur.next;
		}
		second.next = null;
	    return cur.data;
	}

    public void mergesort() {
    }

    public String toString() {
		StringBuilder sb = new StringBuilder(); 
		Node cur = head;
		while(cur != null) { 
			sb.append(cur.data);
			if (cur.next != null) sb.append(", ");
			cur = cur.next;
		}
	    return sb.toString();
	}

	public static void main(String[] args) {
		List list = new List();
		list.insertAtFront(37);
		list.insertAtFront(99);
		list.insertAtFront(12);
		// the list at page 88 of the slides.
		System.out.println(list);
		list.insertAtFront(75);
		System.out.println(list);
		list.insertAtEnd(38);
		System.out.println(list);
		list.insertAfter(list.search(12), 85);
		System.out.println(list);
		list.mergesort();
		System.out.println("after sorting: " + list + "\n");
		System.out.println("delete the first, which is " + list.deleteFirst());		
		System.out.println(list);
		System.out.println("delete the last, which is " + list.deleteLast());		
		System.out.println(list);
	}
}