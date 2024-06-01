package ds.utils;

public class Utils {

    private Utils(){}
    public static void swapElements(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void printArray(int[] nums) {
        for (int num: nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
