import java.util.Scanner;
import java.util.Arrays;
public class Q1 {
	
	public static String sortString(String a) {
		char[] temp = a.toCharArray();
		Arrays.sort(temp);
		a = new String(temp);
		return a;
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String inputString, subString;
		int count = 0;
		inputString = in.next();
		subString = sortString(in.next());
		int len = subString.length();
		for(int i = 0;i<inputString.length()-len+1;++i) {
			String sorted  = sortString(inputString.substring(i,i+len));
			if(sorted.equals(subString)) ++count;
		}
		System.out.println("The count is: "+count);
	}
}
