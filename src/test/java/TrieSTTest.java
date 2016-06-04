import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
}
