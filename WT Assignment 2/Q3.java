import java.util.Arrays;
import java.util.Scanner;

public class Q3 {
	static void swap(String[] data, int idx1,int idx2) {
		String temp = data[idx1];
		data[idx1] = data[idx2];
		data[idx2] = temp;
	}
	
	static int pivot(String[] data,int left,int right) {
		int p = right;
		int index = left;
		for(int j = left;j<=right;++j) {
			if(data[j].compareTo(data[p])<=0) {
				swap(data,index,j);
				++index;
			}
		}
		return index-1;
	}
	
	static void quickSort(String[] data, int left,int right) {
		if(left>=right) {
			return;
		}
		int pivot = pivot(data, left, right);
		quickSort(data, left, pivot-1);
		quickSort(data, pivot+1, right);
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of strings:");
		int n = in.nextInt();
		System.out.println("Enter the strings:");
		String[] data = new String[n];
		for(int i = 0;i<n;++i)
			data[i] = in.next();
		quickSort(data,0,n-1);
		System.out.println("Sorted array is:");
		for(String s: data) {
			System.out.print(s+" ");
		}
	}
}
