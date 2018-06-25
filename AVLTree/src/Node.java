public class Node {

    Node leftChild;
    Node rightChild;
    int value;
    int height;

    Node(int value) {
        this.value = value;
        height = 1;
    }

    public String toString() {
        return Integer.toString(value);
    }

}
