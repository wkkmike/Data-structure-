
public class CustomerNode {
	String name ;
	CustomerNode next ;
	CustomerNode ( String name ) {
		this . name = name ;
		next = null ;
	}
	public String toString () {
		return name ;
	}
}