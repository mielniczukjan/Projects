public class Main {

    public static void main(String[] args){

        LinkList theLinkedList = new LinkList();

        theLinkedList.insertFirstLink("Don Quixote", 500);
        theLinkedList.insertFirstLink("Robinson Crusoe", 250);
        theLinkedList.insertFirstLink("The Lord of the Rings", 300);
        theLinkedList.insertFirstLink("Percy Jackson", 100);

        theLinkedList.display();

        theLinkedList.removeFirst();

        theLinkedList.display();

        System.out.println(theLinkedList.find("The Lord of the Rings").bookName + "Was found");

        theLinkedList.removeLink("The Lord of the Rings");

        theLinkedList.display();

    }

}
