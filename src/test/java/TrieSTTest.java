import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
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
  public void testKeys() {
    TrieST<Integer> st = new TrieST<Integer>();
    st.put("shebang", 5);
    st.put("she", 7);
    st.put("shell", 2);
    st.put("shellfish", 3);

    Queue<String> q = new LinkedList<String>();
  }
}
