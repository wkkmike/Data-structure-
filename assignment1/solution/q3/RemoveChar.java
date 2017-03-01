import java.util.Scanner;
import java.util.Arrays;

public class RemoveChar {
	public static char[] removeChar(char[] a, int length){
			for(int i = 0; i < length - 1; i++){
				if(a[i] == a[i+1]){
					for(int j = i + 2; j < length; j++){
						a[j-2] = a[j];
					}
					length -= 2;
					return removeChar(a, length);
				}
			}
		return Arrays.copyOfRange(a, 0, length);
	}
	public static void main(String[] args){
		System.out.println("Please input a string: ");
		Scanner scanner = new Scanner(System.in);
		String data = scanner.nextLine();
		char[] word = data.toCharArray();
		int length = word.length;
		char[] result = removeChar(word, length);
		String output = String.valueOf(result);
		System.out.println(output);
	}
}
