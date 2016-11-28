import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
private Percolation percolation;
private double[] stats;
private int trials;

public PercolationStats(int n, int trials){
        if ( n <= 0 || trials <= 0) {
                throw new IllegalArgumentException("parameters must be positive numbers");
        } else {
                this.trials = trials;
                stats = new double[trials];

                for ( int i=0 ; i<trials ; i++) {
                        stats[i] = calThreshold(n);
                }
        }
}

private double calThreshold(int n){
        double num = 0;
        percolation = new Percolation(n);
        while (!percolation.percolates()) {
                int p = StdRandom.uniform(n) + 1;
                int q = StdRandom.uniform(n) + 1;

                if (!percolation.isOpen(p,q)) {
                        percolation.open(p,q);
                        num++;
                }
        }
        return num/(n*n);

}
// perform trials independent experiments on an n-by-n grid
public double mean(){
        return StdStats.mean(stats);
}                             // sample mean of percolation threshold
public double stddev(){
        return StdStats.stddev(stats);
}                           // sample standard deviation of percolation threshold
public double confidenceLo(){
        return this.mean()-(1.96 * this.stddev())/(Math.sqrt(trials));
}                     // low  endpoint of 95% confidence interval
public double confidenceHi(){
        return this.mean()+(1.96 * this.stddev())/(Math.sqrt(trials));
}                     // high endpoint of 95% confidence interval

public static void main(String[] args){
        int n = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats pcltStats = new PercolationStats(n,T);
        StdOut.println("mean                    = " + pcltStats.mean());
        StdOut.println("stddev                  = " + pcltStats.stddev());
        StdOut.println("95% confidence interval = " + pcltStats.confidenceLo()+", " + pcltStats.confidenceHi());

}       // test client (described below)
}
