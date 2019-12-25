/**
 * {@inheritDoc}
 */
public class BasicDirectedEdge<V> extends AbstractEdge<V> implements DirectedEdge<V> {

    protected BasicDirectedEdge(V v, V w) {
        super(v, w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V from() {
        return v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V to() {
        return w;
    }

    @Override
    public DirectedEdge<V> reverse() {
        return new BasicDirectedEdge<>(to(), from());
    }

    @Override
    public String toString() {
        return String.format("%s->%s", from(), to());
    }
}
