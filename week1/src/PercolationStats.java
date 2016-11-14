/**
 * Created by zhangsheng on 2016/11/7.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private int experimentCount;
    private double[] fractions;


    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }

        experimentCount = trials;
        fractions = new double[experimentCount];
        for (int i=0; i < experimentCount; i++){
            int openSites = 0;
            Percolation pr = new Percolation(n);
            while(!pr.percolates()){
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                if (!pr.isOpen(row, col)){
                    pr.open(row, col);
                    openSites++;
                }
            }
            fractions[i] = (double) openSites/(n*n);
        }
    }
    public double mean(){
        return StdStats.mean(fractions);
    }
    public double stddev(){
        return StdStats.stddev(fractions);
    }
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(experimentCount);
    }
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(experimentCount);
    }


    public static void main(String[] args){
        int n = 3;
        int t = 5;
//        int n = Integer.parseInt(args[0]);
//        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
