
import edu.princeton.cs.algs4.DirectedDFS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class BasicDiGraph<V> extends AbstractGraph<V> implements Digraph<V> {

    private final Map<V, List<DirectedEdge<V>>> adjMap;
    private int V;
    private int E;

    public BasicDiGraph() {
        adjMap = new HashMap<>();
    }

    @Override
    public Digraph<V> reverse() {
        Digraph<V> G = new BasicDiGraph<>();
        for (V v: vertices())
            for (Edge<V> edge: incidentTo(v)) {
                DirectedEdge<V> e = (DirectedEdge<V>) edge;
                G.addEdge(e.reverse());
            }
        return G;
    }

    @Override
    public int sizeOfVertices() {
        return V;
    }

    @Override
    public int sizeOfEdges() {
        return E;
    }

    @Override
    public void addEdge(Edge<V> edge) {
        if (edge instanceof DirectedEdge) {
            DirectedEdge<V> e = (DirectedEdge<V>) edge;
            V v = e.from(), w = e.to();
            putIfAbsent(v);
            putIfAbsent(w);
            adjMap.get(v).add(e);
            E++;
        } else {
            addEdge(Edges.newDirectedEdge(edge));
        }
    }

    private void putIfAbsent(V v) {
        checkNotNull(v);
        if (!adjMap.containsKey(v)) {
            adjMap.put(v, new ArrayList<>());
            V++;
        }
    }

    @Override
    public Iterable<? extends DirectedEdge<V>> incidentTo(V vertex) {
        if (!contains(vertex))
            return Collections.emptyList();
        List<DirectedEdge<V>> edges = new ArrayList<>();
        for (DirectedEdge<V> edge: adjMap.get(vertex))
            edges.add(edge);
        return edges;
    }

    @Override
    public Iterable<? extends DirectedEdge<V>> edges() {
        if (isEmpty())
            return Collections.emptyList();
        List<DirectedEdge<V>> edges = new ArrayList<>();
        for (V v: vertices())
            for (DirectedEdge<V> edge: incidentTo(v))
                edges.add(edge);
        return edges;
    }

    @Override
    public Iterable<V> vertices() {
        if (isEmpty())
            return Collections.emptyList();
        return new ArrayList<>(adjMap.keySet());
    }

    @Override
    boolean isEmpty() {
        return adjMap.isEmpty();
    }

    @Override
    boolean contains(V vertex) {
        checkNotNull(vertex);
        return adjMap.containsKey(vertex);
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
                Edge<Integer> edge = Edges.newDirectedEdge(scanner.nextInt(), scanner.nextInt());
                G.addEdge(edge);
            }
        }
        System.out.println(G);

        int source = 1;
        Search<Integer> search = new DFSSearch<>(source,  G);
        System.out.printf("Reachablity with vertex (%s)\n", source);
        for (int v: G.vertices())
            if (search.connected(v))
                System.out.print(v + " ");
        System.out.println();

        source = 6;
        search = new DFSSearch<>(source,  G);
        System.out.printf("Reachablity with vertex (%s)\n", source);
        for (int v: G.vertices())
            if (search.connected(v))
                System.out.print(v + " ");
        System.out.println();

        source = 1;
        Paths<Integer> paths = new BFSPaths<>(source, G);
        for (int v: G.vertices()) {
            System.out.printf("%s to %s: ", source, v);
            if (paths.hasPathTo(v))
                for (Edge<Integer> edge: paths.pathTo(v))
                    System.out.print(" " + edge);
            System.out.println();
        }

        source = 6;
        paths = new BFSPaths<>(source, G);
        for (int v: G.vertices()) {
            System.out.printf("%s to %s: ", source, v);
            if (paths.hasPathTo(v))
                for (Edge<Integer> edge: paths.pathTo(v))
                    System.out.print(" " + edge);
            System.out.println();
        }
    }
}
