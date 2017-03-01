package comp2011.lec1;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Sorting {

	public static void selectionSort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n - 1; i++) { //version 0: i < n
			int min = i;
			for (int j = i + 1; j < n; j++) //version 0: j = i
				if (a[j] < a[min]) min = j;
			
			//swap: somebody suggested to swap only when a[min] != a[i]
			int temp = a[min];
			a[min] = a[i];
			a[i] = temp;
		}
	}
	
	public static void main(String[] args) {
		//version 0: int[] a = {1, 20, 9, 4, 28, 2}; 
		int size = 15;
		int[] a = new int[size];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < size; i++)
			a[i] = random.nextInt(200);
		System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
		selectionSort(a);
		System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
	}
}
