import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class SuffixArrayTest {

  private SuffixArray buildDefaultSuffix() {
    String text = "ABRACADABRA!";
    return new SuffixArray(text);
  }

  @Test
  public void testSuffixArray() {
    SuffixArray suffix = buildDefaultSuffix();
    assertNotNull("should not be null", suffix);
  }

  @Test
  public void testLength() {
    SuffixArray suffix = buildDefaultSuffix();
    assertEquals("should have length 12", 12, suffix.length());
  }
}
