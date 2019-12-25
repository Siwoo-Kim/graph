import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@inheritDoc}
 */
public class QuickUnionUF<E> implements UnionFind<E> {

    private Map<E, E> map;
    private Map<E, Integer> sizes;
    private int count = 0;

    public QuickUnionUF(Iterable<E> elements) {
        checkNotNull(elements);
        map = new HashMap<>();
        sizes = new HashMap<>();
        for (E e: elements) {
            map.put(e, e);
            sizes.put(e, 1);
            count++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void union(E v, E w) {
        checkNotNull(w, v);
        E wp = findRoot(w), vp = findRoot(v);
        if (wp.equals(vp))
            return;
        if (sizes.get(wp) > sizes.get(vp)) {
            map.put(vp, wp);
            sizes.put(wp, sizes.get(wp) + sizes.get(vp));
        } else {
            map.put(wp, vp);
            sizes.put(vp, sizes.get(wp) + sizes.get(vp));
        }
        count--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connected(E v, E w) {
        checkNotNull(w, v);
        return find(v) == find(w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count() {
        return count;
    }

    private E findRoot(E v) {
        checkNotNull(v);
        if (v != map.get(v))
            return findRoot(map.get(v));
        return map.get(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int find(E v) {
        checkNotNull(v);
        return findRoot(v).hashCode();
    }
}
