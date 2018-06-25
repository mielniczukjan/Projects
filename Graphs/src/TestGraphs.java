public class TestGraphs {

    public static void main(String... Args) {
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");
        Vertex vertexE = new Vertex("E");
        Vertex vertexF = new Vertex("F");
        Vertex vertexG = new Vertex("G");

        vertexA.addEdge(vertexC,10);
        vertexA.addEdge(vertexF, 20);

        vertexB.addEdge(vertexA, 12);
        vertexB.addEdge(vertexG, 8);

        vertexC.addEdge(vertexB, 16);
        vertexC.addEdge(vertexD, 14);

        vertexD.addEdge(vertexE, 3);

        vertexE.addEdge(vertexG, 6);

        vertexG.addEdge(vertexD, 19);
        vertexG.addEdge(vertexF, 13);
    }


}
