import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Vertex {

    private String name;

    private Integer distance = Integer.MAX_VALUE;

    private List<Vertex> shortestPath = new LinkedList<>();

    Map<Vertex, Integer> adjacentVertices = new HashMap<>();

    public Vertex(String name) {
        this.name = name;
    }

    public void addEdge(Vertex destinationVertex, int weight) {
        adjacentVertices.put(destinationVertex, weight);
    }

    public Map<Vertex, Integer> getAdjacentVertices() {
        return adjacentVertices;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<Vertex> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Vertex> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
