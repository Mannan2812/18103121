import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
public class Q3_b {

	static Vector<Vector<Integer>> paths = new Vector<Vector<Integer>>();
	static void dfs(int source,int dest,int sum,Vector<Integer> path,int N,int[][] edges) {
		if(source==dest) {
			if(sum<0) return;
			else {
				Vector<Integer> temp = new Vector<Integer>(path);
				temp.add(sum);
				paths.add(temp);	
				return;
			}
		}
		else {
			for(int i= 1;i<=N;++i) {
				if(edges[source][i]!=Integer.MAX_VALUE) {
					path.add(i);
					dfs(i, dest, sum+edges[source][i], path, N, edges);
					path.remove(path.size()-1);
				}
			}
		}
		
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of vertices and edges:");
		int n = in.nextInt();
		int m = in.nextInt();
		int edges[][] = new int[n+1][n+1];
		for(int[] e:edges)
			Arrays.fill(e, Integer.MAX_VALUE);
		System.out.println("Enter your edges in form of A B W where A and B are nodes and W is weight:");
		for(int i= 0;i<m;++i) {
			int a,b,w;
			a = in.nextInt();
			b = in.nextInt();
			w = in.nextInt();
			edges[a][b] = w;
		}
		System.out.println("Enter source and dest:");
		Vector<Integer> v = new Vector<Integer>();
		int s = in.nextInt();
		int d = in.nextInt();
		v.add(s);
		dfs(s, d, 0, v, n, edges);
		for(Vector<Integer> vi:paths) {
			for(int i = 0;i<vi.size()-1;++i) {
				if(i!=vi.size()-2)
				System.out.print(vi.get(i)+"-->");
				else
					System.out.print(vi.get(i));
			}
			System.out.print("\t Cost: "+vi.get(vi.size()-1));
			System.out.println();
		}
		if(paths.size()==0) {
			System.out.println("No positive path exists");
		}
	}
	
	

}
