import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SuffixArrayExample {
  public static void main(String[] args) {

    File f = new File("./data/abra.txt");
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(f));
      String text = reader.readLine();
      System.out.println(text);
      SuffixArray suffixes = new SuffixArray(text);

      System.out.println("i\tindex\tLCP\trank\tselectAsString");
      System.out.println("------------------------------------------");

      for (int i = 0; i < suffixes.length(); i++) {
        int index = suffixes.indexOf(i);
        String ith = "\"" +
          text.substring(index, Math.min(index + 50, text.length())) +
          "\"";
        int rank = suffixes.rank(text.substring(index));

        if (i == 0) {
          System.out.printf("%d\t%d\t%s\t%d\t%s\n", i, index, "-", rank, ith);
        }
        else {
          int lcp = suffixes.longestCommonPreffix(i);
          System.out.printf("%d\t%d\t%d\t%d\t%s\n", i, index, lcp, rank, ith);
        }
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
  }
}
