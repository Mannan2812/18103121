import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Q5 {
	
	public static int[] union(int[] A,int [] B,int n) {
		int ans[] = new int[n];
		Arrays.fill(ans, 0);
		
		for(int i = 0;i<n;++i) {
			if(A[i]==1||B[i]==1)
				ans[i]  =1;
		}
		
		return ans;
	}
	
	public static int[] intersection(int[] A,int[] B,int n) {
		int[] ans = new int[n];
		Arrays.fill(ans, 0);
		for(int i= 0;i<n;++i) {
			if(A[i]==1&&B[i]==1)
				ans[i] = 1;
		}
		return ans;
	}
	
	public static int[] complement(int[] A,int n) {
		int[] ans = new int[n];
		Arrays.fill(ans, 1);
		for(int i = 0;i<n;++i)
			ans[i] = (A[i]>0)?0:1;
		return ans;
	}
	
	
	public static HashSet<Integer> union_efficient(HashSet<Integer> A, HashSet<Integer> B) {
		HashSet<Integer> ans = new HashSet<Integer>();
		for(int i:A) ans.add(i);
		for(int i:B) ans.add(i);
		return ans;
	}
	
	public static HashSet<Integer> intersection_efficient(HashSet<Integer> A, HashSet<Integer> B) {
		HashSet<Integer> ans = new HashSet<Integer>();
		for(int i:A) if(B.contains(i)) ans.add(i);
		return ans;
	}
	public static HashSet<Integer> complement_efficient(HashSet<Integer> A,HashSet<Integer> U){
		HashSet<Integer> ans = new HashSet<Integer>();
		for(int i:A) U.remove(i);
		return U;
	}
	public static HashSet<Integer> initUniverse(int n) {
		HashSet<Integer> U = new HashSet<Integer>();
		for(int i = 1;i<=n;++i)
			U.add(i);
		return U;
	}
	public static String addPadding(String s,char ch,int l) {
		return String.format("%"+(-l)+"s",s).replace(' ', ch);
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the upper limit of universe (use value above 1000 to see significant difference):");
		int n = in.nextInt();
		
		long union,union_e,inter,inter_e,complA,complB,complA_e,complB_e;
		
		System.out.println("Enter the number of elements in A and B:");
		int a = in.nextInt();
		int b = in.nextInt();
		HashSet<Integer> dataA = new HashSet<Integer>();
		HashSet<Integer> dataB = new HashSet<Integer>();
		
		int A[] = new int[n];
		int B[] = new int[n];
		
		System.out.println("Enter the elements of A:");
		for(int i = 0;i<a;++i) {
			int temp = in.nextInt();
			A[temp-1] = 1;
			dataA.add(temp);
		}
		
		System.out.println("Enter the elements of B:");
		for(int i = 0;i<b;++i) {
			int temp = in.nextInt();
			B[temp-1] = 1;
			dataB.add(temp);
		}
		
		long start,end;
		
		start = System.nanoTime();
		int[] ans = union(A, B,n);
		end = System.nanoTime();
		union = end-start;
		
		start = System.nanoTime();
		HashSet<Integer> uni = union_efficient(dataA, dataB);
		end = System.nanoTime();
		union_e = end-start;
		
		start = System.nanoTime();
		ans = intersection(A, B, n);
		end = System.nanoTime();
		inter = end-start;
		
		start = System.nanoTime();
		HashSet<Integer> intersection = intersection_efficient(dataA, dataB);
		end = System.nanoTime();
		inter_e = end-start;
		
		start = System.nanoTime();
		ans = complement(A,n);
		end = System.nanoTime();
		complA = end-start;
		
		HashSet<Integer> U = initUniverse(n);
		start = System.nanoTime();
		HashSet<Integer> complementA = complement_efficient(dataA,U);
		end = System.nanoTime();
		complA_e = end-start;

		start = System.nanoTime();
		ans = complement(B,n);
		end = System.nanoTime();
		complB = end-start;
		
		U = initUniverse(n);
		start = System.nanoTime();
		HashSet<Integer> comlementB = complement_efficient(dataB,U);
		end = System.nanoTime();
		complB_e = end-start;
		
		String header = addPadding("", ' ', 20)+"|"+addPadding("Union",' ', 20)+"|"+
				addPadding("Intersection",' ', 20)+"|"+
				addPadding("A Complement", ' ', 20)+"|"+
				addPadding("B Complement", ' ', 20);
		System.out.println(header);
			
		String s1 = addPadding("Using Array", ' ', 20)+"|"+
					addPadding(Long.toString(union)+"ns", ' ', 20)+"|"+
					addPadding(Long.toString(inter)+"ns", ' ', 20)+"|"+
					addPadding(Long.toString(complA)+"ns", ' ', 20)+"|"+
					addPadding(Long.toString(complB)+"ns", ' ', 20);
		System.out.println(s1);
		s1 = addPadding("Using HashSet", ' ', 20)+"|"+
				addPadding(Long.toString(union_e)+"ns", ' ', 20)+"|"+
				addPadding(Long.toString(inter_e)+"ns", ' ', 20)+"|"+
				addPadding(Long.toString(complA_e)+"ns", ' ', 20)+"|"+
				addPadding(Long.toString(complB_e)+"ns", ' ', 20);
		System.out.println(s1);
		System.out.println();
		
		System.out.println("Enter 1 to see output of set operations:");
		int ch = in.nextInt();
		if(ch==1) {
			System.out.println("Union:");
			for(int i:uni) {
				System.out.print(i+" ");
			}
			System.out.println("\nIntersection:");
			for(int i:intersection) {
				System.out.print(i+" ");
			}
			System.out.println("\nA complement");
			for(int i:complementA)
				System.out.print(i+" ");
			System.out.println("\nB complement");
			for(int i:comlementB)
				System.out.print(i+" ");
		}
	}
}
