import java.util.Iterator;

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] s;
	private int size;
	
	
	public RandomizedQueue() {
		s = (Item[]) new Object[1];
		size = 0;
   }                 // construct an empty randomized queue
   public boolean isEmpty() {
	   return size == 0;
   }              // is the queue empty?
   public int size() {
	   return this.size;
   }                        // return the number of items on the queue
   public void enqueue(Item item) {
	   if (item == null) {
			throw new NullPointerException("Cannot enqueue null objects.");
		}
	   
	   if (s.length == size) {
		   Item[] newQueue = (Item[]) new Object[size*2];
		   for (int i = 0; i < size; i++) {
			   newQueue[i] = s[i];
		   }
		   this.s = newQueue;
	   }
	   
	   s[size] = item;
	   size++;
   }          // add the item
   public Item dequeue() {
	   if (isEmpty()) {
			throw new NoSuchElementException("Queue is currently empty.");
		}
	   int random = getRandomIndex();
	   Item dequeue = s[random];
	   s[random] = s[this.size - 1];
	   s[this.size - 1] = null;
	  
	   if (this.size >= 4 && this.size <= s.length/4) {
		   Item[] newQueue = (Item[]) new Object[size/2];
		   for (int i = 0; i < size; i++) {
			   newQueue[i] = s[i];
		   }
		   this.s = newQueue; 
	   }
	   
	   size--;
	   return dequeue;
   }                   // remove and return a random item
   public Item sample() {
	   if (isEmpty()) {
			throw new NoSuchElementException("Queue is currently empty.");
		}
	   int random = getRandomIndex();
	   return s[random];
   }                    // return (but do not remove) a random item
   public Iterator<Item> iterator() {
		return new ListIterator(s, size);
	}
	
	private class ListIterator implements Iterator<Item> {
		
		private Item[] iteratorQueue;
		private int iteratorIndex = 0;
		
		public ListIterator(Item[] queue, int size) {
			
			iteratorQueue = (Item[]) new Object[size];
			
			// Copy items into iterator queue
			for (int i = 0; i < iteratorQueue.length; i++) {
				iteratorQueue[i] = queue[i];
			}
			
			// Knuth shuffle the iterator queue
			for (int j = 1; j < iteratorQueue.length; j++) {
				int swapIndex = StdRandom.uniform(j + 1);
				
				Item temp = iteratorQueue[j];
				iteratorQueue[j] = iteratorQueue[swapIndex];
				iteratorQueue[swapIndex] = temp;
			}
		}
		
		@Override
		public boolean hasNext() {
			return (iteratorIndex < iteratorQueue.length);
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more objects to iterate through");
			}
			
			Item item = iteratorQueue[iteratorIndex];
			iteratorIndex++;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove method not supported");
		}
	}
   
	private int getRandomIndex() {
	   while (true) {
			int rand = StdRandom.uniform(size);
			if (s[rand] != null) {
				return rand;
			}
		}
   }
   
   public static void main(String[] args) {
	   
   }  // unit testing
	}
	
