import java.util.Scanner;
import java.util.Vector;
public class Q2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		int n = in.nextInt();
		Vector<String> wordList = new Vector<String>();
		for(int i= 0;i<n;++i) {
			String word = in.next();
			wordList.add(word);
		}
		for(String s:wordList) {
			String replacement = s.charAt(0)+"*".repeat(s.length()-1);
			input  = input.replaceAll(s,replacement);
		}
		System.out.println(input);
	}
}
