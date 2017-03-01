
import java.util.Arrays;
import java.util.stream.Collectors;

public class CourseRegistrySorted {
	int[][] students;
	int[] count;

	public CourseRegistrySorted(int capacity, int lab_num){
		count = new int[lab_num];
		students = new int[lab_num][];
		for(int i=0;i<lab_num;i++) count[i] = 0;
		for(int i=0;i<lab_num;i++) students[i] = new int[capacity];
	}

	public int search(int student, int lab_num) {
		int l = 0, h = count[lab_num] - 1; 
		while (l <= h) {
			int m = l + (h - l) / 2;
			if (student > students[lab_num][m]) l = m + 1;
			else if (student < students[lab_num][m]) h = m - 1;
			else return m;
		}
		return -1;
	}

	public boolean register(int student, int lab_num) {
		if (search(student, lab_num) >= 0) {
			System.out.println("You have registered for this lab.");
			return false;
		}
		if (count[lab_num] >= students[lab_num].length) {
			System.out.println("lab" + (lab_num+1) + " is full.");
			return false;
		}
		students[lab_num][count[lab_num]] = student;
		int i = count[lab_num] - 1;
		while((i>=0) && (student > students[lab_num][i])){
			students[lab_num][i+1] = students[lab_num][i];
			students[lab_num][i] = student;
			i--;
		}
		count[lab_num]++;
		System.out.println("Student " + student + " registered for lab" + (lab_num+1) +".");
		return true;
	}

	public boolean drop(int student, int lab_num) {
		int pos = search(student, lab_num);
		if (pos < 0) {
			System.out.println("Student " + student + " was not registered.");
			return false;
		}
		if (pos == 30){
			students[lab_num][pos] = 0;
			System.out.println("Student " + student + " dropped.");
			return true;
		}
		for (int i = pos; i < count[lab_num]; i++)
			students[lab_num][i] = students[lab_num][i + 1];
		count[lab_num]--;
		System.out.println("Student " + student + " dropped.");
		return true;
	}

	public String toString(int i) 
	{
		return Arrays.stream(students[i], 0, count[i]).mapToObj(Integer::toString).collect(Collectors.joining(", "));
	}

	public static void main(String[] args) {
		final int CAPACITY = 31;
		final int LAB = 5;
		CourseRegistrySorted comp2011_lab = new CourseRegistrySorted(CAPACITY, LAB);
		int[] studentNos = { 15101317, 15101119, 15079132, 15093307, 15093733, 15072577, 15074725, 15104391, 15103709,
				15101964, 15100967, 15110455, 15093381, 15068106, 15065555, 15103136, 15101743, 15103691, 15100449,
				15101377, 15066186, 15103746, 14081983, 15103875, 15103113, 15101805, 15077145, 15068136, 15077182,
				15084296, 15084839, 14075113, 15090397, 15070086, 15092796, 14073522, 14073607, 15071336, 15081243,
				15082164, 15071732, 15083787, 14110951, 15093252, 15088583, 15084342, 15085065, 13103168, 15086863,
				15067031, 15070864, 15067779, 14110113, 15076735, 15084213, 15079834, 15079422, 15085447, 15089727,
				15067489, 15074198, 15071587, 15090381, 15080117, 15063255, 15075356, 15073415, 14077793, 15067703,
				14075723, 15069796, 14109998, 15071801, 15061473, 13104029, 15084945, 15066736, 15044919, 13105383,
				15076132, 14109533, 13104241, 15063347, 15079765, 14111285, 14111743, 15111284, 15048083, 15062997,
				15062051, 15089223, 14040525, 15011142, 15044827, 15070597, 15034865, 15011089, 15048053, 15083573,
				15053873, 15050378, 15011128, 15051131, 14075067, 15011135, 15065007, 15088378, 15102641, 15063545,
				15061528, 15062081, 15104292, 15050698, 16018978, 14020686, 13104013, 14075341, 16019015, 15078172,
				13102857, 15078386, 13102033, 16010922, 14074222, 16107864, 15074656, 16042701, 14073759, 13103693,
				16010938, 15066483, 15068387, 13114806, 16019008, 12131888, 15063476, 15076979, 15094136, 14110419, };
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 31; j++){
				if((i*31 + j) < studentNos.length)comp2011_lab.register(studentNos[i*31 + j],i);
			}
		}
		System.out.println("The semester begins.");
		for(int i=0;i<5;i++){
			System.out.println("lab"+(i+1)+" : "+comp2011_lab.count[i]+" students.");
			for(int j = 0; j<comp2011_lab.count[i];j++)	
				System.out.print(comp2011_lab.students[i][j]+"   ");
			System.out.println();
		}
		comp2011_lab.register(13103831, 4);
		comp2011_lab.register(12131927, 3);
		System.out.println("The registry is finalized.");
		for(int i=0;i<5;i++){
			System.out.println("lab"+(i+1)+" : "+comp2011_lab.count[i]+" students.");
			for(int j = 0; j<comp2011_lab.count[i];j++)	
				System.out.print(comp2011_lab.students[i][j]+"   ");
			System.out.println();
		}
	}
}