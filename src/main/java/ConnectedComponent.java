/**
 * The {@link ConnectedComponent} represents a data type for identifying
 * all the components in an {@link Graph}
 *
 * The <em>id</em> operation determines in which connected component a given {@code vertex} lies;
 * The <em>connected</em> operation determines whether two vertices are in the same connected component.
 */
public interface ConnectedComponent<V> {

    /**
     * are {@code v} and {@code w} in the same connected component?
     *
     * @param v
     * @param w
     * @return
     * @throws IllegalArgumentException if {@code v == null || w == null}
     */
    boolean connected(V v, V w);

    /**
     * Returns the number of connected component in the graph
     *
     * @return
     */
    int count();

    /**
     * Returns the identifier of the connected component containing given {@code v}
     *
     * @param v
     * @return
     * @throws IllegalArgumentException if v is null
     */
    int id(V v);
}
