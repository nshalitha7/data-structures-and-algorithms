package ds.Sort;

import static ds.utils.Utils.printArray;

public class SelectionSort {

    public static void selectionSort(int[] nums) {
        if (nums == null) return;
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
            printArray(nums);
        }
    }

    public static void selectionSortRec(int[] nums, int start) {
        if (nums == null) return;
        if (start >= nums.length) return;
        int minIndex = start;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] < nums[minIndex]) minIndex = i;
        }
        int temp = nums[start];
        nums[start] = nums[minIndex];
        nums[minIndex] = temp;
        printArray(nums);

        selectionSortRec(nums, start + 1);
    }

    public static void main(String[] args) {
        int[] nums = {22, 5, 8, 88, 67, 8, 1, 10};

        printArray(nums);
        System.out.println();

//        selectionSort(nums);
        selectionSortRec(nums, 0);

        System.out.println();
        printArray(nums);
    }
}
