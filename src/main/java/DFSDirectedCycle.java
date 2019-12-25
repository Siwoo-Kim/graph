import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class DFSDirectedCycle<V> implements Cycle<V> {

    private Stack<Edge<V>> cycle;
    private Set<V> onStack;
    private Map<V, Edge<V>> pathTo;

    public DFSDirectedCycle(Digraph<V> G) {
        checkNotNull(G);
        pathTo = new HashMap<>();
        onStack = new HashSet<>();
        Set<V> visited = new HashSet<>();
        for (V v: G.vertices())
            if (!visited.contains(v))
                dfs(v, visited, G);
    }

    private void dfs(V v, Set<V> visited, Digraph<V> G) {
        visited.add(v);
        onStack.add(v);
        for (Edge<V> edge: G.incidentTo(v)) {
            V w = edge.other(v);
            if (hasCycle())
                return;
            else if (!visited.contains(w)) {
                pathTo.put(w, edge);
                dfs(w, visited, G);
            } else if (onStack.contains(w)) {
                cycle = new Stack<>();
                V o = v;
                for (Edge<V> e=pathTo.get(o);
                     !o.equals(w);
                     o=e.other(o), e=pathTo.get(o))
                    cycle.push(e);
                cycle.push(edge);
                Collections.reverse(cycle);
            }
        }
        onStack.remove(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Edge<V>> cycle() {
        return  cycle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * ================== TEST METHOD ==================
     */
    private static final String TINYDG_TXT
            = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data\\tinyDG.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Digraph<Integer> G;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(TINYDG_TXT)))) {
            int v = scanner.nextInt();
            int e = scanner.nextInt();
            G = new BasicDiGraph<>();
            while (scanner.hasNextInt()) {
                Edge<Integer> edge =
                        new UndirectedEdge<>(scanner.nextInt(), scanner.nextInt());
                G.addEdge(edge);
            }
        }
        Cycle<Integer> cycle = new DFSDirectedCycle<>(G);
        System.out.println(cycle.cycle());
    }
}
