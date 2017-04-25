package character;

import character.skill.Skillset;
import java.util.Random;

/**
 * Created by ClementAndreas on 18/04/17.
 */
public class Player extends Actor {
  private int level;
  private Skillset skillset;

  /**
   * Constructor Player.
   * @param playerName Nama dari Player.
   * @param mapId Kode Map dari Player.
   * @param posX Posisi Ordinat dari Player.
   * @param posY Posisi Absis dari Player.
   * @param skillset skill Set dari PlayStyle Player.
   */
  public Player(String playerName,
                int mapId,
                int posX,
                int posY,
                Skillset skillset) {
    super(playerName,mapId,posX,posY);
    strength = 100;
    health = strength * 5;
    defense = 100;
    agility  = 100;
    intelligence = 100;
    experience = 0;
    level = 1;
    this.skillset = skillset;
  }

  /**
   * Getter SkillSet Player.
   * @return skill Set dari PlayStyle Pilihan Player.
   */
  public Skillset getSkillset() {
    return skillset;
  }

  /**
   * Getter dari Level Player.
   * @return Level dari Player saat ini.
   */
  public int getLevel() {
    return level;
  }

  /**
   * Setter dari Level Player
   * @param level Level Baru Player.
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Setter dari Experience Player.
   * @param experience Experience Baru Player.
   */
  public void setExperience(int experience) {
    this.experience = experience;
  }

  /**
   * Prosedur Menambahkan Experience dan Level Player.
   * @param experience Experience yang didapat Player lewat Battle.
   */
  public void gainExp(int experience) {
    if (this.experience + experience >= getLevel() * 150) {
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
        default:
          assert false;
      }
      System.out.println("Level UP!");
    } else {
      assert (this.experience + experience < level * 150) : "Tidak Naik Level";
      setExperience(this.experience + experience);
    }
  }

  /**
   * Damage Calculator Player.
   * @param attackKey Pilihan Aksi Player.
   * @param defMonster Defense dari Monster.
   * @return nilai Damage oleh Player kepada Monster.
   */
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
        default:
          assert false;
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
        default:
          assert false;
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
        default:
          assert false;
      }
    } else {
      returnValue = agility - defMonster;
    }
    if (returnValue <= 0) {
      return 1;
    } else {
      return returnValue;
    }
  }
}
