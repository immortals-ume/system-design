package algorithms.search;

public class LinearSearch {

    public static void main(String[] args) {
        int index = LinearSearch.search(new int[]{1, 2, 3, 4, 5}, 0);
        System.out.println(index);
    }

    private static int search(int[] ints, int i) {
        for (int j = 0; j < ints.length; j++) {
            if (ints[j] == i) return j;
        }
        return -1;

    }
}
