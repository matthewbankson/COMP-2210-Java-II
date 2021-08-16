import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;


public class ArrayRandomizedListTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void iterator() {
      ArrayRandomizedList<Integer> arl = new ArrayRandomizedList<Integer>();
      arl.add(1);
      arl.add(2);
      Iterator<Integer> itr = arl.iterator();
      itr.remove();
   }
}
