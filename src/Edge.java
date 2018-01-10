public class Edge {
    //public int eid;
    public Vertex sourcevertex;
    public Vertex destinationvertex;
    public int weight;

    public Edge() {

    }

    public Edge(Vertex v1, Vertex v2) {
        //this.eid = eid;
        this.sourcevertex = v1;
        this.destinationvertex = v2;
        this.weight = (int)Math.sqrt(Math.pow((v1.x-v2.x), 2) + Math.pow((v1.y-v2.y), 2));
    }


}
