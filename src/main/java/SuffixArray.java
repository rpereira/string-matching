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

    /**
     * Returns the length of this Suffix.
     */
    private int length() {
      return text.length() - index;
    }

    /**
     * Returns the ith character in the suffix.
     */
    private char charAt(int i) {
      return text.charAt(index + i);
    }

    /**
     * Returns a String representation of the suffix.
     */
    public String toString() {
      return text.substring(index);
    }

    /**
     * Compares two suffixes, for use in sorting.
     */
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
  }

  /**
   * Returns the length of the input text.
   *
   * @return the length of the input text
   */
  public int length() {
    return suffixes.length;
  }

  /**
   * Returns the index into the original string of the ith smallest suffix.
   *
   * @param i an integer between 1 and suffixes.length - 1
   * @return the index into the original string of the ith smallest suffix
   * @throws java.lang.IndexOutOfBoundsException unless
   *         0 < i <= suffixes.length
   */
  public int indexOf(int i) {
    if (i < 0 || i >= suffixes.length) {
      throw new IndexOutOfBoundsException();
    }

    return suffixes[i].index;
  }

  /**
   * Returns the ith smallest suffix as a String.
   *
   * @param i an integer between 1 and suffixes.length - 1
   * @return the ith smallest suffix as a String
   * @throws java.lang.IndexOutOfBoundsException unless
   *         0 < i <= suffixes.length
   */
  public String selectAsString(int i) {
    if (i < 0 || i >= suffixes.length) {
      throw new IndexOutOfBoundsException();
    }

    return suffixes[i].toString();
  }

  /**
   * Returns the length of the longest common prefix of the ith smallest suffix
   * and the i-1st smallest suffix.
   *
   * @param i an integer between 1 and suffixes.length - 1
   * @return the length of the longest common prefix of the ith smallest suffix
   *         and the i-1st smallest suffix.
   * @throws java.lang.IndexOutOfBoundsException unless
   *         1 < i <= suffixes.length
   */
  public int longestCommonPreffix(int i) {
    if (i < 1 || i >= suffixes.length) {
      throw new IndexOutOfBoundsException();
    }

    return _longestCommonPreffix(suffixes[i], suffixes[i-1]);
  }

  /**
   * Find the longest common prefix of the two specified suffixes.
   *
   * This is a brute-force solution and takes time proportional to the length of
   * the match.
   */
  private int _longestCommonPreffix(Suffix s, Suffix t) {
    int length = Math.min(s.length(), t.length());

    for (int i = 0; i < length; i++) {
      if (s.charAt(i) != t.charAt(i)) {
        return i;
      }
    }

    return length;
  }

  /**
   * Returns the number of suffixes strictly less that the specified key.
   *
   * @param key the query string
   * @return the number of suffixes strictly less than key
   */
  public int rank(String key) {
    int lo = 0;
    int hi = suffixes.length - 1;

    while (lo <= hi) {
      // Key is in suffixes[lo..hi] or not present.
      int mid = lo + (hi - lo) / 2;
      int cmp = _compare(key, suffixes[mid]);

      if (cmp < 0) {
        hi = mid - 1;
      } else if (cmp > 0) {
        lo = mid + 1;
      } else {
        return mid;
      }
    }

    return lo;
  }

  /**
   * Compares key string to this suffix.
   */
  private int _compare(String key, Suffix suffix) {
    int length = Math.min(key.length(), suffix.length());

    for (int i = 0; i < length; i++) {
      if (key.charAt(i) < suffix.charAt(i)) {
        return -1;
      }

      if (key.charAt(i) > suffix.charAt(i)) {
        return 1;
      }
    }

    return key.length() - suffix.length();
  }
}
