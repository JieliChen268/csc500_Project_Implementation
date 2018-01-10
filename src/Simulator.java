import java.util.*;
import java.text.*;

public class Simulator {

    public static void main(String[] args) {
       // build a plot with nodes and edges
        MST st = new MST();
        List<Vertex> nodes = st.p.nodes;
        //print out init value of Phis
        printout_Phis(nodes);

      //send one packet  in the spanning tree from 10 src to 10 dests
/*        for (int i = 1; i <= 10; i++) {
            send_one_message(i,st);
        } */

        for (int i = 1; i <= 10; i++) {
            send_one_message(i,st,30);
        }
    }

    public static void send_one_message(int packetID,MST st) {
        SendingMessage sm = new SendingMessage(packetID,st);
        List<Vertex> path = sm.getPath(sm.spanning_tree_edges,sm.source_id,sm.destination_id);
        boolean message_status = sm.lost_message_detection(path, packetID);

        Vertex recoveryNode = null;
        while (message_status == false || sm.Tspend > sm.Time_Corresponding) {

            recoveryNode = sm.node_selective_recovery(path, packetID);
            List<Vertex> newPath = sm.getPath(sm.spanning_tree_edges, recoveryNode.vid, sm.destination_id);
            message_status = sm.lost_message_detection(newPath,packetID);
        }
        printout_Phis(sm.nodes);

    }

    public static void send_one_message(int packetID,MST st, int t) {
        SendingMessage sm = new SendingMessage(packetID,st,t);
        List<Vertex> path = sm.getPath(sm.spanning_tree_edges,sm.source_id,sm.destination_id);
        boolean message_status = sm.lost_message_detection(path, packetID);

        Vertex recoveryNode = null;
        while (message_status == false || sm.Tspend > sm.Time_Corresponding) {

            recoveryNode = sm.node_selective_recovery(path, packetID);
            List<Vertex> newPath = sm.getPath(sm.spanning_tree_edges, recoveryNode.vid, sm.destination_id);
            message_status = sm.lost_message_detection(newPath,packetID);
        }
        printout_Phis(sm.nodes);

    }

    public static void printout_Phis(List<Vertex> nodes) {
        System.out.println();
        NumberFormat formatter = new DecimalFormat("#0.000");
        System.out.println("node id : " + "\t" + "Phis");
        for (Vertex v : nodes) {
            System.out.println(v.vid + "\t\t\t" + formatter.format(v.Phis));
        }
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println();

    }
}
