package Sorting;

public class InsertionSort {

    public static void naiveInsertionSort(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            Comparable tmp = array[i];

//            // naive
//            // found the max index now in [0 ... i - 1] -> array[now - 1] <= tmp
//            int now = i;
//            while (now > 0) {
//                if (tmp.compareTo(array[now - 1]) < 0) {
//                    now--;
//                } else {
//                    break;
//                }
//            }
//
//            for (int j = i; j > now; --j)
//                array[j] = array[j - 1];
//            array[now] = tmp;

            int now = i;
            for (; now >= 1 && tmp.compareTo(array[now - 1]) < 0; --now)
                array[now] = array[now - 1];
            array[now] = tmp;
        }

    }

    public static void binaryInsertionSort(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            Comparable tmp = array[i];

            // binary
            // found the max index now in [0 ... i - 1] -> array[now - 1] <= tmp
            int L = 0;
            int R = i - 1;
            int now = -1;
            while (L <= R) {
                now = (L + R) >> 1;

                if (tmp.compareTo(array[now]) >= 0) {
                    L = now + 1;
                } else {
                    R = now - 1;
                }

                now = R;
            }

            now++; // attention

            for (int j = i; j > now; --j)
                array[j] = array[j - 1];
            array[now] = tmp;
        }
    }

    public static void shellSort(Comparable[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                Comparable tmp = array[i];
                int now = i;
                for (; now >= gap && tmp.compareTo(array[now - gap]) < 0; now -= gap)
                    array[now] = array[now - gap];
                array[now] = tmp;
            }
        }
    }
}
