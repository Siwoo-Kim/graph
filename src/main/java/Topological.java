/**
 * The interface {@link Topological} represents a data type for DAG (Directed Acyclic Graph) which is sorted by
 * topological order.
 *
 * It supports determining the given {@link Digraph} is DAG and if so, then <em>order</em>
 * returns the {@link Digraph} which is order according to Topological Order.
 */
public interface Topological<V> {

    /**
     * is the given {@link Digraph} DAG?
     *
     * @return
     */
    boolean isDAG();

    /**
     * returns all edges ordered by topological order.
     *
     * @return
     */
    Iterable<V> order();
}
