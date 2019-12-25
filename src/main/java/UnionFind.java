/**
 * The interface {@link UnionFind} represents a <em>union-find data type</em>.
 *
 * It supports the union and find operations along with <em>connected</em> operation for determining
 * whether two elements are in the same component.
 *
 */
public interface UnionFind<E> {

    /**
     * Returns the component identifier for the component containing given {@code element}
     *
     * @param element
     * @return
     * @throws IllegalArgumentException if element is null
     */
    int find(E element);

    /**
     * Put given two elements (and with associate all elements)
     * in the same component.
     *
     * @param v
     * @param w
     * @throws IllegalArgumentException if {@code v == null || w == null}
     */
    void union(E v, E w);

    /**
     * Returns true if given two elements are connected.
     *
     * @param v
     * @param w
     * @return
     */
    boolean connected(E v, E w);

    /**
     * Returns the number of connected components.
     *
     * @return
     */
    int count();
}
