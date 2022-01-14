package Sorting;

public class Pair implements Comparable{
    private int first;
    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Object that) {
        int a = this.first;
        int b = ((Pair) that).first;

        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
