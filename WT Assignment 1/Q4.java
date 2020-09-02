import java.util.Arrays;
import java.util.Scanner;
public class Q4 {
	public static String sortString(String a) {
		char[] temp = a.toCharArray();
		Arrays.sort(temp);
		a = new String(temp);
		return a;
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String s1 = in.next(),s2 = in.next();
		s1 = sortString(s1);
		s2 = sortString(s2);
		if(s1.equals(s2)) {
			System.out.println("Given two strings are anagram of each other");
		}
		else {
			System.out.println("Given two strings are not anagram of each other");
		}
	}
}
