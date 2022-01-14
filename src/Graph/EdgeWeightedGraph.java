package Graph;

import LinkedList.LinkedList;
import java.util.Scanner;

public class EdgeWeightedGraph {
    private int V;
    private int E;
    private Edge[][] edges;

    public EdgeWeightedGraph() {
        Scanner scanner = new Scanner(System.in);

        this.V = Integer.parseInt(scanner.nextLine());
        this.E = 0;

        edges = new Edge[V][V];
        while (scanner.hasNextLine()) {
            String[] edges = scanner.nextLine().split(" ");
            int v = Integer.parseInt(edges[0]);
            int w = Integer.parseInt(edges[1]);
            int weight = Integer.parseInt(edges[2]);
            addEdge(new Edge(v, w, weight));
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Edge[][] edges() {
        return edges;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        edges[v][w] = e;
        edges[w][v] = e;
        E++;
    }

    public LinkedList adj(int v) {
        LinkedList adjacent = new LinkedList();
        for (int i = 0; i < V; ++i) {
            Edge edge = edges[v][i];
            if (edge != null) {
                adjacent.insert(edge, adjacent.zeroth());
            }
        }
        return adjacent;
    }

}
