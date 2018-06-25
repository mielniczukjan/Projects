import java.util.Arrays;
import java.util.NoSuchElementException;

public class Heap {

    private Node[] theHeap;
    private int maxSize;
    private int itemsInArray = 0;

    Heap(int maxSize) {
        this.maxSize = maxSize;
        theHeap = new Node[maxSize];
        Node emptyNode = new Node(-1);
        Arrays.fill(theHeap, emptyNode);
    }

    public int size() {
        return itemsInArray;
    }

    public boolean isEmpty() {
        return itemsInArray == 0;
    }

    public void empty() {
        Node emptyNode = new Node(-1);
        Arrays.fill(theHeap, emptyNode);
        itemsInArray = 0;
    }

    public boolean isFull() {
        return itemsInArray >= maxSize;
    }

    private void increment() {
        maxSize = (maxSize * 3) / 2 + 1;
        theHeap = Arrays.copyOf(theHeap, maxSize);
    }

    public void insert(Node newNode) {
        if (isFull()) {
            increment();
        }
        theHeap[itemsInArray++] = newNode;
        heapUp(itemsInArray - 1);
    }

    public Node pop() {
        Node rootNode = theHeap[0];
        delete(0);
        return rootNode;
    }

    public Node delete(int index) {
        if (isEmpty() || index > itemsInArray) {
            throw new NoSuchElementException("There is no such element with this index");
        }
        Node deletedNode = theHeap[index];
        theHeap[index] = theHeap[itemsInArray - 1];
        itemsInArray--;
        heapDown(index);
        return deletedNode;
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private void heapUp(int index) {
        Node temporaryNode = theHeap[index];
        while (index > 0 && temporaryNode.key > theHeap[parentIndex(index)].key) {
            theHeap[index] = theHeap[parentIndex(index)];
            index = parentIndex(index);
        }
        theHeap[index] = temporaryNode;
    }

    private void heapDown(int index) {
        int temporaryInt;
        Node temporaryNode = theHeap[index];
        while (child(index, 1) < itemsInArray) {
            temporaryInt = maxChild(index);
            if (theHeap[temporaryInt].key > temporaryNode.key)
                theHeap[index] = theHeap[temporaryInt];
            else
                break;
            index = temporaryInt;
        }
        theHeap[index] = temporaryNode;
    }

    private int child(int index, int leftRight) {
        return 2 * index + leftRight;
    }

    private int maxChild(int index) {
        int maxChild = child(index, 1);
        int leftRight = 2;
        int position = child(index, 2);
        while (leftRight <= 2 && position < itemsInArray) {
            if (theHeap[position].key > theHeap[maxChild].key)
                maxChild = position;
            position = child(index, leftRight++);
        }
        return maxChild;
    }

    public void print() {
        System.out.println("\n\nTheHeap:");
        for (int index = 0; index < itemsInArray; index++)
            System.out.print(theHeap[index] + " ");
        System.out.println("\n");
    }
}
