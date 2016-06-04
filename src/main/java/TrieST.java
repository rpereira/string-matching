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
   * Distance means the number of letters separating the first and the current
   * letters of a word (key) in this symbol table.
   */
  private int DISTANCE_FROM_FIRST_CHAR = 0;

  /*
   * R-way trie node.
   *
   * The value in Node has to be an Object because Java does not support arrays
   * of generics. Thus, the method get() casts values back to Value.
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

    /**
     * Increases by one the size of this symbol table.
     */
    private void _increaseSize() {
      this.size++;
    }

    /**
     * Checks if this symbol table is empty.
     *
     * @return true if this symbol table is empty; false otherwise
     */
    public boolean isEmpty() {
      return size() == 0;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if it is in this symbol
               table; null otherwise
     * @throws NullPointerException if key is null
     */
    public Value get(String key) {
      Node x = _get(this.root, key, this.DISTANCE_FROM_FIRST_CHAR);

      if (x == null) {
        return null;
      }

      return (Value) x.value;
    }

    /*
     * Each node has a link corresponding to each possible char. Thus, for each
     * char, starting at the root, we follow the link associated with the dth
     * (distance) char in the key, until the last char of the key or a null link
     * is reached.
     */
    private Node _get(Node x, String key, int distance) {
      // Search miss
      if (x == null) {
        return null;
      }

      // Search hit
      if (distance == key.length()) {
        return x;
      }

      char c = key.charAt(distance);
      return _get(x.next[c], key, distance + 1);
    }

    /**
     * Inserts the key-value pair into this symbol table.
     *
     * If the key is already present in this symbol table, its old value will be
     * replaced with the given new value.
     *
     * @param key the key
     * @param value the value
     */
    public void put(String key, Value value) {
      this.root = _put(this.root, key, value, this.DISTANCE_FROM_FIRST_CHAR);
    }

    /*
     * In a trie an insertion is performed using the chars of the key as an
     * pathfinder down the trie until the last char of the key or a null link is
     * reached. At this point, one of the following scenarios applies:
     *   - A null link is found before reaching the last char of the key. This
     *     requires us to create nodes for each of the chars in the key not yet
     *     encountered and set the value of the last one with the given value;
     *   - The last char was encountered before reaching a null link and we set
     *     that node's value with the given value.
     */
    private Node _put(Node x, String key, Value value, int distance) {
      if (x == null) {
        x = new Node();
      }

      if (distance == key.length()) {
        if (x.value == null) {
          _increaseSize();
        }
        x.value = value;
        return x;
      }

      char c = key.charAt(distance);
      x.next[c] = _put(x.next[c], key, value, distance + 1);

      return x;
    }
}
