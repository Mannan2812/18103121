import java.util.*; 
import java.lang.*; 
import java.io.*; 
class Q3 { 
    class Edge { 
        int src, dest, weight; 
        Edge() 
        { 
            src = dest = weight = 0; 
        } 
    }; 
  
    int V, E; 
    Edge edge[]; 
    int parent[];
    Q3(int v, int e) 
    { 
        V = v; 
        E = e; 
        edge = new Edge[e];
        parent = new int[V];
        for (int i = 0; i < e; ++i) 
            edge[i] = new Edge(); 
    } 
    void BellmanFord(Q3 graph, int src,int dest) 
    { 
        int V = graph.V, E = graph.E; 
        int dist[] = new int[V]; 
        for (int i = 0; i < V; ++i) 
            dist[i] = Integer.MAX_VALUE; 
        for (int i = 0;i<V;++i)
        	parent[i] = -1;
        dist[src] = 0; 
        for (int i = 1; i < V; ++i) { 
            for (int j = 0; j < E; ++j) { 
                int u = graph.edge[j].src; 
                int v = graph.edge[j].dest; 
                int weight = graph.edge[j].weight; 
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                	dist[v] = dist[u] + weight; 
                	parent[v] = u;
                }
                    
                
            } 
        } 	
        for (int j = 0; j < E; ++j) { 
            int u = graph.edge[j].src; 
            int v = graph.edge[j].dest; 
            int weight = graph.edge[j].weight; 
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) { 
                System.out.println("Negative Cycles exist"); 
                return; 
            } 
        } 
        printArr(dist, V, src,dest); 
    } 
  
    void printArr(int dist[], int V, int src, int dest) 
    { 
    	System.out.println("Shortest Distance is:"); 
        System.out.println(dist[dest]);
    	System.out.println("Shotest Path is:");
    	while(dest!=src) {
    		System.out.print(dest+1+" ");
    		dest = parent[dest];
    	}
    	System.out.println(src+1);
        
    } 
    public static void main(String[] args) 
    { 
    	Scanner in = new Scanner(System.in);
    	System.out.println("Enter the number of vertices and edges:");
        int V = in.nextInt(); 
        int E = in.nextInt(); 
  
        Q3 graph = new Q3(V, E); 
        
        System.out.println("Enter your edges in form of A B W where A and B are nodes and W is weight:");
        
        for(int i = 0;i<E;++i) {
        	int a = in.nextInt(),b = in.nextInt(),w = in.nextInt();
        	graph.edge[i].src = a-1;
        	graph.edge[i].dest = b-1;
        	graph.edge[i].weight = w;
        	
        }
        System.out.println("Enter source and destination:");
        int src = in.nextInt(), dest = in.nextInt();
        graph.BellmanFord(graph, src-1,dest-1); 
    } 
} 