import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LongestCommonSubstring {
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

  private static String lcs(String s, String t) {
    int sLength = s.length();

    String text = s + t;
    int length = text.length();

    SuffixArray suffixes = new SuffixArray(text);

    String lcs = "";
    for (int i = 1; i < length; i++) {

      if (suffixes.indexOf(i) < sLength && suffixes.indexOf(i-1) < sLength) {
        continue;
      }

      if (suffixes.indexOf(i) > sLength && suffixes.indexOf(i-1) > sLength) {
        continue;
      }

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
