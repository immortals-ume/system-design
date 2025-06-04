# Streams and functional interface

---

### ✅ 1. **Core Concepts**

* What is Stream API? Why was it introduced in Java 8?
  Stream API was introduced in Java 8 to support functional-style operations on collections.
  It allows processing sequences of elements (like Collections) in a declarative way,
  enabling cleaner, readable,
  and efficient code, especially for filtering, mapping, and reducing data.

* Difference between **Stream** and **Collection**
  Answer: Collection stores data, Stream doesn't store elements.
  Collection is eager, Stream is lazy.
  Stream can’t be reused

* Characteristics of a Stream (non-storage, lazy, functional, once-traversable)

---

### ✅ 2. **Creating Streams**

* From collections: `stream()` vs `parallelStream()`
* From arrays: `Arrays.stream()`
* From values: `Stream.of()`
* From generators or builders: `Stream.generate()`, `Stream.iterate()`, `Stream.Builder`
* File-based: `Files.lines(Paths.get(...))`
* Primitive streams: `IntStream`, `LongStream`, `DoubleStream`

---

### ✅ 3. **Intermediate Operations**

These are **lazy** operations.

* `filter(Predicate)`
* `map(Function)` and `flatMap(Function)`
* `distinct()`
* `sorted()` and `sorted(Comparator)`
* `limit(n)` and `skip(n)`
* `peek()` (used for debugging; side-effects only)
* `mapToInt()`, `mapToLong()`, `mapToDouble()`

👉 **Interview Tip:** Be ready to **explain `flatMap` vs `map`** with examples.

---

### ✅ 4. **Terminal Operations**

These are **eager** operations that produce a result.

* `collect(Collectors.toList(), toSet(), toMap())`
* `reduce(identity, accumulator, combiner)` and simplified overloads
* `count()`, `min()`, `max()`, `sum()` (from primitive streams)
* `anyMatch()`, `allMatch()`, `noneMatch()`
* `findFirst()`, `findAny()`
* `forEach()` vs `forEachOrdered()`

---

### ✅ 5. **Collectors & Collectors API**

* `toList()`, `toSet()`, `toMap()`
* `groupingBy()`, `partitioningBy()`
* `joining()`
* `mapping()`, `reducing()`, `summarizingInt()`
* Custom collector using `Collector.of()`

---

### ✅ 6. **Reduction & Aggregation**

* `reduce()` examples (sum, max, min, product, string concat)
* Difference between `reduce()` and `collect()`
* Numeric reduction using `mapToInt().sum()`, `average()`

---

### ✅ 7. **Parallel Streams**

* How to create: `parallelStream()` or `stream().parallel()`
* When to use (I/O vs CPU bound)
* Problems: race conditions, thread-safety, order preservation
* `ForkJoinPool.commonPool()` usage
* Performance impact and tuning

---

### ✅ 8. **Optional + Stream**

* Handling null values with `Optional`
* `Optional.stream()` (Java 9+)
* Using `flatMap` to convert `Stream<Optional<T>>` into `Stream<T>`

---

### ✅ 9. **Primitive Streams**

* Why use `IntStream`, `LongStream`, `DoubleStream`?
* Methods like `range()`, `rangeClosed()`, `sum()`, `average()`, `summaryStatistics()`

---

### ✅ 10. **Real-World Scenarios & Patterns**

* Convert `List<String>` to `Map<String, Integer>` (word frequency)
* Group employees by department → Map\<Dept, List<Employee>>
* Partition students by pass/fail (boolean logic)
* Flat mapping nested lists
* Sorting with comparators in streams
* Pagination using `skip()` and `limit()`
* Duplicate removal with `distinct()` and `Collectors.toSet()`

---

### ✅ 11. **Stream vs Loops**

* When is Stream better than loops?
* Imperative vs declarative style
* Can you break a stream like a loop?

---

### ✅ 12. **Common Pitfalls**

* Using `collect()` incorrectly
* Mutation inside `map()` or `forEach()` (side-effects)
* Stream is not reusable — IllegalStateException on reuse
* Mixing stateful operations (e.g., `sorted()`, `distinct()`) in parallel streams
* Null pointer exceptions in `map()`, `filter()`, etc.

---

### ✅ 13. **Best Practices**

* Use streams only for **non-trivial transformations**
* Avoid heavy computation in parallel streams
* Don't mutate external state inside streams
* Know when to prefer loop over Stream (e.g., indexed iteration)

---

### ✅ 14. **Advanced Topics**

* Custom `Collector`
* Custom comparators with `Comparator.comparing()`, `thenComparing()`
* Lazy evaluation chain debugging with `peek()`
* Combining multiple filters and maps efficiently
* Performance tuning using Spliterator or custom stream sources (rare but interview-worthy)

---

### ✅ 15. **Java Versions Additions**

* **Java 9+**

    * `takeWhile()`, `dropWhile()`
    * `Optional.stream()`
* **Java 16+**

    * Stream `toList()` (unmodifiable)

---

### ✅ Sample Interview Questions

1. How does `flatMap()` work? Give a real use case.
2. Compare `reduce()` and `collect()`.
3. How would you use streams to remove duplicates from a list of custom objects?
4. How do you process nested collections with Stream API?
5. What are the risks of using `parallelStream()`?

---
Certainly! Here's an in-depth guide to **Java Functional Interfaces**, tailored for professionals with 5+ years of
experience. This comprehensive overview covers all types, including standard interfaces, primitive specializations, and
advanced usage patterns.

---

## 🔹 What is a Functional Interface?

A **Functional Interface** in Java is an interface that contains exactly **one abstract method**. They can have multiple
default or static methods. Introduced in Java 8, these interfaces enable the use of **lambda expressions** and **method
references**, promoting functional programming paradigms in Java.([GeeksforGeeks][1], [GeeksforGeeks][2])

* **Annotation**: `@FunctionalInterface` is optional but recommended. It enforces the single abstract method constraint
  at compile time.([Hero Vired][3])

---

## 🔹 Core Functional Interfaces in `java.util.function`

Java provides a rich set of built-in functional interfaces in the `java.util.function` package. Here's a
breakdown:([Hero Vired][3])

### 1. **Predicate<T>**

* **Purpose**: Evaluates a condition on an input and returns a boolean.
* **Method**: `boolean test(T t)`
* **Use Case**: Filtering collections.
* **Example**:

```java
  Predicate<String> isEmpty = String::isEmpty;
```

* **Default Methods**: `and()`, `or()`, `negate()`

### 2. **Function\<T, R>**

* **Purpose**: Transforms an input of type T to an output of type R.
* **Method**: `R apply(T t)`
* **Use Case**: Mapping values.
* **Example**:

```java
  Function<Integer, String> intToString = Object::toString;
```

* **Default Methods**: `andThen()`, `compose()`([Javatute][4])

### 3. **Consumer<T>**

* **Purpose**: Performs an action on an input without returning a result.
* **Method**: `void accept(T t)`
* **Use Case**: Iterating over collections.
* **Example**:

```java
  Consumer<String> print = System.out::println;
```

* **Default Method**: `andThen()`([Wikipedia][5])

### 4. **Supplier<T>**

* **Purpose**: Provides a result without any input.
* **Method**: `T get()`
* **Use Case**: Lazy initialization.
* **Example**:

```java
  Supplier<Double> random = Math::random;
```

([TutorialsPoint][6])

### 5. **UnaryOperator<T>**

* **Purpose**: Represents an operation on a single operand that produces a result of the same type.
* **Method**: `T apply(T t)`
* **Use Case**: Incrementing values.
* **Example**:

```java
  UnaryOperator<Integer> square = x -> x * x;
```

### 6. **BinaryOperator<T>**

* **Purpose**: Represents an operation upon two operands of the same type, producing a result of the same type.
* **Method**: `T apply(T t1, T t2)`
* **Use Case**: Summing values.
* **Example**:

```java
  BinaryOperator<Integer> add = Integer::sum;
```

([TutorialsArena][7])

---

## 🔹 Bi-Functional Interfaces

These interfaces operate on two input arguments:

* **BiPredicate\<T, U>**: `boolean test(T t, U u)`
* **BiFunction\<T, U, R>**: `R apply(T t, U u)`
* **BiConsumer\<T, U>**: `void accept(T t, U u)`([heapsteep.com][8])

**Use Cases**:

* **BiPredicate**: Checking equality between two objects.
* **BiFunction**: Combining two values into one.
* **BiConsumer**: Performing operations on pairs of values, like entries in a map.([DZone][9])

---

## 🔹 Primitive Specializations

To avoid boxing overhead, Java provides primitive-specific versions:([Java Concept Of The Day][10])

* **IntPredicate**, **LongPredicate**, **DoublePredicate**
* **IntConsumer**, **LongConsumer**, **DoubleConsumer**
* **IntSupplier**, **LongSupplier**, **DoubleSupplier**
* **IntFunction<R>**, **LongFunction<R>**, **DoubleFunction<R>**
* **ToIntFunction<T>**, **ToLongFunction<T>**, **ToDoubleFunction<T>**
* **IntUnaryOperator**, **LongUnaryOperator**, **DoubleUnaryOperator**
* **IntBinaryOperator**, **LongBinaryOperator**, **DoubleBinaryOperator**

**Use Case**: Processing primitive data types efficiently.

---

## 🔹 Other Notable Functional Interfaces

* **Runnable**: `void run()`
* **Callable<V>**: `V call()` (can throw exceptions)
* **Comparator<T>**: `int compare(T o1, T o2)`
* **Iterable<T>**: `Iterator<T> iterator()`

---

## 🔹 Custom Functional Interfaces

You can define your own functional interfaces:

```java
@FunctionalInterface
interface Calculator {
    int compute(int a, int b);
}
```

**Use Case**: When existing interfaces don't match the desired method signature.

---

## 🔹 Advanced Concepts

### 1. **Function Composition**

Combine multiple functions:

```java
Function<Integer, Integer> multiply = x -> x * 2;
Function<Integer, Integer> add = x -> x + 3;
Function<Integer, Integer> combined = multiply.andThen(add);
```

### 2. **Currying**

Transforming a function with multiple arguments into a sequence of functions each with a single argument.

```java
Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;
```

### 3. **Exception Handling**

Functional interfaces don't handle checked exceptions by default. You can create custom interfaces or wrap exceptions:

```java
@FunctionalInterface
interface ThrowingFunction<T, R> {
    R apply(T t) throws Exception;
}
```

---

## 🔹 Best Practices

* **Use Built-in Interfaces**: Prefer existing interfaces before creating custom ones.
* **Avoid Side Effects**: Ensure functions are pure and don't modify external state.
* **Use @FunctionalInterface**: Helps catch errors at compile time.
* **Leverage Method References**: For cleaner and more readable code.

---

This comprehensive overview should equip you with a deep understanding of Java Functional Interfaces, their types, and
best practices for usage.

---

[1]: https://www.geeksforgeeks.org/java-functional-interfaces/?utm_source=chatgpt.com "Java Functional Interfaces - GeeksforGeeks"

[2]: https://www.geeksforgeeks.org/function-interface-in-java/?utm_source=chatgpt.com "Function Interface in Java - GeeksforGeeks"

[3]: https://herovired.com/learning-hub/blogs/functional-interface-in-java/?utm_source=chatgpt.com "Functional Interface in Java: Uses, Types, Examples - Hero Vired"

[4]: https://javatute.com/core-java/java-8-functional-interface-examples/?utm_source=chatgpt.com "Java 8 Functional Interface Examples - JavaTute"

[5]: https://en.wikipedia.org/wiki/Fluent_interface?utm_source=chatgpt.com "Fluent interface"

[6]: https://www.tutorialspoint.com/java/java_functional_interfaces.htm?utm_source=chatgpt.com "Types of Functional Interfaces in Java - Online Tutorials Library"

[7]: https://tutorialsarena.com/programming/java/java-functional-interfaces?utm_source=chatgpt.com "Java Functional Interfaces: A Comprehensive Guide with Examples"

[8]: https://heapsteep.com/java-functional-interfaces-predicate-function-consumer-and-supplier/?utm_source=chatgpt.com "Predicate, Function, Consumer and Supplier in Java 8 - heapsteep"

[9]: https://dzone.com/articles/cheatsheet-java-functional-interfaces?utm_source=chatgpt.com "Cheatsheet: Java Functional Interfaces - DZone"

[10]: https://javaconceptoftheday.com/java-8-functional-interfaces/?utm_source=chatgpt.com "Java 8 Functional Interfaces – When & How To Use Them?"

