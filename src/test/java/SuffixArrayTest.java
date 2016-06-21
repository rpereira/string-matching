import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class SuffixArrayTest {
  @Test
  public void testSuffixArray() {
    String text = "ABRACADABRA!";
    SuffixArray suffix = new SuffixArray(text);

    assertNotNull("should not be null", suffix);
  }
}
