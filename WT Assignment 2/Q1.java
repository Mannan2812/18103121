import java.util.Scanner;


public class Q1 {
	
	static int min(int a,int b) {
		return (a<b)?a:b;
	}
	
	static int compareTo(String s1, String s2) {
		int comp = 0;
		for(int i = 0;i<min(s1.length(),s2.length());++i) {
			if(comp!=0)
				return comp;
			comp = (int)s1.charAt(i)-(int)s2.charAt(i);
		}
		if(comp!=0) return comp;
		if(s1.length()<s2.length()) {
			return -1;
		}
		else if(s1.length()>s2.length()) {
			return 1;
		}
		
		return 0;
	}
	
	public static void main(String args[]) {
		Scanner in  = new Scanner(System.in);
		System.out.println("Enter the two strings:");
		String s1,s2;
		s1 = in.next();
		s2 = in.next();
		if(compareTo(s1, s2)<0) {
			System.out.println(s1+" is smaller than "+s2);
		}
		else if(compareTo(s1,s2)==0) {
			System.out.println(s1+" is equal to "+s2);
		}
		else {
			System.out.println(s1+" is greater than "+s2);
		}
	}
}
