import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class UFSearch<V> implements Search<V>  {
    private final UnionFind<V> uf;
    private final V source;
    private int count;

    public UFSearch(final V source, Graph<V> G) {
        checkNotNull(G);
        this.source = source;
        uf = new QuickUnionUF<>(G.vertices());
        for (Edge<V> edge: G.edges()) {
            V v = edge.either(), w = edge.other(v);
            uf.union(v, w);
        }
        for (V v: G.vertices())
            if (uf.connected(v, source))
                count++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connected(V vertex) {
        checkNotNull(vertex);
        return uf.connected(source, vertex);
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
        Search<Integer> search = new UFSearch<>(source, G);
        for (int v: G.vertices())
            if (search.connected(v))
                System.out.print(v + " ");
        System.out.println();

        if (search.count() != G.sizeOfVertices())
            System.out.print("NOT ");
        System.out.println("connected");

        source = 9;
        System.out.printf("Connection with %s\n", source);
        search = new UFSearch<>(source, G);
        for (int v: G.vertices())
            if (search.connected(v))
                System.out.print(v + " ");
        System.out.println();

        if (search.count() != G.sizeOfVertices())
            System.out.print("NOT ");
        System.out.println("connected");
    }
}
