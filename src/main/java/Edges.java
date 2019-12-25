import static com.google.common.base.Preconditions.checkNotNull;

public class Edges {

    private Edges() {
        throw new AssertionError();
    }

    public static <V> DirectedEdge<V> newDirectedEdge(V v, V w) {
        checkNotNull(v, w);
        return new BasicDirectedEdge<>(v, w);
    }

    public static <V> UndirectedEdge<V> newUndirectedEdge(V v, V w) {
        checkNotNull(v, w);
        return new UndirectedEdge<>(v, w);
    }

    public static <V> DirectedEdge<V> newDirectedEdge(Edge<V> edge) {
        checkNotNull(edge);
        if (edge instanceof DirectedEdge)
            return (DirectedEdge<V>) edge;
        else
            return newDirectedEdge(edge.either(), edge.other(edge.either()));
    }

    public static <V> UndirectedEdge<V> newUnDirectedEdge(Edge<V> edge) {
        checkNotNull(edge);
        if (edge instanceof UndirectedEdge)
            return (UndirectedEdge<V>) edge;
        else
            return newUndirectedEdge(edge.either(), edge.other(edge.either()));
    }
}
