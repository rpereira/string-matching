import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class SuffixArrayOptimizedTest {

  private SuffixArrayOptimized buildDefaultSuffix() {
    String text = "ABRACADABRA!";
    return new SuffixArrayOptimized(text);
  }

  @Test
  public void testSuffixArrayOptimized() {
    SuffixArrayOptimized suffix = buildDefaultSuffix();
    assertNotNull("should not be null", suffix);
  }

  @Test
  public void testLength() {
    SuffixArrayOptimized suffix = buildDefaultSuffix();
    assertEquals("should have length 12", 12, suffix.length());
  }

  @Test(expected=IndexOutOfBoundsException.class)
  public void testIndexOfIndexOutOfBounds() {
    SuffixArrayOptimized suffix = buildDefaultSuffix();
    suffix.indexOf(-1);
    suffix.indexOf(suffix.length() + 1);
  }

  @Test
  public void testIndexOf() {
    SuffixArrayOptimized suffix = buildDefaultSuffix();

    assertEquals(11, suffix.indexOf(0));
    assertEquals(0,  suffix.indexOf(1));
    assertEquals(10, suffix.indexOf(2));
    assertEquals(3,  suffix.indexOf(3));
    assertEquals(7,  suffix.indexOf(4));
    assertEquals(5,  suffix.indexOf(5));
    assertEquals(6,  suffix.indexOf(6));
    assertEquals(8,  suffix.indexOf(7));
    assertEquals(9,  suffix.indexOf(8));
    assertEquals(4,  suffix.indexOf(9));
    assertEquals(2,  suffix.indexOf(10));
    assertEquals(1,  suffix.indexOf(11));
  }

  @Test(expected=IndexOutOfBoundsException.class)
  public void testSelectAsStringIndexOutOfBounds() {
    SuffixArrayOptimized suffix = buildDefaultSuffix();
    suffix.selectAsString(-1);
    suffix.selectAsString(suffix.length() + 1);
  }

  @Test
  public void testSelectAsString() {
    SuffixArrayOptimized suffix = buildDefaultSuffix();

    assertEquals("!",            suffix.selectAsString(0));
    assertEquals("ABRACADABRA!", suffix.selectAsString(1));
    assertEquals("A!",           suffix.selectAsString(2));
    assertEquals("ACADABRA!",    suffix.selectAsString(3));
    assertEquals("ABRA!",        suffix.selectAsString(4));
    assertEquals("ADABRA!",      suffix.selectAsString(5));
    assertEquals("DABRA!",       suffix.selectAsString(6));
    assertEquals("BRA!",         suffix.selectAsString(7));
    assertEquals("RA!",          suffix.selectAsString(8));
    assertEquals("CADABRA!",     suffix.selectAsString(9));
    assertEquals("RACADABRA!",   suffix.selectAsString(10));
    assertEquals("BRACADABRA!",  suffix.selectAsString(11));
  }

  @Test(expected=IndexOutOfBoundsException.class)
  public void testLongestCommonPreffixIndexOutOfBounds() {
    SuffixArrayOptimized suffix = buildDefaultSuffix();
    suffix.longestCommonPreffix(0);
  }

  @Test
  public void testLongestCommonPreffix() {
    SuffixArrayOptimized suffix = buildDefaultSuffix();

    assertEquals(0, suffix.longestCommonPreffix(1));
    assertEquals(1, suffix.longestCommonPreffix(2));
    assertEquals(1, suffix.longestCommonPreffix(3));
    assertEquals(1, suffix.longestCommonPreffix(4));
    assertEquals(1, suffix.longestCommonPreffix(5));
    assertEquals(0, suffix.longestCommonPreffix(6));
    assertEquals(0, suffix.longestCommonPreffix(7));
    assertEquals(0, suffix.longestCommonPreffix(8));
    assertEquals(0, suffix.longestCommonPreffix(9));
    assertEquals(0, suffix.longestCommonPreffix(10));
    assertEquals(0, suffix.longestCommonPreffix(11));
  }
}

