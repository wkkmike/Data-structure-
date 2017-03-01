package comp2011.lec3;

public class Expression {

public static boolean isBalanced(char[] s) {
	int count = 0;
	for (int i = 0; i < s.length; i++) {
		if (s[i] == '(') count++;
		if (s[i] == ')') count--;
		if (count < 0) return false;
	}
	return count==0;
}

public static void main(String[] args) {
	String s = "(()(()))";
	System.out.println(s + (isBalanced(s.toCharArray())?" is ":" is not ") + "balanced.");
	s = "(()((()))";
	System.out.println(s + (isBalanced(s.toCharArray())?" is ":" is not ") + "balanced.");
}

}
