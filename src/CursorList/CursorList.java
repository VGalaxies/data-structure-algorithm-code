package CursorList;

public class CursorList {
    private static int alloc() {
        int p = cursorSpace[0].next;
        cursorSpace[0].next = cursorSpace[p].next;
        if (p == 0)
            throw new OutOfMemoryError();
        return p;
    }

    private static void free(int p) {
        cursorSpace[p].element = null;
        cursorSpace[p].next = cursorSpace[0].next;
        cursorSpace[0].next = p;
    }

    public CursorList() {
        header = alloc();
        cursorSpace[header].next = 0;
    }

    public boolean isEmpty() {
        return cursorSpace[header].next == 0;
    }

    public void makeEmpty() {
        while (!isEmpty())
            remove(first().retrieve());
    }

    public CursorListItr zeroth() {
        return new CursorListItr(header);
    }

    public CursorListItr first() {
        return new CursorListItr(cursorSpace[header].next);
    }

    public CursorListItr find(Object x) {
        int itr = cursorSpace[header].next;
        while (itr != 0 && !cursorSpace[itr].element.equals(x))
            itr = cursorSpace[itr].next;
        return new CursorListItr(itr);
    }

    public void insert(Object x, CursorListItr p) {
        if (p != null && p.current != 0) {
            int pos = p.current;
            int tmp = alloc();
            cursorSpace[tmp].element = x;
            cursorSpace[tmp].next = cursorSpace[pos].next;
            cursorSpace[pos].next = tmp;
        }
    }

    public void remove(Object x) {
        CursorListItr p = findPrevious(x);
        int pos = p.current;
        if (cursorSpace[pos].next != 0) {
            int tmp = cursorSpace[pos].next;
            cursorSpace[pos].next = cursorSpace[tmp].next;
            free(tmp);
        }
    }

    public CursorListItr findPrevious(Object x) {
        int itr = cursorSpace[header].next;
        if (itr == 0) {
            return new CursorListItr(itr);
        }
        int itrPrev = 1; // header
        while (itr != 0 && !cursorSpace[itr].element.equals(x)) {
            itrPrev = itr;
            itr = cursorSpace[itr].next;
        }
        return new CursorListItr(itrPrev);
    }

    private int header;
    static CursorNode[] cursorSpace;
    private static final int SPACE_SIZE = 100;

    static {
        cursorSpace = new CursorNode[SPACE_SIZE];
        for (int i = 0; i < SPACE_SIZE; i++)
            cursorSpace[i] = new CursorNode(null, i + 1);
        cursorSpace[SPACE_SIZE - 1].next = 0;
    }

    public static void printList(CursorList theList) {
        if (theList.isEmpty())
            System.out.print("Empty list");
        else {
            CursorListItr itr = theList.first();
            for (; !itr.isPastEnd(); itr.advance())
                System.out.print(itr.retrieve() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CursorList cursorList = new CursorList();
        cursorList.insert(1, cursorList.zeroth());
        cursorList.insert(2, cursorList.find(1));
        cursorList.insert(3, cursorList.find(2));
        printList(cursorList);
        cursorList.remove(2);
        printList(cursorList);
        cursorList.remove(3);
        printList(cursorList);
        cursorList.remove(1);
        printList(cursorList);
        cursorList.remove(1);
        printList(cursorList);
    }
}
