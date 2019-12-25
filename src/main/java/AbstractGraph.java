import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public abstract class AbstractGraph<V> implements Graph<V> {

    /**
     * {@inheritDoc}
     */
    public int degree(V vertex) {
        checkNotNull(vertex);
        int degree = 0;
        for (Edge<V> edge: incidentTo(vertex))
            degree++;
        return degree;
    }

    /**
     * {@inheritDoc}
     */
    public int maxDegree() {
        int max = 0;
        for (V v: vertices())
            max = Math.max(degree(v), max);
        return max;
    }

    /**
     * {@inheritDoc}
     */
    public double averageDegree() {
        return sizeOfEdges() * 1.0 / sizeOfVertices();
    }

    /**
     * {@inheritDoc}
     */
    public int sizeOfSelfLoops() {
        int cnt = 0;
        for (V v: vertices())
            for (Edge<V> edge: incidentTo(v))
                if (edge.other(v).equals(v))
                    cnt++;
        return cnt;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "(empty)";
        StringBuilder sb = new StringBuilder();
        sb.append(sizeOfVertices()).append(" vertices, ")
                .append(sizeOfEdges()).append(" edges.\n");
        for (V v: vertices()) {
            sb.append(v).append(": ");
            for (Edge<V> edge: incidentTo(v))
                sb.append(edge).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns true if there is no vertex on the graph
     *
     * @return
     */
    abstract boolean isEmpty();

    /**
     * Returns true if given {@code vertex} is on the graph
     *
     * @param vertex
     * @return
     * @throws IllegalArgumentException if vertex is null
     */
    abstract boolean contains(V vertex);
}
