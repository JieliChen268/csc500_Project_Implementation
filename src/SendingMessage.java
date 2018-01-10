import java.util.*;

public class SendingMessage {
    public MST mts;
    public int source_id;
    public int destination_id;
    public Packet packet;
    public int Time_Corresponding;
    public List<Edge> spanning_tree_edges;
    public List<Vertex> path;
    public List<Vertex> nodes;
    public Scanner keyboard = new Scanner(System.in);
    public boolean message_status;
    public double Tspend;

    public SendingMessage(int packetID) {
        this.mts = new MST();
        this.packet = new Packet(packetID);
        this.Time_Corresponding = 10;  // default 10 secs

        System.out.println("Enter the source node id : ");
        this.source_id = keyboard.nextInt();
        System.out.println("Enter the destination node id : ");
        this.destination_id = keyboard.nextInt();
        //input.close();
        path = new ArrayList<Vertex>();
        this.message_status = false;
        this.Tspend = 0.0;
    }

    public SendingMessage(int packetID, MST mts) {
           this.mts = mts;
           this.packet = new Packet(packetID);
           this.Time_Corresponding = 20;
           System.out.println("Enter the source node id : ");
           this.source_id = keyboard.nextInt();
           System.out.println("Enter the destination node id : ");
           this.destination_id = keyboard.nextInt();
           path = new ArrayList<Vertex>();
           mts.kruskalAlgorithm();
           this.spanning_tree_edges = mts.stree;
           this.nodes = mts.p.nodes;
           this.message_status = false;
           this.Tspend = 0.0;
    }

    public SendingMessage(int packetID, MST mts, int T) {
        this.mts = mts;
        this.packet = new Packet(packetID);
        this.Time_Corresponding = T;
        System.out.println("Enter the source node id : ");
        this.source_id = keyboard.nextInt();
        System.out.println("Enter the destination node id : ");
        this.destination_id = keyboard.nextInt();
        path = new ArrayList<Vertex>();
        mts.kruskalAlgorithm();
        this.spanning_tree_edges = mts.stree;
        this.nodes = mts.p.nodes;
        this.message_status = false;
        this.Tspend = 0.0;
    }


     public void setUp() {

             mts.kruskalAlgorithm();
             this.spanning_tree_edges = mts.stree;
             this.nodes = mts.p.nodes;
        }


    public List<Vertex> getPath(List<Edge> spanning_tree_edges, int source_id, int destination_id) {

        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for (Edge e : spanning_tree_edges) {
            if (graph.containsKey(e.sourcevertex.vid)) {
                graph.get(e.sourcevertex.vid).add(e.destinationvertex.vid);
            } else {
                graph.put(e.sourcevertex.vid, new ArrayList<Integer>());
                graph.get(e.sourcevertex.vid).add(e.destinationvertex.vid);
            }

            if (graph.containsKey(e.destinationvertex.vid)) {
                graph.get(e.destinationvertex.vid).add(e.sourcevertex.vid);
            } else {
                graph.put(e.destinationvertex.vid, new ArrayList<Integer>());
                graph.get(e.destinationvertex.vid).add(e.sourcevertex.vid);
            }
        }

        System.out.println("Print out the graph according to hashMap: ");
        System.out.println("Node id :" + "\t" + "neighbors");
        for (Vertex v : nodes) {
             if (graph.containsKey(v.vid)) {
                 System.out.print("Node " + v.vid + ": " + "\t");
                 for (Integer n : graph.get(v.vid)) {
                     System.out.print(n + "->");
                 }
                 System.out.println();
             }
        }
        HashMap<Integer, Boolean> visited = new HashMap<>();
        if (source_id == destination_id) {
            System.out.println("The source is same as the destination!");
            return null;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        Stack<Integer> pathStack = new Stack<Integer>();
        pathStack.push(source_id);
        queue.add(source_id);
        visited.put(source_id, true);

        OUTER:while (!queue.isEmpty()) {
            int head = queue.poll();
            for(int neighbor : graph.get(head)) {
                if (!visited.containsKey(neighbor)) {

                    queue.add(neighbor);
                    visited.put(neighbor,true);
                    pathStack.add(neighbor);

                    if (neighbor == destination_id) {
                        break OUTER;
                    }
                }
            }
        }

        int node = 0;
        int currSrc = destination_id;
        List<Integer> route = new ArrayList<>();
        route.add(destination_id);

        while (!pathStack.isEmpty()) {
            node = pathStack.pop();
            if (graph.get(currSrc).contains(node)) {

                route.add(node);
                currSrc = node;
                if (node == source_id) {
                    break;
                }
            }
        }
        Collections.reverse(route);
        List<Vertex> p = new ArrayList<Vertex>();
        for (int n : route) {
            p.add(nodes.get(n - 1));
            path.add(nodes.get(n - 1));
        }

        System.out.println("print out the path from selected source to destination: ");
        System.out.print("The path is :" );
        for (Vertex v : p) {
            System.out.print( v.vid + "->");
        }
        System.out.println();

        return p;
    }

    public void set_level() {
        for (int i = 0; i < path.size(); i++) {
            path.get(i).setLevel(i); // set the level
            System.out.println("level: " + path.get(i).level);
        }
    }

    public void message_relaying(int packetID) {
       // int Tcol = 0;
        //int level = 0;
       // LinkedList<Vertex> path = new LinkedList<>();
        //int i = 0;
        // set the level from src to des : 0 - numOfNodes - 1

            for (int i = 0; i < path.size(); i++) {
                if (i > 0) {
                    if(!path.get(i).cache.contains(packetID)) {
                        path.get(i).cache.add(packetID);// add packetid in vertex cache
                        System.out.println("packet id 5 : " + path.get(i).cache.get(0));
                    }
                }
            }
    }

    public boolean lost_message_detection(List<Vertex> path, int packetID) {

        if (!path.get(0).cache.contains(packetID)) {
              path.get(0).cache.add(packetID);
        }

        int Failed = 7;
        //double TSpend = 0.0; // the count to record the time that the src wait when it sends out a packet
        boolean state;
        Random r = new Random();
        for (int i = 1; i < path.size(); i++) {
            path.get(i).setCondition(r.nextInt(10) + 1);
            System.out.println("The condition is : " + path.get(i).condition);
            if (path.get(i).condition == Failed) {
                state = false;
               // TSpend += Time_Corresponding;  // check it is correct or not
                double t = r.nextDouble();
                System.out.println("t: " + t);
                Tspend += t;
                System.out.println("TSpend : " + Tspend);

                path.get(i).ndown++;
                System.out.println(" node " + path.get(i).vid + ": " + "ndown " + path.get(i).ndown);

                path.get(i).setphis(path.get(i).nup, path.get(i).ndown);
                System.out.println("node " + path.get(i).vid + " : " + " Phis : " + path.get(i).Phis);
                System.out.println(" Message is Lost at node  " + path.get(i).vid);
                message_status = false;
                //Vertex recoveryNode = node_selective_recovery();
                break;

            } else {
                
                state = true;
                double t = r.nextDouble();
                System.out.println("t: " + t);
                Tspend += t;
                System.out.println("TSpend : " + Tspend);

                System.out.println("Before transmission : the nup : " + path.get(i).nup);
                System.out.println("Before transmission : the ndown : " + path.get(i).ndown);
                path.get(i).setphis(path.get(i).nup, path.get(i).ndown);
                System.out.println("Before transmission the Phis is : " + path.get(i).Phis);

                path.get(i).nup++;
                System.out.println("After transmission : the nup : " + path.get(i).nup);
                System.out.println("After transmission : the ndown : " + path.get(i).ndown);
                path.get(i).setphis(path.get(i).nup, path.get(i).ndown);
                System.out.println("After transmission : the Phis : " + path.get(i).Phis);
                System.out.println(" Message is successfully transmitted through " + path.get(i).vid);
                message_status = true;

                //put the packet into node cache
                    if (!path.get(i).cache.contains(packetID)) {
                        path.get(i).cache.add(packetID);// add packetid in vertex cache
                        System.out.println("packet id  " + path.get(i).cache.get(0) + " is stored in  node " + path.get(i).vid);
                    } else {
                        System.out.println("packet id  " + path.get(i).cache.get(0) + " is already in  node " + path.get(i).vid);
                    }
            }
        }
        return message_status;
    }


    public Vertex node_selective_recovery(List<Vertex> path, int packetID) {
        Vertex RecoveryNode = path.get(0);
        double max =0.0;
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).cache.contains(packetID)){
                System.out.println("node " + path.get(i).vid + " Phis is :" + path.get(i).Phis);
                System.out.println("node " + path.get(i).vid + " type is :" + path.get(i).type);
                double reliability = path.get(i).Phis + path.get(i).type;
                System.out.println("The reliability is :" + reliability);
                if (reliability > max){
                    System.out.println("Max is :" + max);
                    max = reliability;
                    RecoveryNode = path.get(i);
                    System.out.println("The recovery node is : " + path.get(i).vid);
                }
            }
        }
        return RecoveryNode;
    }
}
