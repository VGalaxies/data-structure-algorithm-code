package LinkedList;

public class LinkedList {
    private ListNode header;

    public LinkedList() {
        header = new ListNode(null);
    }

    public boolean isEmpty() {
        return header.next == null;
    }

    public void makeEmpty() {
        header.next = null;
    }

    public LinkedListItr zeroth() {
        return new LinkedListItr(header);
    }

    public LinkedListItr first() {
        return new LinkedListItr(header.next);
    }

    public LinkedListItr find(Object x) {
        ListNode itr = header.next;
        while (itr != null && !itr.element.equals(x))
            itr = itr.next;
        return new LinkedListItr(itr);
    }

    public void remove(Object x) {
        LinkedListItr p = findPrevious(x);
        if (p.current.next != null)
            p.current.next = p.current.next.next;
    }

    public LinkedListItr findPrevious(Object x) {
        ListNode itr = header;
        while (itr.next != null && !itr.next.element.equals(x))
            itr = itr.next;
        return new LinkedListItr(itr);
    }

    public void insert(Object x, LinkedListItr p) {
        if (p != null && p.current != null)
            p.current.next = new ListNode(x, p.current.next);
    }

    public static void printList(LinkedList theList) {
        if (theList.isEmpty())
            System.out.print("Empty list");
        else {
            LinkedListItr itr = theList.first();
            for (; !itr.isPastEnd(); itr.advance())
                System.out.print(itr.retrieve() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insert(1, linkedList.zeroth());
        linkedList.insert(2, linkedList.find(1));
        linkedList.insert(3, linkedList.find(2));
        printList(linkedList);
        linkedList.remove(2);
        printList(linkedList);
    }
}
