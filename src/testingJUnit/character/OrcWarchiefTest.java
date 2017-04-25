package character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by ClementAndreas on 4/26/2017.
 */
public class OrcWarchiefTest {

  @Test
  public void getAttackName() throws Exception {
    OrcWarchief orcWarchiefTest = new OrcWarchief(0,0,0);
    assertEquals("Tackle", orcWarchiefTest.getAttackName());
  }

  @Test
  public void getSpecialAttackName() throws Exception {
    OrcWarchief orcWarchiefTest = new OrcWarchief(0,0,0);
    assertEquals("Blood Lust", orcWarchiefTest.getSpecialAttackName());
  }

  @Test
  public void attack() throws Exception {
    OrcWarchief orcWarchiefTest = new OrcWarchief(0,0,0);
    assertEquals(1, orcWarchiefTest.attack(10));
  }

  @Test
  public void specialAttack() throws Exception {
    OrcWarchief orcWarchiefTest = new OrcWarchief(0,0,0);
    assertEquals(10, orcWarchiefTest.specialAttack(10));
  }
}