import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class UndirectedGraph<V> extends AbstractGraph<V> {

    private Map<V, List<Edge<V>>> adjMap;
    private int E;
    private int V;

    public UndirectedGraph() {
        adjMap = new HashMap<>();
    }

    boolean isEmpty() {
        return adjMap.isEmpty();
    }

    boolean contains(V vertex) {
        checkNotNull(vertex);
        return adjMap.containsKey(vertex);
    }

    public int sizeOfVertices() {
        return V;
    }

    public int sizeOfEdges() {
        return 2 * E;
    }

    public void addEdge(Edge<V> edge) {
        checkNotNull(edge);
        V v = edge.either(), w = edge.other(v);
        putIfAbsent(v);
        putIfAbsent(w);
        adjMap.get(v).add(edge);
        adjMap.get(w).add(edge);
        E++;
    }

    private void putIfAbsent(V v) {
        checkNotNull(v);
        if (!contains(v)) {
            adjMap.put(v, new ArrayList<>());
            V++;
        }
    }

    public Iterable<Edge<V>> incidentTo(V vertex) {
        checkNotNull(vertex);
        if (!contains(vertex))
            return Collections.emptyList();
        return new ArrayList<>(adjMap.get(vertex));
    }

    public Iterable<Edge<V>> edges() {
        if (isEmpty())
            return Collections.emptyList();
        List<Edge<V>> edges = new ArrayList<>();
        for (V v: vertices())
            for (Edge<V> edge: incidentTo(v))
                edges.add(edge);
        return edges;
    }

    public Iterable<V> vertices() {
        return new ArrayList<>(adjMap.keySet());
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
    }
}
