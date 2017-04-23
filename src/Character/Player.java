package Character;

import Character.Skill.Skillset;

import java.util.Random;

/**
 * Created by ClementAndreas on 18/04/17.
 */
public class Player extends Actor {
  private int level;
  private Skillset skillset;

  public Player(String playerName, int mapID, int posX, int posY,Skillset skillset) {
    super(playerName,mapID,posX,posY);
    strength = 8;
    health = strength * 5;
    defense = 5;
    agility  = 8;
    intelligence = 8;
    experience = 0;
    level = 1;
    this.skillset = skillset;
  }

  public Skillset getSkillset() {
    return skillset;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public void gainExp(int experience) {
    if (this.experience + experience >= getLevel()*150) {
      setLevel(getLevel() + 1);
      setExperience((this.experience + experience) % ((getLevel() - 1) * 150));
      switch (skillset.getSkillsetName()) {
        case "Warrior":
          strength += 3;
          health = strength * 5;
          defense += 1;
          agility += 1;
          intelligence += 1;
          break;
        case "Rogue":
          strength += 1;
          health = strength * 5;
          defense += 1;
          agility += 3;
          intelligence += 1;
          break;
        case "Mage":
          strength += 1;
          health = strength * 5;
          defense += 1;
          agility += 3;
          intelligence += 3;
          break;
      }
      System.out.println("Level UP!");
    } else {
      setExperience(this.experience + experience);
    }
  }

  public int attack(char attackKey, int defMonster) {
    int returnValue = -1;
    if (attackKey == 'W' && skillset.getSkills().get(0).isUnlocked()) {
      switch (skillset.getSkillsetName()) {
        case "Warrior":
          returnValue = strength - defMonster;
          break;
        case "Rogue":
          returnValue = 2 * (agility - defMonster);
          break;
        case "Mage":
          returnValue = intelligence - defMonster;
          break;
      }
    } else if (attackKey == 'E' && skillset.getSkills().get(1).isUnlocked()) {
      switch (skillset.getSkillsetName()) {
        case "Warrior":
          returnValue = (2 * strength) - agility - defMonster;
          health = health - 10;
          break;
        case "Rogue":
          returnValue = agility;
          break;
        case "Mage":
          returnValue = intelligence;
          break;
      }
    } else if (attackKey == 'R' && skillset.getSkills().get(2).isUnlocked()) {
      switch (skillset.getSkillsetName()) {
        case "Warrior":
          returnValue = (2 * ((5 * strength) - health)) - defMonster;
          break;
        case "Rogue":
          Random random = new Random();
          int multiplier = random.nextInt(4) + 2;
          returnValue = (multiplier * agility) - defMonster;
          break;
        case "Mage":
          returnValue = (2 * intelligence) - defMonster;
          break;
      }
    } else {
      returnValue = agility - defMonster;
    }
    if (returnValue < 0) {
      return 1;
    } else {
      return returnValue;
    }
  }
}
