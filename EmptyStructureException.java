/**
 * CS303 Chapter 4 Homework: EmptyStructureException
 * Name: Gabrielle Aguilar
 * Date: 9/11/2016
 * Purpose of the program: Thrown when an attempt is made to access an element in a
 * structure which contains no elements.
 */
public class EmptyStructureException extends RuntimeException {
  
  public EmptyStructureException(String message) {
   System.err.println(message);
  }
  
}