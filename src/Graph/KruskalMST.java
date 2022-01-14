package Graph;

import BinaryHeap.BinaryHeap;
import DisjointSet.DisjointSet;
import LinkedList.LinkedList;
import LinkedList.LinkedListItr;

public class KruskalMST {
    private int weightsOfMST;
    private LinkedList edgesOfMST;

    public KruskalMST(EdgeWeightedGraph G) {
        edgesOfMST = new LinkedList();
        weightsOfMST = 0;

        int V = G.V();
        Edge[][] edges = G.edges();

        BinaryHeap heap = new BinaryHeap();
        for (int i = 1; i < V; ++i) {
            for (int j = i + 1; j < V; ++j) {
                Edge edge = edges[i][j];
                if (edge != null)
                    heap.insert(edge);
            }
        }

        DisjointSet set = new DisjointSet(V);

        int count = 1;
        while (count < V - 1) { // minus 1 for index 0
            Edge edge = (Edge) heap.findMin();
            int v = edge.either();
            int w = edge.other(v);
            if (set.find(v) != set.find(w)) {
                edgesOfMST.insert(edge, edgesOfMST.zeroth());
                weightsOfMST += edge.weight();
                set.union(v, w);
                count++;
            }
            heap.deleteMin();
        }

        printMST();
    }

    private void printMST() {
        assert edgesOfMST != null;
        System.out.println("Minimum Spanning Tree:");
        for (LinkedListItr itr = edgesOfMST.first(); !itr.isPastEnd(); itr.advance()) {
            Edge edge = (Edge) itr.retrieve();
            int v = edge.either();
            int w = edge.other(v);
            int weight = edge.weight();
            System.out.println(v + " - " + w + "  " + weight);
        }
        System.out.println("total weight: " + weightsOfMST);
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph();
        new KruskalMST(graph);
    }
}
