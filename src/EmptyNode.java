
public class EmptyNode implements QuadNode {

    private static EmptyNode flyweight = null;

    private EmptyNode() {

    }


    public static EmptyNode getInstance() {
        if (flyweight == null) {
            flyweight = new EmptyNode();
        }
        return flyweight;
    }


    public QuadNode insert(KVPair<String, Point> pair, int x, int y, int width, int height) {
        return new LeafNode(pair);
    }

}
