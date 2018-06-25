import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Vertex {
    private String label;
    private int intelligence;
    private int gullibility;
    private int readingFrequency; // % szansy na to że przeczyta dany post
    private int postingFrequency; // % szansy na to że w dany dzień wyśle posta
    private int persuasion; // siła perswazji
    private int illnessRate; // rate zarażenia 0-80 jest zarażony, 80-100 neutralny, 100-200 jest normalny
    private int scandalism;
    private int degree = 0;
    private int inDegree = 0;
    private int outDegree = 0;
    private int distance = Integer.MAX_VALUE;
    private LinkedList<Vertex> shortestPath = new LinkedList<>();
    private Map<Vertex, Integer> adjacencyList = new HashMap<Vertex, Integer>();

    public Vertex(String label) {
        this.label = label;
        this.intelligence = generateRandomIntelligence();
        this.gullibility = generateGullibility();
        this.persuasion = generatePersuasion();
        this.readingFrequency = (int) (Math.random() * 99) + 1;
        this.postingFrequency = (int) (Math.random() * 99) + 1;
        this.illnessRate = (int) (Math.random() * 60) + 80;
        this.scandalism = (int) (Math.random() * 10);
    }

    private int generateRandomIntelligence() {
        int intelligence;
        int chance = (int) (Math.random() * 100);
        if (chance <= 5)
            intelligence = (int) (Math.random() * 39) + 1;
        else if (chance <= 30)
            intelligence = (int) (Math.random() * 30) + 40;
        else if (chance <= 50)
            intelligence = (int) (Math.random() * 20) + 70;
        else if (chance <= 70)
            intelligence = (int) (Math.random() * 20) + 90;
        else if (chance <= 95)
            intelligence = (int) (Math.random() * 30) + 110;
        else
            intelligence = (int) (Math.random() * 40) + 140;
        return intelligence;
    }

    private int generateGullibility() {
        int gullibilityRate = this.intelligence / 40;
        int gullibility = (int) (Math.random() * 10) - gullibilityRate;
        if (gullibility < 0)
            return 0;
        else
            return gullibility;
    }

    private int generatePersuasion() {
        int persuasionRate = this.intelligence / 40;
        int persuasion = (int) (Math.random() * 5) + persuasionRate;
        return persuasion;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(label);
        stringBuilder.append("| |Intelligence: ");
        stringBuilder.append(this.intelligence);
        stringBuilder.append("| |Gullibility: ");
        stringBuilder.append(this.gullibility);
        stringBuilder.append("| |Persuasion: ");
        stringBuilder.append(this.persuasion);
        stringBuilder.append("| |Frequency of reading: ");
        stringBuilder.append(this.readingFrequency);
        stringBuilder.append("| |Frequency of posting: ");
        stringBuilder.append(this.postingFrequency);
        stringBuilder.append("| |IllnessRate: ");
        stringBuilder.append(this.illnessRate);
        stringBuilder.append("| |Scandalism: ");
        stringBuilder.append(this.scandalism);
        stringBuilder.append("|");
        String outPutString = stringBuilder.toString();
        return outPutString;
    }

    //Getters && Setters

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(int outDegree) {
        this.outDegree = outDegree;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setShortestPath(Vertex sourceVertex) {
        this.shortestPath.add(sourceVertex);
    }

    public LinkedList<Vertex> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Vertex> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public String getLabel() {
        return label;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setGullibilityWithIntelligence() {
        this.persuasion  = generatePersuasion();
    }

    public void setPersuasionWithIntelligence() {
        this.gullibility = generateGullibility();
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIllnessRate(int illnessRate) {
        this.illnessRate = illnessRate;
    }

    public int getIllnessRate() {
        return illnessRate;
    }

    public int getGullibility() {
        return gullibility;
    }

    public int getPostingFrequency() {
        return postingFrequency;
    }

    public int getReadingFrequency() {
        return readingFrequency;
    }

    public int getPersuasion() {
        return persuasion;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setGullibility(int gullibility) {
        this.gullibility = gullibility;
    }

    public void setPersuasion(int persuasion) {
        this.persuasion = persuasion;
    }

    public void setPostingFrequency(int postingFrequency) {
        this.postingFrequency = postingFrequency;
    }

    public void setReadingFrequency(int readingFrequency) {
        this.readingFrequency = readingFrequency;
    }

    public Map<Vertex, Integer> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(Map<Vertex, Integer> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public int getScandalism() {
        return scandalism;
    }

    public void setScandalism(int scandalism) {
        this.scandalism = scandalism;
    }
}
