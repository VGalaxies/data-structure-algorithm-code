package Sorting;

import java.util.Random;

public class SwapSort {
    private static void swap(Comparable[] array, int left, int right) {
        Comparable temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void bubbleSort(Comparable[] array) {
        for (int i = array.length - 1; i >= 0; --i) {
            boolean flag = false;

            for (int j = 0; j < i; ++j) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    public static void quickSort(Comparable[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(Comparable[] array, int left, int right) {
        if (right <= left) return;

        Random r = new Random();
        int index = left + r.nextInt(right - left + 1);
        Comparable pivot = array[index];
        swap(array, left, index); // 主元置最前

        int i = left + 1, j = right;

        while (true) {
            while (i <= right && array[i].compareTo(pivot) <= 0) {
                ++i;
            }
            while (j >= left + 1 && array[j].compareTo(pivot) >= 0) {
                --j;
            }
            if (i < j)
                swap(array, i, j);
            else
                break;
        }

        int target = Math.min(i, j);
        swap(array, left, target);

        quickSort(array, left, target - 1);
        quickSort(array, target + 1, right);
    }
}
