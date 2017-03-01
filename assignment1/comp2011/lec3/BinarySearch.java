package comp2011.lec3;

import java.util.Arrays;
import java.util.stream.Collectors;
public class BinarySearch {

	// the recursive version
	public static int binarySearch(int[] a, int l, int h, int key) {
		if(l > h) return -1;
		int m = l + (h - l) / 2;
		if (a[m] == key) return m;
		return (a[m] > key)?binarySearch(a, m + 1, h, key): binarySearch(a, l, m - 1, key);
	}
	
	// the non-recursive (iterative) version
	public static int binarySearch(int[] a, int key) {
		int l = 0, h = a.length - 1; 
		while (l <= h) {
			int m = l + (h - l) / 2;
			if (key > a[m]) l = m + 1;
			else if (key < a[m]) h = m - 1;
			else return m;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a = new int[20];
		for (int i = 0; i < 20; i++) a[i] = i + 1;
		System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
		System.out.println(binarySearch(a, 10));
	}
}
