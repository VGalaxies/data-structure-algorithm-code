package Graph;

import LinkedList.LinkedList;
import java.util.Scanner;

public class EdgeWeightedDigraph {
    private int V;
    private int E;
    private DirectedEdge[][] edges;

    public EdgeWeightedDigraph() {
        Scanner scanner = new Scanner(System.in);

        this.V = Integer.parseInt(scanner.nextLine());
        this.E = 0;

        edges = new DirectedEdge[V][V];
        while (scanner.hasNextLine()) {
            String[] edges = scanner.nextLine().split(" ");
            int v = Integer.parseInt(edges[0]);
            int w = Integer.parseInt(edges[1]);
            int weight = Integer.parseInt(edges[2]);
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public DirectedEdge[][] edges() {
        return edges;
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        edges[v][w] = e;
        E++;
    }

    public LinkedList adj(int v) {
        LinkedList adjacent = new LinkedList();
        for (int i = 0; i < V; ++i) {
            DirectedEdge edge = edges[v][i];
            if (edge != null) {
                adjacent.insert(edge, adjacent.zeroth());
            }
        }
        return adjacent;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph();
    }
}
