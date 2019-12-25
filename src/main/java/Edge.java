
/**
 * The interface {@link Edge} represents a edge of the {@link Graph} for generic items.
 *
 * The {@link Edge} supports primary operations of providing two vertices on the edge.
 *
 * @param <V>
 */
public interface Edge<V> {

    /**
     * Returns the tail vertex on the graph
     *
     * @return
     */
    V either();

    /**
     * Return the vertex which is different than given {@code vertex}
     *
     * @param vertex
     * @return
     * @throws IllegalArgumentException
     * if a vertex is not one of two vertexes on the edge or vertex is null.
     */
    V other(V vertex);
}
