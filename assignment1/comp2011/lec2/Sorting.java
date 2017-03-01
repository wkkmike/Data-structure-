package comp2011.lec2;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Sorting {

	// The following method was my first attempt. Somehow it worked, unbelievably.
	// There were two (possible) improvements proposed afterwards during the class.
	// If you remember them, you may want to try whether they really improve.
	public static void selectionSort(int[] b) {
		int n = b.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i; j < n; j++)
				if (b[j] < b[min])
					min = j;

			// swap
			int temp = b[min];
			b[min] = b[i];
			b[i] = temp;
		}
	}

	public static void insertionSort(int[] a) {
		int i, j, key, n = a.length;
		for (i = 1; i < n; i++) {
			key = a[i];
			for (j = i - 1; j >= 0 && a[j] > key; j--)
				a[j + 1] = a[j];
			a[j + 1] = key;
		}
	}
	
	public static void main(String[] args) {
		// you need to change the sie and turns if it runs too slow,
		// or if the differences are too insignificant.
		//
		// try at least three different sizes.
		int size = 10, turns = 1;
		long startTime = 0, duration = 0;

		int[] a = new int[size];
		int[] b = new int[size];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < size; i++)
			a[i] = random.nextInt(size);

		// If you would like to see the array (before or after sorting),
		// use the following to print out the content of the array.
		//
		// But please comment them off when you start testing. WHY?
		//
		System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
		selectionSort(a);
		System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")));

		if(true)  return;
		startTime = System.currentTimeMillis();
		for (int j = 0; j < turns; j++) {
			for (int i = 0; i < size; i++)
				b[i] = a[i];
			selectionSort(b);
		}
		duration = (System.currentTimeMillis() - startTime);
		System.out.println(turns + " runs of selectionSort takes " + (duration / 1000.) + " seconds.");
		startTime = System.currentTimeMillis();
		for (int j = 0; j < turns; j++) {
			for (int i = 0; i < size; i++)
				b[i] = a[i];
			insertionSort(b);
		}
		duration = (System.currentTimeMillis() - startTime);
		System.out.println(turns + " runs of insertionSort takes " + (duration / 1000.) + " seconds.");
		startTime = System.currentTimeMillis();
		for (int j = 0; j < turns; j++) {
			for (int i = 0; i < size; i++)
				b[i] = a[i];
			Arrays.sort(b);
		}
		duration = (System.currentTimeMillis() - startTime);
		System.out.println(turns + " runs of sort of Java takes... " + (duration / 1000.) + " seconds.");

		/*
		 * // you can use the following to try theor performance on an
		 * increasingly ordered array, for (int j = 0; j < turns; j++ ) {
		 * for(int i = 0; i < size; i++) b[i] = i; insertionSort(b); } // or an
		 * decreasingly ordered array; for (int j = 0; j < turns; j++ ) {
		 * for(int i = 0; i < size; i++) b[i] = size - i; insertionSort(b); }
		 */
	}
}
