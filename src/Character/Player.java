package Character;

/**
 * Created by edwin on 18/04/17.
 */
public class Player extends Actor {
  private int level;

  public Player(String playerName, int mapID, int posX, int posY) {
    super(playerName,mapID,posX,posY);
    health = 10;
    strength = 10;
    defense = 10;
    agility  = 10;
    intelligence = 10;
    experience = 10;

    level = 1;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void heal(int value) {
    health += value;
  }
}
