import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
private static final int SITE_OPEN = 1;
private static final int SITE_CLOSE = 0;
private WeightedQuickUnionUF sites;
private int[] siteStateArr;
private int sideLen;
private int virtualTop;
private int virtualBottom;

public Percolation(int n) {
        if (n <= 0) {
                throw new IllegalArgumentException("your n should be larger than 0");
        } else {
                this.sites = new WeightedQuickUnionUF(n*n+2);
                this.siteStateArr = new int[n*n+2];

                virtualTop = 0;
                virtualBottom = n * n+1;

                siteStateArr[virtualTop] = SITE_OPEN;
                siteStateArr[virtualBottom] = SITE_OPEN;

                this.sideLen = n;
        }
}                   // create n-by-n grid, with all sites blocked
public void open(int row, int col) {
        checkForValidIndex(row, col);

        int gridIndex = sideLen*(row-1)+col;

        siteStateArr[gridIndex] = SITE_OPEN;
        //if the site is in the front row, connect it with virtualTop
        if (row == 1) {
            sites.union(virtualTop, gridIndex);
        } else if (row == sideLen) {
                sites.union(virtualBottom, gridIndex);
            }

        if (col > 1 && isOpen(row, col-1)) {
            sites.union(gridIndex, gridIndex-1);
        }

        if (col < sideLen && isOpen(row, col+1)) {
            sites.union(gridIndex， gridIndex+1);
        }

        if (row > 1 && isOpen(row-1,col)) {
            sites.union(gridIndex, gridIndex-sideLen);
        }

        if (row < sideLen && isOpen(row+1,col)) {
            sites.union(gridIndex, gridIndex+sideLen);
        }

}          // open site (row, col) if it is not open already
public boolean isOpen(int row, int col){
        checkForValidIndex(row,col);
        return siteStateArr[sideLen*(row-1)+col] == SITE_OPEN;
}     // is site (row, col) open?
public boolean isFull(int row, int col){
        checkForValidIndex(row,col);
        return sites.connected(sideLen*(row-1)+col, virtualTop);
}     // is site (row, col) full?
public boolean percolates(){
        return sites.connected(virtualTop, virtualBottom);
}                 // does the system percolate?

public static void main(String[] args){

}      // test client (optional)

private void checkForValidIndex(int i, int j) {
    if (i <= 0
            || j <= 0
            || i > sideLen
            || j > sideLen) {
        throw new IndexOutOfBoundsException("Attempting to access a cell outside of the grid");
    }

}
}
