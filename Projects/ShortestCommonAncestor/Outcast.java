import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Outcast {
	WordNet toWorkon;
   public Outcast(WordNet wordnet) {
	   toWorkon = wordnet;
   }
   
   public String outcast(String[] nouns) {
	   	int[] values = new int[nouns.length];
	   	int maximum = 0;
	   	int indexlookingfor = -1;
	   	for(int i = 0; i<nouns.length; i++) {
	   		for (int j = 0; j<nouns.length; j++) {
	   			if(i != j) {
	   				values[i] += toWorkon.distance(nouns[i], nouns[j]);
	   			}
	   		}
	   	
	   	}

	   	for(int i = 0; i<values.length; i++) {
	   		if(values[i] > maximum) {
	   			maximum = values[i];
	   			indexlookingfor = i;
	   		}
	   	}
	   	
	   	return nouns[indexlookingfor];
   }
   
   public static void main(String[] args) {
	   Stopwatch timer = new Stopwatch();
	   WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	    System.out.println(timer.elapsedTime());
	    
   }
   
}