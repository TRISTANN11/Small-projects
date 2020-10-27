import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stopwatch;


public class ShortestCommonAncestor {
	private Digraph toWorkOn;
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path   
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    private int minimum = INFINITY;
    private int sharedancestor;
    
   // constructor takes a rooted DAG as argument
   public ShortestCommonAncestor(Digraph G) {
	   toWorkOn = G;
   }

   // length of shortest ancestral path between v and w
   public int length(int v, int y) {

       marked = new boolean[toWorkOn.V()];
       distTo = new int[toWorkOn.V()];
       
       bfs(v);

       int distance = bfs2(y) + distTo[sharedancestor];
 
	return distance;
	   
   }
   

   private void bfs(int s) {   
       Queue<Integer> q = new Queue<Integer>();
       for (int v = 0; v < toWorkOn.V(); v++)
           distTo[v] = INFINITY;
       distTo[s] = 0;
       marked[s] = true;
       q.enqueue(s);



       while (!q.isEmpty()) {
           int v = q.dequeue();
          
           for (int w : toWorkOn.adj(v)) {
               if (!marked[w]) {
                   distTo[w] = distTo[v] + 1;
                   marked[w] = true;
                   q.enqueue(w);
               }
           }
       }
   }
   
   private int bfs2(int s) {
	      Queue<Integer> q = new Queue<Integer>();

	       q.enqueue(s);
	       int returnvalue = 0;

	       while (!q.isEmpty()) {
	           int v = q.dequeue();
	           for (int w : toWorkOn.adj(v)) {
	               if (!marked[w]) {
	                   returnvalue++;
	                   q.enqueue(w);
	               }
	               if(marked[w]) {
	            	   sharedancestor = w;
	            	   return returnvalue;
	               }
	           }
	       }
	       return 0;
   }
  
   // a shortest common ancestor of vertices v and w
   public int ancestor(int v, int y) {
       marked = new boolean[toWorkOn.V()];
       distTo = new int[toWorkOn.V()];
       
       bfs(v);
       int returnvalue = bfs2(y);
       
	   return returnvalue;
   }

   // length of shortest ancestral path of vertex subsets A and B
   public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
	   int minimum = INFINITY;
	   for (Integer Integer1: subsetA) {
		   for(Integer Integer2: subsetB) {

			   int value = length(Integer1, Integer2);
			   
			   if(value<minimum) {
				   minimum = value;
			   }
		   }
	   }
	 
	   return minimum;
	
	   
   }

   // a shortest common ancestor of vertex subsets A and B
   public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
	   Iterator<Integer> A = subsetA.iterator();
	   Iterator<Integer> B = subsetB.iterator();
	   int minimum = INFINITY;
	   int placeholder1 = -1;
	   int placeholder2 = -1;
	   
	   for (Integer Integer1: subsetA) {
		   for(Integer Integer2: subsetB) {
			   
			   int value = length(Integer1, Integer2);
			   
			   if(value<minimum) {
				   minimum = value;
				   placeholder1 = Integer1;
				   placeholder2 = Integer2;
			   }
		   }
	   }
	   return ancestor(placeholder1, placeholder2);
   }

   // do unit testing of this class
   public static void main(String[] args) {
	   WordNet test = new WordNet("synsets.txt", "hypernyms.txt");
	   ShortestCommonAncestor ancestortest = new ShortestCommonAncestor(test.adj);
	   System.out.println(ancestortest.length(1, 53));
	   System.out.println(ancestortest.ancestor(1, 53));
   }
}
