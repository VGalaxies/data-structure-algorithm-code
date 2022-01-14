package BinaryHeap;

public class BinaryHeap { // 最小堆
    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = new Comparable[capacity + 1];
    }

    public BinaryHeap(Comparable[] array) {
        currentSize = array.length - 1; // for index 0
        this.array = array;
        buildHeap();
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    public void insert(Comparable x) throws RuntimeException { // 自顶向下建堆
        if (isFull())
            throw new RuntimeException();
        int hole = ++currentSize;
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];
        array[hole] = x;
    }

    public Comparable findMin() {
        if (isEmpty())
            return null;
        return array[1];
    }

    public Comparable deleteMin() {
        if (isEmpty())
            return null;
        Comparable minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == array.length;
    }

    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;
    private Comparable[] array;

    private void percolateDown(int hole) {
        int child;
        Comparable tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    private void buildHeap() { // 自底向上建堆
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }
}
