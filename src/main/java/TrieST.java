import java.util.LinkedList;
import java.util.Queue;

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
 *
 * In the worst case, the put(), contains(), delete(), and longestPrefixOf()
 * methods take time proportional to the length of the key. The size() and
 * isEmpty() methods take constant time. The object's construction takes
 * constant time.
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
  private void increaseSize() {
    size++;
  }

  /**
   * Decreases by one the size of this symbol table.
   */
  private void decreaseSize() {
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
   *         table; null otherwise
   * @throws NullPointerException if key is null
   */
  @SuppressWarnings("unchecked")
  public Value get(String key) {
    Node x = get(root, key, DISTANCE_FROM_FIRST_CHAR);

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
  private Node get(Node x, String key, int distance) {
    // Search miss
    if (x == null) {
      return null;
    }

    // Search hit
    if (distance == key.length()) {
      return x;
    }

    char c = key.charAt(distance);
    return get(x.next[c], key, distance + 1);
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

    root = put(root, key, value, DISTANCE_FROM_FIRST_CHAR);
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
  private Node put(Node x, String key, Value value, int distance) {
    if (x == null) {
      x = new Node();
    }

    if (distance == key.length()) {
      if (x.value == null) {
        increaseSize();
      }
      x.value = value;
      return x;
    }

    char c = key.charAt(distance);
    x.next[c] = put(x.next[c], key, value, distance + 1);

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
    root = delete(root, key, DISTANCE_FROM_FIRST_CHAR);
  }

  /*
   * The first step is to find the node that corresponds to the given key and
   * then set its associated value to null.
   * If node has null value and all null links, remove that node (and recur).
   *
   * Returns null if the value and all of the links in a node are null.
   * Otherwise, returns the node x itself.
   */
  private Node delete(Node x, String key, int distance) {
    if (x == null) {
      return null;
    }

    if (distance == key.length()) {
      if (x.value != null) {
        decreaseSize();
      }
      x.value = null;
    } else {
      char c = key.charAt(distance);
      x.next[c] = delete(x.next[c], key, distance + 1);
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

  /**
   * Returns an Iterable for all the keys in this symbol table.
   *
   * @return an Iterable for all the keys in this symbol table
   */
  public Iterable<String> keys() {
    return keysWithPrefix("");
  }

  /**
   * Returns an Iterable for all the keys in this symbol table that start with
   * the specified prefix.
   *
   * @param prefix the prefix to search for
   * @return an Iterable for the keys that start with the specified prefix
   */
  public Iterable<String> keysWithPrefix(String prefix) {
    Queue<String> queue = new LinkedList<String>();
    Node x = get(root, prefix, 0);
    collect(x, prefix, queue);
    return queue;
  }

  /**
   * This recursive implementation maintains a string with the sequence of
   * characters on the path from the root. Because charecters and keys are
   * represented implicitly in this TrieST class, and we need to iterate
   * through the keys, we need to create explicit representations of all of the
   * string keys (not just find them in the data structure). Hence, each time we
   * visit a node via a call to collect() with that node as first argument, the
   * second argument is the string associated with that node. To visit a node,
   * we add its associated string ti the queue uf uts value is not null, then
   * visit recursively all the nodes in its array of links. To create the key
   * for each call, we append the char corresponding to the link to the current
   * key.
   */
  private void collect(Node x, String prefix, Queue<String> q) {
    if (x == null) {
      return;
    }

    if (x.value != null) {
      q.add(prefix);
    }

    for (char c = 0; c < R; c++) {
      // TODO: isn't a StringBuilder more efficient?
      collect(x.next[c], prefix + c, q);
    }
  }

  /**
   * Returns the key in this symbol table that is the longest prefix of the
   * specified query, or null, if there is no such key.
   *
   * @param query the query string
   * @return the key in this symbol table that is the longest prefix of the
   *         specified query; null otherwise
   */
  public String longestPrefixOf(String query) {
    int length = search(root, query, 0, 0);
    return query.substring(0, length);
  }

  /**
   * This method keeps track of the length of the longest key found on the
   * search path (by passing it as a parameter to the recursive method, updating
   * the value whenever a node with a non-null value is encountered). The search
   * ends when the end of the string or a null link is encountered, whichever
   * comes first.
   */
  private int search(Node x, String query, int d, int length) {
    if (x == null) {
      return length;
    }

    if (x.value != null) {
      length = d;
    }

    if (d == query.length()) {
      return length;
    }

    char c = query.charAt(d);

    return search(x.next[c], query, d + 1, length);
  }

  /**
   * Returns all of the keys in the symbol table that match the specified
   * pattern, where '_' character is treated as a wildcard character.
   *
   * @param pattern the pattern to match
   * @return all of the keys in the symbol table that match pattern, as an
   *         iterable, where '_' is treated as a wildcard character.
   */
  public Iterable<String> wildKeys(String pattern) {
    Queue<String> queue = new LinkedList<String>();
    wildCollect(root, "", pattern, queue);
    return queue;
  }

  /**
   * Similar idea to the one used in collect(), but adds an argument specifying
   * the pattern to collect and also adds a test to make a recursive call for
   * all links when the pattern char is a wildcard or only for the link
   * corresponding to the pattern charecter.
   */
  private void wildCollect(Node x, String prefix, String pattern, Queue<String> q) {
    if (x == null) {
      return ;
    }

    int prefixLength  = prefix.length();
    int patternLength = pattern.length();

    if (prefixLength == patternLength && x.value != null) {
      q.add(prefix);
    }

    // No need to consider keys longer than pattern
    if (prefixLength == patternLength) {
      return;
    }

    // TODO: a prefix of type StringBuilder instead of string would be more
    // efficient. It will do for now.
    char next = pattern.charAt(prefixLength);
    for (char c = 0; c < R; c++) {
      if (next == '*' || next == c) {
        wildCollect(x.next[c], prefix + c, pattern, q);
      }
    }
  }
}
