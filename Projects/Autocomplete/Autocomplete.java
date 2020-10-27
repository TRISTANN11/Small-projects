import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Autocomplete {
	Term[] Autoterms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
    	Autoterms = terms;
    	Arrays.sort(Autoterms);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
    	int counter = 0;
    	Term prefixholder = new Term(prefix, 0);
    	int firstindex = BinarySearchDeluxe.firstIndexOf(Autoterms, prefixholder, Term.byPrefixOrder(prefix.length()));
    	int secondindex = BinarySearchDeluxe.lastIndexOf(Autoterms, prefixholder, Term.byPrefixOrder(prefix.length()));
    	if(firstindex + secondindex == -2) {
    		Term[] emptylist = new Term[0];
    		return emptylist;
    	}
    	Term[] newlist = new Term[secondindex - firstindex + 1];
    	for(int i = firstindex; i<secondindex+1; i++) {
    		newlist[counter] = Autoterms[i];
    		counter++;
    		}
    	Arrays.sort(newlist, Term.byReverseWeightOrder());
    	return newlist;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
         int numberofmatches = 0;
         Term prefixholder = new Term(prefix, 0);
         int firstindex = BinarySearchDeluxe.firstIndexOf(Autoterms, prefixholder, Term.byPrefixOrder(prefix.length()));
     	 int secondindex = BinarySearchDeluxe.lastIndexOf(Autoterms, prefixholder, Term.byPrefixOrder(prefix.length()));
     	 numberofmatches = secondindex-firstindex + 1;
     	 if(numberofmatches < 1) {
     		 return 0;
     	 }
         return numberofmatches;
    }
    
    public static void main(String[] args)   {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}