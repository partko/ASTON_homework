package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.example.CustomLinkedList.bubbleSort;

public class CustomLinkedListTest {
    private static final Integer[] emptyArray = {};
    private static final Integer[] emptyArraySorted = {};
    private static final Integer[] arrayOneElement = {123};
    private static final Integer[] arrayOneElementSorted = {123};
    private static final Integer[] evenArray = {3, 15, 0, 6, -7, 4, 1, -94, 5, -3, -5, 2};
    private static final Integer[] evenArraySorted = {-94, -7, -5, -3, 0, 1, 2, 3, 4, 5, 6, 15};
    private static final Integer[] oddArray = {7, -1, 2, -4, 23, -6, -12, 5, 0, 9, 4};
    private static final Integer[] addArraySorted = {-12, -6, -4, -1, 0, 2, 4, 5, 7, 9, 23};
    private static final Integer[] arrayWithDuplicateElements = {3, 5, 1, 0, 6, -7, 5, 5, 4, 1, 0, 5, 5, 5, -3, -5, 2, 2, 0};
    private static final Integer[] arrayWithDuplicateElementsSorted = {-7, -5, -3, 0, 0, 0, 1, 1, 2, 2, 3, 4, 5, 5, 5, 5, 5, 5, 6};
    private static Integer[] bigArray = null;
    private static Integer[] bigArraySorted = null;

    @BeforeAll
    static void createBigArray() {
        Random r = new Random();
        bigArray = new Integer[1000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = r.nextInt();
        }
        bigArraySorted = bigArray.clone();
        Arrays.sort(bigArraySorted);
    }

    @Test
    void bubbleSort_emptyArray() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(emptyArray));
        bubbleSort(test);
        Assertions.assertArrayEquals(emptyArraySorted, test.toArray());
    }

    @Test
    void bubbleSort_arrayOneElement() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(arrayOneElement));
        bubbleSort(test);
        Assertions.assertArrayEquals(arrayOneElementSorted, test.toArray());
    }

    @Test
    void bubbleSort_evenArray() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(evenArray));
        bubbleSort(test);
        Assertions.assertArrayEquals(evenArraySorted, test.toArray());
    }

    @Test
    void bubbleSort_oddArray() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(oddArray));
        bubbleSort(test);
        Assertions.assertArrayEquals(addArraySorted, test.toArray());
    }

    @Test
    void bubbleSort_arrayWithDuplicateElements() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(arrayWithDuplicateElements));
        bubbleSort(test);
        Assertions.assertArrayEquals(arrayWithDuplicateElementsSorted, test.toArray());
    }

    @Test
    void bubbleSort_bigArray() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(bigArray));
        bubbleSort(test);
        Assertions.assertArrayEquals(bigArraySorted, test.toArray());
    }

    @Test
    void bubbleSort_withComparator() {
        CustomLinkedList<Person> test = new CustomLinkedList<>(Arrays.asList(
                new Person("Alice", 22),
                new Person("Kate", 18),
                new Person("Emily", 25)));
        bubbleSort(test, new ComparatorByAge());
        Assertions.assertEquals(18, test.get(0).getAge());
        Assertions.assertEquals(22, test.get(1).getAge());
        Assertions.assertEquals(25, test.get(2).getAge());

        bubbleSort(test, new ComparatorByName());
        Assertions.assertEquals("Alice", test.get(0).getName());
        Assertions.assertEquals("Emily", test.get(1).getName());
        Assertions.assertEquals("Kate", test.get(2).getName());
    }



    @Test
    void constructorWithCollection() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(evenArray));
        Assertions.assertEquals(test.size(), evenArray.length);
        Assertions.assertArrayEquals(evenArray, test.toArray());
    }

    @Test
    void get() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(evenArray));
        Assertions.assertEquals(3, test.get(0));
        Assertions.assertEquals(15, test.get(1));
        Assertions.assertEquals(-7, test.get(4));
        Assertions.assertEquals(2, test.get(11));
        Assertions.assertEquals(evenArray.length, test.size());
    }

    @Test
    void add() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(evenArray));
        test.add(545);
        test.add(-47);
        test.add(90);
        Assertions.assertEquals(evenArray.length + 3, test.size());
        Assertions.assertEquals(4, test.get(5));
        Assertions.assertEquals(2, test.get(11));
        Assertions.assertEquals(545, test.get(12));
        Assertions.assertEquals(-47, test.get(13));
        Assertions.assertEquals(90, test.get(14));
    }

    @Test
    void add_withIncreaseLength() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>();
        Assertions.assertEquals(0, test.size());
        for (int i = 0; i < 100; i++) {
            test.add(i);
            Assertions.assertEquals(i+1, test.size());
            Assertions.assertEquals(i, test.get(i));
        }
    }

    @Test
    void add_toMiddle() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(evenArray));
        test.add(5, 777);
        test.add(0, -5);
        test.add(12, 34);
        test.add(15, -12);
        Assertions.assertEquals(evenArray.length + 4, test.size());
        Integer[] result = {-5, 3, 15, 0, 6, -7, 777, 4, 1, -94, 5, -3, 34, -5, 2, -12};
        Assertions.assertArrayEquals(result, test.toArray());
    }

    @Test
    void set() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(evenArray));
        test.set(0, 23);
        test.set(1, 0);
        test.set(4, -5);
        test.set(11, -123);
        Assertions.assertEquals(23, test.get(0));
        Assertions.assertEquals(0, test.get(1));
        Assertions.assertEquals(-5, test.get(4));
        Assertions.assertEquals(-123, test.get(11));
        Assertions.assertEquals(evenArray.length, test.size());
    }

    @Test
    void remove() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(evenArray));
        test.remove(0);
        Assertions.assertEquals(15, test.get(0));
        test.remove(5);
        Assertions.assertEquals(-94, test.get(5));
        test.remove(7);
        Assertions.assertEquals(-94, test.get(5));
        test.remove(0);
        Assertions.assertEquals(0, test.get(0));
        Assertions.assertEquals(evenArray.length - 4, test.size());
    }

    @Test
    void addAll() {
        CustomLinkedList<Integer> test = new CustomLinkedList<>(Arrays.asList(evenArray));
        Assertions.assertEquals(evenArray.length, test.size());
        test.addAll(Arrays.asList(evenArray));
        Assertions.assertEquals(evenArray.length * 2, test.size());
        Assertions.assertEquals(3, test.get(12));
        Assertions.assertEquals(2, test.get(23));
        test.addAll(Arrays.asList(evenArray));
        Assertions.assertEquals(evenArray.length * 3, test.size());
        Assertions.assertEquals(3, test.get(24));
        test.addAll(Arrays.asList(bigArray));
        Assertions.assertEquals(evenArray.length * 3 + bigArray.length, test.size());
    }
}
