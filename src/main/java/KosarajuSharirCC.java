import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class KosarajuSharirCC<V> implements ConnectedComponent<V> {

    private Map<V, Integer> ccs;
    private int count;

    public KosarajuSharirCC(Digraph<V> G) {
        checkNotNull(G);
        ccs = new HashMap<>();
        Digraph<V> GR = G.reverse();
        Iterable<V> tp = topological(GR);
        Set<V> visited = new HashSet<>();
        for (V v: tp)
            if (!visited.contains(v)) {
                dfs(v, visited, G);
                count++;
            }
    }

    private Iterable<V> topological(Digraph<V> G) {
        Stack<V> stack = new Stack<>();
        Set<V> visited = new HashSet<>();
        for (V v: G.vertices())
            if (!visited.contains(v))
                topolDFS(v, visited, stack, G);
        Collections.reverse(stack);
        return stack;
    }

    private void topolDFS(V v, Set<V> visited, Stack<V> stack, Digraph<V> G) {
        visited.add(v);
        for (DirectedEdge<V> edge: G.incidentTo(v))
            if (!visited.contains(edge.to()))
                topolDFS(edge.to(), visited, stack, G);
        stack.push(v);
    }

    private void dfs(V v, Set<V> visited, Digraph<V> G) {
        visited.add(v);
        ccs.put(v, count);
        for (Edge<V> edge: G.incidentTo(v)) {
            V w = edge.other(v);
            if (!visited.contains(w))
                dfs(w, visited, G);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connected(V v, V w) {
        return id(v) == id(w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count() {
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int id(V v) {
        checkNotNull(v);
        if (!ccs.containsKey(v))
            return -1;
        return ccs.get(v);
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
        ConnectedComponent<Integer> CC = new KosarajuSharirCC<>(G);
        Queue<Integer>[] queues = new Queue[CC.count()];
        for (int i=0; i<queues.length; i++)
            queues[i] = new LinkedList<>();
        for (int v: G.vertices())
            queues[CC.id(v)].add(v);
        for (int i=0; i<queues.length; i++) {
            Queue<Integer> q = queues[i];
            System.out.printf("%d's connected components.\n", i);
            while (!q.isEmpty())
                System.out.print(q.poll() + " ");
            System.out.println();
        }
    }
}
