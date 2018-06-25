public class TestTheQueue {

    public static void main(String ...args) {

        TheQueue theQueue = new TheQueue();

        theQueue.enQueue("Niech");
        theQueue.enQueue("Epsilon");
        theQueue.enQueue("Bedzie");
        theQueue.enQueue("Mniejszy");
        theQueue.enQueue("Od");

        System.out.println("czy posiada ?? " + theQueue.contains("Od"));

        System.out.println(theQueue.indexOf("Od"));

        System.out.println("Na pierwszym miejscu w kolejce stoi : " + theQueue.peek());
        System.out.println("Size : " + theQueue.size());

        theQueue.enQueue("Zera");

        theQueue.print();

        System.out.println(theQueue.deQueue());
        System.out.println(theQueue.deQueue());
        System.out.println(theQueue.deQueue());

        System.out.println("Na pierwszym miejscu w kolejce stoi : " + theQueue.peek());

        System.out.println(theQueue.deQueue());
        System.out.println(theQueue.deQueue());
        System.out.println(theQueue.deQueue());

        System.out.println("Size : " + theQueue.size());

        theQueue.print();

        theQueue.enQueue("Niech");
        theQueue.enQueue("Epsilon");
        theQueue.enQueue("Bedzie");
        theQueue.enQueue("Mniejszy");
        theQueue.enQueue("Od");
        theQueue.enQueue("Zera");

        theQueue.print();

        theQueue.deQueueAll();
        System.out.println("Size : " + theQueue.size());
    }
}
