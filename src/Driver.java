import java.util.*;
public class Driver {

    public static void main(String[] args) {

        /*MST spanningTree = new MST();
        Plot p = spanningTree.p;
        List<Vertex> nodes = p.nodes;
        List<Edge> edges = p.edges;

        System.out.println("node: " + "\t" +" id " + "\t" + "x" + "\t" + "y" + "\t" + "Phis");
        for (Vertex v : nodes) {
            System.out.println("node id : " + "\t" + v.vid + "\t" + v.x + "\t" + v.y + "\t" + v.Phis);
        }
        System.out.println();
        System.out.println("edge : "  + "source.vid" + "destination.vid"  + "weight");
        for(Edge e : edges) {
            System.out.println("edge : " + "\t\t" + e.sourcevertex.vid + "\t\t" + e.destinationvertex.vid + "\t\t" + e.weight);
        }
        System.out.println();

        spanningTree.kruskalAlgorithm();
        List<Edge> treeEdges = spanningTree.stree;
        System.out.println("edge : "  + "source.vid" + "destination.vid"  + "weight");
        for (Edge e : treeEdges) {
            System.out.println("edge : " + "\t\t" + e.sourcevertex.vid + "\t\t" + e.destinationvertex.vid + "\t\t" + e.weight);
        }*/

        SendingMessage sm = new SendingMessage(5);
        sm.setUp();
        List<Edge> treeEdges = sm.spanning_tree_edges;
        System.out.println("edge : "  + "source.vid" + "destination.vid"  + "weight");
        for (Edge e : treeEdges) {
            System.out.println("edge : " + "\t\t" + e.sourcevertex.vid + "\t\t" + e.destinationvertex.vid + "\t\t" + e.weight);
        }
        int src = sm.source_id, des = sm.destination_id;
        System.out.println("src : " + src);
        System.out.println("des: " + des);

        List<Vertex> path = sm.getPath(sm.spanning_tree_edges, src,des);
        sm.set_level();

        //send 10 message from src to des
        //for (int i = 0; i < 10; i++) {
            //sm.message_relaying();
       //}

        //sm.lost_message_detection(sm.path);
        //Vertex newSource = sm.node_selective_recovery(sm.path);
    }
}
