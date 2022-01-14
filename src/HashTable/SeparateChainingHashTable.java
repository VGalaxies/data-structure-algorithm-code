package HashTable;

import LinkedList.LinkedList;
import LinkedList.LinkedListItr;

public class SeparateChainingHashTable {
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList();
    }

    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++)
            theLists[i].makeEmpty();
    }

    public void remove(Hashable x) {
        theLists[x.hash(theLists.length)].remove(x);
    }

    public Hashable find(Hashable x) {
        return (Hashable) theLists[x.hash(theLists.length)].find(x).retrieve();
    }

    public void insert(Hashable x) {
        LinkedList whichList = theLists[x.hash(theLists.length)];
        LinkedListItr itr = whichList.find(x);
        if (itr.isPastEnd())
            whichList.insert(x, whichList.zeroth());
    }

    private static final int DEFAULT_TABLE_SIZE = 11;
    private LinkedList[] theLists;

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
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();
        hashTable.insert(new MyString("hello"));
        hashTable.insert(new MyString("world"));
        hashTable.insert(new MyString("this"));
        hashTable.insert(new MyString("is"));
        hashTable.insert(new MyString("a"));
        hashTable.insert(new MyString("test"));
    }
}
