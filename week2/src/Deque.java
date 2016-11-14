/**
 * Created by zhangsheng on 2016/11/9.
 */
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (this.isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node();
            oldFirst.prev = first;
            first.item = item;
            first.next = oldFirst;
            first.prev = null;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (this.isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            oldLast.next = last;
            last.item = item;
            last.prev = oldLast;
            last.next = null;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = first.item;

        if (this.size() == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        n--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = last.item;

        if (this.size() == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>  {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

//    public static void main(String[] args) {
//        Deque<Integer> deq2 = new Deque<Integer>();
//        System.out.println("size: " + deq2.size());
//        deq2.addFirst(1);
//        deq2.addFirst(2);
//        deq2.addFirst(3);
//        deq2.addFirst(4);
//        deq2.addFirst(5)
//        System.out.println("size: " + deq2.size());
//        for (Integer x : deq2) {
//            System.out.println(x);
//        }
//        deq2.removeLast();
//        System.out.println("size: " + deq2.size());
//        for (Integer x : deq2) {
//            System.out.println(x);
//        }
//        deq2.removeFirst();
//        deq2.removeFirst();
//        System.out.println("size: " + deq2.size());
//        for (Integer x : deq2) {
//            System.out.println(x);
//        }
//        deq2.removeLast();
//        deq2.removeLast();
//        System.out.println("size: " + deq2.size());
//        for (Integer x : deq2) {
//            System.out.println(x);
//        }
//        deq2.addFirst(1);
//        deq2.addLast(2);
//        System.out.println("size: " + deq2.size());
//        for (Integer x : deq2) {
//            System.out.println(x);
//        }
//        deq2.addFirst(3);
//        deq2.addLast(4);
//        System.out.println("size: " + deq2.size());
//        for (Integer x : deq2) {
//            System.out.println(x);
//        }
//        Iterator itr = deq2.iterator();
//        //System.out.println(itr.);
//        System.out.println(itr.next());
//        System.out.println(itr.next());
//        System.out.println(itr.next());
//        System.out.println(itr.next());
//        //System.out.println(itr.next());
//    }
}
