import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StopwatchCPU;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.LinearProbingHashST;

public class CompareTwoArrays
{
  

    private static boolean compareWithHeap (In inA, In inB, int size) {
        
        
        MaxPQ<Integer> maxheap1 = new MaxPQ<Integer>();
        MaxPQ<Integer> maxheap2 = new MaxPQ<Integer>();
        while (!inA.isEmpty()) {
            int item = inA.readInt();
            maxheap1.insert(item);
        }
        while(!inB.isEmpty()) {
        	int item = inB.readInt();
        	maxheap2.insert(item);
        }
        
        while(!maxheap1.isEmpty()) {
        	int val1 = maxheap1.delMax();
        	int val2 = maxheap2.delMax();
        	if(val1 != val2) {
        		return false;
        	}
        }
        	
        return true;
    }
    
    private static boolean compareWithHash (In inA, In inB, int size) {
        LinearProbingHashST hash = new LinearProbingHashST();

        while (!inA.isEmpty()) {
            int item = inA.readInt();
            hash.put(item, item);
        }
        
        while(!inB.isEmpty()) {
        	int item = inB.readInt();
        	if(!hash.contains(item)) {
        		return false;
        	}
        	hash.delete(item);
        }
        
        if(hash.isEmpty()) {
        	return true;
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        
        if (args.length < 3) {
            StdOut.println("Usage: java-algs4 CompareTwoArrays filenameA filenameB [heap/hash]");
            System.exit(1);
        }
        String filenameA = args[0];
        String filenameB = args[1];
        String method    = args[2];
        
        if ( !method.equals("heap") && !method.equals("hash") ) {
            StdOut.println("Usage: java-algs4 CompareTwoArrays filenameA filenameB [heap/hash]");
            System.exit(1);
        }
        
        In inA = new In(filenameA);
        int nA = inA.readInt();

        In inB = new In(filenameB);
        int nB = inB.readInt();

        if (nA != nB) {
            StdOut.println("Arrays are not the same size, so not equivalent");
            System.exit(0);
        }
        
        boolean match = false;
        
        StopwatchCPU sw = new StopwatchCPU();
        if (method.equals("heap")) {
            match = compareWithHeap(inA, inB, nA);
        } else {
            match = compareWithHash(inA, inB, nA);
        }
        
        
        double elapsed = sw.elapsedTime();
        
        
        StdOut.println("The two input arrays do " + (match?"":"not ") + "match" );
        StdOut.println("elapsed time: " + elapsed + " seconds");
        
    }

   
}

