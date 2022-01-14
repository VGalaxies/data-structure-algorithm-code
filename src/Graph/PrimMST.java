package Graph;

import LinkedList.LinkedList;
import LinkedList.LinkedListItr;

public class PrimMST {
    private int weightsOfMST;
    private LinkedList edgesOfMST;

    public PrimMST(EdgeWeightedGraph G, int source) {
        edgesOfMST = new LinkedList();
        weightsOfMST = 0;

        int V = G.V();
        Edge[][] edges = G.edges();

        int[] lowCost = new int[V];
        int[] nearVex = new int[V];

        for (int i = 1; i < V; ++i) {
            Edge edge = edges[source][i];
            if (edge == null) {
                lowCost[i] = Integer.MAX_VALUE;
            } else {
                lowCost[i] = edge.weight();
                nearVex[i] = source;
            }
        }
        nearVex[source] = -1;

        int count = 1;
        while (count < V - 1) { // minus 1 for index 0
            int minCost = Integer.MAX_VALUE;
            int targetVex = -1;
            for (int i = 1; i < V; ++i) {
                if (i == source) continue;
                if (nearVex[i] != -1 && lowCost[i] < minCost) {
                    targetVex = i;
                    minCost = lowCost[i];
                }
            }

            Edge targetEdge = edges[targetVex][nearVex[targetVex]];
            edgesOfMST.insert(targetEdge, edgesOfMST.zeroth());
            nearVex[targetVex] = -1;

            count++;
            weightsOfMST += targetEdge.weight();

            for (int i = 1; i < V; ++i) {
                if (i == source) continue;
                if (nearVex[i] != -1 && edges[targetVex][i] != null
                        && edges[targetVex][i].weight() < lowCost[i]) {
                    lowCost[i] = edges[targetVex][i].weight();
                    nearVex[i] = targetVex;
                }
            }
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
        new PrimMST(graph, 6);
    }
}
