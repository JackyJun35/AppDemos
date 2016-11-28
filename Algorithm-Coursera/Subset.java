import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
   
	public static void main(String[] args) {
	   int subsetSize = Integer.parseInt(args[0]);
	   String[] subset = StdIn.readAllStrings();
	   
	   performSubset(subsetSize,subset);
    }
   
   private static void performSubset(int subsetSize, String[] strings) {
	   RandomizedQueue<String> queue = new RandomizedQueue<>();
	   for (int i = 0; i < strings.length; i++) {
		   queue.enqueue(strings[i]);
	   }
	   
	   for (int i = 0; i < subsetSize; i++) {
		   StdOut.println(queue.dequeue());
	   }
   }
   
}
