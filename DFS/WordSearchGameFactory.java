
/**
 * Provides a factory method for creating word search games. 
 *
 * @author Matthew Bankson (mlb0126@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 */
public class WordSearchGameFactory {

   /**
    * Returns an instance of a class that implements the WordSearchGame
    * interface.
    */
   public static WordSearchGame createGame() {
      // You must return an instance of your solution class here instead of null.
      return new BoggleTown();
   }

}
