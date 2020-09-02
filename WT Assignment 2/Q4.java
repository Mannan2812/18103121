import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Q4 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		long sum = 0;
		System.out.println("Integers satisfying the given constraint are:");
		for(long i = 1;i<=Integer.MAX_VALUE;++i) {
			sum+=i;
			if(sum==(long)(i*i))
				System.out.println(i);
		}
		
	}
}
