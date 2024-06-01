package ds;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicIntArray {
    private int[] array;
    private int capacity;
    private int size;

    DynamicIntArray(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
        this.size = 0;
    }

    public void resize() {
        this.capacity *= 2;
        this.array = Arrays.copyOf(this.array, this.capacity);
    }

    public int[] getArray() {
        return this.array;
    }

    public int getArray(int index) {
        if (index >= 0 && index < this.size) return this.array[index];
        throw new IndexOutOfBoundsException();
    }

    public void add(int index, int value) {

    }

    public void add(int value) {
        if (this.size == this.capacity) {
            resize();
        }
        array[this.size++] = value;
    }

    public void remove(int index) {

    }

    public int search(int value) {
        return -1;
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public static void main(String[] args) {
        DynamicIntArray dynamicIntArray = new DynamicIntArray(2);

        dynamicIntArray.add(2);
        dynamicIntArray.add(4);
        dynamicIntArray.add(88);
        dynamicIntArray.add(66);
        dynamicIntArray.add(46);
        dynamicIntArray.add(664);
        dynamicIntArray.add(2, 6);
        dynamicIntArray.remove(3);

        int[] array = dynamicIntArray.getArray();
        int indexOf2 = dynamicIntArray.search(2);
        int valueAtIndex1 = dynamicIntArray.getArray(1);
        System.out.println("valueAtIndex1: " + valueAtIndex1);
        System.out.println("size: " + dynamicIntArray.getSize());
        System.out.println("capacity: " + dynamicIntArray.getCapacity());
    }
}
