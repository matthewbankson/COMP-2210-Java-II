import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class BoggleTown implements WordSearchGame {

   public TreeSet<String> lex = new TreeSet<String>();
   public String[][] board = { {"E", "E", "C", "A"},
         {"A", "L", "E", "P"},
         {"H", "N", "B", "O"},
         {"Q", "T", "T", "Y"} };
   public boolean[][] visited = new boolean[4][4];
   public int sizeBoard;
   private static final int MAX_NEIGHBORS = 8;
   
   public BoggleTown() {
   
   }

   public void loadLexicon(String fileName) throws IllegalArgumentException {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      
      try {
      
         Scanner scanFile = new Scanner(new File(fileName));
         while (scanFile.hasNext()) {
            lex.add(scanFile.next().toUpperCase());
         }
      }
      catch (java.io.IOException e) {
         throw new IllegalArgumentException();
      }
   }

   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      double M = Math.sqrt(letterArray.length);
      boolean square = ((M - Math.floor(M)) == 0);
      if (square == false) {
         throw new IllegalArgumentException();
      }
      sizeBoard = (int) M;
      board = new String[sizeBoard][sizeBoard];
      int index = 0;
      for (int i = 0; i < sizeBoard; i++) {
         for (int j = 0; j < sizeBoard; j++) {
            board[i][j] = letterArray[index].toUpperCase();
            index++;
         }
      }
      visited = new boolean[sizeBoard][sizeBoard];
   }

   public String getBoard() {
      return "";
   }

   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lex.size() == 0) {
         throw new IllegalStateException();
      }
      visited = new boolean[sizeBoard][sizeBoard];
      String word = new String();
      SortedSet<String> result = new TreeSet<String>();
      Iterator<String> itr = lex.iterator();
      while (itr.hasNext()) {
         word = itr.next();
         if ((isOnBoard(word).size() != 0) && (word.length() >= minimumWordLength)) {
            result.add(word);
         }
      }
      return result;
   }
   
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      int score = 0;
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lex.size() == 0) {
         throw new IllegalStateException();
      }
      String word = new String();
      List<Integer> test = new ArrayList<Integer>();
      for (String s : words) {
         test = isOnBoard(s);
         if ((s.length() >= minimumWordLength) && (isValidWord(s)) && (test.size() != 0)) {
            if (s.length() == minimumWordLength) {
               score++;
            }
            else {
               score += (1 + (s.length() - minimumWordLength));
            }
         }
      }
      return score;
   }
   
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lex.size() == 0) {
         throw new IllegalStateException();
      }
      String test = wordToCheck.toUpperCase();
      return lex.contains(test);
   }
   
   public boolean isValidPrefix(String prefixToCheck) {
      String test;
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lex.size() == 0) {
         throw new IllegalStateException();
      }
      prefixToCheck = prefixToCheck.toUpperCase();
      test = lex.ceiling(prefixToCheck);
      if (test == null) {
         return false;
      }
      else if (test.contains(prefixToCheck)) {
         return true;
      }
      return false;
   }
   
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lex.size() == 0) {
         throw new IllegalStateException();
      }
      visited = new boolean[sizeBoard][sizeBoard];
      StringBuilder wordSoFar = new StringBuilder();
      wordToCheck = wordToCheck.toUpperCase();
      List<Integer> path = new ArrayList<Integer>();
      for (int i = 0; i < sizeBoard; i++) {
         for (int j = 0; j < sizeBoard; j++) {
            if (wordToCheck.startsWith(board[i][j])) {
               if (dfsOneWord(i, j, wordToCheck, wordSoFar, path)) {
                  return path;
               }
            }
         }
      }
      return path;
   }
   
   public boolean dfsOneWord(int i, int j, String wordToCheck,
   StringBuilder wordSoFar, List<Integer> path) {
      Position p = new Position(i, j);
      String wordSoFarS = wordSoFar.substring(0);
      if (p.isValid(p) == false) {
         return false;
      }
      if (p.isVisited(p) == true) {
         return false;
      }
      if (!(wordToCheck.startsWith(wordSoFarS))) {
         return false;
      }
      p.visit(p);
      String content = board[i][j];
      wordSoFar.append(content);
      path.add(i * sizeBoard + j);
      wordSoFarS = wordSoFar.substring(0);
      if (wordSoFarS.equals(wordToCheck)) {
         return true;
      }
      Position[] neybor = p.neighbors();
      for (Position p1 : neybor) {
         if (dfsOneWord(p1.x, p1.y, wordToCheck, wordSoFar, path)) {
            return true;
         }
      }
      visited[i][j] = false;
      wordSoFar.delete((wordSoFar.length() - content.length()), wordSoFar.length());
      path.remove(path.size() - 1);
      return false;
   }
   
   private class Position {
      int x;
      int y;
   
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
      
      private boolean isValid(Position p) {
         return (p.x >= 0) && (p.x < sizeBoard)
            && (p.y >=0) && (p.y < sizeBoard);
      }
      
      private boolean isVisited(Position p) {
         return visited[p.x][p.y];
      }
      
      private void visit(Position p) {
         visited[p.x][p.y] = true;
      }
      
      public Position[] neighbors() {
         Position[] nmbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nmbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nmbrs, count);
      }
   }
}