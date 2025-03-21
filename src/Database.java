import java.util.Iterator;

public class Database {

    private SkipList<String, Point> list;
    
    private QuadTree qt;
    
    private final int SIZE = 1024;

    public Database() {
        list = new SkipList<String, Point>();
        qt = new QuadTree();
    }


    public void insert(KVPair<String, Point> pair) {
        String name = pair.key();
        Point p = pair.value();
        String regex = "^[A-Za-z]\\w*$";

        if (!name.matches(regex)) {
            System.out.println("Point rejected: " + pair.toString());
            return;
        }

        if (p.getX() < 0 || p.getX() >= SIZE || p.getY() < 0 || p
            .getY() >= SIZE) {
            System.out.println("Point rejected: " + pair.toString());
            return;
        }
        
        list.insert(pair);
        qt.insert(pair);
        System.out.println("Point inserted: " + pair.toString());
    }


    public void remove(String name) {
        System.out.println("Remove by name");
        KVPair<String, Point> pair = list.remove(name);
        qt.remove(pair.value());
    }


    public void remove(Point p) {
        System.out.println("Remove by coords");
        KVPair<String, Point> pair = qt.remove(p);
        list.remove(pair.key());
    }


    public void regionsearch(int x, int y, int w, int h) {
        System.out.println("Regionsearch");
    }


    public void duplicates() {
        System.out.println("Duplicates");
    }


    public void search(String name) {
        ArrayList<KVPair<String, Point>> pairs = list.search(name);
        if (pairs.size() == 0) {
            System.out.println("Point not found: " + name);
        }
        for (int i = 0; i < pairs.size(); i++) {
            KVPair<String, Point> pair = pairs.get(i);
            System.out.println("Found " + pair.toString());
        }
    }
 


    public void dump() {
        list.dump();
        qt.dump();
    }

}
