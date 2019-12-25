/**
 * The interface represents a data type for finding the path from a {@link #source()}
 * to every other vertex in the {@link Graph}
 *
 * It supports the <em>hasPathTo</em>, <em>pathTo</em> operations which for
 * iterate all edges from {#link #source()} to given {@code vertex}
 *
 * @param <V>
 */
public interface Paths<V> {

    /**
     * Is there a path from the {@link #source()} to given {@code vertex}?
     *
     * @param vertex
     * @return
     * @throws IllegalArgumentException if vertex is null
     */
    boolean hasPathTo(V vertex);

    /**
     * Returns a path from the {@link #source()} to given {@code vertex}.
     *
     * @return
     * @throws IllegalArgumentException if vertex is null
     */
    Iterable<Edge<V>> pathTo(V vertex);

    /**
     * Returns the source vertex.
     *
     * @return
     */
    V source();
}
