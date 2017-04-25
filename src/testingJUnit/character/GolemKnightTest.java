package character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by ClementAndreas on 4/25/2017.
 */
public class GolemKnightTest {

  @Test
  public void getAttackName() throws Exception {
    GolemKnight golemKnightTest = new GolemKnight(0,0,0);
    assertEquals("Kor'kron Annihilator", golemKnightTest.getAttackName());
  }

  @Test
  public void getSpecialAttackName() throws Exception {
    GolemKnight golemKnightTest = new GolemKnight(0,0,0);
    assertEquals("Echo Slam", golemKnightTest.getSpecialAttackName());
  }

  @Test
  public void attack() throws Exception {
    GolemKnight golemKnightTest =  new GolemKnight(0,0,0);
    assertEquals(50,golemKnightTest.attack(50));
  }

  @Test
  public void specialAttack() throws Exception {
    GolemKnight golemKnightTest = new GolemKnight(0,0,0);
    assertEquals(300,golemKnightTest.specialAttack(100));
  }
}