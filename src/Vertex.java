import java.util.*;
public class Vertex {
    public int vid;
    public int x;
    public int y;
    public List<Integer> cache;
    public double Phis;
    public int level;
    public int type;
    public int condition;
    public int nup;
    public int ndown;


    public Vertex(int id) {
        this.vid = id;
        this.x = 0;
        this.y = 0;
        cache = new ArrayList<Integer>();
        Random r = new Random();
        this.level = 0;
        this.type = r.nextInt(3) + 1; // 1 : laptop; 2 : pc; 3 : mobile phone;
        this.condition = 0;
        this.nup = r.nextInt(20) + 1;
        this.ndown = r.nextInt(20) + 1;
        this.Phis = (nup + 0.0)/(nup + ndown) + 0.0;

    }

    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {

        this.y = y;
    }
    public void setLevel(int level) {

        this.level = level;
    }

    public void setCondition(int condition) {

        this.condition = condition;
    }

    public void setNup (int n) {

        this.nup = n;
    }
    public void setNdown (int n) {

        this.ndown = n;
    }

    public void setphis (int nup, int ndown) {

        this.Phis = (nup + 0.0)/(nup + ndown) + 0.0;
    }

    public int getNup() {
        return nup;
    }

    public int getNdown() {
        return ndown;
    }

    public double getPhis() {
        return Phis;
    }


}

class Packet {
    public int id;
    public int data;
    public Packet(int id) {
        this.id = id;
        this.data = 10; // default 10
    }
}
