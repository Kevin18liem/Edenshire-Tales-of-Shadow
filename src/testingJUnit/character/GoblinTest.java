package character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by ClementAndreas on 4/25/2017.
 */
public class GoblinTest {
  @Test
  public void getAttackName() throws Exception {
    Goblin goblinTest = new Goblin(0,0,0);
    assertEquals("Slash",goblinTest.getAttackName());
  }

  @Test
  public void getSpecialAttackName() throws Exception {
    Goblin goblinTest = new Goblin(0,0,0);
    assertEquals("Pounce", goblinTest.getSpecialAttackName());
  }

  @Test
  public void attack() throws Exception {
    Goblin goblinTest = new Goblin(0,0,0);
    assertEquals(1,goblinTest.attack(5));
  }

  @Test
  public void specialAttack() throws Exception {
    Goblin goblinTest = new Goblin(0,0,0);
    assertEquals(2,goblinTest.specialAttack(4));
  }
}