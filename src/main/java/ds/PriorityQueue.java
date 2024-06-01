package ds;

import java.util.*;

/**
 * min priority queue implementation using a binary heap
 */
public class PriorityQueue<T extends Comparable<T>> {
    private List<T> heap; // track elements inside min pq heap
    private Map<T, TreeSet<Integer>> map = new HashMap<>(); // store indices of elements in  heap

    private int heapSize;

    private int heapCapacity;

    public PriorityQueue() {
        this(1);
    }

    public PriorityQueue(int initialCapacity) {
        heap = new ArrayList<>(initialCapacity);
    }

    // O(n)
    public PriorityQueue(T[] elems) {
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<>(heapCapacity);

        // store all elements in heap
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elems[i], i);
            heap.add(elems[i]);
        }

        //Heapify process, O(n)
    }

    // O(nlog(n))
    public PriorityQueue(Collection<T> elems) {
        this(elems.size());
        heapSize = heapCapacity = elems.size();
    }

    // O(log(n))
    public void add(T elem) {
        if (elem == null) throw new IllegalArgumentException();
        if (heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        } else {
            heap.add(elem);
            heapCapacity++;
        }
        mapAdd(elem, heapSize);

        swim(heapSize);
        heapSize++;
    }

    // returns lowest priority value of heap, if empty pq returns null
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    // removes the root of the heap, O(log(n))
    public T poll() {
        return null;
    }

    // O(log(n))
    public boolean remove(T elem) {
        if (elem == null) return false;
        int removeIndex = map.get(elem).first();
        if (removeIndex > 0 && removeIndex < heapSize) {
            return removeAt(removeIndex) != null;
        }

        return false;
    }

    // O(log(n))
    public T removeAt(int index) {
        if (isEmpty()) return null;
        T removeNode = heap.get(index);

        swap(index, heapSize - 1);

        heap.set(heapSize - 1, null);
        mapRemove(removeNode, heapSize - 1);

        if (index == heapSize - 1) return removeNode;

        if (less(2 * index + 1, index) || less(2 * index + 2, index))
            sink(index);
        else
            swim(index);

        heapSize--;

        return removeNode;
    }

    public boolean contains(T elem) {
        if (elem == null) return false;
        return map.containsKey(elem);
    }

    // O(n)
    public void clear() {
        for (int i = 0; i < heapCapacity; i++)
            heap.set(i, null);
        heapSize = 0;
        map.clear();
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public int size() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    // test if node i <= node j, O(1)
    // assume i, j are valid indices
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);

        return node1.compareTo(node2) <= 0;
    }

    private void mapAdd(T elem, int index) {
        if (!map.containsKey(elem)) {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(index);
            map.put(elem, set);
        } else {
            map.get(elem).add(index);
        }
    }

    // O(log(n))
    private void mapRemove(T value, int index) {
        TreeSet<Integer> remove = map.get(value);
        remove.remove(index);

        if (remove.isEmpty()) map.remove(value);
    }

    // top down node sink, O(log(n))
    private void sink(int k) {
        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = left;

            if (k >= 0 && right < heapSize && less(right, left))
                smallest = right;

            if (k < 0 || left >= heapSize || less(k, smallest))
                break;

            swap(k, smallest);
            k = smallest;
        }

    }

    // bottom up node swim, O(log(n))
    private void swim(int k) {
        int parentIndex = (k - 1) / 2;

        while (k > 0 && less(k, parentIndex)) {
            swap(parentIndex, k);
            k = parentIndex;

            parentIndex = (k - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);

        heap.set(i, node2);
        heap.set(j, node1);

        mapSwap(node1, node2, i, j);
    }

    private void mapSwap(T node1, T node2, int i, int j) {
        TreeSet<Integer> set1 = map.get(node1);
        TreeSet<Integer> set2 = map.get(node2);

        set1.remove(i);
        set1.add(j);
        set2.remove(j);
        set2.add(i);
    }

    public boolean isMinHeap(int k) {
        if ( k >= heapSize) return true;
        int left = 2*k + 1;
        int right = 2*k + 2;

        if ( (left < heapSize && less(left, k)) || (right < heapSize && less(right, k)))
            return false;

        return isMinHeap(left) && isMinHeap(right);
    }

    public static void main(String[] args) {

    }
}
