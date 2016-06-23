import java.util.Arrays;

/**
 *
 *
 * Suffix arrays with less memory. Instead of using an array of substrings where
 * suffixes[i] referes to the ith sorted suffix, maintain an array of integers
 * so that index[i] referes to the offset of the ith sorted suffix. To compare
 * the substrings represented by a = index[i] and b = index[j], compare the
 * character s.charAt(a) against s.charAt(b), s.charAt(a+1) against
 * s.charAt(b+1), and so forth.
 */
public class SuffixArrayOptimized {

  /**
   * The input text.
   */
  private final char[] text;

  /**
   * Number of characters in this text.
   */
  private final int length;

  /**
   * index[i] refers to the offset of the ith sorted suffix.
   */
  private final int[] index;

  /**
   * Cut-off to insertion sort.
   *
   * This value may change. See the following for reference:
   *   - http://cs.stackexchange.com/a/37971
   *   - http://stackoverflow.com/a/19396786/1319249
   */
  private static final int CUT_OFF = 8;

  /**
   * Build an array of Suffix objects for the given text String, and sort them.
   *
   * @param text the input String
   */
  public SuffixArrayOptimized(String text) {
    this.text   = text.toCharArray();
    this.length = text.length();
    this.index  = new int[this.length];

    for (int i = 0; i < this.length; i++) {
      this.index[i] = i;
    }

    sort(0, this.length - 1, 0);
  }

  private void sort(int lo, int hi, int k) {
    if (hi <= lo + CUT_OFF) {
      insertionSort(lo, hi, k);
    } else {
      quickSort(lo, hi, k);
    }
  }

  private void insertionSort(int lo, int hi, int k) {

  }

  private void quickSort(int lo, int hi, int k) {

  }

  private boolean isLessThan(int i, int j, int k) {

  }

  /**
   * Swaps index[i] and index[j].
   */
  private void swap(int i, int j) {
    int tmp = index[i];
    index[i] = index[j];
    index[j] = tmp;
  }

  /**
   * Returns the length of the input text.
   *
   * @return the length of the input text
   */
  public int length() {
    return length;
  }

  /**
   * Returns the index into the original string of the ith smallest suffix.
   *
   * @param i an integer between 1 and length - 1
   * @return the index into the original string of the ith smallest suffix
   * @throws java.lang.IndexOutOfBoundsException unless
   *         0 < i <= length
   */
  public int indexOf(int i) {
    if (i < 0 || i >= length) {
      throw new IndexOutOfBoundsException();
    }

    return index[i];
  }

  /**
   * Returns the ith smallest suffix as a String. Note: this method should be
   * used primarily for debugging purposes.
   *
   * @param i an integer between 1 and length - 1
   * @return the ith smallest suffix as a String
   * @throws java.lang.IndexOutOfBoundsException unless
   *         0 < i <= length
   */
  public String selectAsString(int i) {
    if (i < 0 || i >= length) {
      throw new IndexOutOfBoundsException();
    }

    // String(char[] value, int offset, int count)
    return new String(text, index[i], length - index[i]);
  }

  /**
   * Returns the length of the longest common prefix of the ith smallest suffix
   * and the i-1st smallest suffix.
   *
   * @param i an integer between 1 and length - 1
   * @return the length of the longest common prefix of the ith smallest suffix
   *         and the i-1st smallest suffix.
   * @throws java.lang.IndexOutOfBoundsException unless
   *         1 < i <= length
   */
  public int longestCommonPreffix(int i) {
    if (i < 1 || i >= length) {
      throw new IndexOutOfBoundsException();
    }

    return longestCommonPreffix(index[i], index[i-1]);
  }

  /**
   * Returns the longest common prefix of text[i..length) and text[j..length)
   */
  private int longestCommonPreffix(int i, int j) {
    int size = 0;

    while (i < length && j < length) {
      if (text[i] != text[j]) {
        return size;
      }

      i++;
      j++;
      size++;
    }

    return size;
  }

  /**
   * Returns the number of suffixes strictly less that the specified key.
   *
   * @param key the query string
   * @return the number of suffixes strictly less than key
   */
  public int rank(String key) {
  }

  /**
   * Compares key string to this suffix.
   *
   * Optimization idea:
   * Let lo and hi denote the left and right endpoints of the current search
   * interval. Let lcpLo denote the lcp of the query string and suffixes[lo] and
   * let lcpHi denote the lcp of the query string and suffixes[hi]. Then, when
   * comparing the query string to suffixes[mid], we only need to compare the
   * characters starting at lcp = min(lcpLo, lcpHi) because all of the suffixes
   * in the search interval have the same first lcp characters.
   */
  private int compare(String key, Suffix suffix) {
  }
}
