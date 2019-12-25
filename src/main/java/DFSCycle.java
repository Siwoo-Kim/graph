import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class DFSCycle<V> implements Cycle<V> {

    private final Map<V, Edge<V>> pathTo;

    private Stack<Edge<V>> stack;

    public DFSCycle(Graph<V> G) {
        checkNotNull(G);
        pathTo = new HashMap<>();
        Set<V> visited = new HashSet<>();
        for (V v: G.vertices())
            if (!visited.contains(v))
                dfs(v, v, visited, G);
    }

    private void dfs(V v, V u, Set<V> visited, Graph<V> G) {
        visited.add(v);
        for (Edge<V> edge: G.incidentTo(v)) {
            V w = edge.other(v);
            if (hasCycle())
                return;
            else if (!visited.contains(w)) {
                pathTo.put(w, edge);
                dfs(w, v, visited, G);
            } else if (!w.equals(u)) {
                stack = new Stack<>();
                V o = v;
                for (Edge<V> e=pathTo.get(o); !o.equals(w);
                    o=e.other(o), e=pathTo.get(o))
                    stack.push(e);
                stack.push(edge);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Edge<V>> cycle() {
        return stack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCycle() {
        return stack != null;
    }

    /**
     * ================== TEST METHOD ==================
     */
    private static final String TINYG_TXT
            = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data\\tinyG.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Graph<Integer> G;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(TINYG_TXT)))) {
            int v = scanner.nextInt();
            int e = scanner.nextInt();
            G = new UndirectedGraph<>();
            while (scanner.hasNextInt()) {
                Edge<Integer> edge =
                        new UndirectedEdge<>(scanner.nextInt(), scanner.nextInt());
                G.addEdge(edge);
            }
        }
        DFSCycle<Integer> cycle = new DFSCycle<>(G);
        System.out.println(cycle.cycle());
    }
}
