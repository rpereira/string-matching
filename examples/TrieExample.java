import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrieExample {
  public static void main(String[] args) {

    // Open the data file
    Scanner scf = null;
    try {
      scf = new Scanner(new File("./data/peter_piper.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    TrieST<Integer> st = new TrieST<Integer>();

    // Read file's next line
    while (scf.hasNextLine()) {
      Scanner scl = new Scanner(scf.nextLine());

      // Read line's next word
      int i = 0;
      while (scl.hasNext()) {
        String key = scl.next();
        st.put(key, i);
        i++;
      }
    }

    // print results
    System.out.println("Symbol table:");
    for (String key : st.keys()) {
      System.out.println("* " + key + " " + st.get(key));
    }

    System.out.println();

    System.out.println("Keys with prefix \"pi\":");
    for (String key : st.keysWithPrefix("pi")) {
      System.out.println("* " + key);
    }
    System.out.println();

    System.out.println("Longest prefix of \"peckleton\":");
    System.out.println("* " + st.longestPrefixOf("peckleton"));
    System.out.println();

    System.out.println("Keys that match pattern \"p**er\":");
    for (String key : st.wildKeys("p**er")) {
      System.out.println("* " + key);
    }
  }
}
