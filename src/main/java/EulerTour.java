import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The class {@code EulerTour} represents an strategy to determine
 * the given {@code Graph} is eligible for Euler Tour.
 *
 * Recall, Euler Tour has a cycle that uses every edges exactly once.
 *
 * @param <V>
 */
public class EulerTour<V> {

    private final boolean result;

    public EulerTour(Graph<V> G) {
        checkNotNull(G);
        boolean even = true;
        for (V v: G.vertices())
            if (G.degree(v) % 2 != 0) {
                even = false;
                break;
            }
        boolean cc = true;
        if (even) {
            ConnectedComponent<V> CC = new DFSConnectedComponent<>(G);
            if (CC.count() != 1)
                cc = false;
        }
        result = even && cc;
    }

    public boolean isEulerTour() {
        return result;
    }

    /**
     * ======================== test method ========================
     */
    public static final String EULER_TXT = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data" +
            "\\euler.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Graph<Integer> G;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(EULER_TXT)))) {
            G = new UndirectedGraph<>();
            while (scanner.hasNextInt())
                G.addEdge(Edges.newUndirectedEdge(scanner.nextInt(), scanner.nextInt()));
        }
        EulerTour<Integer> eulerTour = new EulerTour<>(G);
        System.out.println(eulerTour.isEulerTour());
    }
}
