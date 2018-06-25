import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {

    private int vertexAmount;
    private Set<Vertex> vertexSet = new HashSet<>();

    public void addVertex(Vertex newVertex) {
        vertexSet.add(newVertex);

    }
}
