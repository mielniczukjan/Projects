import java.util.*;

public class Digraph {

    private int numberOfVertices = 0;
    private int numberOfEdges = 0;
    private int initialNumberOfVertices;
    public List<Vertex> vertices = new ArrayList<Vertex>();
    public List<ArrayList<Integer>> adjacencyList = new ArrayList<>();
    private int stronglyConnectedComponentsNumber;
    private int[] lowLinks;
    private int[] ids;
    private boolean[] onStack;
    private Stack<Integer> stackSSC;
    private int currentId;

    public Digraph(int initialNumberOfVertices) {
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
            removeEdge(vertices.get(i), vertexToDelete);
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
        if (!adjacencyList.get(vertices.indexOf(vertex1)).get(vertices.indexOf(vertex2)).equals(-1)) {
            changeWeight(vertex1, vertex2, weight);
            return;
        }
        adjacencyList.get(vertices.indexOf(vertex1)).set(vertices.indexOf(vertex2), weight);
        numberOfEdges++;
        Map<Vertex, Integer> tempMap = vertex1.getAdjacencyList();
        tempMap.put(vertex2, weight);
        vertex1.setAdjacencyList(tempMap);
        vertex1.setOutDegree(vertex1.getOutDegree() + 1);
        vertex2.setInDegree(vertex2.getInDegree() + 1);
        vertex1.setDegree(vertex1.getDegree() + 1);
        vertex2.setDegree(vertex2.getDegree() + 1);
    }

    public boolean removeEdge(Vertex vertex1, Vertex vertex2) {
        if (!vertices.contains(vertex1) || !vertices.contains(vertex2))
            return false;
        if (adjacencyList.get(vertices.indexOf(vertex1)).get(vertices.indexOf(vertex2)).equals(-1))
            return false;
        adjacencyList.get(vertices.indexOf(vertex1)).set(vertices.indexOf(vertex2), -1);
        numberOfEdges--;
        Map<Vertex, Integer> tempMap = vertex1.getAdjacencyList();
        tempMap.remove(vertex2);
        vertex1.setAdjacencyList(tempMap);
        vertex1.setDegree(vertex1.getDegree() - 1);
        vertex2.setDegree(vertex2.getDegree() - 1);
        vertex1.setOutDegree(vertex1.getOutDegree() - 1);
        vertex2.setInDegree(vertex2.getInDegree() - 1);
        return true;
    }

    public boolean changeWeight(Vertex vertex1, Vertex vertex2, int weight) {
        if (!vertices.contains(vertex1) || !vertices.contains(vertex2))
            return false;
        if (adjacencyList.get(vertices.indexOf(vertex1)).get(vertices.indexOf(vertex2)).equals(-1))
            return false;
        adjacencyList.get(vertices.indexOf(vertex1)).set(vertices.indexOf(vertex2), weight);
        Map<Vertex, Integer> tempMap = vertex1.getAdjacencyList();
        tempMap.put(vertex2, weight);
        vertex1.setAdjacencyList(tempMap);
        return true;
    }

    public void printEdges() {
        int printedEdgeNumber = 0;
        System.out.println("\n\nPrinting edges 1 directional\n");
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                if (!adjacencyList.get(i).get(j).equals(-1)) {
                    printedEdgeNumber++;
                    if (adjacencyList.get(i).get(j).equals(-2))
                        System.out.println(printedEdgeNumber + ". " + vertices.get(i).getLabel() + " ----> " + vertices.get(j).getLabel());
                    else
                        System.out.println(printedEdgeNumber + ". " + vertices.get(i).getLabel() + " --" + adjacencyList.get(i).get(j) + "-> " + vertices.get(j).getLabel());
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
        System.out.println("\n\nPrinting all Vertices with degrees\n");
        for (int i = 1; i <= numberOfVertices; i++) {
            System.out.println(i + "." + vertices.get(i - 1) + " with degree of: " + vertices.get(i - 1).getDegree() + " with inDegree of: " + vertices.get(i - 1).getInDegree() + " with outDegree of: " + vertices.get(i - 1).getOutDegree());
        }
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    private void dijkstraAlgorithm(Vertex sourceVertex) {
        sourceVertex.setDistance(0);
        Set<Vertex> unsettledVertices = new HashSet<>();
        Set<Vertex> settledVertices = new HashSet<>();
        unsettledVertices.add(sourceVertex);

        while (!unsettledVertices.isEmpty()) {
            Vertex currentVertex = getlowLinksestDistanceVertex(unsettledVertices);
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

    private Vertex getlowLinksestDistanceVertex(Set<Vertex> unsettledVertices) {
        Vertex lowLinksestDistanceVertex = null;
        Integer lowLinksestDistance = Integer.MAX_VALUE;
        for (Vertex vertex : unsettledVertices) {
            int vertexDistance = vertex.getDistance();
            if (vertexDistance < lowLinksestDistance) {
                lowLinksestDistanceVertex = vertex;
                lowLinksestDistance = vertexDistance;
            }
        }
        return lowLinksestDistanceVertex;
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
        if (!vertices.contains(initialVertex)) {
            System.out.println("This vertex isn't a part of this graph");
            return;
        }
        System.out.println("\n\nlowLinksest cost of the path from the vertex " + initialVertex + ":");
        dijkstraAlgorithm(initialVertex);
        for (Vertex vertex : vertices) {
            if (vertex.getDistance() < Integer.MAX_VALUE)
                System.out.print(vertex + " : " + vertex.getDistance() + " ");
            if (vertex.equals(initialVertex)) {
                System.out.println(" Path is empty");
                continue;
            }
            if (vertex.getShortestPath().isEmpty()) {
                System.out.println("Can't make a route from vertex " + initialVertex + " to vertex " + vertex + " ");
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

    private int[] tarjanAlgorithm() {
        currentId = -1;
        int unvisited = -1;
        stronglyConnectedComponentsNumber = 0;
        lowLinks = new int[numberOfVertices];
        ids = new int[numberOfVertices];
        Arrays.fill(ids, unvisited);
        onStack = new boolean[numberOfVertices];
        Arrays.fill(onStack, false);
        stackSSC = new Stack<Integer>();

        for (int i = 0; i < numberOfVertices; i++) {
            if (ids[i] == unvisited) {
                depthForSearch(i);
            }
        }
        return lowLinks;
    }

    private void depthForSearch(int at) {
        stackSSC.push(at);
        onStack[at] = true;
        ids[at] = lowLinks[at] = currentId++;
        for (int to = 0; to < numberOfVertices; to++) {
            if (!adjacencyList.get(at).get(to).equals(-1)) {
                if (ids[to] == -1)
                    depthForSearch(to);
                if (onStack[to])
                    lowLinks[at] = Math.min(lowLinks[at], lowLinks[to]);
            }
        }

        if (ids[at] == lowLinks[at]) {
            int currentVertex;
            for (currentVertex = stackSSC.pop(); ; currentVertex = stackSSC.pop()) {
                onStack[currentVertex] = false;
                lowLinks[currentVertex] = ids[at];
                if (currentVertex == at) break;
            }
            stronglyConnectedComponentsNumber++;
        }
    }

    public void printTarjanAlgorithm() {
        lowLinks = tarjanAlgorithm();
        System.out.println("\n\nNumber of strongly connected components: " + stronglyConnectedComponentsNumber);
        System.out.println("Printing all connections\n");
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.println(vertices.get(i) + " : " + lowLinks[i]);
        }
    }


}
