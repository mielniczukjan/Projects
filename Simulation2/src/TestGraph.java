public class TestGraph {

    public static void main(String... args) {
    /*
        Vertex vertexA = new Vertex("A", 15);
        Vertex vertexB = new Vertex("B", 5);
        Vertex vertexC = new Vertex("C", 10);
        Vertex vertexD = new Vertex("D", 12);
        Vertex vertexE = new Vertex("E", 14);

        Graph graph = new Graph(6);

        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);
        graph.addVertex(vertexE);

        graph.addEdge(vertexA, vertexB, 6);
        graph.addEdge(vertexA, vertexD, 7);
        graph.addEdge(vertexB, vertexC, 5);
        graph.addEdge(vertexC, vertexE, 9);
        graph.addEdge(vertexC, vertexD, 3);

        graph.printEdges();

        graph.printDijkstra(vertexA);

        graph.removeEdge(vertexA, vertexB);

        graph.printEdges();

        graph.removeVertex(vertexA);

        graph.printEdges();

        graph.printVertices();

        graph.printVerticesDegree();
    *//*


        Vertex vertexF = new Vertex("F", 15);
        Vertex vertexG = new Vertex("G", 5);
        Vertex vertexH = new Vertex("H", 10);
        Vertex vertexI = new Vertex("I", 12);
        Vertex vertexJ = new Vertex("J", 14);
        Vertex vertexK = new Vertex("K", 16);
        Vertex vertexL = new Vertex("L", 23);
        Vertex vertexM = new Vertex("M", 20);

        Digraph diGraph = new Digraph(4);

        diGraph.addVertex(vertexF);
        diGraph.addVertex(vertexG);
        diGraph.addVertex(vertexH);
        diGraph.addVertex(vertexI);
        diGraph.addVertex(vertexJ);
        diGraph.addVertex(vertexK);
        diGraph.addVertex(vertexL);
        diGraph.addVertex(vertexM);

        diGraph.addEdge(vertexF, vertexG, 10);
        diGraph.addEdge(vertexG, vertexK, 15);
        diGraph.addEdge(vertexG, vertexI, 12);
        diGraph.addEdge(vertexK, vertexJ, 5);
        diGraph.addEdge(vertexF, vertexH, 15);
        diGraph.addEdge(vertexH, vertexI, 13);
        diGraph.addEdge(vertexI, vertexK, 1);
        diGraph.addEdge(vertexH, vertexJ, 10);
        diGraph.addEdge(vertexL, vertexG, 90);
        diGraph.addEdge(vertexF, vertexL, 80);
        diGraph.addEdge(vertexI, vertexJ, 2);

        diGraph.printEdges();

        diGraph.removeEdge(vertexH, vertexI);

        diGraph.printEdges();

        diGraph.removeVertex(vertexL);

        diGraph.printEdges();

        diGraph.printVertices();

        diGraph.printVerticesDegree();

        diGraph.printDijkstra(vertexF);

        Vertex vertex0 = new Vertex("0", 5);
        Vertex vertex1 = new Vertex("1", 5);
        Vertex vertex2 = new Vertex("2", 5);
        Vertex vertex3 = new Vertex("3", 5);
        Vertex vertex4 = new Vertex("4", 5);
        Vertex vertex5 = new Vertex("5", 5);
        Vertex vertex6 = new Vertex("6", 5);
        Vertex vertex7 = new Vertex("7", 5);

        Graph graphKruskal = new Graph(8);

        graphKruskal.addVertex(vertex0);
        graphKruskal.addVertex(vertex1);
        graphKruskal.addVertex(vertex2);
        graphKruskal.addVertex(vertex3);
        graphKruskal.addVertex(vertex4);
        graphKruskal.addVertex(vertex5);
        graphKruskal.addVertex(vertex6);
        graphKruskal.addVertex(vertex7);

        graphKruskal.addEdge(vertex4, vertex6, 1);
        graphKruskal.addEdge(vertex4, vertex5, 2);
        graphKruskal.addEdge(vertex2, vertex7, 3);
        graphKruskal.addEdge(vertex0, vertex6, 3);
        graphKruskal.addEdge(vertex2, vertex4, 4);
        graphKruskal.addEdge(vertex0, vertex1, 5);
        graphKruskal.addEdge(vertex2, vertex6, 5);
        graphKruskal.addEdge(vertex1, vertex5, 6);
        graphKruskal.addEdge(vertex5, vertex6, 6);
        graphKruskal.addEdge(vertex1, vertex7, 7);
        graphKruskal.addEdge(vertex1, vertex4, 8);
        graphKruskal.addEdge(vertex3, vertex6, 8);
        graphKruskal.addEdge(vertex0, vertex3, 9);
        graphKruskal.addEdge(vertex1, vertex2, 9);
        graphKruskal.addEdge(vertex2, vertex3, 9);
        graphKruskal.addEdge(vertex6, vertex7, 9);

        graphKruskal.printEdges();

        graphKruskal.kruskalAlgorithm();

       // graph.kruskalAlgorithm();*/

       /* Digraph tarjanAlgorithmGraph = new Digraph(8);

        Vertex vertex0 = new Vertex("0");
        Vertex vertex1 = new Vertex("1");
        Vertex vertex2 = new Vertex("2");
        Vertex vertex3 = new Vertex("3");
        Vertex vertex4 = new Vertex("4");
        Vertex vertex5 = new Vertex("5");
        Vertex vertex6 = new Vertex("6");
        Vertex vertex7 = new Vertex("7");
        Vertex vertex8 = new Vertex("8");


        tarjanAlgorithmGraph.addVertex(vertex0);
        tarjanAlgorithmGraph.addVertex(vertex1);
        tarjanAlgorithmGraph.addVertex(vertex2);
        tarjanAlgorithmGraph.addVertex(vertex3);
        tarjanAlgorithmGraph.addVertex(vertex4);
        tarjanAlgorithmGraph.addVertex(vertex5);
        tarjanAlgorithmGraph.addVertex(vertex6);
        tarjanAlgorithmGraph.addVertex(vertex7);
        tarjanAlgorithmGraph.addVertex(vertex8);

        tarjanAlgorithmGraph.addEdge(vertex0, vertex1);
        tarjanAlgorithmGraph.addEdge(vertex1, vertex2);
        tarjanAlgorithmGraph.addEdge(vertex2, vertex0);
        tarjanAlgorithmGraph.addEdge(vertex3, vertex4);
        tarjanAlgorithmGraph.addEdge(vertex3, vertex7);
        tarjanAlgorithmGraph.addEdge(vertex4, vertex5);
        tarjanAlgorithmGraph.addEdge(vertex5, vertex6);
        tarjanAlgorithmGraph.addEdge(vertex5, vertex0);
        tarjanAlgorithmGraph.addEdge(vertex6, vertex0);
        tarjanAlgorithmGraph.addEdge(vertex6, vertex2);
        tarjanAlgorithmGraph.addEdge(vertex6, vertex4);
        tarjanAlgorithmGraph.addEdge(vertex7, vertex3);
        tarjanAlgorithmGraph.addEdge(vertex7, vertex4);
        tarjanAlgorithmGraph.addEdge(vertex7, vertex8);
        tarjanAlgorithmGraph.addEdge(vertex8, vertex3);

        tarjanAlgorithmGraph.printEdges();

        tarjanAlgorithmGraph.printTarjanAlgorithm();*/
        Simulation simulation = new Simulation(6);
        simulation.simulate();
    }

}
