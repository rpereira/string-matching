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

  @Test(expected=IndexOutOfBoundsException.class)
  public void testLongestCommonPreffixIndexOutOfBounds() {
    SuffixArray suffix = buildDefaultSuffix();
    suffix.longestCommonPreffix(0);
  }

  @Test
  public void testLongestCommonPreffix() {
    SuffixArray suffix = buildDefaultSuffix();

    assertEquals(0, suffix.longestCommonPreffix(1));
    assertEquals(1, suffix.longestCommonPreffix(2));
    assertEquals(4, suffix.longestCommonPreffix(3));
    assertEquals(1, suffix.longestCommonPreffix(4));
    assertEquals(1, suffix.longestCommonPreffix(5));
    assertEquals(0, suffix.longestCommonPreffix(6));
    assertEquals(3, suffix.longestCommonPreffix(7));
    assertEquals(0, suffix.longestCommonPreffix(8));
    assertEquals(0, suffix.longestCommonPreffix(9));
    assertEquals(0, suffix.longestCommonPreffix(10));
    assertEquals(2, suffix.longestCommonPreffix(11));
  }
}
