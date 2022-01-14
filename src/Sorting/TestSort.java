package Sorting;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class TestSort {
    private static boolean isSort(Comparable[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private static void test(String className, String methodName, Comparable[] array) {
        System.out.println("-> " + methodName + " <-");

        Collections.shuffle(Arrays.asList(array));

        System.out.print("Before sorting: ");
        System.out.println(Arrays.toString(array));

        try {
            Class<TestSort> clazz = (Class<TestSort>) Class.forName(className);
            Method method = clazz.getMethod(methodName, Comparable[].class);
            method.invoke(null, (Object) array);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.print(" After sorting: ");
        System.out.println(Arrays.toString(array));

        assert isSort(array); // VM options: -ea

        System.out.println();
    }

    private static void testList(Comparable[] array) {
        test("Sorting.InsertionSort", "naiveInsertionSort", array);
        test("Sorting.InsertionSort", "binaryInsertionSort", array);
        test("Sorting.InsertionSort", "shellSort", array);

        test("Sorting.SwapSort", "bubbleSort", array);
        test("Sorting.SwapSort", "quickSort", array);

        test("Sorting.SelectionSort", "naiveSelectionSort", array);
        test("Sorting.SelectionSort", "heapSort", array);

        test("Sorting.MergeSort", "mergeSort", array);
    }

    public static void basicTest() {
        System.out.println("---------------");
        System.out.println("| Basic tests |");
        System.out.println("---------------\n");

        Comparable[] array = {1, 3, 5, 6, 7, 4, 4};

        testList(array);
    }

    public static void randomTest() {
        System.out.println("----------------");
        System.out.println("| Random tests |");
        System.out.println("----------------\n");

        Random r = new Random();
        int max = 100;
        Comparable[] array = new Comparable[max];
        for (int i = 0; i < max; ++i) array[i] = r.nextInt(max);

        testList(array);
    }

    public static void stabilityTest() {
        System.out.println("-------------------");
        System.out.println("| Stability tests |");
        System.out.println("-------------------\n");

        Comparable[] pairs = {new Pair(1, 3), new Pair(1, 2), new Pair(1, 1),
                new Pair(5, 7), new Pair(6, 2), new Pair(4, 4)};

        testList(pairs);
    }


    public static void main(String[] args) {
        basicTest();
        stabilityTest();
        randomTest();
    }
}
