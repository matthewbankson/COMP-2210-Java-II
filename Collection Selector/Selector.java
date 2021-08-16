import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Matthew Bankson (mlb0126@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      else {
         Iterator<T> itr = coll.iterator();
         T min = itr.next();
         T next;
         for (int i = 0; i < coll.size() - 1; i++) {
            next = itr.next();
            if (comp.compare(next, min) < 0) {
               min = next;
            }
         }
         return min;
      }
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      else {
         Iterator<T> itr = coll.iterator();
         T max = itr.next();
         T next;
         for (int i = 0; i < coll.size() - 1; i++) {
            next = itr.next();
            if (comp.compare(next, max) > 0) {
               max = next;
            }
         }
         return max;
      }
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      else {
         List<T> b = new ArrayList<T>(coll.size());
         b.addAll(coll);
         java.util.Collections.sort(b, comp);
         int duplicate = 0;
         int unique = 1;
         
         for (int i = 0; i < b.size() - 1; i++) {
         
            if (k == unique) {
               return b.get(i);
            }
            
            else if (comp.compare(b.get(i), b.get(i+1)) == 0) {
               duplicate++;
            }
            
            else {
               unique++;
            }
         }
         if (k < 1 || k > (b.size() - duplicate)) {
            throw new NoSuchElementException();
         }
         return b.get(b.size() - 1);
      }
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      else {
         List<T> b = new ArrayList<T>(coll.size());
         b.addAll(coll);
         java.util.Collections.sort(b, comp);
         int duplicate = 0;
         int unique = 1;
         
         for (int i = b.size() - 1; i > 0; i--) {
         
            if (k == unique) {
               return b.get(i);
            }
            
            else if (comp.compare(b.get(i), b.get(i-1)) == 0) {
               duplicate++;
            }
            
            else {
               unique++;
            }
         }
         if (k < 1 || k > (b.size() - duplicate)) {
            throw new NoSuchElementException();
         }
         return b.get(0);
      }
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      else {
         Iterator<T> itr = coll.iterator();
         T next;
         Collection<T> b = new ArrayList<T>();
         for (int i = 0; i < coll.size(); i++) {
            next = itr.next();
            if ((comp.compare(next, low) >= 0) && (comp.compare(next, high) <= 0)) {
               b.add(next);
            }
         }
         
         if (b.size() == 0) {
            throw new NoSuchElementException();
         }
         return b;
      }
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      else {
         Iterator<T> itr = coll.iterator();
         T ceiling = max(coll, comp);
         if (comp.compare(key, ceiling) > 0) {
            throw new NoSuchElementException();
         }
         for (T t : coll) {
            if ((comp.compare(t, key) >= 0) && (comp.compare(t, ceiling) <= 0)) {
               ceiling = t;
            }
         }
         return ceiling;
      }
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      else {
         Iterator<T> itr = coll.iterator();
         T floor = min(coll, comp);
         if (comp.compare(key, floor) < 0) {
            throw new NoSuchElementException();
         }
         for (T t : coll) {
            if ((comp.compare(t, key) <= 0) && (comp.compare(t, floor) >= 0)) {
               floor = t;
            }
         }
         return floor;
      }
   }

}
