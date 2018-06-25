import java.util.Arrays;
import java.util.Iterator;
public class MyArrayList<T> implements Iterable<T> {

    private T[] myArrayList;
    private final static int DEFAULT_SIZE = 5;
    private int currentSize;

    MyArrayList() {
         final T[] myArrayList = (T[]) new Object[DEFAULT_SIZE];
         this.myArrayList = myArrayList;
         this.currentSize = DEFAULT_SIZE;
    }

    public int size() {
        return countElements();
    }

    public boolean isEmpty() {
        return countElements() == 0;
    }

    private int countElements() {
        int elementCount = 0;
        for(int i = 0; i < currentSize; i++){
            if(myArrayList[i] != null)
            elementCount++;
        }
        return elementCount;
    }

    public T get(int index) {
        if(currentSize <= index || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Array index out of bounds exception because of index :  " + index);
        }
        return myArrayList[index];
    }

    public int indexOf(T element) {
        int initialIndex = 0;
        if(!isEmpty()) {
            while(initialIndex < currentSize) {
                if(element == myArrayList[initialIndex])
                    return initialIndex;
                initialIndex++;
            }
        }
        System.out.println("Value not found");
        return -1;
    }

    public void set(int index, T element) {
        if(currentSize <= index || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Array index out of bounds exception because of index : " + index);
        }
        myArrayList[index] = element;
    }

    public void add(int index, T element) {
        ensureCapacity(currentSize + 1);
        System.arraycopy(myArrayList, index, myArrayList, index + 1, currentSize - index);
        myArrayList[index] = element;
        currentSize++;
    }

    private void ensureCapacity(int minCapacity) {
        if(currentSize < minCapacity) {
            growArraySize(minCapacity);
        }
    }

    private void growArraySize(int minCapacity) {
        int oldCapacity = currentSize;
        int newCapacity = (oldCapacity * 3) / 2 + 1;
        if(newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        myArrayList = Arrays.copyOf(myArrayList, newCapacity);
    }

    public void clear() {
        for(int index = 0; index < currentSize; index++) {
            myArrayList[index] = null;
        }
        currentSize = 0;
        // T[] myArrayList = (T[]) new Object[0];
        // this.myArrayList = myArrayList;
    }

    public T remove(int index) {
        if(currentSize <= index || index < 0) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException with an index of : " + index);
        }
        T oldValue = myArrayList[index];
        int removeIndex = currentSize - index - 1;
        if(0 < removeIndex)
            System.arraycopy(myArrayList, index +1, myArrayList, index, removeIndex);
        myArrayList[--currentSize] = null;
        return oldValue;
    }

    public void show() {
        for(int index = 0; index < currentSize; index++) {
            System.out.print(get(index) + ", ");
        }
    }

    public boolean contains(T aValue) {
        T temporaryValue;
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()) {
            temporaryValue = iterator.next();
            if(temporaryValue == aValue)
                return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return (currentIndex < currentSize);
            }

            @Override
            public T next() {
                T aValue = get(currentIndex);
                currentIndex++;
                return aValue;
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(currentIndex);
            }
        };
    }
}
