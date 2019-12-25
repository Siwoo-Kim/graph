import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class BFSPaths<V> implements Paths<V> {
    private final Map<V, Edge<V>> pathTo;
    private final Edge<V> NULL_EDGE;
    private final V source;

    public BFSPaths(V source, Graph<V> G) {
        checkNotNull(source, G);
        this.source = source;
        this.NULL_EDGE = new UndirectedEdge<>(source, source);
        pathTo = new HashMap<>();
        pathTo.put(source, NULL_EDGE);
        bfs(source, G);
    }

    private void bfs(V source, Graph<V> G) {
        Set<V> visited = new HashSet<>();
        Queue<V> q = new LinkedList<>();
        q.offer(source);
        visited.add(source);
        while (!q.isEmpty()) {
            V v = q.poll();
            for (Edge<V> edge: G.incidentTo(v)) {
                V w = edge.other(v);
                if (!visited.contains(w)) {
                    pathTo.put(w, edge);
                    visited.add(w);
                    q.offer(w);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPathTo(V vertex) {
        checkNotNull(vertex);
        return pathTo.containsKey(vertex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Edge<V>> pathTo(V vertex) {
        if (!hasPathTo(vertex))
            return Collections.emptyList();
        List<Edge<V>> edges = new ArrayList<>();
        for (Edge<V> e=pathTo.get(vertex);
             e!=NULL_EDGE;
             vertex=e.other(vertex), e=pathTo.get(vertex))
            edges.add(e);
        Collections.reverse(edges);
        return edges;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V source() {
        return source;
    }

    /**
     * ================== TEST METHOD ==================
     */
    private static final String TINYG_TXT
            = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data\\tinyCG.txt";

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
        int source = 0;
        Paths<Integer> paths = new BFSPaths<>(0, G);
        System.out.println(source + " vertex Paths\n");
        for (int v: G.vertices()) {
            System.out.print(source + " to " + v + ": ");
            for (Edge<Integer> edge: paths.pathTo(v))
                System.out.print(edge + " ");
            System.out.println();
        }
    }
}
