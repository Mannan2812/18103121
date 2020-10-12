
public class Q2 {
	public static void main(String[] args) {
		int val = -1;
		byte a = (byte)val;
		char b = (char)a;
		int c = (int)b;
		System.out.println(c);
	}
}
/**
EXPLANATION

=> Initially value -1 is stored in "val" variable which is 32-bit integer type
   Range of Integer is -2^31 to 2^31-1 so -1 can be stored in "val" variable
   Then we cast int -> byte and stored it in "a" which is 8-bit which can store -128 to 127
   So -1 can be stored in "a"
   Then we cast byte to char and stored it in "b" which is 26-bit and can store 0 to 2^16-1 values
   As val -1 is out of range and as it is -1 so 2^16-1 is stored in "b"
   And then when we cast "b" -> "c" the value printed in 2^16-1


*/