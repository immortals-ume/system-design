package interview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        // Streams
        List<String> list = List.of("1333", "2333", "33333");

        List<String> stringList = list.stream()
                .filter(x -> x.length() > 3)
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .limit(5)
                .skip(2)
                .peek(System.out::println)
                .collect(Collectors.toList());

        Optional<String> first = list.stream()
                .findFirst();
        boolean any = list.stream()
                .anyMatch(s -> s.contains("x"));
        long count = list.stream()
                .filter(s -> s.startsWith("A"))
                .count();
        list.stream()
                .forEach(System.out::println);


        Stream<String> stream1 = list.stream();
        Stream<Integer> stream2 = Stream.of(1, 2, 3);
        IntStream intStream = IntStream.range(1, 10);
        LongStream longStream = LongStream.range(1, 10);
        DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0);
        Stream<String> stream3 = Stream.generate(() -> "Hello");
        Stream<String> stream4 = Stream.iterate("Hello", s -> s + " World");
        Stream<String> fileLines = Files.lines(Paths.get("Java.txt"));
        List<Employee> employeeStream = List.of(new Employee(1L, "John", 2000L), new Employee(2L, "Mary", 1500L));

        Map<Long, List<Employee>> byDept = employeeStream.stream()
                .collect(Collectors.groupingBy(Employee::getSalary));

        String csv = list.stream()
                .collect(Collectors.joining(", "));

        Map<Boolean, List<String>> partitioned = list.stream()
                .collect(Collectors.partitioningBy(n -> n.length() % 2 == 0));

        Collector<Employee, ?, Map<Object, Double>> avgSalaryByDept =
                Collectors.groupingBy(Employee::getName,
                        Collectors.averagingDouble(Employee::getSalary));
//        int sum = numbers.stream().reduce(0, Integer::sum);
//        String combined = list.stream().reduce("", String::concat);
//        Optional<Integer> max = numbers.stream().reduce(Integer::max);

        Map<String, Long> freq = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
