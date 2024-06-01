package ds.Sort;

import static ds.utils.Utils.printArray;

public class InsertionSort {
    public static void main(String[] args) {
        int[] nums = {22, 5, 8, 88, 67, 8, 1, 10};

        printArray(nums);
        System.out.println();
        insertionSort(nums);

        System.out.println();
        printArray(nums);
    }

    private static void insertionSort(int[] nums) {
        // assume element with index 0 is already sorted
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > key ) {
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1]=key;

            printArray(nums);
        }
    }
}
