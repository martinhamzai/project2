
public class LeafNode implements QuadNode {

    private ArrayList<KVPair<String, Point>> pairs;

    public LeafNode(KVPair<String, Point> pair) {
        pairs = new ArrayList<>();
        pairs.insert(pair);
    }


    public QuadNode insert(
        KVPair<String, Point> insertPair,
        int x,
        int y,
        int width,
        int height) {
        ArrayList<KVPair<String, Point>> temp = new ArrayList<>();
        for (int i = 0; i < pairs.size(); i++) {
            temp.insert(pairs.get(i));
        }
        temp.insert(insertPair);

        if (temp.size() <= 3 || same(temp)) {
            pairs = temp;
            return this;
        }

        InternalNode internal = new InternalNode();
        for (int i = 0; i < temp.size(); i++) {
            internal.insert(temp.get(i), x, y, width, height);
        }
        return internal;
    }


    private boolean same(ArrayList<KVPair<String, Point>> list) {
        if (list.size() == 0) {
            return true;
        }
        Point ref = list.get(0).value();
        for (int i = 1; i < list.size(); i++) {
            Point p = list.get(i).value();
            if (p.getX() != ref.getX() || p.getY() != ref.getY()) {
                return false;
            }
        }
        return true;
    }


    public int dump(int depth, int x, int y, int size) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ":");

        for (int i = 0; i < pairs.size(); i++) {
            for (int j = 0; j < depth; j++) {
                System.out.print("  ");
            }
            System.out.println("  " + pairs.get(i).toString());
        }
        return 1;
    }


    public ArrayList<KVPair<String, Point>> getList() {
        return pairs;
    }


    @Override
<<<<<<< Updated upstream
    public KVPair<String, Point> remove(
        Point p,
        int x,
        int y,
        int width,
        int height) {
        ArrayList<KVPair<String, Point>> values = this.getList(); // Access
                                                                  // pairs via
                                                                  // getter
=======
    public QuadNode remove(QuadNode node, Point p, int x, int y, int width, int height)
    {
        ArrayList<KVPair<String, Point>> values = this.getList();
>>>>>>> Stashed changes
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).value().getX() == p.getX() && values.get(i).value().getY() == p.getY())  {
                System.out.println("Removed point: " + values.remove(i).toString());
                if (values.size() == 0) {
                    return EmptyNode.getInstance();
                }
            }
        }
       return this;
    
    }
<<<<<<< Updated upstream
=======
}
>>>>>>> Stashed changes

}
