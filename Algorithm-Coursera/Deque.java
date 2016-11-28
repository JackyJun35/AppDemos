import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.css.ElementCSSInlineStyle;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;

    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node before;
    }

   public Deque() {

   }
                        // construct an empty deque
   public boolean isEmpty() {
       return size == 0;
   }
                 // is the deque empty?
   public int size() {
	   return this.size;
   }
                        // return the number of items on the deque
   public void addFirst(Item item) {
       if (item == null) throw new NullPointerException();

       Node oldFirst = first;
       first = new Node();
       first.item = item;
       first.before = null;
       first.next = oldFirst;
       if (isEmpty()) last = first;
       else oldFirst.before = first;

       size++;
    }
         // add the item to the front
   public void addLast(Item item) {
       if (item == null) throw new NullPointerException();

       Node oldLast = last;
       last = new Node();
       last.item = item;
       last.next = null;
       last.before = oldLast;
       if (isEmpty()) first = last;
       else oldLast.next = last;
       size++;
   }
          // add the item to the end
   public Item removeFirst() {
       if (isEmpty()) throw new java.util.NoSuchElementException();
       Item item = first.item;
       first = first.next;

       size--;
       if (isEmpty()) last = first;
       else first.before = null;
       return item;
   }
              // remove and return the item from the front
   public Item removeLast() {
       if (isEmpty()) throw new java.util.NoSuchElementException();
       Item item = last.item;
       last = last.before;
       size--;

       if (isEmpty()) first = last;
       else last.next = null;
       return item;
   }
              // remove and return the item from the end
   public Iterator<Item> iterator() {
       return new DequeIterator();
       }

   private class DequeIterator implements Iterator<Item> {
	   private Node current = first;

	  @Override
	public boolean hasNext() {
		return current != null;
	}

	  @Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	  @Override
	public Item next() {
		  if (current.item == null) {
			  throw new NoSuchElementException();
		  }
		Item item = current.item;
		current = current.next;
		return item;
	}
   }
        // return an iterator over items in order from front to end
   public static void main(String[] args) {

   }  // unit testing


}
