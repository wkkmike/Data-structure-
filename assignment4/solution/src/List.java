class Node {
	int data;
	Node next;

	public Node(int a) {data = a; next = null;}
}

/*
 * The list added to the class comp2011.lec4.List
 * the size and 
 * a toArray method.
 */
public class List {
	private Node head;  //firstNode
	private int size;
	
	public List () {
		head = null;
		size = 0;
	}

	public Node getHead(){
		return head;
	}

	public boolean isEmpty() {
		return head == null;
	}
	
	public void insertAtFront(int a) {
		Node node = new Node(a);
		node.next = head;
		head = node;
		size++;
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
		size++;
	}

	public void insertAfter(Node node, int a) {
		Node newNode = new Node(a);
		if (node == null) {
			System.out.println("Oops...");
			return;
		}
		newNode.next = node.next;
		node.next = newNode;
		size++;
	}

	public Node search(int a) {
		Node cur = head;
		while(cur != null && cur.data != a) cur = cur.next;
	    return cur;
	}

	public int delete(int a){
		if(search(a) == null) return -1;
		if(search(a) == head) return deleteFirst();
		Node first = head;
		Node second = head.next;
		while(second.data != a){
			first = second;
			second = second.next;
		}
		first.next = second.next;
		return a;
	}

	public int deleteFirst() {
		if (head == null) {
			System.out.println("Oops...");
			return -1;
		}
		Node node = head;
		head = head.next;
		node.next = null;
		size--;
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
		size--;
	    return cur.data;
	}

	// can you make this work for the list with tail?
    public void insertionSort() {
		System.out.println("\nstart sorting");
        if (head == null || head.next == null) return;
        Node pOfCur = head;
        Node cur = head.next;

        int i = 0;
        while(cur != null) {
    		System.out.println("turn " + (i++) + ": " + this);
            Node n = head;
            if (cur.data < n.data) {
                pOfCur.next = cur.next;
                cur.next = head;
                head = cur;
                cur = pOfCur.next;
                continue;
            }
            while(n.next != null && n.next.data < cur.data) {
                n = n.next;
            }
            if (n.next == cur) {
                pOfCur = cur;
                cur = cur.next;
                continue;
            }
            pOfCur.next = cur.next;
            cur.next = n.next;
            n.next = cur;

            cur = pOfCur.next;
        }
    }

    public int length() {
    	return size;
    }
    
    public int[] toArray() {
    	int[] a = new int[size];
    	Node cur = head;
    	for (int i = 0; i < size; i++) {
    		a[i] = cur.data;
			cur = cur.next;
    	}
    	return a;
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
		list.insertionSort();
		System.out.println("after sorting: " + list + "\n");
		System.out.println("delete the first, which is " + list.deleteFirst());		
		System.out.println(list);
		System.out.println("delete the last, which is " + list.deleteLast());
		System.out.println(list);
	}
}