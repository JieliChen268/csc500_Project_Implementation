import java.util.*;
import java.io.*;

public class MST {

    public List<Edge> edges;
    public int numberOfVertices;
    public Plot p;
    public ArrayList<Edge> stree;

    public MST() {
        p = new Plot();
        p.addNodes();
        p.addEdges();
        this.edges = p.edges;
        this.numberOfVertices = p.numOfNodes;
        stree = new ArrayList<Edge>();	//list of edges included in the Minimum spanning tree (initially empty)
    }

    public void kruskalAlgorithm() {



        Collections.sort(edges, new EdgeComparator());

//        System.out.println();
//        System.out.println("edge : "  + "source.vid" + "destination.vid"  + "weight");
        for(Edge e : edges) {
//            System.out.println("edge : " + "\t\t" + e.sourcevertex.vid + "\t\t" + e.destinationvertex.vid + "\t\t" + e.weight);
        }
//        System.out.println();


       // String outputMessage = "";	//hold output for the user to know algorithm's progress



        DisjointSet nodeSet = new DisjointSet( numberOfVertices + 1);		//Initialize singleton sets for each node in graph. (nodeCount +1) to make the array 1-indexed & ignore the 0th position

        for(int i = 1; i< edges.size() && stree.size() < (numberOfVertices - 1); i++){		//loop over all edges. Start @ 1 (ignore 0th as placeholder). Also early termination when number of edges=(number of nodes -1)
            Edge currentEdge = edges.get(i);
            int root1 = nodeSet.find(currentEdge.sourcevertex.vid);
            int root2 = nodeSet.find(currentEdge.destinationvertex.vid);

            if(root1 != root2){			//if roots are in different sets
                stree.add(currentEdge);		//add the edge to the graph
                nodeSet.union(root1, root2);	//merge the sets
            }
        }
        int mstTotalEdgeWeight=0;		//keeps track of total weight of all edges in the MST
        for(Edge edge: stree){
           // outputMessage+= edge +"\n";		//print each edge
            mstTotalEdgeWeight += edge.weight;
        }

        try (PrintWriter outputFile = new PrintWriter( new File("06outputMST.txt") ); ){
//            outputFile.println(outputMessage);
            System.out.println("\nOpen \"06outputMST.txt\" for backup copy of answers");
        } catch (FileNotFoundException e) {
            System.out.println("Error! Couldn't create file");
        }
    }
}

class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge edge1, Edge edge2) {
        if (edge1.weight < edge2.weight)
            return -1;
        if (edge1.weight > edge2.weight)
            return 1;
        return 0;
    }
}

class DisjointSet {
    private int [] s;		//the set field

    public int[] getSet(){		//mostly debugging method to print array

        return s;
    }
    public DisjointSet( int numElements ) {		//constructor creates singleton sets
        s = new int [ numElements ];
        for( int i = 0; i < s.length; i++ )		//initialize to -1 so the trees have nothing in them
            s[ i ] = -1;
    }
    public void union( int root1, int root2 ) {
        if( s[ root2 ] < s[ root1 ] )  // root2 is deeper
            s[ root1 ] = root2;        // Make root2 new root
        else {
            if( s[ root1 ] == s[ root2 ] )
                s[ root1 ]--;          // Update height if same
            s[ root2 ] = root1;        // Make root1 new root
        }
    }
    public int find( int x ) {
        if( s[ x ] < 0 )	//if tree has no elements, then it is its own root
            return x;
        else
            return s[ x ] = find( s[ x ] );
    }
}

