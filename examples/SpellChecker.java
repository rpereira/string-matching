import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is an example of a spell checker implementation using a Trie.
 *
 * It works by reading a dictionary of words form the file ./data/words.txt,
 * another file that simulates user input from ./data/words_with_typos.txt, and
 * printing out any misspelled words that appear on the latter.
 */
public class SpellChecker {

  /**
   * Read in the dictionary of words.
   */
  private static TrieST<Integer> buildDictionary() {
    TrieST<Integer> dict = new TrieST<Integer>();
    File f = new File("./data/words.txt");
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(f));
      String word = null;

      int i = 0;
      while ((word = reader.readLine()) != null) {
        dict.put(word, i);
        i++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
      }
    }

    return dict;
  }

  /**
   * Read in the file containing _some_ misspelled words.
   */
  private static List<String> buildWordsList() {
    List<String> words = new ArrayList<String>();
    File f = new File("./data/words_with_typos.txt");
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(f));
      String word = null;

      while ((word = reader.readLine()) != null) {
        words.add(word);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
      }
    }

    return words;
  }

  public static void main(String[] args) {
    List<String> misspelledWords = new ArrayList<String>();
    List<String> words = buildWordsList();

    TrieST<Integer> dictionary = buildDictionary();

    for (String w : words) {
      if (!dictionary.contains(w)) {
        misspelledWords.add(w);
      }
    }

    if (misspelledWords.size() > 0) {
      System.out.println("Misspelled words are:");

      for (String w : misspelledWords) {
        System.out.println("* " + w);
      }
    } else {
      System.out.println("There are no misspelled words!");
    }
  }
}
