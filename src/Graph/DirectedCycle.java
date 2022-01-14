package Graph;

import LinkedList.LinkedList;
import LinkedList.LinkedListItr;

public class DirectedCycle {
    private boolean[] onStack;
    private int[] edgeTo;
    private LinkedList cycles;

    public DirectedCycle(EdgeWeightedDigraph G) {
        for (int source = 0; source < G.V(); ++source) {
            cycles = new LinkedList();
            edgeTo = new int[G.V()];
            onStack = new boolean[G.V()];

            dfs(G, source, source);

            printDC(source);
        }

    }

    private void dfs(EdgeWeightedDigraph G, int v, int source) {
        onStack[v] = true;

        LinkedList adjacent = G.adj(v);
        for (LinkedListItr itr = adjacent.first(); !itr.isPastEnd(); itr.advance()) {
            DirectedEdge edge = (DirectedEdge) itr.retrieve();
            assert v == edge.from();
            int w = edge.to();

            if (!onStack[w]) {
                edgeTo[w] = v;
                dfs(G, w, source);
            } else if (w == source) {
                LinkedList cycle = new LinkedList();

                cycle.insert(source, cycle.zeroth());
                for (int x = v; x != source; x = edgeTo[x])
                    cycle.insert(x, cycle.zeroth());
                cycle.insert(source, cycle.zeroth());

                cycles.insert(cycle, cycles.zeroth());
            }
        }

        onStack[v] = false;
    }

    private void printDC(int source) {
        System.out.println("source: " + source);
        for (LinkedListItr cyclesItr = cycles.first(); !cyclesItr.isPastEnd(); cyclesItr.advance()) {
            LinkedList cycle = (LinkedList) cyclesItr.retrieve();
            for (LinkedListItr cycleItr = cycle.first(); !cycleItr.isPastEnd(); cycleItr.advance()) {
                int vertex = (int) cycleItr.retrieve();
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph();
        new DirectedCycle(digraph);
    }

}
