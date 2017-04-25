package character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by ClementAndreas on 4/25/2017.
 */
public class DragonTest {

  @Test
  public void getAttackName() throws Exception {
    Dragon dragonTest = new Dragon(0,0,0);
    assertEquals("Cleave",dragonTest.getAttackName());
  }

  @Test
  public void getSpecialAttackName() throws Exception {
    Dragon dragonTest = new Dragon(0,0,0);
    assertEquals("Shadowflame Breath", dragonTest.getSpecialAttackName());
  }

  @Test
  public void attack() throws Exception {
    Dragon dragonTest = new Dragon(0,0,0);
    assertEquals(5,dragonTest.attack(30));
  }

  @Test
  public void specialAttack() throws Exception {
    Dragon dragonTest = new Dragon(0,0,0);
    assertEquals(40, dragonTest.specialAttack(10));
  }
}