
/**
 * The interface {@link DirectedEdge} represents a directed edge of the {@link Digraph} for generic items.
 *
 * The {@link DirectedEdge} supports primary operations of providing two vertices on the edge which consists of tail
 * and head.
 *
 * @param <V>
 */
public interface DirectedEdge<V> extends Edge<V> {

    /**
     * Returns the tail of the edge
     *
     * @return
     */
    V from();

    /**
     * Returns the head of the edge
     *
     * @return
     */
    V to();

    /**
     * Reverses and returns the tail with the head of the edge
     *
     * @return
     */
    DirectedEdge<V> reverse();
}
