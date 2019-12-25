
/**
 * The interface {@link Digraph} represents a directed graph for the generic items.
 *
 * The {@link Graph} supports primary operations of providing number of vertices and edges on the graph,
 * reversing and returning the direction of the graph and iterate over all vertices by the edges.
 *
 * @param <V>
 */
public interface Digraph<V> extends Graph<V> {

    @Override
    Iterable<? extends DirectedEdge<V>> incidentTo(V vertex);

    @Override
    Iterable<? extends DirectedEdge<V>> edges();

    Digraph<V> reverse();
}
