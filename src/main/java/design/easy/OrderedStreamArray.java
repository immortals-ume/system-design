package design.easy;

import java.util.ArrayList;
import java.util.List;

class OrderedStreamArray {
    private final String[] stream;
    private int ptr;

    public OrderedStreamArray(int n) {
        stream = new String[n + 1]; // 1-based indexing
        ptr = 1;
    }

    public static void main(String[] args) {
        OrderedStreamArray os = new OrderedStreamArray(5);
        System.out.println(os.insert(3, "ccccc")); // []
        System.out.println(os.insert(1, "aaaaa")); // [aaaaa]
        System.out.println(os.insert(2, "bbbbb")); // [bbbbb, ccccc]
        System.out.println(os.insert(5, "eeeee")); // []
        System.out.println(os.insert(4, "ddddd")); // [ddddd, eeeee]
    }

    public List<String> insert(int idKey, String value) {
        stream[idKey] = value;
        List<String> result = new ArrayList<>();

        while (ptr < stream.length && stream[ptr] != null) {
            result.add(stream[ptr]);
            ptr++;
        }

        return result;
    }
}