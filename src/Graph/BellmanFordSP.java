package Graph;


public class BellmanFordSP {
    int[] path;
    int[] dist;

    public BellmanFordSP(EdgeWeightedDigraph G, int source) {
        int V = G.V();
        DirectedEdge[][] edges = G.edges();

        path = new int[V];
        dist = new int[V];

        for (int i = 1; i < V; ++i) {
            if (i == source) continue;
            DirectedEdge edge = edges[source][i];
            if (edge != null) {
                dist[i] = edge.weight();
                path[i] = source;
            } else {
                dist[i] = Integer.MAX_VALUE;
                path[i] = -1;
            }
        }

        printSP(V, source); // first cycle

        int count = 1;
        while (count < (V - 1) - 1) { // minus 2 for index 0 and first cycle
            for (int i = 1; i < V; ++i) {
                if (i == source) continue;
                for (int j = 1; j < V; ++j) {
                    if (j == source) continue;
                    DirectedEdge edge = edges[i][j];
                    if (edge != null && dist[i] + edge.weight() < dist[j]) {
                        dist[j] = dist[i] + edge.weight();
                        path[j] = i;
                    }
                }
            }
            printSP(V, source);
            count++;
        }
    }

    private void printSP(int V, int source) {
        for (int i = 1; i < V; ++i) {
            if (i == source) continue;
            System.out.print("vertex: " + i + "  dist: " + dist[i] + "  path: ");

            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("NULL");
                continue;
            }

            int curr = i;
            while (curr != 0) {
                System.out.print(curr);
                curr = path[curr];
                if (curr != 0) System.out.print(" ← ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph();
        new BellmanFordSP(digraph, 1);
    }


}
