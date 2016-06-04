public class TrieST<Value> {

  /*
   * Extendeed ASCII
   */
  private static int R = 256;

  /*
   * The root of the trie
   */
  private Node root;

  /*
   * The number of keys in this trie
   */
  private int size;

  /*
   * R-way trie node
   */
  private static class Node {
    private Object value;
    private Node[] next = new Node[R];
  }

   /**
    * Initializes an empty string symbol table.
    */
    public TrieST() { }

    /**
     * Returns the number of key-value pairs in this symbol table.
     */
    public int size() {
      return this.size;
    }
    }
}
