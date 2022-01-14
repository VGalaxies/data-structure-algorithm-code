package Sorting;

public class MergeSort {
    public static void mergeSort(Comparable[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(Comparable[] array, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) >> 1;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(Comparable[] array, int left, int mid, int right) {
        // merge [left ... mid] and [mid + 1 ... right]

        Comparable[] temp = new Comparable[array.length];
        for (int i = left; i <= right; ++i) {
            temp[i] = array[i];
        }

        int i = left, j = mid + 1, now = i;
        for (; i <= mid && j <= right;) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                array[now++] = temp[i++];
            } else {
                array[now++] = temp[j++];
            }
        }

        while (i <= mid) array[now++] = temp[i++];
        while (j <= right) array[now++] = temp[j++];
    }
}
