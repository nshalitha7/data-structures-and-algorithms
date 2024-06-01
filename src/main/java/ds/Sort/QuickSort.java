package ds.Sort;

import static ds.utils.Utils.printArray;
import static ds.utils.Utils.swapElements;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {22, 5, 8, 88, 67, 8, 1, 10};

        printArray(nums);
        System.out.println();
        quickSort(nums);

        System.out.println();
        printArray(nums);
    }

    private static void quickSort(int[] nums) {
        quickSortRec(nums, 0, nums.length - 1);
    }

    private static void quickSortRec(int[] nums, int low, int high) {
        if (high <= low) return;
        int pivotIndex = partition(nums, low, high);
        printArray(nums);

        quickSortRec(nums, low, pivotIndex - 1);
        quickSortRec(nums, pivotIndex + 1, high);
    }

    private static int partition(int[] nums, int low, int high) {
        int i = low - 1;
        int pivot = nums[high];

        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                i++;
                swapElements(nums, i, j);
            }
        }

        swapElements(nums, i + 1, high);

        return i + 1;
    }
}
