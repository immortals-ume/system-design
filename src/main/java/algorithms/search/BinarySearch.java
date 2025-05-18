package algorithms.search;

import java.util.Collections;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        int index = BinarySearch.search(new int[]{1, 2, 3, 4, 5}, 0);
        System.out.println(index);

        int indx = Collections.binarySearch(List.of(1, 2, 3, 4, 5), 0);
        System.out.println(indx);
    }

    private static int search(int[] ints, int i) {
        int low = 0;
        int high = ints.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (ints[mid] == i) {
                return mid;
            } else if (ints[mid] < i) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
