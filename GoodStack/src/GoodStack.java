import java.util.EmptyStackException;

public class GoodStack<T> {
    private int size = 0;
    private Node top = null;

    private boolean isEmpty() {
        return top == null;
    }

    public void push(T item) {
        Node element = new Node(item);
        if(top == null)
            top = element;
        else {
            element.next = top;
            top = element;
        }
        size++;
    }

    public T pop() {
        if(isEmpty())
            throw new EmptyStackException();
        T temp = (T)top.item;
        top = top.next;
        size--;
        return temp;
    }

    public T peek() {
        if(isEmpty())
            throw new EmptyStackException();
        return (T)top.item;
    }

    public int size() {
        return size;
    }

    public boolean contains(T element) {
        Node temp = top;
        for(int i = 0; i < size; i++) {
            if(temp.item.equals(element))
                return true;
            temp = temp.next;
        }
        return false;
    }

    public int indexOf(T element) {
        Node temp = top;
        for(int i = size - 1; 0 <= i; i--) {
            if(temp.item.equals(element))
                return i;
            temp = temp.next;
        }
        return -1;
    }

    public void clear() {
        if(isEmpty())
            throw new EmptyStackException();
        for(int i = 0; i < size; i++){
            top = top.next;
        }
        size = 0;
    }

    public void print() {
        Node temp = top;
        for(int i = 0; i < size; i++) {
            System.out.print("| " + temp.item + " ");
            temp = temp.next;
        }
        System.out.print("|");
        System.out.println();
    }
}
