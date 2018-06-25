public class auxiliaryEdge implements Comparable<auxiliaryEdge> {
    private Vertex vertexFrom, vertexTo;
    private int weight;

    public auxiliaryEdge(Vertex vertexFrom, Vertex vertexTo, int weight) {
        this.vertexFrom = vertexFrom;
        this.vertexTo = vertexTo;
        this.weight = weight;
    }

    @Override
    public int compareTo(auxiliaryEdge comparingauxiliaryEdge) {
        if (weight > comparingauxiliaryEdge.weight)
            return 1;
        else if (weight == comparingauxiliaryEdge.weight)
            return 0;
        else
            return -1;
    }

    @Override
    public String toString() {
        return vertexFrom + " -" + weight + "- " + vertexTo;
    }

    public Vertex getVertexFrom() {
        return vertexFrom;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getVertexTo() {
        return vertexTo;
    }
}
