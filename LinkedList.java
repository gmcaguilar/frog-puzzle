/** A linked data-structure implementing the list interface. */

public class LinkedList<E> implements List<E>, Predecessor<E> {
  
  /** The first node in the List. */
  public ListNode<E> front;
  
  /** A LinkedList is initially empty. */
  public LinkedList() {
    front = null;
  }
  
  public boolean contains(E target) {
    for (ListNode<E> node = front; node != null; 
         node = node.getNext()) {
           if (node.getItem().equals(target)) {
             return true;
           }
         }
         return false;
  }
  
  public int size() {
    int tally = 0;
    for (ListNode<E> node = front;
         node != null;
         node = node.getNext()) {
           tally++;
         }
         return tally;
  }
  
  public String toString() {
    String result = "(";
    for (ListNode<E> node = front;
         node != null;
         node = node.getNext()) {
           result += node.getItem() + " ";
         }
         return result + ")";
  }
  
  
  public boolean isEmpty() {
    return front == null;
  }
  
  public E get(int index) { 
    ListNode<E> node = front;
    for (int i = 0; i < index; i++) {
      node = node.getNext();
    }
    return node.getItem();
  }
  
  public void set(int index, E target) {
    ListNode<E> node = front;
    for (int i = 0; i < index; i++) {
      node = node.getNext();
    }
    node.setItem(target);
  }
  
  public void add(E target) {
    Predecessor<E> last = this;
    while (last.getNext() != null) {
      last = last.getNext();      
    }
    last.setNext(new ListNode<E>(target));
  }
  
  public boolean remove(E target) {
    Predecessor<E> prev = this;
    ListNode<E> node = front;
    while (node != null) {
      if (node.getItem().equals(target)) {
        prev.setNext(node.getNext());
        return true;
      }
      prev = node;
      node = node.getNext();
    }
    return false;
  }
  
  public E remove(int index) {
    Predecessor<E> prev = this;
    ListNode<E> node = front;
    for (int i = 0; i < index; i++) {
      
      prev = node;
      node = node.getNext();
    }
    prev.setNext(node.getNext());
    return node.getItem();
  }
  
  public ListNode<E> getNext() {
    return front;
  }
  
  public void setNext(ListNode<E> next) {
    front = next;
  }
  
  public java.util.Iterator<E> iterator() {
    java.util.Iterator<E> t = null;
    return t;
  }
  
}


