import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

public class ArrayRandomizedList<T> implements RandomizedList<T> {

   private static final int INITIAL_CAPACITY = 10;
   private T[] elements;
   private int size;
   private int rear;
   private Random num = new Random();
   
   public ArrayRandomizedList() {
      this(INITIAL_CAPACITY);
   }
   
   @SuppressWarnings("unchecked")
   public ArrayRandomizedList(int capacity) {
      elements = (T[]) new Object[capacity];
      size = 0;
   }

   public int size() {
      return size;
   }
   
   public boolean isEmpty() {
      return size == 0;
   }
   
   public Iterator<T> iterator() {
      return new ArrayRandomIterator();
   }
   
   private class ArrayRandomIterator implements Iterator<T> {
   
      private T[] items;
      private int count = size;
      private int current = 0;
      
      @SuppressWarnings("unchecked")
      private ArrayRandomIterator() {
         items = (T[]) new Object[size];
         for (int i = 0; i < size; i++) {
            items[i] = elements[i];
         }
         
         for (int i = 0; i < size; i++) {
            int rand = num.nextInt(size);
            T now = items[i];
            items[i] = items[rand];
            items[rand] = now;
         }
      }
   
      public boolean hasNext() {
         return (current < count);
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         return items[current++];
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
   
   public void add(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      if (size == elements.length) {
         resize(elements.length * 2);
      }
      elements[size] = element;
      size++;
   }
   
   @SuppressWarnings("unchecked")
   private void resize(int capacity) {
      T[] a = (T[]) new Object[capacity];
      for (int i = 0; i < size(); i++) {
         a[i] = elements[i];
      }
      elements = a;
   }
   
   public T remove() {
      if (isEmpty()) {
         return null;
      }
      int index = num.nextInt(size);
      T result = elements[index];
      elements[index] = elements[--size];
      elements[size] = null;
      return result;
   }
   
   public T sample() {
      if (isEmpty()) {
         return null;
      }
      int index = num.nextInt(size);
      T result = elements[index];
      return result;
   }
}