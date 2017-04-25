package character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by ClementAndreas on 4/26/2017.
 */
public class WolfTest {

  @Test
  public void getAttackName() throws Exception {
    Wolf wolfTest = new Wolf(0,0,0);
    assertEquals("Bite", wolfTest.getAttackName());
  }

  @Test
  public void getSpecialAttackName() throws Exception {
    Wolf wolfTest = new Wolf(0,0,0);
    assertEquals("Swift Strike", wolfTest.getSpecialAttackName());
  }

  @Test
  public void attack() throws Exception {
    Wolf wolfTest = new Wolf(0,0,0);
    assertEquals(1, wolfTest.attack(10));
  }

  @Test
  public void specialAttack() throws Exception {
    Wolf wolfTest = new Wolf(0,0,0);
    assertEquals(1, wolfTest.specialAttack(10));
  }
}