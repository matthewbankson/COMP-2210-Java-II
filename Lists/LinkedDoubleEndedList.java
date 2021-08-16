import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDoubleEndedList<T> implements DoubleEndedList<T> {

   private Node front;
   private Node rear;
   private int size;
   
   public LinkedDoubleEndedList() {
      front = null;
      rear = null;
      size = 0;
   }
   
   private class Node {
      private T element;
      private Node next;
      private Node prev;
      
      public Node(T e) {
         element = e;
      }
   }
   
   public int size() {
      return size;
   }
   
   public boolean isEmpty() {
      return size == 0;
   }
   
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }
   
   private class LinkedIterator implements Iterator<T> {
      private Node current = front;
      public boolean hasNext() {
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T result = current.element;
         current = current.next;
         return result;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
   
   public void addFirst(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      if (isEmpty()) {
         front = n;
         rear = n;
      }
      else {
         n.next = front;
         front.prev = n;
         front = n;
         n.prev = null;
      }
      size++;
   }
   
   public void addLast(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      if (isEmpty()) {
         front = n;
         rear = n;
      }
      else {
         n.prev = rear;
         rear.next = n;
         rear = n;
         n.next = null;
      }
      size++;
   }
   
   public T removeFirst() {
      T result = null;
      if (isEmpty()) {
         return null;
      }
      if (size == 1) {
         result = front.element;
         front = null;
         rear = front;
         size--;
      }
      else {
         result = front.element;
         front.next.prev = null;
         front = front.next;
         size--;
      }
      return result;
   }
   
   public T removeLast() {
      T result = null;
      if (isEmpty()) {
         return null;
      }
      if (size == 1) {
         result = rear.element;
         front = null;
         rear = front;
         size--;
      }
      else {
         result = rear.element;
         rear.prev.next = null;
         rear = rear.prev;
         size--;
      }
      return result;
   }
}