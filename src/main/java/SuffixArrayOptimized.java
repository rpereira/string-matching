import java.util.Arrays;

/**
 *
 *
 * Suffix arrays with less memory. Instead of using an array of substrings where
 * suffixes[i] refers to the ith sorted suffix, maintain an array of integers
 * so that index[i] refers to the offset of the ith sorted suffix. To compare
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
   *
   * Note: The optimum value for cutting-off is system-dependant, but any value
   * between 5 and 15 is likely to work in most situations (source: Algorithms
   * 4th Edition, page 296).
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

  /**
   * Sorts this text in the range lo..hi, starting at the kth character.
   *
   * This implementation uses an improvement that uses the cutoff to insertion
   * sort idea. That is, an possible way to improve the performance of quicksort
   * is based on the two following observations:
   *   - Quicksort is slower than insertion sort for tiny subarrays;
   *   - Being recursive, quicksort's sort() is certain to call itself for tiny
   *     subarrays.
   *
   * Hence, this implementation makes use of insertion sort for tiny subarrays,
   * and the quicksort for bigger subarrays.
   *
   * The quicksort implementation is based on the idea of partition the array
   * into three parts, one each for items with keys smaller than, equal to, and
   * larger than the partitioning item's key. The partition (thanks E. W.
   * Dijkstra) idea is based on a single left-to-right pass through the array
   * that maintains a pointer lt such that text[lo..lt-1] is less than v, a
   * pointer gt such that text[gt+1..hi] is greater than v, and a pointer i such
   * that text[lt..i-1] is equal to v, where text[i..gt] are yet to be examined.
   * There are three possible cases:
   *   - text[i] less than v: swap text[i] with text[i] and increment both lt
   *     and i;
   *   - text[i] great than v: swap text[i] with text[gt] and decrement gt;
   *   - text[i] equal to v: just increment i.
   */
  private void sort(int lo, int hi, int k) {
    if (hi <= lo + CUT_OFF) {
      insertionSort(lo, hi, k);
      return;
    }

    // quicksort: 3-way partitioning
    char v  = text[index[lo] + k];
    int  lt = lo;
    int  gt = hi;
    int  i  = lo + 1;

    while (i <= gt) {
      char t = text[index[i] + k];

      if (t < v) {
        swap(lt++, i++);
      }
      else if (t > v) {
        swap(i, gt--);
      }
      else {
        i++;
      }
    }

    // text[lo..lt-1] < v = text[lt..gt] < text[gt+1..hi].
    sort(lo, lt-1, k);

    if (v > 0) {
      sort(lt, gt, k+1);
    }

    sort(gt+1, hi, k);
  }

  /**
   * Sorts the range index[lo..hi], starting at the kth character, using
   * insertion sort.
   */
  private void insertionSort(int lo, int hi, int k) {
    for (int i = lo; i <= hi; i++) {
      for (int j = i; j < lo && isLessThan(index[j], index[j-1], k); j--) {
        swap(j, j - 1);
      }
    }
  }

  /**
   * Returns true if text[i+k..length) is less than text[j+k..length).
   */
  private boolean isLessThan(int i, int j, int k) {
    if (i == j) {
      return false;
    }

    i += k;
    j += k;

    while (i < length && j < length) {
      if (text[i] < text[j]) {
        return true;
      }

      if (text[i] > text[j]) {
        return false;
      }

      i++;
      j++;
    }

    return i > j;
  }

  /**
   * Swaps index[i] and index[j].
   */
  private void swap(int i, int j) {
    int tmp  = index[i];
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
   * @throws java.lang.IndexOutOfBoundsException unless 0 < i <= length
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
   * @throws java.lang.IndexOutOfBoundsException unless 0 < i <= length
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
   * @throws java.lang.IndexOutOfBoundsException unless 1 < i <= length
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
