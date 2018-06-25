import java.lang.reflect.Array;
import java.util.*;

public class Graph {

    private int numberOfVertices = 0;
    private int numberOfEdges = 0;
    private int initialNumberOfVertices;
    private List<Vertex> vertices = new ArrayList<Vertex>();
    private List<ArrayList<Integer>> adjacencyList = new ArrayList<>();

    public Graph(int initialNumberOfVertices) {
        this.initialNumberOfVertices = initialNumberOfVertices;

        for (int i = 0; i < initialNumberOfVertices; i++) {
            adjacencyList.add(i, new ArrayList<Integer>());
            for (int j = 0; j < initialNumberOfVertices; j++) {
                adjacencyList.get(i).add(j, -1);
            }
        }
    }

    public void addVertex(Vertex newVertex) {
        numberOfVertices++;
        if (numberOfVertices == initialNumberOfVertices)
            incrementGraph();
        vertices.add(newVertex);
    }

    private void incrementGraph() {
        for (int i = 0; i < initialNumberOfVertices * 3 / 2; i++) {
            if (i >= initialNumberOfVertices) {
                adjacencyList.add(new ArrayList<Integer>());
                for (int j = 0; j < initialNumberOfVertices * 3 / 2; j++) {
                    adjacencyList.get(i).add(-1);
                }
            } else {
                for (int j = initialNumberOfVertices; j < initialNumberOfVertices * 3 / 2; j++) {
                    adjacencyList.get(i).add(-1);
                }
            }
        }
        initialNumberOfVertices = initialNumberOfVertices * 3 / 2;
    }

    public boolean removeVertex(Vertex vertexToDelete) {
        if (isEmpty())
            return false;
        int indexOfVertex = vertices.indexOf(vertexToDelete);
        for (int i = 0; i < numberOfVertices; i++) {
            removeEdge(vertexToDelete, vertices.get(i));
        }
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyList.get(i).remove(indexOfVertex);
        }
        adjacencyList.remove(indexOfVertex);
        numberOfVertices--;
        vertices.remove(vertexToDelete);
        return true;
    }

    public boolean isEmpty() {
        return numberOfVertices == 0;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2) {
        addEdge(vertex1, vertex2, -2);
    }

    public void addEdge(Vertex vertex1, Vertex vertex2, Integer weight) {
        if (!vertices.contains(vertex1) || !vertices.contains(vertex2))
            return;
        if(!adjacencyList.get(vertices.indexOf(vertex1)).get(vertices.indexOf(vertex2)).equals(-1)) {
            changeWeight(vertex1, vertex2, weight);
            return;
        }
        adjacencyList.get(vertices.indexOf(vertex1)).set(vertices.indexOf(vertex2), weight);
        adjacencyList.get(vertices.indexOf(vertex2)).set(vertices.indexOf(vertex1), weight);
        numberOfEdges++;
        vertex1.setDegree(vertex1.getDegree() + 1);
        vertex2.setDegree(vertex2.getDegree() + 1);
    }

    public boolean removeEdge(Vertex vertex1, Vertex vertex2) {
        if (!vertices.contains(vertex1) || !vertices.contains(vertex2))
            return false;
        if (adjacencyList.get(vertices.indexOf(vertex1)).get(vertices.indexOf(vertex2)).equals(-1) && adjacencyList.get(vertices.indexOf(vertex2)).get(vertices.indexOf(vertex1)).equals(-1))
            return false;
        adjacencyList.get(vertices.indexOf(vertex1)).set(vertices.indexOf(vertex2), -1);
        adjacencyList.get(vertices.indexOf(vertex2)).set(vertices.indexOf(vertex1), -1);
        numberOfEdges--;
        vertex1.setDegree(vertex1.getDegree() - 1);
        vertex2.setDegree(vertex2.getDegree() - 1);
        return true;
    }

    public boolean changeWeight(Vertex vertex1, Vertex vertex2, int weight) {
        if (!vertices.contains(vertex1) || !vertices.contains(vertex2))
            return false;
        if (adjacencyList.get(vertices.indexOf(vertex1)).get(vertices.indexOf(vertex2)).equals(-1))
            return false;
        adjacencyList.get(vertices.indexOf(vertex1)).set(vertices.indexOf(vertex2), weight);
        adjacencyList.get(vertices.indexOf(vertex2)).set(vertices.indexOf(vertex1), weight);
        return true;
    }

    public void printEdges() {
        System.out.println("\n\nPrinting edges 2 directional\n");
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = i; j < numberOfVertices; j++) {
                if (!adjacencyList.get(i).get(j).equals(-1)) {
                    if (adjacencyList.get(i).get(j).equals(-2))
                        System.out.println(vertices.get(i) + " --- " + vertices.get(j));
                    else
                        System.out.println(vertices.get(i) + " -" + adjacencyList.get(i).get(j) + "- " + vertices.get(j));
                }
            }
        }
    }

    public void printVertices() {
        System.out.println("\n\nPrinting all Vertices\n");
        for (int i = 1; i <= numberOfVertices; i++) {
            System.out.println(i + "." + vertices.get(i - 1));
        }
    }

    public void printVerticesDegree() {
        System.out.println("\n\nPrinting all vertices with degrees\n");
        for (int i = 1; i <= numberOfVertices; i++) {
            System.out.println(i + "." + vertices.get(i - 1) + " with degree of: " + vertices.get(i - 1).getDegree());
        }
    }

    private void dijkstraAlgorithm(Vertex sourceVertex) {
        sourceVertex.setDistance(0);
        Set<Vertex> unsettledVertices = new HashSet<>();
        Set<Vertex> settledVertices = new HashSet<>();
        unsettledVertices.add(sourceVertex);

        while (!unsettledVertices.isEmpty()) {
            Vertex currentVertex = getLowestDistanceVertex(unsettledVertices);
            unsettledVertices.remove(currentVertex);
            for (int i = 0; i < numberOfVertices; i++) {
                if (!adjacencyList.get(vertices.indexOf(currentVertex)).get(i).equals(-1)) {
                    if (!settledVertices.contains(vertices.get(i))) {
                        calculateMinimumDistance(vertices.get(i), adjacencyList.get(vertices.indexOf(currentVertex)).get(i), currentVertex);
                        unsettledVertices.add(vertices.get(i));
                    }
                }
            }
            settledVertices.add(currentVertex);
        }
    }

    private Vertex getLowestDistanceVertex(Set<Vertex> unsettledVertices) {
        Vertex lowestDistanceVertex = null;
        Integer lowestDistance = Integer.MAX_VALUE;
        for (Vertex vertex : unsettledVertices) {
            int vertexDistance = vertex.getDistance();
            if (vertexDistance < lowestDistance) {
                lowestDistanceVertex = vertex;
                lowestDistance = vertexDistance;
            }
        }
        return lowestDistanceVertex;
    }

    private static void calculateMinimumDistance(Vertex evaluationVertex, int edgeWeight, Vertex sourceVertex) {
        int sourceDistance = sourceVertex.getDistance();
        if (sourceDistance + edgeWeight < evaluationVertex.getDistance()) {
            evaluationVertex.setDistance(sourceDistance + edgeWeight);
            LinkedList<Vertex> shortestPath = new LinkedList<>(sourceVertex.getShortestPath());
            shortestPath.add(sourceVertex);
            evaluationVertex.setShortestPath(shortestPath);
        }
    }

    public void printDijkstra(Vertex initialVertex) {
        System.out.println("\n\nLowest cost of the path from the vertex " + initialVertex + ":");
        dijkstraAlgorithm(initialVertex);
        for (Vertex vertex : vertices) {
            System.out.print(vertex + " : " + vertex.getDistance() + " ");
            if (vertex.equals(initialVertex)) {
                System.out.println(" Path is empty");
                continue;
            }
            if (vertex.getShortestPath().isEmpty()) {
                System.out.println("Can't make a rout from vertex " + initialVertex + " to vertex " + vertex + " ");
                continue;
            }
            LinkedList<Vertex> shortestPath = new LinkedList<>(vertex.getShortestPath());
            for (Vertex auxiliaryVertex : shortestPath) {
                System.out.print(auxiliaryVertex + "-->");
            }
            System.out.print(vertex);
            System.out.println();
        }
    }

    public ArrayList<auxiliaryEdge> createEdgeList() {
        ArrayList<auxiliaryEdge> edgeList = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = i; j < numberOfVertices; j++) {
                if (!adjacencyList.get(i).get(j).equals(-1)) {
                    edgeList.add(new auxiliaryEdge(vertices.get(i), vertices.get(j), adjacencyList.get(i).get(j)));
                }
            }
        }
        Collections.sort(edgeList);
        return edgeList;
    }

    public boolean kruskalAlgorithm() {

        ArrayList<auxiliaryEdge> edgeList = new ArrayList<>();
        ArrayList<auxiliaryEdge> kruskalEdgeList = new ArrayList<>();
        edgeList = createEdgeList();
        int[] colour = new int[vertices.size()];
        Arrays.fill(colour, Integer.MAX_VALUE);
        int currentColour = 0;
        int colour0Counter = 0;

        while (colour0Counter < vertices.size()) {
            if (edgeList.isEmpty()) {
                System.out.println("Graph is not connected");
                return false;
            }
            auxiliaryEdge currentEdge = edgeList.remove(0);
            if (colour[vertices.indexOf(currentEdge.getVertexFrom())] == Integer.MAX_VALUE && colour[vertices.indexOf(currentEdge.getVertexTo())] == Integer.MAX_VALUE) {
                colour[vertices.indexOf(currentEdge.getVertexFrom())] = currentColour;
                colour[vertices.indexOf(currentEdge.getVertexTo())] = currentColour;
                if (currentColour == 0) {
                    colour0Counter += 2;
                }
                kruskalEdgeList.add(currentEdge);
                currentColour++;
            } else if (colour[vertices.indexOf(currentEdge.getVertexFrom())] != Integer.MAX_VALUE && colour[vertices.indexOf(currentEdge.getVertexTo())] == Integer.MAX_VALUE) {
                colour[vertices.indexOf(currentEdge.getVertexTo())] = colour[vertices.indexOf(currentEdge.getVertexFrom())];
                if (colour[vertices.indexOf(currentEdge.getVertexFrom())] == 0) {
                    colour0Counter++;
                }
                kruskalEdgeList.add(currentEdge);
            } else if (colour[vertices.indexOf(currentEdge.getVertexFrom())] == Integer.MAX_VALUE && colour[vertices.indexOf(currentEdge.getVertexTo())] != Integer.MAX_VALUE) {
                colour[vertices.indexOf(currentEdge.getVertexFrom())] = colour[vertices.indexOf(currentEdge.getVertexTo())];
                if (colour[vertices.indexOf(currentEdge.getVertexTo())] == 0) {
                    colour0Counter++;
                }
                kruskalEdgeList.add(currentEdge);
            } else if (colour[vertices.indexOf(currentEdge.getVertexFrom())] == colour[vertices.indexOf(currentEdge.getVertexTo())]) {
                continue;
            } else {
                int newColourForEvery = Math.min(colour[vertices.indexOf(currentEdge.getVertexFrom())], colour[vertices.indexOf(currentEdge.getVertexTo())]);
                int colourToChange = Math.max(colour[vertices.indexOf(currentEdge.getVertexFrom())], colour[vertices.indexOf(currentEdge.getVertexTo())]);
                for (int i = 0; i < colour.length; i++) {
                    if (colour[i] == colourToChange) {
                        colour[i] = newColourForEvery;
                        if (newColourForEvery == 0) {
                            colour0Counter++;
                        }
                    }
                }
                kruskalEdgeList.add(currentEdge);
            }
        }
        printKruskal(kruskalEdgeList);
        return true;
    }

    private void printKruskal(ArrayList<auxiliaryEdge> kruskalEdgeList) {
        int sumOfWeights = 0;
        System.out.println("\n\nKruskal spanning tree");
        for(auxiliaryEdge edge: kruskalEdgeList) {
            System.out.println(edge);
            sumOfWeights += edge.getWeight();
        }
        System.out.println("Weight of spanning tree: " + sumOfWeights);
    }
}
