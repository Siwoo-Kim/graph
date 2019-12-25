/**
 * The interface {@code Search} represents a search algorithm to figure out
 * the {@link #source()} is connected with other vertices on the graph.
 *
 * The class mainly supports find connection between {@link #source()} and given {@code vertex} and the number of
 * connection with {@link #source()}
 */
interface Search<V> {

    /**
     * is the given {@code vertex} connected with {@link #source()}?
     *
     * @param vertex
     * @return
     * @throws IllegalArgumentException if vertex is null
     */
    boolean connected(V vertex);

    /**
     * Returns the number of connected vertices with {@link #source()} .
     *
     * @return
     */
    int count();

    /**
     * Returns the source vertex.
     *
     * @return
     */
    V source();
}
