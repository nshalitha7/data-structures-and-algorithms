package ds;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<T> implements Iterable<T>{
    private T[] array;
    private int size;
    private int capacity;

    DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("illegal capacity: " + capacity);
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void resize() {
        this.capacity *= 2;
        this.array = Arrays.copyOf(this.array, this.capacity);
    }

    public void add(T value) {
        if (this.size == this.capacity) resize();
        this.array[size++] = value;
    }

    public void set(int index, T value) {
        this.array[index] = value;
    }

    public T get(int index) {
        if (index >= 0 && index < this.size) return this.array[index];
        throw new IndexOutOfBoundsException("Invalid index" + index);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T removeAt(int index) {
        if (index >= 0 && index < this.size) {
            T[] newArray = (T[]) new Object[this.size - 1];
            int j = 0;
            T data = this.array[index];
            for (int i = 0; i < this.size; i++) {
                if ( i == index) {
                    continue;
                }
                newArray[j++] = this.array[i];
            }
            this.array = newArray;
            return data;
        }

        return null;
    }

    public boolean remove(T value) {
        for (int i = 0; i < this.size; i++) {
            if ( value.equals(this.array[i])) {
                removeAt(i);
                return true;
            }
        }

        return false;
    }

    public int indexOf(T value) {
        for (int i = 0; i < this.size; i++) {
            if ( value.equals(this.array[i])) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public void clear() {
        for(int i = 0; i < this.capacity; i++)
            this.array[i] = null;
        this.size = 0;
    }

    public T[] getArray() {
        return this.array;
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return array[index++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.array[i]);
            if ( i != this.size - 1) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>(2);
        dynamicArray.add(5);
        dynamicArray.add(2);
        dynamicArray.add(7);

        System.out.println("value at Index 1: " + dynamicArray.get(1));
        System.out.println("element 2 found At: " + dynamicArray.indexOf(2));
        System.out.println("size: " + dynamicArray.getSize());
        System.out.println("capacity: " + dynamicArray.getCapacity());
        System.out.println(dynamicArray.toString());
    }
}
