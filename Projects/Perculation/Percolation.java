import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
	boolean grid[][];
	int Nsub; //For counting in other functions, stores 'N'
	int counter = 0;
	 WeightedQuickUnionUF finder;
	 WeightedQuickUnionUF correctgrid;
	 	
	   public int indexer(int row, int col) { // Method to make index for UnionFind
		   int index = row * Nsub +col +1;
		   return index;
	   }
	   
	   public Percolation(int N) {
		   if (N<=0) {
			   System.out.println("N has to be 0 or higher");// Catches if someone inputs under 0 or 0 for the grid. 
		   }else {
			   //Creates a 2 D array that is N by N big of booleans so we can see if the slot is open or not.
			   int numberofitems = (N * N) + 2;
			   finder = new WeightedQuickUnionUF(numberofitems);
			   correctgrid = new WeightedQuickUnionUF(numberofitems-1);
			   grid = new boolean[N][N];
			   Nsub = N;
			  
		   }
	   }
	   
	   public void open(int row, int col) {
		  
		   //opens slots based on input
		   grid[row][col] = true;
		   counter++;
		   //puts items together if a part of the grid is true, or able to percolate.
		   if (row == 0) {
			   finder.union(0, indexer(row, col));
		   }else if (grid[row-1][col]) {
			  finder.union(indexer(row, col), indexer(row - 1, col));
			  correctgrid.union(indexer(row, col), indexer(row - 1, col));
		   }
		   
		   if(col < Nsub-1) {
			   if (grid[row][col+1]) {
				   finder.union(indexer(row, col), indexer(row, col+1));
				   correctgrid.union(indexer(row, col), indexer(row, col+1));
			   }
		   }
		   
		   if (row<Nsub-1) {
			   if (grid[row + 1][col]) {
				   finder.union(indexer(row, col), indexer(row + 1, col));
				   correctgrid.union(indexer(row, col), indexer(row + 1, col));
			   	}
			}
		   
		   if(col > 0) {
			   if (grid [row][col-1]) {
				   finder.union(indexer(row, col), indexer(row, col-1));
				   correctgrid.union(indexer(row, col), indexer(row, col-1));
			   }
			}
		   if (row == Nsub -1) {
			   finder.union(indexer(row, col), indexer(Nsub-1, Nsub-1));
		   }
	   }
	   
	   public boolean isOpen(int row, int col) {
		   return grid[row][col];
	   }
	   
	   public boolean isFull(int row, int col) {
		   return correctgrid.connected(0, indexer(row, col));
	   }
	   
	   public int numberOfOpenSites() {

		  return counter;
	   }
	   
	   
	   public boolean percolates()    {
		   
		   return finder.connected(0, indexer(Nsub-1, Nsub-1));
		   
	   }
	   

}
