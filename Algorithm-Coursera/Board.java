import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.Count;
import edu.princeton.cs.algs4.FileIndex;

public class Board {
	
	private final int[][] board;
	
	private int[][] copyBoard(int[][] src) {
		int[][] tar = new int[src.length][src.length];
		for(int i=0;i<src.length;i++)
			for(int j=0;j<src.length;j++) tar[i][j]=src[i][j];
		return tar;
	}
	
	private final int dimension;
	
    public Board(int[][] blocks) {
    	this.board = blocks;
    	this.dimension = board.length;
    }          // construct a board from an n-by-n array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
    	return this.dimension;
    }                 // board dimension n
    
    public int hamming()  {
    	int count = 0;
    	for (int i = 0; i < dimension(); i++) {
    		for (int j = 0; j < dimension(); j++) {
    			if (board[i][j] != i * dimension() + j+1 && board[i][j] != 0) count++;
    		}
    	}
    	return count;
    }                 // number of blocks out of place
    
    public int manhattan() {
    	int prior = 0;
    	for (int i = 0; i < dimension; i++) {
    		for (int j = 0; j < dimension; j++) {
    			if (board[i][j] != 0) {
    				if (board[i][j] % dimension != 0){
    					int x = board[i][j] / dimension;
	    				int y = board[i][j] - x * dimension - 1;
	    				int diff = Math.abs(i - x) + Math.abs(j - y);
	    				prior += diff;
	    			} else {
	    				int y = dimension-1;
	    				int x = board[i][j] / dimension - 1;
	    				int diff = Math.abs(i - x) + Math.abs(j - y);
	    				prior += diff;
	    			}
    			}
    		}
    	}
    	return prior;
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
    	for (int i = 0; i < dimension(); i++) {
    		for (int j = 0; j < dimension(); j++) {
    			if (board[i][j] != i*dimension() + j+1 && (i != dimension-1 || j != dimension-1)) return false;
    		}
    	}
    	return true;
    }    // is this board the goal board?
    
    public Board twin() {
    	int[][] twin = copyBoard(this.board);
    	if (twin[0][0] != 0 && twin[0][1] != 0) {
    		int temp = twin[0][0];
    		twin[0][0] = twin[0][1];
    		twin[0][1] = temp;
    	} else {
    		int temp = twin[1][0];
    		twin[1][0] = twin[1][1];
    		twin[1][1] = temp;
    	}
    	return new Board(twin);
    }                   // a board that is obtained by exchanging any pair of blocks
    
    public boolean equals(Object y) {
    	if(y==this) return true;
    	if(y==null) return false;
    	if(!(y instanceof Board)) return false;
    	if(((Board)y).dimension != this.dimension) return false;
    	for (int r = 0; r < dimension; r++)
            if (!Arrays.equals(this.board[r], ((Board)y).board[r]))
                return false;
    	return true;
    }       // does this board equal y?
    
    public Iterable<Board> neighbors() {
    	ArrayList<Board> neighbors = new ArrayList<>();
    	Coordinate o = findZeroPoint();
    	
    	if (o.x - 1 >= 0) {
    		int[][] neighbor1 = copyBoard(this.board);
    		neighbor1[o.x][o.y] = neighbor1[o.x-1][o.y];
    		neighbor1[o.x-1][o.y] = 0;
    		neighbors.add(new Board(neighbor1));
    	}
    	if (o.x + 1 < dimension) {
    		int[][] neighbor2 = copyBoard(this.board);
    		neighbor2[o.x][o.y] = neighbor2[o.x+1][o.y];
    		neighbor2[o.x+1][o.y] = 0;
    		neighbors.add(new Board(neighbor2));
    	}
    	if (o.y - 1 >= 0) {
    		int[][] neighbor3 = copyBoard(this.board);
    		neighbor3[o.x][o.y] = neighbor3[o.x][o.y-1];
    		neighbor3[o.x][o.y-1] = 0;
    		neighbors.add(new Board(neighbor3));
    	}
    	if (o.y + 1 < dimension) {
    		int[][] neighbor4 = copyBoard(this.board);
    		neighbor4[o.x][o.y] = neighbor4[o.x][o.y+1];
    		neighbor4[o.x][o.y+1] = 0;
    		neighbors.add(new Board(neighbor4));
    	}
    	return neighbors;
    }    // all neighboring boards
    
    public String toString() {
    	StringBuilder str = new StringBuilder();
    	str.append(dimension +"\n");
    	for(int i = 0; i< dimension; i++) {
    		for(int j = 0; j < dimension; j++) str.append(this.board[i][j]+" ");
    		str.append("\n");
    	}
    	return str.toString();
    }              // string representation of this board (in the output format specified below)
    
    private Coordinate findZeroPoint(){
    	for (int i = 0; i < dimension(); i++) {
    		for (int j = 0; j < dimension(); j++) {
    			if (board[i][j] == 0) return new Coordinate(i, j);
    		}
    	}
    	return null;
    }
    
    private class Coordinate {
    	int x,y;
    	public Coordinate(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    
    public static void main(String[] args) {
    	int[][] test = new int[][]{
    		{0, 1, 2},
    		{3, 4, 5},
    		{7, 6, 8}
    	};
    	Board testBoard = new Board(test);
    	if (testBoard.isGoal()) System.out.println("isGoal");
    	System.out.println(testBoard.twin());
    	for (Board neighbor : testBoard.neighbors()) System.out.println(neighbor);
    }// unit tests (not graded)
}
