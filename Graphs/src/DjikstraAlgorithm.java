import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class DjikstraAlgorithm {

    public static Graph shortestPathFromSource(Graph graph, Vertex sourceVertex) {
        Set<Vertex> settledVertices = new HashSet<>();
        Set<Vertex> unsettledVertices = new HashSet<>();

        unsettledVertices.add(sourceVertex);

        while (unsettledVertices.size() != 0) {
            Vertex currentVertex = getLowestDistanceVertex(unsettledVertices);
            unsettledVertices.remove(currentVertex);
            for (Map.Entry<Vertex, Integer> adjacencyPair : currentVertex.getAdjacentVertices().entrySet()) {
                Vertex adjacentVertex = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledVertices.contains(adjacentVertex)) {
                    calculateMinimumDistance(adjacentVertex, edgeWeight, currentVertex);
                    unsettledVertices.add(adjacentVertex);
                }
            }
            settledVertices.add(currentVertex);
        }
        return graph;
    }

    private static Vertex getLowestDistanceVertex(Set<Vertex> unsettledVertices) {
        Vertex lowestDistanceVertex = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Vertex vertex : unsettledVertices) {
            int vertexDistance = vertex.getDistance();
            if (vertexDistance < lowestDistance) {
                lowestDistance = vertexDistance;
                lowestDistanceVertex = vertex;
            }
        }
        return lowestDistanceVertex;
    }

    private static void calculateMinimumDistance(Vertex evaluationVertex, Integer edgeWeight, Vertex sourceVertex) {
        Integer sourceDistance = sourceVertex.getDistance();
        if (sourceDistance + edgeWeight < evaluationVertex.getDistance()) {
            evaluationVertex.setDistance(sourceDistance + edgeWeight);
            LinkedList<Vertex> shortestPath = new LinkedList<>(sourceVertex.getShortestPath());
            shortestPath.add(sourceVertex);
            evaluationVertex.setShortestPath(shortestPath);
        }
    }

}
