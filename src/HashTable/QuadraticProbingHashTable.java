package HashTable;

class HashEntry {
    Hashable element;
    boolean isActive;

    public HashEntry(Hashable e) {
        this(e, true);
    }

    public HashEntry(Hashable e, boolean i) {
        element = e;
        isActive = i;
    }
}

public class QuadraticProbingHashTable {
    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    private void allocateArray(int arraySize) {
        array = new HashEntry[arraySize];
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }

    public Hashable find(Hashable x) {
        int currentPos = findPos(x);
        return isActive(currentPos) ? array[currentPos].element : null;
    }

    public void insert(Hashable x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) return;
        array[currentPos] = new HashEntry(x, true);
        if (++currentSize > array.length / 2)
            rehash();
    }

    public void remove(Hashable x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    private static final int DEFAULT_TABLE_SIZE = 11;
    protected HashEntry[] array;
    private int currentSize;

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    private int findPos(Hashable x) {
        int collisionNum = 0;
        int currentPos = x.hash(array.length);
        while (array[currentPos] != null &&
                !array[currentPos].element.equals(x)) {
            currentPos += 2 * ++collisionNum - 1; // Quadratic Probing
            if (currentPos >= array.length)
                currentPos -= array.length;
        }
        return currentPos;
    }

    private void rehash() {
        HashEntry[] oldArray = array;
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;
        for (int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].element);
    }

    private static int nextPrime(int n) {
        while (true) {
            if (isPrime(n)) {
                return n;
            }
            n++;
        }
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        QuadraticProbingHashTable hashTable = new QuadraticProbingHashTable();
        hashTable.insert(new MyString("hello"));
        hashTable.insert(new MyString("world"));
        hashTable.insert(new MyString("this"));
        hashTable.insert(new MyString("is"));
        hashTable.insert(new MyString("a"));
        hashTable.insert(new MyString("test"));
    }
}
