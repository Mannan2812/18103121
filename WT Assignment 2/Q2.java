import java.util.Arrays;
import java.util.Scanner;

public class Q2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of elements:");
		int n = in.nextInt();
		int[] countingArray = new int[21];
		Arrays.fill(countingArray, 0);
		for(int i = 0;i<n;++i)
		{
			int ele = in.nextInt();
			countingArray[ele]++;
		}
		System.out.println("Sorted Array is:");
		for(int i = 0;i<=20;++i) {
			for(int j = 0;j<countingArray[i];++j)
				System.out.print(i+" ");
		}
	}
}
