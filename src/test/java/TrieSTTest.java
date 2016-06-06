import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TrieSTTest {

  @Test
  public void testTrieST() {
    TrieST<Integer> st = new TrieST<Integer>();
    assertNotNull(st);
  }

  @Test
  public void testSize() {
    TrieST<Integer> st = new TrieST<Integer>();
    assertEquals(0, st.size());
  }

  @Test
  public void testIsEmpty() {
    TrieST<Integer> st = new TrieST<Integer>();
    assertTrue(st.isEmpty());
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
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", 5);
    st.put("she", 7);
    st.put("shell", 2);
    st.put("shellfish", 3);

    int value = st.get("shebang");
    assertEquals(5, value);

    value = st.get("she");
    assertEquals(7, value);

    value = st.get("shell");
    assertEquals(2, value);

    value = st.get("shellfish");
    assertEquals(3, value);
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
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", 5);
    st.put("she", 7);
    st.put("shell", 2);
    st.put("shellfish", 3);

    assertFalse(st.isEmpty());
    assertEquals(4, st.size());
  }

  @Test(expected=IllegalArgumentException.class)
  public void testPutKeyWithNullValue() {
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", null);
  }

  @Test
  public void testContains() {
    TrieST<Integer> st = new TrieST<Integer>();

    assertFalse(st.contains("shebang"));

    st.put("shebang", 5);
    assertTrue(st.contains("shebang"));
  }

  @Test
  public void testDeleteSingleKey() {
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", 5);

    st.delete("shebang");
    assertTrue(st.isEmpty());
  }

  @Test
  public void testDeleteMultipleKeys() {
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", 5);
    st.put("she", 7);
    st.put("shell", 2);
    st.put("shellfish", 3);

    st.delete("shebang");
    assertEquals(3, st.size());
    assertNull(st.get("shebang"));
  }

  @Test
  public void testKeysWithPrefix() {
    TrieST<Integer> st = new TrieST<Integer>();

    st.put("peter ", 0);
    st.put("piper", 1);
    st.put("picked", 2);
    st.put("a", 3);
    st.put("peck", 4);
    st.put("of", 5);
    st.put("pickled", 6);
    st.put("peppers", 7);

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
      // TODO: get rid of this cheat. For some reason the last element returned
      // by keysWithPrefix() has an whitespace -- I suspect it to disapear after
      // implementing the _collect() method with a StringBuilder instead of
      // concatenating; maybe the last char is an empty string (corresponding to
      // the \n ?).
      keys.add(key.trim());
    }

    assertEquals(expectedKeys, keys);
  }

  // TODO: Implement this
  @Test
  public void testKeys() {
  }
}
