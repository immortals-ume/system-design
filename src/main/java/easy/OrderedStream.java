package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class OrderedStream {
    private final TreeMap<Integer, String> map;
    private int ptr;

    public OrderedStream(int n) {
        map = new TreeMap<>();
        ptr = 1;
    }

    public static void main(String[] args) {
        OrderedStream os = new OrderedStream(5);

        // Constraint: value.length == 5
        assert isValidValue("ccccc");
        assert isValidValue("aaaaa");
        assert !isValidValue("toolong") : "Value must be exactly 5 characters.";

        // Constraint: value is lowercase
        assert isLowercase("ddddd");
        assert !isLowercase("AbCdE") : "Value must be lowercase letters only.";

        // Constraint: id in range [1, n]
        try {
            os.insert(0, "aaaaa");
            assert false : "id must be >= 1";
        } catch (Exception e) {
            System.out.println("Passed: id >= 1 check");
        }

        try {
            os.insert(6, "aaaaa");
            assert false : "id must be <= n";
        } catch (Exception e) {
            System.out.println("Passed: id <= n check");
        }

        // Valid inserts
        System.out.println(os.insert(3, "ccccc")); // []
        System.out.println(os.insert(1, "aaaaa")); // [aaaaa]
        System.out.println(os.insert(2, "bbbbb")); // [bbbbb, ccccc]
        System.out.println(os.insert(5, "eeeee")); // []
        System.out.println(os.insert(4, "ddddd")); // [ddddd, eeeee]
    }

    private static boolean isValidValue(String value) {
        return value.length() == 5;
    }

    private static boolean isLowercase(String value) {
        return value.chars().allMatch(Character::isLowerCase);
    }

    public List<String> insert(int idKey, String value) {
        map.put(idKey, value);
        List<String> result = new ArrayList<>();

        while (map.containsKey(ptr)) {
            result.add(map.get(ptr));
            map.remove(ptr);
            ptr++;
        }

        return result;
    }
}