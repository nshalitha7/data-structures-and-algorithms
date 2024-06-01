package ds.Sort;

import java.util.Arrays;

import static ds.utils.Utils.printArray;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {22, 5, 8, 88, 67, 8, 1, 10};

        printArray(nums);
        System.out.println();

        mergeSort(nums);

        System.out.println();
        printArray(nums);
    }

    private static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;

        int mid = (start + end) / 2;

        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);

        merge(nums, start, mid, end);
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int lSize = mid - start + 1;
        int rSize = end - mid;
        int[] lArray = new int[lSize];
        int[] rArray = new int[rSize];

        for (int i = 0; i < lSize; i++) {
            lArray[i] = nums[i + start];
        }
        for (int i = 0; i < rSize; i++) {
            rArray[i] = nums[i + mid + 1];
        }

        int k = start;
        int i = 0;
        int j = 0;
        while(i < lSize && j < rSize) {
            if(lArray[i] < rArray[j]) {
                nums[k] = lArray[i];
                i++;
            } else {
                nums[k] = rArray[j];
                j++;
            }
            k++;
        }

        while (i < lSize) {
            nums[k] = lArray[i];
            i++;
            k++;
        }
        while (j < rSize) {
            nums[k] = rArray[j];
            j++;
            k++;
        }
    }
}
