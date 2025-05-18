package algorithms.sorting;

import java.util.Arrays;

public class CountingSort {
    public static void countingSort(int[] arr) {
        if (arr.length == 0) return;

        // 1. Find the max value in the array
        int max = Arrays.stream(arr).max().getAsInt();

        // 2. Create a count array
        int[] count = new int[max + 1];

        // 3. Count each number's occurrence
        for (int num : arr) {
            count[num]++;
        }

        // 4. Rewrite the original array in sorted order
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {4, 2, 2, 8, 3, 3, 1};

        System.out.println("Before sorting: " + Arrays.toString(numbers));
        countingSort(numbers);
        System.out.println("After sorting:  " + Arrays.toString(numbers));
    }
}
