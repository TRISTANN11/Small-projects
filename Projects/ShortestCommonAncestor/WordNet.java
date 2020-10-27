import java.util.Scanner;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class WordNet {
	
	private LinearProbingHashST<String, Bag<Integer>> hmap = new LinearProbingHashST<String, Bag<Integer>>();
	private LinearProbingHashST<Integer, Bag<String>> smap = new LinearProbingHashST<Integer, Bag<String>>();
	public Digraph adj;
   // constructor takes the name of the two input files
	
   public WordNet(String synsets, String hypernyms) {
	   Stopwatch watch = new Stopwatch();
	   int numbercount = 0;
	   In synsetin = new In(synsets);
	   In hypernymin = new In(hypernyms);
	   Scanner scan;
	   
	   while(synsetin.hasNextLine()) {
		   String[] string = synsetin.readLine().split(",");
		   String[] words = string[1].split(" ");
		   numbercount++;
		   
		   int index = Integer.parseInt(string[0]);
		   String gloss = string[2];		   
		   
		   for(int i = 0; i < words.length; i++) {
			   if(hmap.contains(words[i])){
				  hmap.get(words[i]).add(index);
			   }else {
				   Bag<Integer> newbag = new Bag<Integer>();
				   hmap.put(words[i], newbag);
				   hmap.get(words[i]).add(index);
			   }
			   
			   if(smap.contains(index)) {
				   smap.get(index).add(words[i]);
			   }else {
				   Bag<String> newbag = new Bag<String>();
				   smap.put(index, newbag);
				   smap.get(index).add(words[i]);
			   }
		   }
	   }
		
	   
	   adj = new Digraph(numbercount);
	   
	   while(hypernymin.hasNextLine()) {
		   String[] Hypernym = hypernymin.readLine().split(",");
		  for(int i = 0; i<Hypernym.length-1; i++) {
			  adj.addEdge(Integer.parseInt(Hypernym[0]), Integer.parseInt(Hypernym[i+1]));
		  }
	   }
	   System.out.println(watch.elapsedTime());
   }
   
   public Iterable<String> nouns(){
	   return hmap.keys();
   }
   
   public boolean isNoun(String word) {
	  if(hmap.contains(word)) {
		  return true;
	  }
	  return false;
   }
   
   public String sca(String noun1, String noun2) {
	   if(!hmap.contains(noun1) || !hmap.contains(noun2)) {
		   return "Value not present";
	   }
	   String returnstring = "";
	   ShortestCommonAncestor s = new ShortestCommonAncestor(adj);
	   Bag<Integer> a = hmap.get(noun1);
	   Bag<Integer> b = hmap.get(noun2);
	   Bag<String> returnvalue = smap.get(s.ancestor(a, b));
	   for(String string: returnvalue) {
		   returnstring = returnstring + " " + string ;
	   }
	   return returnstring; 	   
   }

   // distance between noun1 and noun2 (defined below)
   public int distance(String noun1, String noun2) {
	   if(!hmap.contains(noun1) || !hmap.contains(noun2)) {
		   return -1;
	   }

	   ShortestCommonAncestor s = new ShortestCommonAncestor(adj);
	   Bag<Integer> a = hmap.get(noun1);
	   Bag<Integer> b = hmap.get(noun2);

	   return s.length(a, b); 
   }
   
   public static void main(String[] args)  {
	   WordNet test = new WordNet("synsets.txt", "hypernyms.txt");
	   ShortestCommonAncestor ancestortest = new ShortestCommonAncestor(test.adj);
	   System.out.println(ancestortest.length(1, 1000));
	   System.out.println(test.sca("123", "Aplysia"));
	   System.out.println(test.distance("123", "Aplysia"));
	  
   }
}