
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BinarySorting {
    // sort arr such that all female students come before male students. 
    public static void binarySort (Student[] arr) {
        int start = 0;
		int last = arr.length - 1;
		while(start < last){
			if(arr[start].gender == 0 && arr[last].gender == 1){
				start++;
				last--;
			}	
			else if(arr[start].gender == 0 && arr[last].gender == 0){
				start++;
			}
			else if(arr[start].gender == 1 && arr[last].gender == 0){
				Student temp = arr[start];
				arr[start] = arr[last];
				arr[last] = temp;
				start++;
				last--;
			}
			else{
				last--;
			}
		}
    }
    
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        int size = 10;
        Student[] arr = new Student[size];
        // build 100 students with random id and gender.
        for (int i= 0; i < size; i++ ) {
            int id = Math.abs(random.nextInt(100));
            arr[i] = new Student(id, (byte) (id % 2));
        }
        System.out.println(Arrays.stream(arr).
                map(Student::toString).
                collect(Collectors.joining("\n ")));
        binarySort(arr);
        System.out.println("after sorting");
        System.out.println(Arrays.stream(arr).
                map(Student::toString).
                collect(Collectors.joining("\n ")));
    }
}

class Student {
    int id;
    byte gender; // 0 for girls and 1 for boys. 
    
    public Student (int id, byte gender) {
        this.id = id;
        this.gender = gender;
    }
    
    public String toString () {
        return (gender == 0? "female":"male") + " student " + id;
    }
}