package Sorting;

import BinaryHeap.BinaryHeap;

import java.util.Arrays;

public class SelectionSort {
    private static void swap(Comparable[] array, int left, int right) {
        Comparable temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void naiveSelectionSort(Comparable[] array) {
        for (int i = 0; i < array.length; ++i) {
            int index = i;
            Comparable minElem = array[index];
            for (int j = i; j < array.length; ++j) {
                if (array[j].compareTo(minElem) < 0) {
                    index = j;
                    minElem = array[j];
                }
            }
            swap(array, i, index);
        }
    }

    public static void heapSort(Comparable[] array) {
        Comparable[] temp = new Comparable[array.length + 1];

        for (int i = 0; i < array.length; ++i) temp[i + 1] = array[i]; // for index 0
        BinaryHeap heap = new BinaryHeap(temp);

        for (int i = 0; i < array.length; ++i) {
            array[i] = heap.deleteMin();
        }
    }

//    public static void heapSortRange(Comparable[] array, int low, int high) {
//        // sort [low ... high]
//        Comparable[] temp = new Comparable[high - low + 2];
//
//        for (int i = 0; i < high - low + 1; ++i)
//            temp[i + 1] = array[i + low];
//        BinaryHeap heap = new BinaryHeap(temp);
//
//        for (int i = 0; i < high - low + 1; ++i)
//            array[i + low] = heap.deleteMin();
//    }
//
//    public static void main(String[] args) {
//        Comparable[] array = {1, 3, 5, 7, 6, 4, 4};
//        SelectionSort.heapSortRange(array, 2, 4);
//        System.out.println(Arrays.toString(array));
//    }

}
