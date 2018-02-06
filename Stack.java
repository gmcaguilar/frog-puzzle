/**
 * CS303 Chapter 4 Homework: Stack
 * Name: Gabrielle Aguilar
 * Date: 9/11/2016
 * Purpose of the program: A first-in, last-out
 * stack interface.
 */
public interface Stack<E>  {
  
  /** Return true if this Stack is empty. */
  public boolean isEmpty();
  
  /**
   * Return the top item on this Stack, but do not modify the Stack.
   * @throws EmptyStructureException if this Stack is empty.
   */
  public E peek();
  
  /**
   * Remove and return the top item on this Stack.
   * @throws EmptyStructureException if this Stack is empty.
   */
  public E pop();
  
  /** Add target to the top of the Stack. */
  public void push(E target);
  
}
