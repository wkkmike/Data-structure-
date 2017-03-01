package comp2011.ass3;

import java.util.*;
import java.security.SecureRandom;
import java.util.stream.Collectors;
 
public class Sorting {
 
    public static int[] selection(int[] arr) {
        return arr;
    }

    // the recursive version of selection sort.
    public static void selection(int[] a, int begin, int end) {
    	if (begin + 1 >= end) return;
    	int min = begin;
    	for (int i = begin + 1; i < end; i++) 
    		if (a[i] < a[min]) min = i;
    	int temp = a[begin];
    	a[begin] = a[min];
    	a[min] = temp;
    	selection(a, begin + 1, end);
    }
    
    // we're not using this because it requires us to build
    // two auxiliary arrays a1 and a2;
    // this is not a problem in C++, but needs extra work in Java. 
    public static void merge(int[] a1, int[] a2, int[] a) {
        int ind1 = 0, ind2 = 0, ind = 0;
        while (ind1 < a1.length - 1 && ind2 < a2.length - 1) {
            if ( a1[ind1] < a2[ind2] ) a[ind++] = a1[ind1++];
            else a[ind++] = a2[ind2++];
            // or, if you like,
            // a[ind++] = (a1[ind1] < a2[ind2])? a1[ind1++]:a2[ind2++];
        }
        while (ind1 < a1.length) a[ind++] = a1[ind1++];
        while (ind2 < a2.length) a[ind++] = a2[ind2++];
    }
    
    public static void merge(int[] a, int begin, int mid, int end) {
        int low = begin, high = mid + 1, i = 0;
        int [] workspace = new int[end - begin + 1]; 
        while (low <= mid && high <= end) 
            workspace[i++] = (a[low] < a[high])?a[low++]:a[high++];
            /*
            if (a[low] < a[high])
                workspace[i++] = a[low++];
            else 
                workspace[i++] = a[high++];
            */
        // only one of the following will be executed
        while (low <= mid) workspace[i++] = a[low++];
        while (high <= end) workspace[i++] = a[high++];
        for (i = 0; i < workspace.length; i++)
            a[begin + i] = workspace[i];
    }
    
    public static void mergesort(int[] a, int begin, int end) {
    	if (end <= begin) return;
    	int mid = begin + (end - begin) / 2;
    	mergesort(a, begin, mid);
    	mergesort(a, mid + 1, end);
    	merge(a, begin, mid, end);
    }
    
    public static void mergesort(int[] arr) {
        mergesort(arr, 0, arr.length - 1);
    }
    public static void swap(int[] a, int x, int y) {
    	int temp = a[x];
    	a[x] = a[y];
    	a[y] = temp;
    }
    public static void quicksort(int[] a, int begin, int end) {
    	if (end <= begin) return;
    	int pivot = a[end];
    	int i = begin;
    	for (int j = i; j <= end; j++) {
    		if (a[j] <= pivot) swap(a, j, i++);
    	}
    	quicksort(a, begin, i - 2);
    	quicksort(a, i, end);
    }
    
    public static void quicksort(int[] a) {
        quicksort(a, 0, a.length - 1);
    }
    
    public static void hoare(int[] a, int low, int high) {
    }
    
    public static void main(String args[]){
		// you need to change the sie and turns if it runs too slow,
		// or if the differences are too insignificant.
		//
		// try at least three different sizes.
		int size = 10, turns = 1;
		long startTime = 0, duration = 0;

		int[] a = new int[size];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < size; i++)
			a[i] = random.nextInt(size);

		System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
		hoare(a, 0, size - 1);
		System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")));

		startTime = System.currentTimeMillis();
		int[] b = new int[size];
		for (int j = 0; j < 0; j++) {
			for (int i = 0; i < size; i++)
				b[i] = a[i];
			mergesort(b);
		}
		duration = (System.currentTimeMillis() - startTime);
		System.out.println(turns + " runs of mergesort takes... " + (duration / 1000.) + " seconds.");
    }
}
