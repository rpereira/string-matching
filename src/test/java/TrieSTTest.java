import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TrieSTTest {

  @Test
  public void size() {
    TrieST<Integer> st = new TrieST<Integer>();
    assertEquals(0, st.size());
  }
}
