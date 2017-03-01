import java.security.SecureRandom;
import java.util.Arrays;

public class Barray {
	int[][] barray;
	int k;
	int size;
	
	Barray(){
		k = 1;
		size = 0;
		barray = new int[2][];
	}
	
	Barray(int k){
		this.size = 0;
		this.k = k;
		barray = new int[(int) Math.pow(2, k)][];
	}
	
	public boolean checkSize(int k, int size){
		if (Math.pow(2, 2*k) >= size) return true;
		else return false;
	}
	
	public boolean checkShrink(int k, int size){
		if(Math.pow(2, 2*(k - 1)) >= size) return true;
		else return false;
	}
	
	public void add(int item, int pos){ //complexity = O(n)
		if(pos > size + 1){
			System.out.println("no such position avaiable");
			return;
		}
		this.addp(item);
		for(int i = size; i > pos; i--){
			this.set(i, this.get(i-1));
		}
		this.set(pos, item);
	}
	
	public void addp(int item){
		
		 if(checkSize(k, size + 1)){
			 size++;
			 int length = (int) Math.pow(2, k);
			 int row = (size-1) / length;
			 int column = (size - 1) % length;
			 if(column != 0) barray[row][column] = item;
			 else{
				 barray[row] = new int[(int) Math.pow(2, k)];
				 barray[row][column] = item;
			 }
		 }
		 
		 else{
			 Barray b = new Barray(k+1);
			 for(int i = 0; i < Math.pow(2, k); i++){
				 for(int j = 0; j < Math.pow(2, k); j++){
					 b.addp(barray[i][j]);
				 }
			 }
			 k++;
			 barray = new int[(int) Math.pow(2, k)][];
			 size = 0;
			 int count = 0;
			 for(int i = 0; i < Math.pow(2, b.k); i++){
				 for(int j = 0; j < Math.pow(2, b.k) && count < b.size; j++){
					 this.addp(b.barray[i][j]);
					 count++;
					}
			}
			 this.addp(item);
		 }
	}
	
	public int search(int item){ //complexity = O(n)
		int count = 0;
		for(int i = 0; i < Math.pow(2, k); i++){
			 for(int j = 0; j < Math.pow(2, k) && count < size + 1; j++){
				 if(barray[i][j] == item){
					// int[] a = {i,j};
					 return count + 1;
				 }
				 count++;
			 }
		 }
		//int[] b={-1,-1};
		return -1;
	}
	
	public void delete(int pos){// complexity = O(n)
		int[] position = new int[2];
		int length = (int) Math.pow(2, k);
		position[0] = (pos -1) / length;
		position[1] = (pos - 1) % length;
		if(pos > size) System.out.println("No such element in this barray!");
		else{
			int count = position[0] * ((int)Math.pow(2, k)) + position[1];
			int temp = count;
			for(int j = position[1]; j < Math.pow(2, k) - 1; j++){
				barray[position[0]][j] = barray[position[0]][j+1];
				count++;
			}
			if(((size - temp - 1)/(int)Math.pow(2, k)) != 0){
				barray[position[0]][(int)Math.pow(2, k) - 1] = barray[position[0]+1][0];
				count++;
				for(int i = position[0] + 1; i < Math.pow(2, k); i++){
					for(int j = 0; j < (Math.pow(2, k) - 1) && (count < size - 1); j++){
						barray[i][j] = barray[i][j+1];
						count++;
					}
					if(count < size - 1){
						barray[i][(int)Math.pow(2, k) - 1] = barray[i + 1][0];
						count++;
					}
				}
			}
			size--;
			if(checkShrink(k, size)){
				count = 0;
				Barray b = new Barray(k-1);
				for(int i = 0; i < Math.pow(2, k); i++){
					for(int j = 0; j < Math.pow(2, k) && count < size; j++){
						b.addp(barray[i][j]);
						count++;
					}
				}
				k--;
				barray = new int[(int) Math.pow(2, k)][];
				size = 0;
				count = 0;
				for(int i = 0; i < Math.pow(2, b.k); i++){
					for(int j = 0; j < Math.pow(2, b.k) && count < b.size; j++){
						this.addp(b.barray[i][j]);
						count++;
					}
				}
			}
		}
	}
	
	public int get(int position){ //complexity = O(1)
		if(position > size){
			System.out.println("There isn't any element in this position.");
			return -1;
		}
		else{
			int length = (int) Math.pow(2, k);
			int row = (position-1) / length;
			int column = (position - 1) % length;
			return barray[row][column];
		}
	}
	
	public void set(int position, int num){ //omplexity = O(1)
		if(position > size){
			System.out.println("There isn't such position");
		}
		else{
			int length = (int) Math.pow(2, k);
			int row = (position-1) / length;
			int column = (position - 1) % length;
			barray[row][column] = num;
		}
	}
	
	/*private void quick_sort_recursive(int start, int end) {
		if (start >= end)
			return;
		int mid = arr[end];
		int left = start, right = end - 1;
		while (left < right) {
			while (arr[left] < mid && left < right)
				left++;
			while (arr[right] >= mid && left < right)
				right--;
			swap(left, right);
		}
		if (arr[left] >= arr[end])
			swap(left, end);
		else
			left++;
		quick_sort_recursive(start, left - 1);
		quick_sort_recursive(left + 1, end);
	}*/
	
	public void quickSort(int start, int end){ //complexity = O(n log n)
		if(start >= end) return;
		int mid = get(end);
		int left = start;
		int right = end - 1;
		while (left < right) {
			while (get(left) < mid && left < right)
				left++;
			while (get(right) >= mid && left < right)
				right--;
			int temp = get(right);
			set(right, get(left));
			set(left, temp);
		}
		if (get(left) >= mid){
			int temp = get(left);
			set(left, get(end));
			set(end, temp);
		}
		else
			left++;
		quickSort(start, left - 1);
		quickSort(left + 1, end);
	}
	
	public static void main(String[] args){
		/*Barray test = new Barray();
		test.addp(5);
		test.addp(6);
		test.addp(7);
		test.addp(8);
		test.add(10, 3);
		*/
		
		
		//the quick sort of the same size data for barray is slowly than array;
		//the average overhead of barray is the square root of n;
		int size = 10000, turns = 200;
		long startTime = 0, duration = 0;
		int[] a = new int[size];
		int[] b = new int[size];
		Barray test = new Barray();
		for(int i = 0; i < size; i++) test.addp(a[i]);
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < size; i++)
			a[i] = random.nextInt(size);
		startTime = System.currentTimeMillis();
		for (int j = 0; j < turns; j++) {
			for (int i = 0; i < size; i++){
				test.set(i+1, a[i]);
				b[i] = a[i];
			}
				Arrays.sort(b);
		}
		duration = (System.currentTimeMillis() - startTime);
		System.out.println(turns + " runs of quickSort for array takes " + (duration / 1000.) + " seconds.");
		startTime = System.currentTimeMillis();
		
		startTime = System.currentTimeMillis();
		for (int j = 0; j < turns; j++) {
			for (int i = 0; i < size; i++){
				b[i] = a[i];
				test.set(i+1, a[i]);
			}
				test.quickSort(1, size);
		}
		duration = (System.currentTimeMillis() - startTime);
		System.out.println(turns + " runs of quickSort for barray takes " + (duration / 1000.) + " seconds.");
		startTime = System.currentTimeMillis();
	}
}
