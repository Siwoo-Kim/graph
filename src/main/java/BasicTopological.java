import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class BasicTopological<V> implements Topological<V> {

    private final Stack<V> stack;

    public BasicTopological(Digraph<V> G) {
        checkNotNull(G);
        if (hasCycle(G)) {
            stack = null;
            return;
        }
        stack = new Stack<>();
        Set<V> visited = new HashSet<>();
        for (V v: G.vertices())
            if (!visited.contains(v))
                dfs(v, visited, G);
        Collections.reverse(stack);
    }

    private void dfs(V v, Set<V> visited, Digraph<V> G) {
        visited.add(v);
        for (DirectedEdge<V> edge: G.incidentTo(v))
            if (!visited.contains(edge.to()))
                dfs(edge.to(), visited, G);
        stack.push(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDAG() {
        return stack != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<V> order() {
        return stack;
    }

    private boolean hasCycle(Digraph<V> G) {
        Stack<V> stack;
        Set<V> visited = new HashSet<>();
        for (V v: G.vertices())
            if (!visited.contains(v)) {
                stack = new Stack<>();
                if (cycle(v, visited, stack, G))
                    return true;
            }
        return false;
    }

    private boolean cycle(V v, Set<V> visited, Stack<V> stack, Digraph<V> G) {
        visited.add(v);
        stack.push(v);
        for (DirectedEdge<V> edge: G.incidentTo(v)) {
            V w = edge.to();
            if (!visited.contains(w)) {
                return cycle(w, visited, stack, G);
            } else if (stack.contains(w)) {
                return true;
            }
        }
        stack.pop();
        return false;
    }

    /**
     * ================== TEST METHOD ==================
     */
    private static final String TINYDG_TXT
            = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data\\tinyDG.txt";

    private static final String TINYDAG_TXT
            = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data\\tinyDAG.txt";

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
        Topological<Integer> topological = new BasicTopological<>(G);
        System.out.println(topological.isDAG());

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(TINYDAG_TXT)))) {
            int v = scanner.nextInt();
            int e = scanner.nextInt();
            G = new BasicDiGraph<>();
            while (scanner.hasNextInt()) {
                Edge<Integer> edge =
                        new UndirectedEdge<>(scanner.nextInt(), scanner.nextInt());
                G.addEdge(edge);
            }
        }
        topological = new BasicTopological<>(G);
        System.out.println(topological.isDAG());
        System.out.println(topological.order());
    }
}
