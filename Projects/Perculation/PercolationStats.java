import java.util.Random;
import java.lang.Math;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
		int arraycount = 0; //To add numbers to our array.
		int Tsub;
		int Nsub;
		double numberformean = 0.0;
		double[] numbersforstddev; //For the standard deviation
		
	   public PercolationStats(int N, int T) {
		   // perform T independent experiments on an N-by-N grid
		   Random ran = new Random();
		   double currentnum = 0.0;
		   numbersforstddev = new double[T];
		   Tsub = T;
		   Nsub = N;
		   for(int i = 0; i<T; i++) {
			   Percolation grid = new Percolation(N);
			   while (grid.percolates() != true) {
				   int random1 = ran.nextInt(N); int random2 = ran.nextInt(N);
				   if(grid.isOpen(random1, random2)!= true) {
					 grid.open(random1, random2);
				   }
			   }
			   currentnum = (double)grid.numberOfOpenSites() / (N*N);
 			   numberformean += currentnum;
 			   numbersforstddev[arraycount] = currentnum;
 			   arraycount++;
		   }
	
	   }
	   
	   public double mean() {
		   return numberformean / Tsub;
	   }
	   public double stddev() {
		  return StdStats.stddev(numbersforstddev);
	   }
	   public double confidenceLow() {
		   return mean() - (1.96 * stddev())/ Math.sqrt(Tsub);
	   }
	   public double confidenceHigh() {
		   return mean() + (1.96 * stddev())/ Math.sqrt(Tsub);
	   }
	   
	   public static void main(String[] args) {
		   int Namount = Integer.parseInt(args[0]);
		   int Tamount = Integer.parseInt(args[1]);
		   Stopwatch stopwatch = new Stopwatch();
		   PercolationStats stats = new PercolationStats(Namount, Tamount);
		   double stopwatchtime = stopwatch.elapsedTime();
		   System.out.println("Time taken: " + stopwatchtime);
		   System.out.println("Mean: " + stats.mean());
		   System.out.println("Standard deviation: " +stats.stddev());
		   System.out.println("Confidence Low: " + stats.confidenceLow());
		   System.out.println("Confidence High: " + stats.confidenceHigh());
	   }
	   
	   
}
