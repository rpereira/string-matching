import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * SuffixArray client that computes the longest common strung that appears in
 * the two texts.
 *
 * TODO: this could be changed so that the files are given as an argument
 */
public class LongestCommonSubstring {

  /**
   * Reads the contents of the specified file and returns them as a string.
   *
   * @param filePath the path to the input file
   */
  private static String readFileAsString(String filePath) {
    File f = new File(filePath);
    BufferedReader reader = null;
    StringBuilder text = new StringBuilder(470000);
    String line = null;

    try {
      reader = new BufferedReader(new FileReader(f));
      while ((line = reader.readLine()) != null) {
        text.append(line);
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

    return text.toString();
  }

  /**
   * Returns the longest common string of the two specified strings.
   */
  private static String lcs(String s, String t) {
    int sLength = s.length();

    String text = s + t;
    int length = text.length();

    SuffixArray suffixes = new SuffixArray(text);

    // Search for longest common substring
    String lcs = "";
    for (int i = 1; i < length; i++) {

      // Adjacent suffixes from first string
      if (suffixes.indexOf(i) < sLength && suffixes.indexOf(i-1) < sLength) {
        continue;
      }

      // Adjacent suffixes from second string
      if (suffixes.indexOf(i) > sLength && suffixes.indexOf(i-1) > sLength) {
        continue;
      }

      // Are adjancent suffixes longer than common substring?
      int _length = suffixes.longestCommonPreffix(i);
      if (_length > lcs.length()) {
        int index = suffixes.indexOf(i);
        lcs = text.substring(index, index + _length);
      }
    }

    return lcs;
  }

  public static void main(String[] args) {
    String s = readFileAsString("./data/brave_new_world.txt");
    String t = readFileAsString("./data/the_doors_of_perception.txt");

    System.out.println("The longest common substring is: '" + lcs(s, t) + "'");
  }
}
