import java.util.Comparator;

public class Term implements Comparable<Term> {
	public String query;
	public long weight;
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
    	this.query = query;
    	this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
    	return new Comparator<Term> () {
    		public int compare(Term v, Term w) {
    			if ( v.weight < w.weight) {
    				return 1;
    			}
    			if (v.weight > w.weight) {
    				return -1;
    			}
    			else {
    				return 0;
    			}
    		}
    	};
    }

    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
    	return new Comparator<Term> () {
    		public int compare(Term v, Term w) {
    			
        		return v.query.substring(0, Math.min(r, v.query.length())).compareTo(w.query.substring(0, Math.min(r, w.query.length())));
 
    		}
    	};
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
    	return (query.compareTo(that.query));
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
    	String thestring = weight + "	" + query;
    	return thestring;
    }

    // unit testing (you should have some Unit Testing here to confirm that your methods work)
    public static void main(String[] args) {
    	Term term1 = new Term("a", 1);
    	Term term2 = new Term("b", 2);
    	
    }
}