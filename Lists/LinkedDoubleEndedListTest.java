import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinkedDoubleEndedListTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void sizeTest3() {
      LinkedDoubleEndedList<Integer> lDB = new LinkedDoubleEndedList<Integer>();
   
   // removeFirst until empty
      for (int i = 0; i < 10; i++) {
         lDB.addFirst(i);
      }
   
   // removeFirst the 10 things just added
      for (int i = 0; i < 10; i++) {
         lDB.removeFirst();
      }
      
      Assert.assertEquals(0, lDB.size());
   
   // addFirst multiple elements
      for (int i = 0; i < 10; i++) {
         lDB.addFirst(i);
      }
      
      int expected = 10;
      int actual = lDB.size();
      Assert.assertEquals("AddRemoveOrder: addFirst multiple elements, removeFirst until empty, addFirst multiple elements.", expected, actual);
   }
}
