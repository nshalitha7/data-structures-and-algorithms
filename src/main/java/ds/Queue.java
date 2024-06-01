package ds;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable<T>{
    private LinkedList<T> list = new LinkedList<>();

    public Queue(){
        // empty queue
    }

    public Queue(T firstElem) {
        list.add(firstElem);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Empty Queue");
        return list.peekFirst();
    }

    public T poll() {
        if (isEmpty()) throw new RuntimeException("Empty Queue");
        return list.removeFirst();
    }

    public void offer(T elem) {
        list.addLast(elem);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
