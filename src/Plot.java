import java.util.*;
public class Plot {
    public int width;
    public int length;
    public List<Vertex> nodes;
    public int numOfNodes;
    public List<Edge> edges;
    public Random r = new Random();

    public Plot() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of nodes: ");
        this.numOfNodes = input.nextInt();

        System.out.println("Enter width of the plot: ");
        this.width = input.nextInt();

        System.out.println("Enter length of the plot: ");
        this.length = input.nextInt();
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();

    }

    public void addNodes(){
        for (int i = 0;i < numOfNodes; i++) {
            int x = r.nextInt(width);
            int y = r.nextInt(length);
            Vertex node = new Vertex(i + 1);
            node.setX(x);
            node.setY(y);
            nodes.add(node);
        }
    }

    public void addEdges() {

        for (int source = 1; source <=numOfNodes; source++) {
            for (int destination = 1; destination <= numOfNodes; destination++) {
                if(source < destination) {
                    Edge e = new Edge(nodes.get(source - 1), nodes.get(destination - 1));
                    edges.add(e);
                }
            }
        }
    }
}
