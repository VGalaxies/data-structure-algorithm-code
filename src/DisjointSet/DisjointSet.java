package DisjointSet;

public class DisjointSet {
    private int[] s;

    public DisjointSet(int numElements) {
        s = new int[numElements];
        for (int i = 0; i < s.length; i++)
            s[i] = -1; // 根结点中放负数，而且是代表高度
    }

    public void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        if (s[root2] < s[root1])
            s[root1] = root2;
        else { // s[root1] <= s[root2]
            if (s[root1] == s[root2])
                s[root1]--;
            s[root2] = root1;
        }
    }

    public int find(int x) {
        if (s[x] < 0) {
            return x;
        }
        else {
            return s[x] = find(s[x]); // 路径压缩，高度信息会被破坏
            // return find(s[x]);
        }
    }

    public static void main(String[] args) {
        int num = 13;
        DisjointSet set = new DisjointSet(num);
        set.union(0, 4);
        set.union(3, 1);
        set.union(6, 10);
        set.union(8, 9);
        set.union(7, 4);
        set.union(6, 8);
        set.union(3, 5);
        set.union(2, 11);
        set.union(11, 0);
        for (int i = 0; i < num; ++i)
            System.out.println(i + " belong to " + set.find(i));
    }
}