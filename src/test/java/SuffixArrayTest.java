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
  public void testIndexOfIndexOutOfBounds() {
    SuffixArray suffix = buildDefaultSuffix();
    suffix.indexOf(-1);
    suffix.indexOf(suffix.length() + 1);
  }

  @Test
  public void testIndexOf() {
    SuffixArray suffix = buildDefaultSuffix();

    assertEquals(11, suffix.indexOf(0));
    assertEquals(10, suffix.indexOf(1));
    assertEquals(7,  suffix.indexOf(2));
    assertEquals(0,  suffix.indexOf(3));
    assertEquals(3,  suffix.indexOf(4));
    assertEquals(5,  suffix.indexOf(5));
    assertEquals(8,  suffix.indexOf(6));
    assertEquals(1,  suffix.indexOf(7));
    assertEquals(4,  suffix.indexOf(8));
    assertEquals(6,  suffix.indexOf(9));
    assertEquals(9,  suffix.indexOf(10));
    assertEquals(2,  suffix.indexOf(11));
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
