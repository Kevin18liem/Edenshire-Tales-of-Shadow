package Character;

/**
 * Created by ClementAndreas on 18/04/17.
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

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public void levelUp(int experience) {
    if (this.experience + experience >= getLevel()*150) {
      setLevel(getLevel() + 1);
      setExperience((this.experience + experience) % ((getLevel() - 1) * 150));
    } else {
      setExperience(this.experience + experience);
    }
  }

  public int attack(char attackKey, int defMonster) {
    if (attackKey == 'Q') {
      if (getAgility() > defMonster) {
        return (getAgility() - defMonster);
      }
    } else if (attackKey == 'W') {

    } else if (attackKey == 'E') {

    } else if (attackKey == 'R') {

    }
    return 1;
  }
}
