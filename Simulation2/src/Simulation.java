import java.util.*;

public class Simulation {

    private Digraph graph;
    private int day = 1;
    private int simulationType;
    private int numberOfAfflicted = 0;
    private int[] arrayOfOrder;
    private Stack<Vertex> retweet = new Stack<Vertex>();

    Simulation(int simulationType) {
        this.graph = new Digraph(1000);
        this.simulationType = simulationType;
        generateVertices(1000);
        generateEdges(10000);
    }

    private void generateVertices(int number) {
        for (int i = 0; i < number; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Osoba #");
            stringBuilder.append(i + 1);
            String label = stringBuilder.toString();
            graph.addVertex(new Vertex(label));
        }
    }

    private void generateEdges(int number) {
        while (graph.getNumberOfEdges() < number) {
            int vertex1;
            int vertex2;
            while (true) {
                vertex1 = (int) (Math.random() * graph.getNumberOfVertices());
                vertex2 = (int) (Math.random() * graph.getNumberOfVertices());
                if (vertex1 != vertex2)
                    break;
            }
            graph.addEdge(graph.vertices.get(vertex1), graph.vertices.get(vertex2), (int) (Math.random() * 99) + 1);
        }
    }


    public void simulate() {
        graph.printVertices();
        //graph.printEdges();
        generateAfflicted();
        printAfflicted();
        generateArrayOfOrder();
        while (day < 365) {
            //System.out.println("\n\nDzien nr : " + day + "\n\n");
            System.out.println(numberOfAfflicted + "       " + graph.getNumberOfEdges());
            shuffleArray();
            for (int i = 0; i < arrayOfOrder.length; i++) {
                Vertex currentVertex = graph.vertices.get(arrayOfOrder[i]);
                retweet = new Stack<Vertex>();
                retweet.push(currentVertex);
                if ((int) (Math.random() * 100) < currentVertex.getPostingFrequency()) {
                    //System.out.println(currentVertex.getLabel() + " Zapostuje dzisiaj");
                    for (Map.Entry<Vertex, Integer> vertexIntegerEntry : currentVertex.getAdjacencyList().entrySet()) {
                        Vertex vertexTo = vertexIntegerEntry.getKey();
                        if (vertexTo.getReadingFrequency() + (vertexIntegerEntry.getValue() / 10) > (int) (Math.random() * 100))
                            postWasRead(currentVertex, vertexTo, false);
                    }

                    for (Iterator<Map.Entry<Vertex, Integer>> it = currentVertex.getAdjacencyList().entrySet().iterator(); it.hasNext(); ) {
                        Map.Entry<Vertex, Integer> entry = it.next();
                        if (entry.getValue().equals(0)) {
                            it.remove();
                            graph.removeEdge(currentVertex, entry.getKey());
                        }
                    }
                }
            }
            day++;
        }
    }

    private void generateArrayOfOrder() {
        arrayOfOrder = new int[graph.getNumberOfVertices()];
        for (int i = 0; i < arrayOfOrder.length; i++) {
            arrayOfOrder[i] = i;
        }
    }

    private void generateAfflicted() {
        int afflictedVertex;
        switch (simulationType) {
            case 1:                    //1 randomowo zarażona
                afflictedVertex = (int) (Math.random() * graph.getNumberOfVertices());
                graph.vertices.get(afflictedVertex).setIllnessRate((int) (Math.random() * 50));
                numberOfAfflicted++;
                break;
            case 2:                    //pare randomowo zarażonych min 2 max 5
                for (int i = (int) (Math.random() * 4); i < 5; i++) {
                    while (true) {
                        afflictedVertex = (int) (Math.random() * graph.getNumberOfVertices());
                        if (graph.vertices.get(afflictedVertex).getIllnessRate() >= 80)
                            break;
                    }
                    graph.vertices.get(afflictedVertex).setIllnessRate((int) (Math.random() * 70));
                    numberOfAfflicted++;
                }
                break;
            case 3:                     //node z najwiekszym outDegree
                int vertexWithHighestOutDegreeValue = 0;
                for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                    if (graph.vertices.get(vertexWithHighestOutDegreeValue).getOutDegree() < graph.vertices.get(i).getOutDegree()) {
                        vertexWithHighestOutDegreeValue = i;
                    }
                }
                graph.vertices.get(vertexWithHighestOutDegreeValue).setIllnessRate((int) (Math.random() * 40));
                numberOfAfflicted++;
                break;
            case 4:                     //mądry z największą liczbą followersow
                vertexWithHighestOutDegreeValue = 0;
                for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                    if (graph.vertices.get(vertexWithHighestOutDegreeValue).getOutDegree() < graph.vertices.get(i).getOutDegree()) {
                        vertexWithHighestOutDegreeValue = i;
                    }
                }
                graph.vertices.get(vertexWithHighestOutDegreeValue).setIllnessRate((int) (Math.random() * 40));
                graph.vertices.get(vertexWithHighestOutDegreeValue).setIntelligence(((int) (Math.random() * 80) + 100));
                graph.vertices.get(vertexWithHighestOutDegreeValue).setGullibilityWithIntelligence();
                graph.vertices.get(vertexWithHighestOutDegreeValue).setPersuasionWithIntelligence();
                numberOfAfflicted++;
                break;
            case 5:                     //maxymalne statystyki
                vertexWithHighestOutDegreeValue = 0;
                for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                    if (graph.vertices.get(vertexWithHighestOutDegreeValue).getOutDegree() < graph.vertices.get(i).getOutDegree()) {
                        vertexWithHighestOutDegreeValue = i;
                    }
                }
                graph.vertices.get(vertexWithHighestOutDegreeValue).setIllnessRate((int) (Math.random() * 40));
                graph.vertices.get(vertexWithHighestOutDegreeValue).setIntelligence(((int) (Math.random() * 30) + 150));
                graph.vertices.get(vertexWithHighestOutDegreeValue).setGullibility(0);
                graph.vertices.get(vertexWithHighestOutDegreeValue).setPersuasion(10);
                graph.vertices.get(vertexWithHighestOutDegreeValue).setReadingFrequency(10);
                graph.vertices.get(vertexWithHighestOutDegreeValue).setPostingFrequency(100);
                graph.vertices.get(vertexWithHighestOutDegreeValue).setScandalism(2);
                numberOfAfflicted++;
                break;
            case 6:                     //wysokie statystyki ale maly outdegree
                int vertexWithLowestOutDegreeValue = 0;
                for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                    if (graph.vertices.get(vertexWithLowestOutDegreeValue).getOutDegree() > graph.vertices.get(i).getOutDegree()) {
                        vertexWithLowestOutDegreeValue = i;
                    }
                }
                graph.vertices.get(vertexWithLowestOutDegreeValue).setIllnessRate((int) (Math.random() * 40));
                graph.vertices.get(vertexWithLowestOutDegreeValue).setIntelligence(((int) (Math.random() * 30) + 150));
                graph.vertices.get(vertexWithLowestOutDegreeValue).setGullibility(0);
                graph.vertices.get(vertexWithLowestOutDegreeValue).setPersuasion(10);
                graph.vertices.get(vertexWithLowestOutDegreeValue).setReadingFrequency(10);
                graph.vertices.get(vertexWithLowestOutDegreeValue).setPostingFrequency(100);
                graph.vertices.get(vertexWithLowestOutDegreeValue).setScandalism(2);
                numberOfAfflicted++;
                break;
            case 7:                     //najglupsza osoba z najslabszymi statystykami z najwiekszym outdegree
                vertexWithHighestOutDegreeValue = 0;
                for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                    if (graph.vertices.get(vertexWithHighestOutDegreeValue).getOutDegree() < graph.vertices.get(i).getOutDegree()) {
                        vertexWithHighestOutDegreeValue = i;
                    }
                }
                graph.vertices.get(vertexWithHighestOutDegreeValue).setIllnessRate((int) (Math.random() * 40));
                graph.vertices.get(vertexWithHighestOutDegreeValue).setIntelligence(((int) (Math.random() * 39) + 1));
                graph.vertices.get(vertexWithHighestOutDegreeValue).setGullibility(10);
                graph.vertices.get(vertexWithHighestOutDegreeValue).setPersuasion(0);
                graph.vertices.get(vertexWithHighestOutDegreeValue).setReadingFrequency(100);
                graph.vertices.get(vertexWithHighestOutDegreeValue).setPostingFrequency(100);
                graph.vertices.get(vertexWithHighestOutDegreeValue).setScandalism(9);
                numberOfAfflicted++;
                break;
            case 8:                     //pare z najlepszymi
                for (int i = (int) (Math.random() * 4); i < 5; i++) {
                    while (true) {
                        afflictedVertex = (int) (Math.random() * graph.getNumberOfVertices());
                        if (graph.vertices.get(afflictedVertex).getIllnessRate() >= 80 && graph.vertices.get(afflictedVertex).getOutDegree() > 18)
                            break;
                    }
                    graph.vertices.get(afflictedVertex).setIllnessRate((int) (Math.random() * 70));
                    graph.vertices.get(afflictedVertex).setIllnessRate((int) (Math.random() * 40));
                    graph.vertices.get(afflictedVertex).setIntelligence(((int) (Math.random() * 30) + 150));
                    graph.vertices.get(afflictedVertex).setGullibility(0);
                    graph.vertices.get(afflictedVertex).setPersuasion(10);
                    graph.vertices.get(afflictedVertex).setReadingFrequency(10);
                    graph.vertices.get(afflictedVertex).setPostingFrequency(100);
                    graph.vertices.get(afflictedVertex).setScandalism(2);
                    numberOfAfflicted++;
                }
                break;
            case 9:                     //czlowiek sredni o srednich umiejetnosciach
                while (true) {
                    afflictedVertex = (int) (Math.random() * graph.getNumberOfVertices());
                    if (graph.vertices.get(afflictedVertex).getOutDegree() > 10 && graph.vertices.get(afflictedVertex).getOutDegree() < 18 && graph.vertices.get(afflictedVertex).getIntelligence() > 70 && graph.vertices.get(afflictedVertex).getIntelligence() < 120 && graph.vertices.get(afflictedVertex).getGullibility() > 3 && graph.vertices.get(afflictedVertex).getGullibility() < 8 && graph.vertices.get(afflictedVertex).getPersuasion() < 8 && graph.vertices.get(afflictedVertex).getPersuasion() > 3 && graph.vertices.get(afflictedVertex).getScandalism() < 8 && graph.vertices.get(afflictedVertex).getScandalism() > 3 && graph.vertices.get(afflictedVertex).getPostingFrequency() > 30 && graph.vertices.get(afflictedVertex).getPostingFrequency() < 80 && graph.vertices.get(afflictedVertex).getReadingFrequency() < 80 && graph.vertices.get(afflictedVertex).getReadingFrequency() > 30)
                        break;
                }
                graph.vertices.get(afflictedVertex).setIllnessRate((int) (Math.random() * 50));
                numberOfAfflicted++;
                break;
            case 10:                    //zlepek nodow
                afflictedVertex = (int) (Math.random() * graph.getNumberOfVertices());
                graph.vertices.get(afflictedVertex).setIllnessRate((int) (Math.random() * 70));
                numberOfAfflicted++;
                for (Map.Entry<Vertex, Integer> adjacencyList : graph.vertices.get(afflictedVertex).getAdjacencyList().entrySet()) {
                    if((int)(Math.random()*100) > 50) {
                        adjacencyList.getKey().setIllnessRate((int) (Math.random() * 70));
                        numberOfAfflicted++;
                    }
                   /* if(numberOfAfflicted == 5)
                        break;*/
                }
                break;
        }
    }

    private void shuffleArray() {
        int index, temp;
        Random random = new Random();
        for (int i = arrayOfOrder.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = arrayOfOrder[index];
            arrayOfOrder[index] = arrayOfOrder[i];
            arrayOfOrder[i] = temp;
        }
    }

    private void postWasRead(Vertex vertexFrom, Vertex vertexTo, boolean isRetweet) {

        int points = calculatePoints(vertexFrom, vertexTo, isRetweet);
        changeIllnessRate(vertexTo, points);
        changeIllnessRate(vertexFrom, points / 5);
        int edgePoints = calculateEdgePoints(vertexFrom, vertexTo, points, isRetweet);
        changeEdgeWeight(vertexFrom, vertexTo, edgePoints);
        if (isRetweet && 0 <= points) {
            if ((int) (Math.random() * 100) < 4 + points / 10 && !vertexTo.getAdjacencyList().containsKey(retweet.peek()))
                makeNewEdge(retweet.peek(), vertexTo, points);
        }
        if (edgePoints > 0 && (int) (Math.random() * 100) < (vertexFrom.getAdjacencyList().get(vertexTo) / 10 + edgePoints)) {
            reTweet(vertexTo);
        }
    }

    private int calculatePoints(Vertex vertexFrom, Vertex vertexTo, boolean isRetweet) {
        int points = 0;
        if (!isRetweet) {
            if (vertexFrom.getIllnessRate() < 80) {
                if (vertexTo.getIntelligence() / 20 < 10 - vertexFrom.getScandalism())
                    points = -1 * (vertexFrom.getPersuasion() + vertexTo.getGullibility() + vertexFrom.getAdjacencyList().get(vertexTo) / 20 + (vertexFrom.getIntelligence() - vertexTo.getIntelligence()) / 20);
                else {
                    points = vertexTo.getIntelligence() / 20;
                }
            } else if (vertexFrom.getIllnessRate() >= 160) {
                points = (vertexFrom.getIntelligence() - vertexTo.getIntelligence()) / 20 + vertexFrom.getPersuasion() + vertexTo.getGullibility() - (200 - vertexTo.getIllnessRate()) / 30 + vertexFrom.getAdjacencyList().get(vertexTo) / 10;
            }
        } else {
            if (retweet.peek().getIllnessRate() < 80) {
                if (vertexTo.getIntelligence() / 20 < 10 - retweet.peek().getScandalism())
                    points = -1 * (retweet.peek().getPersuasion() + vertexTo.getGullibility() + vertexFrom.getAdjacencyList().get(vertexTo) / 20 + (retweet.peek().getIntelligence() - vertexTo.getIntelligence()) / 20);
                else {
                    points = vertexTo.getIntelligence() / 20;
                }
            } else if (retweet.peek().getIllnessRate() >= 160) {
                points = (retweet.peek().getIntelligence() - vertexTo.getIntelligence()) / 20 + retweet.peek().getPersuasion() + vertexTo.getGullibility() - (200 - vertexTo.getIllnessRate()) / 20;
            }
            points /= 5;
        }
        return points;
    }

    private int calculateEdgePoints(Vertex vertexFrom, Vertex vertexTo, int points, boolean isRetweet) {
        if (!isRetweet) {
            if (vertexFrom.getIllnessRate() < 80) {
                points *= -1;
            }
        } else {
            if (retweet.peek().getIllnessRate() < 80)
                points *= -1;
        }
        return points / 2;
    }

    private void reTweet(Vertex vertexTo) {
        if (retweet.contains(vertexTo))
            return;
        retweet.push(vertexTo);

        for (Map.Entry<Vertex, Integer> vertexIntegerEntry : vertexTo.getAdjacencyList().entrySet()) {
            Vertex vertexToReadRetweet = vertexIntegerEntry.getKey();
            if (retweet.contains(vertexToReadRetweet))
                continue;
            if (vertexTo.getReadingFrequency() + (vertexIntegerEntry.getValue() / 100) > (int) (Math.random() * 100))
                postWasRead(vertexTo, vertexToReadRetweet, true);
        }
    }

    private void changeIllnessRate(Vertex vertex, int points) {
        int newIllnessRate = vertex.getIllnessRate() + points;
        if (newIllnessRate < 0)
            newIllnessRate = 0;
        else if (newIllnessRate > 200)
            newIllnessRate = 200;
        if (vertex.getIllnessRate() >= 80 && newIllnessRate < 80)
            numberOfAfflicted++;
        else if (vertex.getIllnessRate() < 80 && newIllnessRate >= 80)
            numberOfAfflicted--;
        vertex.setIllnessRate(newIllnessRate);
    }


    private void changeEdgeWeight(Vertex vertexFrom, Vertex vertexTo, int points) {
        int edgeWeight = vertexFrom.getAdjacencyList().get(vertexTo) + points;
        if (edgeWeight > 100)
            edgeWeight = 100;
        else if (edgeWeight < 0)
            edgeWeight = 0;
        graph.changeWeight(vertexFrom, vertexTo, edgeWeight);
    }

    private void makeNewEdge(Vertex vertexFrom, Vertex vertexTo, int points) {
        graph.addEdge(vertexFrom, vertexTo, points + (int) (Math.random() * 20));
    }

    public void printAfflicted() {
        System.out.println("\nNumber of Afflicted Nodes: " + numberOfAfflicted + "\n");
        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            if (graph.vertices.get(i).getIllnessRate() < 80) {
                System.out.println(graph.vertices.get(i) + " OutDegree of: " + graph.vertices.get(i).getOutDegree() + "|");
            }
        }
    }

}
