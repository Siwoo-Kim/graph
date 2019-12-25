import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class DFSSearch<V> implements Search<V> {
    private final V source;
    private final Set<V> visited;

    public DFSSearch(V source, Graph<V> G) {
        this.source = source;
        visited = new HashSet<>();
        dfs(source, G);
    }

    private void dfs(V v, Graph<V> G) {
        visited.add(v);
        for (Edge<V> edge: G.incidentTo(v))
            if (!visited.contains(edge.other(v)))
                dfs(edge.other(v), G);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connected(V vertex) {
        checkNotNull(vertex);
        return visited.contains(vertex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count() {
        return visited.size();
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

        int source = 0;
        System.out.printf("Connection with %s\n", source);
        Search<Integer> search = new DFSSearch<>(source, G);
        for (int v: G.vertices())
            if (search.connected(v))
                System.out.print(v + " ");
        System.out.println();

        if (search.count() != G.sizeOfVertices())
            System.out.print("NOT ");
        System.out.println("connected");

        source = 9;
        System.out.printf("Connection with %s\n", source);
        search = new DFSSearch<>(source, G);
        for (int v: G.vertices())
            if (search.connected(v))
                System.out.print(v + " ");
        System.out.println();

        if (search.count() != G.sizeOfVertices())
            System.out.print("NOT ");
        System.out.println("connected");
    }
}
