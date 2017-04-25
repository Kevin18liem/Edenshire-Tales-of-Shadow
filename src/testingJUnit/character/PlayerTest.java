package character;

import static org.junit.Assert.assertEquals;

import character.skill.Skillset;
import org.junit.Test;

/**
 * Created by ClementAndreas on 4/26/2017.
 */
public class PlayerTest {
  @Test
  public void getSkillset() throws Exception {
    Skillset skillsetTest = new Skillset("Warrior");
    Player playerTest = new Player("Clement", 0,0,0, skillsetTest);
    assertEquals(skillsetTest, playerTest.getSkillset());
  }

  @Test
  public void getLevel() throws Exception {
    Skillset skillsetTest = new Skillset("Rogue");
    Player playerTest = new Player("Clement",0,0,0, skillsetTest);
    assertEquals(1, playerTest.getLevel());
  }

  @Test
  public void setLevel() throws Exception {
    Skillset skillsetTest = new Skillset("Mage");
    Player playerTest = new Player("Clement", 0,0,0, skillsetTest);
    playerTest.setLevel(5);
    assertEquals(5, playerTest.getLevel());
  }

  @Test
  public void setExperience() throws Exception {
    Skillset skillsetTest = new Skillset("Warrior");
    Player playerTest = new Player("Clement", 0,0,0, skillsetTest);
    playerTest.setExperience(100);
    assertEquals(100, playerTest.getExperience());
  }

  @Test
  public void gainExp() throws Exception {
    Skillset skillsetTest = new Skillset("Rogue");
    Player playerTest = new Player("Clement", 0,0,0, skillsetTest);
    playerTest.gainExp(150);
    assertEquals(2, playerTest.getLevel());
  }

  @Test
  public void attack() throws Exception {
    Skillset skillsetTest = new Skillset("Mage");
    Player playerTest = new Player("Clement", 0,0,0, skillsetTest);
    assertEquals(1, playerTest.attack('Q', 100));
  }

}