package BST;

import Stack.StackLi;

public class BinarySearchTree {
    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Comparable find(Comparable x) {
        return elementAt(find(x, root));
    }

    public Comparable findMin() {
        return elementAt(findMin(root));
    }

    public Comparable findMax() {
        return elementAt(findMax(root));
    }

    public void insert(Comparable x) {
        root = insert(x, root);
    }

    public void remove(Comparable x) {
        root = remove(x, root);
    }

    public void printTree() {
        printTree(root);
    }

    private BinaryNode root;

    private Comparable elementAt(BinaryNode t) {
        return t == null ? null : t.element;
    }

    private BinaryNode find(Comparable x, BinaryNode t) {
        if (t == null)
            return null;
        if (x.compareTo(t.element) < 0)
            return find(x, t.left);
        else if (x.compareTo(t.element) > 0)
            return find(x, t.right);
        else
            return t;
    }

    private BinaryNode findMin(BinaryNode t) { // recursive
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    private BinaryNode findMax(BinaryNode t) { // non-recursive
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private BinaryNode insert(Comparable x, BinaryNode t) {
        if (t == null)
            t = new BinaryNode(x, null, null);
        else if (x.compareTo(t.element) < 0)
            t.left = insert(x, t.left);
        else if (x.compareTo(t.element) > 0)
            t.right = insert(x, t.right);
        else
            ; // duplicate; do nothing
        return t;
    }

    private BinaryNode remove(Comparable x, BinaryNode t) {
        if (t == null)
            return t;
        if (x.compareTo(t.element) < 0)
            t.left = remove(x, t.left);
        else if (x.compareTo(t.element) > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    private void printTree(BinaryNode t) {
        printTree(root, 0);
    }

    private void printTree(BinaryNode t, int indent) { // in order
        System.out.println("  ".repeat(indent) + t.element);
        if (t.left != null) printTree(t.left, indent + 1);
        if (t.right != null) printTree(t.right, indent + 1);
    }

    // for non-recursive traversal

    private void visit(BinaryNode t) {
        System.out.print(" " + t.element);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(BinaryNode t) {
        System.out.print("InOrder:");
        StackLi stack = new StackLi();
        BinaryNode curr = t;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = (BinaryNode) stack.topAndPop();
                visit(curr);
                curr = curr.right;
            }
        }
        assert stack.isEmpty();
        System.out.println();
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(BinaryNode t) {
        System.out.print("PreOrder:");
        StackLi stack = new StackLi();
        BinaryNode curr = t;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                visit(curr);
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = (BinaryNode) stack.topAndPop();
                curr = curr.right;
            }
        }
        assert stack.isEmpty();
        System.out.println();
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(BinaryNode t) {
        System.out.print("PostOrder:");
        StackLi stack = new StackLi();
        BinaryNode prev = null;
        BinaryNode curr = t;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = (BinaryNode) stack.top();
                if (curr.right != null && curr.right != prev) {
                    curr = curr.right;
                    stack.push(curr);
                    curr = curr.left;
                } else {
                    curr = (BinaryNode) stack.topAndPop();
                    visit(curr);
                    prev = curr;
                    curr = null; // !
                }
            }
        }
        assert stack.isEmpty();
        System.out.println();
    }


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        tree.insert(5);

        tree.printTree();

        tree.inOrder();
        tree.preOrder();
        tree.postOrder();
    }
}
