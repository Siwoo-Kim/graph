
/**
 * The interface {@link Graph} represents a graph for the generic items.
 *
 * The {@link Graph} supports primary operations of providing number of vertices and edges on the graph
 * and iterate over all vertices by the edges.
 *
 * @param <V>
 */
public interface Graph<V> {

    /**
     * Returns the number of vertices on the graph
     *
     * @return
     */
    int sizeOfVertices();

    /**
     * Returns the number of edges on the graph
     *
     * @return
     */
    int sizeOfEdges();

    /**
     * Add the given {@code edge} to the graph
     *
     * @param edge
     * @throws IllegalStateException if edge is null
     */
    void addEdge(Edge<V> edge);

    /**
     * Returns the edges incident to given {@code vertex}
     *
     * @param vertex
     * @return
     * @throws IllegalStateException if vertex is null
     */
    Iterable<? extends Edge<V>> incidentTo(V vertex);

    /**
     * Returns all edges on the graph
     *
     * @return
     */
    Iterable<? extends Edge<V>> edges();

    /**
     * Returns all vertices on the graph
     *
     * @return
     */
    Iterable<V> vertices();

    /**
     * Returns the degree of given {@code vertex}
     *
     * @param vertex
     * @return
     * @throws IllegalArgumentException if vertex is null
     */
    int degree(V vertex);

    /**
     * Returns the max degree of the graph
     *
     * @return
     */
    int maxDegree();

    /**
     * Returns the average degree of the graph
     *
     * @return
     */
    double averageDegree();

    /**
     * Returns the number of self-loop on the graph
     *
     * @return
     */
    int sizeOfSelfLoops();
}
