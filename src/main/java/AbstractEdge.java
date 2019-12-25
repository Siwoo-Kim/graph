import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public abstract class AbstractEdge<V> implements Edge<V> {
    final V v;
    final V w;

    protected AbstractEdge(V v, V w) {
        checkNotNull(v, w);
        this.v = v;
        this.w = w;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V either() {
        return v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V other(V vertex) {
        if (v.equals(vertex))
            return w;
        if (w.equals(vertex))
            return v;
        throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return String.format("%s-%s", v, w);
    }
}
