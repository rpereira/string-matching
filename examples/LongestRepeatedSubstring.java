import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is an example of how to find the longest repeated substring.
 *
 * Using this example for a sequence with millions of characters would require
 * trillions of longestCommonPreffix() calls, which is unfeasible.
 */
public class LongestRepeatedSubstring {
  public static void main(String[] args) {

    // Open the data file
    Scanner scf = null;
    try {
      scf = new Scanner(new File("./data/tiny_tale.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    StringBuilder text = new StringBuilder(300);

    // Read file's next line
    while (scf.hasNextLine()) {
      Scanner scl = new Scanner(scf.nextLine());

      // Read line's next word
      while (scl.hasNext()) {
        text.append(" " + scl.next());
      }
    }

    int length = text.length();
    SuffixArray suffixes = new SuffixArray(text.toString());
    String lrs = "";

    for (int i = 1; i < length; i++) {
      int _length = suffixes.longestCommonPreffix(i);
      if (_length > lrs.length()) {
        lrs = text.substring(suffixes.indexOf(i), suffixes.indexOf(i) + _length);
      }
    }

    System.out.println("LRS: '" + lrs + "'");
  }
}
