/**
 * CS303 Chapter 4 Homework: ArrayStack
 * Name: Gabrielle Aguilar
 * Date: 9/11/2016
 * Purpose of the program: A class that represents
 * a stack of cards.
 */
public class ArrayStack<E> implements Stack<E> {
  
  ///////////////////////////// fields ///////////////////////////////
  
  /** Array of items in this Stack. */
  private E[] data;
  
  /** Number of items currently in this Stack. */
  private int size;
  
  ///////////////////////// constructors ////////////////////////////
  
  /** The Stack is initially empty. */
  public ArrayStack() {
    data = (E[])(new Object[1]); // This causes a compiler warning
    size = 0;
  }
  
  ///////////////////////// methods /////////////////////////////////
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public E pop() {
    if (isEmpty()) {
      throw new EmptyStructureException("I'm sorry, the lilypad is empty.");
    }
    size--;
    return data[size];
  }
  
  public E peek() {
    if (isEmpty()) {
      throw new EmptyStructureException("I'm sorry, the lilypad is empty.");
    }
    return data[size - 1];
  }
  
  /** Return true if data is full. */
  protected boolean isFull() {
    return size == data.length;
  }
  
  public void push(E target) {
    if (isFull()) {
      stretch();
    }
    data[size] = target;
    size++;
  }
  
  /** Double the length of data. */
  protected void stretch() {
    E[] newData = (E[])(new Object[data.length * 2]); // Warning
    for (int i = 0; i < data.length; i++) {
      newData[i] = data[i];
    }
    data = newData;
  }
  
  
}