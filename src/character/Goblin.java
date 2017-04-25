package character;

/**
 * Created by ClementAndreas on 4/23/2017.
 */
public class Goblin extends Monster {
  protected String attackName;
  protected String specialAttackName;

  /**
   * Constructor Goblin.
   * @param monsterMapId Kode Map dari Monster.
   * @param monsterX Posisi Ordinat dari Monster.
   * @param monsterY Posisi Absis dari Monster.
   */
  public Goblin(int monsterMapId, int monsterX, int monsterY) {
    super("Goblin", monsterMapId, monsterX, monsterY, 50, 10, 5, 5,2,50, 'M');
    attackName = "Slash";
    specialAttackName = "Pounce";
  }

  /**
   * Normal Attack Calculator Goblin.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Normal Attack Goblin.
   */
  public int attack(int defPlayer) {
    if (getAgility() > defPlayer) {
      return (getAgility() - defPlayer);
    }
    assert (getAgility() - defPlayer <= 0) : "Defense Player > Agility Monster";
    return 1;
  }

  /**
   * Special Attack Calculator Goblin.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Special Attack Goblin.
   */
  public int specialAttack(int defPlayer) {
    if (getAgility() > defPlayer) {
      return 2 * (getAgility() - defPlayer);
    }
    return 1;
  }

  /**
   * Getter Nama Normal Attack.
   * @return nama Normal Attack.
   */
  public String getAttackName() {
    return attackName;
  }

  /**
   * Getter Nama Special Attack.
   * @return nama Special Attack.
   */
  public String getSpecialAttackName() {
    return specialAttackName;
  }
}

// Goblin, Wolf, Dragon, Golem Knight, Orc, Necromancer