import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	
	private class Node implements Comparable<Node>{
		
		private Board board;
		private int numOfMoves;
		private Node previous;
		
		public Node(Board board, Node previous){
			this.board = board;
			this.previous = previous;
			if (this.previous == null) numOfMoves = 0;
			else this.numOfMoves = previous.numOfMoves + 1;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.board.manhattan() - o.board.manhattan() + this.numOfMoves - o.numOfMoves;
		}
	}
	
	private MinPQ<Node> pq;
	private MinPQ<Node> twinPq;
	private boolean solvable;
	private boolean twinSolvable;
	private Node endNode;
    
	public Solver(Board initial) {
		solvable = false;
		twinSolvable = false;
		
		pq = new MinPQ<>();
		twinPq = new MinPQ<>();
    	
		Node iNode = new Node(initial, null);
    	pq.insert(iNode);
    	Node twinNode = new Node(initial.twin(), null);
    	twinPq.insert(twinNode);
     	
    	while (!solvable && !twinSolvable){
    		solvable = solvePriorQueue(pq);
    		twinSolvable = solvePriorQueue(twinPq);
    	}
    }          // find a solution to the initial board (using the A* algorithm)
	
	private boolean solvePriorQueue(MinPQ<Node> pq) {
		Node leastPrior = pq.delMin();
		
		if (leastPrior.board.isGoal()) {
			endNode = leastPrior;
			return true;
		}
		
		for (Board neighbor : leastPrior.board.neighbors()) 
			if (leastPrior.previous == null || !neighbor.equals(leastPrior.previous.board)) 
				pq.insert(new Node(neighbor, leastPrior));
		
		return false;
	}
    
    public boolean isSolvable() {
    	return solvable;
    }           // is the initial board solvable?
    
    public int moves() {
    	if (isSolvable()) return endNode.numOfMoves;
    	return -1;
    }                    // min number of moves to solve initial board; -1 if unsolvable
    
    public Iterable<Board> solution() {
    	if (isSolvable()){
	    	Stack<Board> solution = new Stack<>();
	    	Node current = endNode;
	    	solution.push(endNode.board);
	    	
	    	while (current.previous != null) {
	    		solution.push(current.previous.board);
	    		current = current.previous;
	    	}
	    	return solution;
    	}
    	return null;
    }     // sequence of boards in a shortest solution; null if unsolvable
    
   /* 
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    } // solve a slider puzzle (given below)
    */
    
}