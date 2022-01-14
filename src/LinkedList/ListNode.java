package LinkedList;

public class ListNode {
    // public for
    // 1. the list implementation of stack
    // 2. separate chaining hash table
    public ListNode(Object theElement) {
        this(theElement, null);
    }

    public ListNode(Object theElement, ListNode n) {
        element = theElement;
        next = n;
    }

    public Object element;
    public ListNode next;
}