import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TrieSTTest {
  private static int DEFAULT_TRIE_ST_SIZE = 8;

  private TrieST<Integer> buildDefaultTrieST() {
    TrieST<Integer> st = new TrieST<Integer>();

    st.put("peter", 0);
    st.put("piper", 1);
    st.put("picked", 2);
    st.put("a", 3);
    st.put("peck", 4);
    st.put("of", 5);
    st.put("pickled", 6);
    st.put("peppers", 7);

    return st;
  }

  @Test
  public void testTrieST() {
    TrieST<Integer> st = new TrieST<Integer>();
    assertNotNull(st);
  }

  @Test
  public void testSizeOfEmptyST() {
    TrieST<Integer> st = new TrieST<Integer>();
    assertEquals(0, st.size());
  }

  @Test
  public void testSize() {
    TrieST<Integer> st = buildDefaultTrieST();
    assertEquals(DEFAULT_TRIE_ST_SIZE, st.size());
  }

  @Test
  public void testIsEmpty() {
    TrieST<Integer> st = new TrieST<Integer>();
    assertTrue(st.isEmpty());
  }

  @Test
  public void testIsNotEmpty() {
    TrieST<Integer> st = buildDefaultTrieST();
    assertFalse(st.isEmpty());
  }

  @Test(expected=NullPointerException.class)
  public void testGetNullKey() {
    TrieST<Integer> st = new TrieST<Integer>();
    int value = st.get("shebang");

    assertEquals(5, value);
  }

  @Test
  public void testGetOfSingleValue() {
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", 5);

    int value = st.get("shebang");
    assertEquals(5, value);
  }

  @Test
  public void testGetOfMultipleValues() {
    TrieST<Integer> st = buildDefaultTrieST();

    int value = st.get("peter");
    assertEquals(0, value);

    value = st.get("piper");
    assertEquals(1, value);

    value = st.get("of");
    assertEquals(5, value);

    value = st.get("peck");
    assertEquals(4, value);
  }

  @Test
  public void testPutWithSingleKey() {
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", 5);

    assertFalse(st.isEmpty());
    assertEquals(1, st.size());
  }

  @Test
  public void testPutWithMultipleKeys() {
    TrieST<Integer> st = buildDefaultTrieST();

    assertFalse(st.isEmpty());
    assertEquals(DEFAULT_TRIE_ST_SIZE, st.size());
  }

  @Test(expected=IllegalArgumentException.class)
  public void testPutKeyWithNullValue() {
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", null);
  }

  @Test
  public void testContains() {
    TrieST<Integer> st = buildDefaultTrieST();

    assertFalse(st.contains("quicksort"));
    assertTrue(st.contains("peter"));
  }

  @Test
  public void testDeleteSingleKey() {
    TrieST<Integer> st = buildDefaultTrieST();

    st.delete("peter");
    assertEquals(DEFAULT_TRIE_ST_SIZE - 1, st.size());
    assertNull(st.get("peter"));
  }

  @Test
  public void testDeleteMultipleKeys() {
    TrieST<Integer> st = buildDefaultTrieST();

    st.delete("peter");
    st.delete("a");
    st.delete("of");
    assertEquals(DEFAULT_TRIE_ST_SIZE - 3, st.size());
    assertNull(st.get("peter"));
    assertNull(st.get("a"));
    assertNull(st.get("of"));
  }

  @Test
  public void testKeysWithPrefix() {
    TrieST<Integer> st = buildDefaultTrieST();

    // Basic idea for unit test this: Since keysWithPrefix() returns an
    // Iterable<String>, we iterate it and add each element to a LinkedList.
    // Then we create a linkedList with the expected elements (also in the
    // expected order). Finally, we assert that both objects are the same.
    // Overkill? Maybe, but at the time couldn't think of a better approach.
    Iterable<String> keysIter = st.keysWithPrefix("pe");
    Queue<String> keys = new LinkedList<String>();
    Queue<String> expectedKeys = new LinkedList<String>();

    expectedKeys.add("peck");
    expectedKeys.add("peppers");
    expectedKeys.add("peter");

    for (String key : keysIter) {
      keys.add(key);
    }

    assertEquals(expectedKeys, keys);
  }

  @Test
  public void testKeysWithEmptyStringAsPrefix() {
    TrieST<Integer> st = new TrieST<Integer>();

    st.put("peter", 0);
    st.put("piper", 1);
    st.put("picked", 2);

    Iterable<String> keysIter = st.keysWithPrefix("");
    Queue<String> keys = new LinkedList<String>();
    Queue<String> expectedKeys = new LinkedList<String>();

    expectedKeys.add("peter");
    expectedKeys.add("picked");
    expectedKeys.add("piper");

    for (String key : keysIter) {
      keys.add(key);
    }

    // Specifying an empty string should return all keys
    assertEquals(expectedKeys, keys);
  }

  // TODO: Implement this
  @Test
  public void testKeys() {
  }

  @Test
  public void testLongestPrefixOf() {
    TrieST<Integer> st = buildDefaultTrieST();
    assertEquals("peck", st.longestPrefixOf("peckleton"));
  }

  @Test
  public void testLongestPrefixOfEmptyString() {
    TrieST<Integer> st = buildDefaultTrieST();
    assertEquals("", st.longestPrefixOf(""));
  }

  @Test
  public void testNonexistentLongestPrefixOf() {
    TrieST<Integer> st = buildDefaultTrieST();
    assertEquals("", st.longestPrefixOf("quicksort"));
  }

  @Test
  public void testWildKeys() {
    TrieST<Integer> st = buildDefaultTrieST();
    Iterable<String> keysIter = st.wildKeys("p**er");
    Queue<String> keys = new LinkedList<String>();
    Queue<String> expectedKeys = new LinkedList<String>();

    expectedKeys.add("peter");
    expectedKeys.add("piper");

    for (String key : keysIter) {
      keys.add(key);
    }

    // Specifying an empty string should return all keys
    assertEquals(expectedKeys, keys);
  }
}
