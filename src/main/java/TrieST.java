/**
 * The TrieST class holds an symbol table of key-value pairs, with string keys
 * and generic values. Implements all of the basic tree operations, including
 * size, isEmpty, retrieve, contains, insert, and delete.
 *
 * By design, this implementation uses the convention that values cannot be
 * null. Furthermore, when associating a value with an already existing key, the
 * old value is replaced with the new (given) value.
 *
 * This implementation uses a 256-way trie.
 */
public class TrieST<Value> {

  /**
   * Extendeed ASCII
   */
  private static short R = 256;

  /**
   * The root of the trie
   */
  private Node root;

  /**
   * The size of this symbol table (the number of key-value pairs it contains).
   */
  private int size;

  /**
   * Distance means the number of letters separating the first and the current
   * letters of a word (key) in this symbol table.
   */
  private static int DISTANCE_FROM_FIRST_CHAR = 0;

  /**
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
    *
    * By Java's default, int size will be initialized to 0.
    */
    public TrieST() { }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
      return size;
    }

    /**
     * Increases by one the size of this symbol table.
     */
    private void _increaseSize() {
      size++;
    }

    /**
     * Decreases by one the size of this symbol table.
     */
    private void _decreaseSize() {
      size--;
    }

    /**
     * Returns true if this symbol table contains no key-value pairs.
     *
     * @return true if this symbol table contains no key-value pairs
     */
    public boolean isEmpty() {
      return size == 0;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if it is in this symbol
               table; null otherwise
     * @throws NullPointerException if key is null
     */
    @SuppressWarnings("unchecked")
    public Value get(String key) {
      Node x = _get(root, key, DISTANCE_FROM_FIRST_CHAR);

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
     * Returns true if this symbol table contains the specified key.
     *
     * @param key element whose presence in this symbol table is to be tested
     * @return true if this symbol table contains the specified key
     */
    public boolean contains(String key) {
      return get(key) != null;
    }

    /**
     * Inserts the key-value pair into this symbol table.
     *
     * If the key is already present in this symbol table, its old value will be
     * replaced with the given new value.
     *
     * @param key the key
     * @param value the value
     * @throws IllegalArgumentException if the specified value is null
     */
    public void put(String key, Value value) {
      if (value == null) {
        throw new IllegalArgumentException("Value cannot be null");
      }

      root = _put(root, key, value, DISTANCE_FROM_FIRST_CHAR);
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

    /**
     * Deletes the specified key (and its associated value) from this symbol
     * table if the key is present.
     *
     * @param key the key to remove
     */
    public void delete(String key) {
      // TODO: handle null key? maybe throw NullPointerException ???
      root = _delete(root, key, DISTANCE_FROM_FIRST_CHAR);
    }

    /*
     * The first step is to find the node that corresponds to the given key and
     * then set its associated value to null.
     * If node has null value and all null links, remove that node (and recur).
     *
     * Returns null if the value and all of the links in a node are null.
     * Otherwise, returns the node x itself.
     */
    private Node _delete(Node x, String key, int distance) {
      if (x == null) {
        return null;
      }

      if (distance == key.length()) {
        if (x.value != null) {
          _decreaseSize();
        }
        x.value = null;
      } else {
        char c = key.charAt(distance);
        x.next[c] = _delete(x.next[c], key, distance + 1);
      }

      // Remove the subtree rooted at x if it is completely empty
      if (x.value != null) {
        return x;
      }

      for (short c = 0; c < R; c++) {
        if (x.next[c] != null) {
          return x;
        }
      }

      return null;
    }
}
