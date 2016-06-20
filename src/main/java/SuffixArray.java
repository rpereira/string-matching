public class SuffixArray {

  /**
   * The array of suffixes.
   */
  private Suffix[] suffixes;

  // TODO
  public SuffixArray(String text) { }

  private static class Suffix {
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
