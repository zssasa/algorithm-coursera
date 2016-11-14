/**
 * Created by zhangsheng on 2016/11/9.
 */
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int n;

    public RandomizedQueue() { // construct an empty randomized queue
        queue = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {              // is the queue empty?
        return n == 0;
    }

    public int size() {                        // return the number of items on the queue
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;

        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public void enqueue(Item item) {           // add the item
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (n == queue.length) resize(2*queue.length);
        queue[n++] = item;
    }

    public Item dequeue() {                    // remove and return a random item
        // it changes the order of original array
        // we only care about the random access to the element
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(n);
        Item item = queue[index];
        queue[index] = queue[n - 1];
        queue[n - 1] = null;
        n--;

        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item sample() {                    // return (but do not remove) a random item
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(n);
        Item item = queue[index];
        return item;
    }

    public Iterator<Item> iterator() {         // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int index = 0;
        private int iteratorLength = n;
        private Item[] iteratorQueue;

        private RandomizedQueueIterator() {
            iteratorQueue = (Item[]) new Object[n];
            for (int i = 0; i < iteratorQueue.length; i++) {
                iteratorQueue[i] = queue[i];
            }
        }

        public boolean hasNext() {
            return iteratorLength > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            index = StdRandom.uniform(iteratorLength);
            Item item = iteratorQueue[index];
            iteratorQueue[index] = iteratorQueue[iteratorLength - 1];
            iteratorQueue[iteratorLength - 1] = null;
            iteratorLength--;
            return item;
        }
    }
//    public static void main(String[] args) {   // unit testing
//        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
//        rq.enqueue(0);
//        rq.enqueue(1);
//        rq.enqueue(2);
//        rq.enqueue(3);
//        rq.enqueue(4);
//        rq.enqueue(5);
//        rq.enqueue(6);
//        rq.enqueue(7);
//        rq.enqueue(8);
//        rq.enqueue(9);
//        StdOut.println("items: " + rq.n);
//        StdOut.println(rq.toString());
//        StdOut.println(rq.dequeue());
//        StdOut.println(rq.dequeue());
//        StdOut.println(rq.dequeue());
//        //System.out.println(rq.to`String());
//        StdOut.println("items: " + rq.n);
//        Iterator it1 = rq.iterator();
//        Iterator it2 = rq.iterator();
//        while (it1.hasNext()) {
//            StdOut.println(it1.next());
//        }
//        StdOut.println("\n");
//        while (it2.hasNext()) {
//            StdOut.println(it2.next());
//        }
//    }
}
