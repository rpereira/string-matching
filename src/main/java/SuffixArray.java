import java.util.Arrays;

public class SuffixArray {

  /**
   * The array of suffixes.
   */
  private Suffix[] suffixes;

  /**
   * Build an array of Suffix objects for the given text String, and sort them.
   *
   * The purpose of sorting is that index(i) just returns the index associated
   * with suffixes[i].
   */
  public SuffixArray(String text) {
    int textLength = text.length();

    suffixes = new Suffix[textLength];
    for (int i = 0; i < textLength; i++) {
      suffixes[i] = new Suffix(text, i);
    }

    Arrays.sort(suffixes);
  }

  /**
   * This nested class represents a suffix of a text string.
   *
   * A Suffix has two instance variables:
   *   - a String reference to the text string
   *   - an int index of its first charecter
   *
   * It also provides four utility methods, namely length(), charAt(i),
   * toString(), and compareTo().
   */
  private static class Suffix implements Comparable<Suffix> {
    /**
     * Reference to text string.
     */
    private final String text;

    /**
     * Index to suffix's first char.
     */
    private final int index;

    private Suffix(String text, int index) {
      this.text  = text;
      this.index = index;
    }

    private int length() {
      return text.length() - index;
    }

    private char charAt(int i) {
      return text.charAt(index + i);
    }

    public int compareTo(Suffix that) {
      if (this == that) {
        return 0;
      }

      int length = Math.min(this.length(), that.length());
      for (int i = 0; i < length; i++) {
        if (this.charAt(i) < that.charAt(i)) {
          return -1;
        }
        if (this.charAt(i) > that.charAt(i)) {
          return 1;
        }
      }

      return this.length() - that.length();
    }

    public String toString() {
      return text.substring(index);
    }
  }

  public int indexOf(int i) {
    return suffixes[i].index;
  }

  public int length() {
    return suffixes.length;
  }
}
