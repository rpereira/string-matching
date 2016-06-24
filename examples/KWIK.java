import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Keyword-in-context search.
 *
 * Computes all of the occurrences of a keyword in a string, with surrounding
 * context.
 */
public class KWIK {

  /**
   * Reads the contents of the specified file and returns them as a string.
   *
   * @param filePath the path to the input file
   */
  private static String readFileAsString(String filePath) {
    File f = new File(filePath);
    BufferedReader reader = null;
    StringBuilder text = new StringBuilder();
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

  public static void main(String[] args) {
    String text = readFileAsString("./data/brave_new_world.txt");
    // int context = Integer.parseInt(args[0]);
    // String query = args[1];

    // number of characters of surrounding context in each side
    int context = 20;

    // query string
    String query = "world";

    int length = text.length();

    // build suffix array
    SuffixArray sa = new SuffixArray(text);

    for (int i = sa.rank(query); i < length && sa.selectAsString(i).startsWith(query); i++) {
      int from = Math.max(0, sa.indexOf(i) - context);
      int to   = Math.min(length-1, from + query.length() + 2*context);
      System.out.println(text.substring(from, to));
    }
  }
}
