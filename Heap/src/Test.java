public class Test {

    public static void main(String... args) {
        Heap theHeap = new Heap(10);
        theHeap.insert(new Node(24));
        theHeap.print();
        theHeap.insert(new Node(6));
        theHeap.print();
        theHeap.insert(new Node(28));
        theHeap.print();
        theHeap.insert(new Node(5));
        theHeap.print();
        theHeap.insert(new Node(63));
        theHeap.print();
        theHeap.insert(new Node(19));
        theHeap.print();
        theHeap.insert(new Node(69));
        theHeap.print();
        theHeap.insert(new Node(56));
        theHeap.print();
        theHeap.insert(new Node(60));
        theHeap.print();
        theHeap.insert(new Node(34));
        theHeap.print();
        theHeap.insert(new Node(45));
        theHeap.print();
        theHeap.insert(new Node(34));
        theHeap.print();
        theHeap.insert(new Node(9));
        theHeap.print();

        System.out.println(theHeap.delete(5));

        theHeap.print();

        System.out.println(theHeap.size());

        theHeap.print();

        System.out.print(theHeap.delete(5));

        theHeap.print();
    }

}
