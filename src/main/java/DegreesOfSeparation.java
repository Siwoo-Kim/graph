import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@link DegreesOfSeparation} class provides a data type for finding the degree of separation between one
 * individual and every other individual in a network.
 */
public class DegreesOfSeparation {

    private final Graph<String> G;

    public DegreesOfSeparation(String stream, String sp) {
        G = new UndirectedGraph<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(stream)))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(sp);
                String vertex = line[0];
                for (int i=1; i<line.length; i++)
                    G.addEdge(new UndirectedEdge<>(vertex, line[i]));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the degree of separation between {@code from} to {@code to}
     *
     * @param from
     * @param to
     * @return
     */
    public String degreeOfSeparation(String from, String to) {
        Paths<String> BFS = new BFSPaths<>(from, G);
        if (!BFS.hasPathTo(to))
            return "not connected";
        else {
            StringBuilder sb = new StringBuilder(from)
                    .append("\n");
            for (Edge<String> edge: BFS.pathTo(to)) {
                sb.append("\t").append(edge).append("\n");
            }
            return sb.toString();
        }
    }

    private static final String MOVIES_TXT = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data" +
            "\\movies.txt";
    private static final String ROUTES_TXT = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data" +
            "\\routes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DegreesOfSeparation ds = new DegreesOfSeparation(ROUTES_TXT, " ");
        while (true) {
            String from = scanner.nextLine();
            String to = scanner.nextLine();
            System.out.println(ds.degreeOfSeparation(from, to));
        }
    }
}
