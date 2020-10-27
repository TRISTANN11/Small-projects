import java.util.Collections;
import java.util.Comparator;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
    	//Used binary search found in BinarySearch.class and switched the basic api with our comparator as defined in Term.java
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
        	if(comparator.compare(key, a[0]) == 0) {
        		return 0;
        	}
        	
            int mid = lo + (hi - lo) / 2;
            //uses custom comparator instead of java comparisons. 
            if      (comparator.compare(key, a[mid]) < 0) {
            	hi = mid - 1;
            }
            else if (comparator.compare(key, a[mid]) > 0) {
            	lo = mid + 1;
            }
            else if(comparator.compare(a[mid], a[mid-1])== 0) {
            		hi = mid-1;
            }
            else {
            		return mid;
            	}
        }
        return -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        int lo = 0;
        int hi = a.length - 1;
        //If the highest value is still equal to our number then we have the last in the index and no need to search. 
        if(comparator.compare(key, a[hi]) == 0) {
        	return hi;
        }
        //The while loop searches until the lo and hi are equal or lo is larger than the hi, allowing us to continue finding values equal
        //to our key until we find the last one, where we can return the mid or where the last value is found. Once again we need the -1 just
        //In the case there is no such key in the array. 
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (comparator.compare(key, a[mid]) < 0) hi = mid - 1;
            else if (comparator.compare(key, a[mid]) > 0) lo = mid + 1;
            else if(comparator.compare(a[mid], a[mid+1])== 0) {
        		lo = mid+1;
            }
            else {
        		return mid;
        	}
        }
        
        return -1;
    }

    // unit testing (you should have some Unit Testing here to confirm that your methods work)
    public static void main(String[] args)   {
    	Term[] terms = new Term[5];
    	Term keyterm = new Term("aa", 10);
    	Term term1 = new Term("aa", 9);
    	Term term2 = new Term("aa", 10);
    	Term term3 = new Term("aa", 10);
    	Term term4 = new Term("aa", 10);
    	Term term5 = new Term("ff", 10);
    	terms[0] = term1;
    	terms[1] = term2;
    	terms[2] = term3;
    	terms[3] = term4;
    	terms[4] = term5;
    	System.out.println(BinarySearchDeluxe.firstIndexOf(terms, keyterm, Term.byPrefixOrder(1)) + "\n");
    	System.out.println(BinarySearchDeluxe.lastIndexOf(terms, keyterm, Term.byPrefixOrder(1)) + "");
    }
}	
