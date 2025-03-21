
public class LeafNode<K, E> implements QuadNode<E> {

    private KVPair<K, E> pair;
    
    public LeafNode(Point p) {
        this.p = p;
    }

