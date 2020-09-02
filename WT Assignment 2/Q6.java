import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Q6 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number:");
		int n = in.nextInt();
		System.out.println("Hailstone sequence is:");
		System.out.print(n+" ");
		while(n!=1) {
			if((n&1)==1) {
				n = 3*n+1;
				System.out.print(n+" ");
			}
			else {
				n/=2;
				System.out.print(n+" ");
			}
		}
		
	}
}
