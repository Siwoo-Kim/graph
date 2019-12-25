/**
 * The interface {@link Cycle} represents data type for determining whether a graph has a cycle.
 *
 * The <em>hasCycle</em> operation determines whether the graph has a cycle;
 * The <em>cycle</em> returns the cycle if <em>hasCycle</em> returns true
 *
 * @param <V>
 */
public interface Cycle<V> {

    /**
     * Returns a cycle of the graph
     *
     * @return
     */
    Iterable<Edge<V>> cycle();

    /**
     * Returns true if there is a cycle in the graph.
     *
     * @return
     */
    boolean hasCycle();
}
