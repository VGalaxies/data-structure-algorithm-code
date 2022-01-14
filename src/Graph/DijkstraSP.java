package Graph;

public class DijkstraSP {
    int[] path;
    int[] dist;

    public DijkstraSP(EdgeWeightedDigraph G, int source) {
        int V = G.V();
        DirectedEdge[][] edges = G.edges();

        path = new int[V];
        dist = new int[V];
        int[] vis = new int[V];

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
            vis[i] = 0;
        }
        vis[source] = 1;

        printSP(V, source); // first cycle

        int count = 1;
        while (count < (V - 1) - 1) { // minus 2 for index 0 and first cycle
            int minDist = Integer.MAX_VALUE;
            int targetVex = -1;
            for (int i = 1; i < V; ++i) {
                if (i == source) continue;
                if (vis[i] != 1 && dist[i] < minDist) {
                    targetVex = i;
                    minDist = dist[i];
                }
            }

            vis[targetVex] = 1;

            for (int i = 1; i < V; ++i) {
                if (i == source) continue;
                if (vis[i] != 1 && edges[targetVex][i] != null
                        && dist[targetVex] + edges[targetVex][i].weight() < dist[i]) {
                    dist[i] = dist[targetVex] + edges[targetVex][i].weight();
                    path[i] = targetVex;
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
        new DijkstraSP(digraph, 1);
    }
}
