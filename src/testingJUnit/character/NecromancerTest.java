package character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by ClementAndreas on 4/25/2017.
 */
public class NecromancerTest {

  @Test
  public void getAttackName() throws Exception {
    Necromancer necromancerTest = new Necromancer(0,0,0);
    assertEquals("Siphon's Soul", necromancerTest.getAttackName());
  }

  @Test
  public void getSpecialAttackName() throws Exception {
    Necromancer necromancerTest = new Necromancer(0,0,0);
    assertEquals("Twisting Nether", necromancerTest.getSpecialAttackName());
  }

  @Test
  public void attack() throws Exception {
    Necromancer necromancerTest = new Necromancer(0,0,0);
    assertEquals(1, necromancerTest.attack(9));
  }

  @Test
  public void specialAttack() throws Exception {
    Necromancer necromancerTest = new Necromancer(0,0,0);
    assertEquals(25, necromancerTest.specialAttack(100));
  }
}