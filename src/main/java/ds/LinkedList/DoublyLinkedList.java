package ds.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.next = trav.prev = null;
            trav.data = null;

            trav = next;
        }

        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void addFirst(T elem) {
        if (isEmpty()) {
            head = new Node<>(elem, null, null);
        } else {
            head.prev = new Node<>(elem, null, head);
            head = head.prev;
        }

        size++;
    }

    public void addLast(T elem) {
        if (isEmpty()) {
            head = new Node<>(elem, null, null);
        } else {
            tail.next = new Node<>(elem, tail, null);
            tail = tail.next;
        }

        size++;
    }

    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        T data = head.data;
        head = head.next;
        size--;

        if (isEmpty()) tail = null;
        else head.prev = null;

        return data;
    }

    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        T data = tail.data;
        tail = tail.prev;
        size--;

        if (isEmpty()) head = null;
        else tail.next = null;

        return data;
    }

    private T remove(Node<T> node) {
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        T data = node.data;
        node.next.prev = node.prev;
        node.prev.next = node.next;

        node.data = null;
        node.prev = node.next = null;
        node = null;

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("index not valid");
        Node<T> node = head;
        for (int j = 0; j < index; j++)
            node = node.next;

        return remove(node);
    }

    public int indexOf(Object obj) {
        Node<T> trav = head;
        if (obj == null) {
            for (int i = 0; i < size; i++) {
                if (trav.data == null) return i;
                trav = trav.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (obj.equals(trav.data)) return i;
                trav = trav.next;
            }
        }

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        Node<T> trav = head;
        StringBuilder sb = new StringBuilder("[");
        while (trav != null) {
            sb.append(trav.data);
            trav = trav.next;
            if (trav != null ) sb.append(", ");
        }

        return sb.append("]").toString();
    }

    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
    }
}
