import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class UFConnectedComponent<V> implements ConnectedComponent<V> {

    private Map<V, Integer> ccs;
    private int count;

    public UFConnectedComponent(Graph<V> G) {
        checkNotNull(G);
        ccs = new HashMap<>();
        UnionFind<V> uf = new QuickUnionUF<>(G.vertices());
        for (Edge<V> edge: G.edges()) {
            V v = edge.either(), w = edge.other(v);
            uf.union(v, w);
        }
        Map<Integer, Integer> idMap = new HashMap<>();
        for (V v: G.vertices()) {
            int UFID = uf.find(v); int id;
            if (!idMap.containsKey(UFID))
                idMap.put(UFID, count++);
            id = idMap.get(UFID);
            ccs.put(v, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connected(V v, V w) {
        checkNotNull(v, w);
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
        return ccs.get(v);
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
        ConnectedComponent<Integer> cc = new UFConnectedComponent<>(G);
        @SuppressWarnings("unchecked")
        Queue<Integer>[] q = new Queue[cc.count()];
        for (int i=0; i<q.length; i++)
            q[i] = new LinkedList<>();
        for (int v: G.vertices())
            q[cc.id(v)].add(v);
        for (int i=0; i<q.length; i++) {
            System.out.print(i + " id: ");
            for (int v: q[i])
                System.out.print(v + " ");
            System.out.println();
        }
    }
}
