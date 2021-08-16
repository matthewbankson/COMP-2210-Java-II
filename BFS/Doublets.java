import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Matthew Bankson (mlb0126@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-03-29
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////

   public HashSet<String> lexicon;

   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new HashSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toUpperCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////

   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      else if (str1.equals(str2)) {
         return 0;
      }
      else {
         int N = str1.length();
         int ham = 0;
         for (int i = 0; i < N; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
               ham++;
            }
         }
         return ham;
      }
   }

   public List<String> getMinLadder(String start, String end) {
      List<String> result = new ArrayList<String>();
      if (start.equals(end)) {
         result.add(start);
         return result;
      }
      else if (start.length() != end.length()) {
         return result;
      }
      else if (!isWord(start) || !isWord(end)) {
         return result;
      }
      
      start = start.toUpperCase();
      end = end.toUpperCase();
      Deque<Node> queue = new ArrayDeque<Node>();
      HashSet<String> yeet = new HashSet<String>();
      queue.addLast(new Node(start, null));
      while (!queue.isEmpty()) {
         Node n = queue.removeFirst();
         String content = n.content;
         for (String neigh : getNeighbors(content)) {
            neigh = neigh.toUpperCase();
            if (!yeet.contains(neigh)) {
               yeet.add(neigh);
               queue.addLast(new Node(neigh, n));
            }
            if (neigh.equals(end)) {
               Node m = queue.removeLast();
               while (m != null) {
                  result.add(0, m.content);
                  m = m.prev;
               }
               return result;
            }
         }
      }
      return result;
   }

   public List<String> getNeighbors(String word) {
      word = word.toUpperCase();
      List<String> result = new ArrayList<String>();
      word = word.toLowerCase();
      String test = word;
      int N = word.length();
      for (int i = 0; i < N; i++) {
         for (char letter = 'a'; letter <= 'z'; letter++) {
            if (i == 0) {
               test = letter + test.substring(i + 1);
               if (!test.equals(word) && getHammingDistance(test, word) == 1 && isWord(test)) {
                  result.add(test);
               }
            }
            else if (i == N - 1) {
               test = test.substring(0, i) + letter;
               if (!test.equals(word) && getHammingDistance(test, word) == 1 && isWord(test)) {
                  result.add(test);
               }
            }
            else {
               test = test.substring(0, i) + letter + test.substring(i + 1);
               if (!test.equals(word) && getHammingDistance(test, word) == 1 && isWord(test)) {
                  result.add(test);
               }
            }
         }
         test = word;
      }
      return result;
   }

   public int getWordCount() {
      return lexicon.size();
   }

   public boolean isWord(String str) {
      str = str.toUpperCase();
      return lexicon.contains(str);
   }

   public boolean isWordLadder(List<String> sequence) {
      boolean result = true;
      if (sequence == null || sequence.isEmpty()) {
         return false;
      }
      for (int i = 0; i < sequence.size() - 1; i++) {
         if (!isWord(sequence.get(i)) || !isWord(sequence.get(i + 1)) || getHammingDistance(sequence.get(i), sequence.get(i + 1)) != 1) {
            result = false;
         }
      }
      return result;
   }
   
   private class Node {
      String content;
      Node prev;
   
      public Node(String s, Node n) {
         content = s;
         prev = n;
      }
   }
}

