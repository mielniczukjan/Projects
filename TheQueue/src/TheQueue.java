public class TheQueue<T> {

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private boolean isEmpty() {
        return head == null;
    }

    public T deQueue() {
        if(isEmpty()) {
            throw new RuntimeException("Queue Underflow");
        } else if (head == tail){
            T f = (T)head.item;
            head = null;
            tail = null;
            size--;
            return f;
        }
        T f = (T)head.item;
        head = head.next;
        size--;
        return f;
    }

    public void enQueue(T item) {
        Node element = new Node(item);
        if(head == null && tail == null) {
            head = element;
            tail = element;
            size++;
            return;
        }
        tail.next = element;
        tail = element;
        size++;
    }

  /*  public void enQueueMany(String manyValues) {
        String[] values = manyValues.split(" ");
        for (String value : values) {
            enQueue(value);
        }
    } */

    public void deQueueAll() {
        int tempSize = size;
        for(int i = 0; i < tempSize; i++) {
            System.out.println(deQueue());
        }
    }

    public T peek() {
        return (T)head.item;
    }

    public int size() {
        return size;
    }

    public void print() {
        Node element = head;
        for(int i = 0; i < size; i++) {
            System.out.print("| " + element.item + " ");
            element = element.next;
        }
        System.out.print("|");
        System.out.println();
    }

    public boolean contains(T item) {
        Node element = head;
        for(int i = 0; i < size; i++) {
            if(element.item.equals(item))
                return true;
            element = element.next;
        }
        return false;
    }

    public int indexOf(T item) {
        Node element = head;
        for(int i = 0; i < size; i++) {
            if(element.item.equals(item))
                return i;
            element = element.next;
        }
        return -1;
    }
}
