import java.util.Arrays;
/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   YOUR NAME (YOU@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  TODAY
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
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      for (int i : a) {
         if (i < min) {
            min = i;
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int max = a[0];
      for (int i : a) {
         if (i > max) {
            max = i;
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = Arrays.copyOf(a, a.length); //copy array to not change original
      Arrays.sort(b);
      int duplicate = 0;
      int unique = 1;
      
      for (int i = 0; i < b.length - 1; i++) {
         if (k == unique) {
            return b[i];
         }
         
         else if (b[i] == b[i+1]) {
            duplicate++; //counts repeated numbers
         }
         
         else {
            unique++; //counts unique numbers
         }
      }
      
      if (k < 1 || k > (b.length - duplicate)) {
         throw new IllegalArgumentException();
      }
      return b[b.length - 1];
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = Arrays.copyOf(a, a.length); //copy array to not change original
      Arrays.sort(b);
      int duplicate = 0;
      int unique = 1;
      for (int i = b.length - 1; i > 0; i--) { //works from end of array to beginning
         if (k == unique) {
            return b[i];
         }
         
         else if (b[i] == b[i-1]) {
            duplicate++; //counts repeated numbers
         }
         
         else {
            unique++; //counts unique numbers
         }
      }
      
      if (k < 1 || k > (b.length - duplicate)) {
         throw new IllegalArgumentException();
      }
      return b[0];
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int j = 0;
      for (int i : a) {
         if ((low <= i) && (i <= high)) {
            j++; //keeps count of numbers in range to create
            //correct size range array
         }
      }
      int[] b = new int[j];
      int k = 0; //used as index for the array b
      for (int i = 0; i < a.length; i++) {
         if ((low <= a[i]) && (a[i] <= high)) {
            b[k] = a[i];
            k++;
         }
      }
      return b;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (key > max(a)) {
         throw new IllegalArgumentException();
      }
      int ceiling = max(a);
      for (int i : a) {
         if ((i >= key) && (i < ceiling)) {
            ceiling = i;
         }
      }
      return ceiling;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (key < min(a)) {
         throw new IllegalArgumentException();
      }
      int floor = min(a);
      for (int i : a) {
         if ((i <= key) && (i > floor)) {
            floor = i;
         }
      }
      return floor;
   }

}
