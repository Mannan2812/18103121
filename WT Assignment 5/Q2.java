import java.util.Scanner;
public class Q2 {
   final static int MAX = 100000;
   static int maxCount = 0;
   static int ans = 0;
   
   
   public static int countDivisors(int N) {
	      int count = 0;
	      for (int i = 1; i*i <= N ; i++) {
	         if ( N % i == 0 )
	            count +=2;
	         if(i== (N/i))
	        	 count -=1;
	      }
	      return count;
	   }
   
   
   synchronized private static void update(int numOfDivisors, 
         int num) {
      if (numOfDivisors > maxCount) {
         maxCount = numOfDivisors;
         ans = num;
      }
   }

   private static class CountDivisorsThreaded extends Thread {
      int start, end;
      public CountDivisorsThreaded(int s, int e) {
         this.start = s;
         this.end = e;
      }
      public void run() {
         int max = 0;
         int res = 0;
         for (int i = start; i < end; i++) {
            int divisors = countDivisors(i);
            if (divisors > max) {
               max = divisors;
               res = i;
            }
         }
         update(max,res);
      }
   }
   
   public static void getDivisors (int numberOfThreads) {
      long startTime = System.currentTimeMillis();
      CountDivisorsThreaded[] threads = new CountDivisorsThreaded[numberOfThreads];
      int equiPartition = MAX/numberOfThreads; 
      int start = 1;  
      int end = start + equiPartition - 1;   
      for (int i = 0; i < numberOfThreads; i++) {
         if (i == numberOfThreads - 1) {
            end = MAX; 
         }
         threads[i] = new CountDivisorsThreaded( start, end );
         start = end+1;    
         end = start + equiPartition - 1;
      }
      maxCount = 0;
      for (int i = 0; i < numberOfThreads; i++)
         threads[i].start();
      for (int i = 0; i < numberOfThreads; i++) {
            try {
               threads[i].join();
            }
            catch (Exception e) {
            }
      }
      long totalTime = System.currentTimeMillis() - startTime;
      System.out.println(ans+" has maximum no. of divisors i.e "+maxCount+" in range (1-100000)");
      System.out.println("Total time taken:  " + 
            (totalTime) + " milliseconds.\n");
   }
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the number of threads (1 - 10 is preferrable):");
      int numberOfThreads = in.nextInt();
      getDivisors(numberOfThreads);
   } 
}