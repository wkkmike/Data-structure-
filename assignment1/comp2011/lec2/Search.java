package comp2011.lec2;

import java.util.*;
import java.security.SecureRandom;
import java.util.stream.Collectors;

public class Search {
 
    public static int linearSearch(int[] a, int k) {
        for (int i = 0; i < a.length; i++) 
	    if (a[i] == k) return i;
        return -1;
    }
    
    public static void main(String[] args){
	int size = 15; 
	int k = 10;
	int[] a = new int[size];
	SecureRandom random = new SecureRandom();
	for (int i = 0; i < size; i++) a[i] = random.nextInt(20);
	System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
	System.out.println(k + " appears in position " + linearSearch(a, k));
   }
}
