import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
}
