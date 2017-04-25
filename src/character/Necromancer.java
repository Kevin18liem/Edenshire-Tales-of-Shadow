package character;

/**
 * Created by ClementAndreas on 4/23/2017.
 */
public class Necromancer extends Monster {
  protected String attackName;
  protected String specialAttackName;

  /**
   * Constructor Necromancer.
   * @param monsterMapId Kode Map dari Monster.
   * @param monsterX Posisi Ordinat dari Monster.
   * @param monsterY Posisi Absis dari Monster.
   */
  public Necromancer(int monsterMapId, int monsterX, int monsterY) {
    super("Necromancer", monsterMapId, monsterX, monsterY, 150, 30, 15, 8, 25, 200, 'M');
    attackName = "Siphon's Soul";
    specialAttackName = "Twisting Nether";
  }

  /**
   * Normal Damage Calculator Necromancer.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Normal Attack Necromancer.
   */
  public int attack(int defPlayer) {
    if (getAgility() > defPlayer) {
      return (getAgility() - defPlayer);
    }
    assert (getAgility() - defPlayer <= 0) : "Defense Player > Agility Monster";
    return 1;
  }

  /**
   * Special Damage Calculator Necromancer.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Special Attack Necromancer.
   */
  public int specialAttack(int defPlayer) {
    return (getIntelligence());
  }

  /**
   * Getter nama Normal Attack.
   * @return nama Normal Attack.
   */
  public String getAttackName() {
    return attackName;
  }

  /**
   * Getter nama Special Attack.
   * @return nama Special Attack.
   */
  public String getSpecialAttackName() {
    return specialAttackName;
  }

}
