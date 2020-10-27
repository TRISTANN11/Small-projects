import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StopwatchCPU;

public class CheckArraysForDuplicates
{
  
    
    public static void main(String[] args) {
        
        if (args.length < 1) {
            StdOut.println("Usage: java-algs4 CheckArraysForDuplicates filename");
            System.exit(1);
        }
        String filename = args[0];
        
        In in = new In(filename);
        
        int k = in.readInt(); // how many arrays
        int[] sizes = new int[k];  
        int[][] vals = new int[k][];  

        /* Read in all numbers into a set of arrays */
        for (int i=0; i<k; i++) {
            int n = in.readInt();
            sizes[i] = n;
            vals[i] = new int[n];
        }
        for (int i=0; i<k; i++) {
            for (int j=0; j < sizes[i]; j++) {
                vals[i][j] = in.readInt();
            }
        }
        
        
        
        /* Now it's your turn:  how to use a RedBlack tree (of size k) to solve this?*/
        boolean duplicate = false;
        StopwatchCPU sw = new StopwatchCPU();

        RedBlackBST<Integer, Integer> rbt = new RedBlackBST<Integer, Integer>();
        int[] offsets = new int[k];
        for (int i=0; i<k; i++) {
            offsets[i] = 0;
            
            // anything here to check for duplicates?
            
            rbt.put(vals[i][0], i); // use the array entry as the key, and the array index as the value
        }
        

        while(!rbt.isEmpty()) {
        	
        	int index = rbt.get(rbt.min());
        	offsets[index]++;
        	rbt.deleteMin();
        	
        	
        	if(offsets[index] < sizes[index]) {
        		rbt.put(vals[index][offsets[index]], index);
        	}
        	
        	
        }
        
       for (int i = 0; i<k; i++) {
    	   if(offsets[i] != sizes[i]) {
    		   duplicate = true;
    	   }
       }
        

        
        
        double elapsed = sw.elapsedTime();
        
        
        
        StdOut.println("The arrays do " + (duplicate?"":"not ") + "contain a duplicate " );
        StdOut.println("elapsed time: " + elapsed + " seconds");
        
    }

   
}

