package ds.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {
    Node<T> head;
    int size;

    public static class Node<T> {
        T data;
        Node<T> next;
        Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

    public boolean add(T data) {
        if (data == null) return false;
        Node<T> newNode = new Node<>(data, null);
        if (head == null) {
            head= newNode;
            size++;
            return true;
        }

        Node<T> trav = head;
        while(trav.next != null) trav= trav.next;
        trav.next = newNode;
        size++;

        return true;
    }

    public T get(int index) {
        if (index < 0 || index >= size || head == null) return null;
        Node<T> trav = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) return trav.data;
            trav= trav.next   ;
        }

        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T result;
                if (hasNext()) {
                    result = trav.data;
                    trav = trav.next;
                } else {
                    throw new NoSuchElementException();
                }

                return result;
            }
        };
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>();
        sl.add(5);
        sl.add(2);
        sl.add(8);
        sl.add(6);
        sl.add(9);
        sl.add(54);
        System.out.println(sl.get(2));
        for (Integer integer : sl) System.out.print(integer + " ");
        System.out.println();
    }
}
