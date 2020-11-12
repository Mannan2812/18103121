


class Threaded extends Thread{
	public void run() {
		for(int i = 1;i<=100;++i) {
			if(i%10==0) {
				System.out.println("Counter reaches "+i);
			}
			try {
			currentThread().sleep(1000);
			} catch(Exception e) {}
		}
	}
}

public class Q1 {
	public static void main(String args[]) {
		System.out.println("Warning! as question demands 1 sec pause after each number so this program will take approx 2 mins to run.");
		Threaded t1 = new Threaded();
		t1.start();
	}
}
