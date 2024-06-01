package ds;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Stack <T> implements Iterable <T>{
    private final LinkedList<T> linkedList = new LinkedList<>();

    Stack() {
        // empty stack
    }

    Stack(T firstElem) {
        push(firstElem);
    }

    public int size() {
        return linkedList.size();
    }

    public void push(T elem){
        linkedList.addLast(elem);
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return linkedList.removeLast();
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return linkedList.peekLast();
    }

    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }
}
