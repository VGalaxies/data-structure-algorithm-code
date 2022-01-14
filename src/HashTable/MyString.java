package HashTable;

public class MyString implements Hashable {
    public MyString() {
        element = "";
    }

    public MyString(String str) {
        element = str;
    }

    @Override
    public int hash(int tableSize) {
        return element.hashCode() % tableSize;
    }

    private String element;
}
