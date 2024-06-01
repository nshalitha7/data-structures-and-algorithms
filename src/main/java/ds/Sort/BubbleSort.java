package ds.Sort;

import static ds.utils.Utils.printArray;
import static ds.utils.Utils.swapElements;

public class BubbleSort {
    public static void bubbleSort(int[] nums) {
        if (nums == null) return;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swapElements(nums, j, j+1);
                }
            }
            printArray(nums);
        }
    }

    public static void main(String[] args) {
        int[] nums = {22, 5, 8, 88, 67, 8, 1, 10};

        printArray(nums);
        System.out.println();

        bubbleSort(nums);

        System.out.println();
        printArray(nums);
    }
}
