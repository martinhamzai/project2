import java.awt.Point;
import java.util.Iterator;

public class Database {

    private SkipList<String, Point> list;

    public Database() {
        list = new SkipList<String, Point>();
    }

    public void insert(KVPair<String, Point> pair) {
        System.out.println("Insert");
    }

    public void remove(String name) {
        System.out.println("Remove by name");
    }

    public void remove(Point p) {
        System.out.println("Remove by coords");
    }

    public void regionsearch(int x, int y, int w, int h) {
        System.out.println("Regionsearch");
    }

    public void duplicates() {
        System.out.println("Duplicates");
    }

    public void search(String name) {
        System.out.println("Search");
    }

    public void dump() {
        System.out.println("Dump");
    }

}
