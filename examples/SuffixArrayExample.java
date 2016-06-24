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

      SuffixArrayOptimized suffix1 = new SuffixArrayOptimized(text);
      SuffixArray suffix2 = new SuffixArray(text);

      boolean check = true;
      for (int i = 0; check && i < text.length(); i++) {
        if (suffix1.indexOf(i) != suffix2.indexOf(i)) {
          System.out.println("suffix1(" + i + ") = " + suffix1.indexOf(i));
          System.out.println("suffix2(" + i + ") = " + suffix2.indexOf(i));

          String ith = "\"" +
            text.substring(suffix1.indexOf(i),
                           Math.min(suffix1.indexOf(i) + 50,
                           text.length())) +
            "\"";
          String jth = "\"" +
            text.substring(suffix2.indexOf(i),
                           Math.min(suffix2.indexOf(i) + 50,
                           text.length())) +
            "\"";

          System.out.println();
          System.out.println(ith);
          System.out.println(jth);

          check = false;
        }
      }

      System.out.println();
      System.out.println("==================================================");
      System.out.println();

      System.out.println("i\tindex\tLCP\trank\tselectAsString");
      System.out.println("------------------------------------------");

      for (int i = 0; i < text.length(); i++) {
        int index = suffix2.indexOf(i);
        String ith = "\"" +
          text.substring(index, Math.min(index + 50, text.length())) +
          "\"";
        int rank = suffix2.rank(text.substring(index));

        if (i == 0) {
          System.out.printf("%d\t%d\t%s\t%d\t%s\n", i, index, "-", rank, ith);
        }
        else {
          int lcp = suffix2.longestCommonPreffix(i);
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
