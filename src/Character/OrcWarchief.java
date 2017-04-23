package Character;

/**
 * Created by ClementAndreas on 4/23/2017.
 */
public class OrcWarchief extends Monster {
  protected String attackName;
  protected String specialAttackName;

  /**
   * Constructor Orc Warchief.
   * @param monsterMapId Kode Map dari Monster.
   * @param monsterX Posisi Ordinat dari Monster.
   * @param monsterY Posisi Absis dari Monster.
   */
  public OrcWarchief(int monsterMapId, int monsterX, int monsterY) {
    super("Orc Warchief", monsterMapId, monsterX, monsterY, 100, 20, 10, 10, 5, 100, 'M');
    attackName = "Tackle";
    specialAttackName = "Blood Lust";
  }

  /**
   * Normal Attack Calculator Orc Warchief.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Normal Attack Orc Warchief.
   */
  public int attack(int defPlayer) {
    if (getAgility() > defPlayer) {
      return (getAgility() - defPlayer);
    }
    return 1;
  }

  /**
   * Special Attack Calculator Orc Warchief.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Special Attack Orc Warchief.
   */
  public int specialAttack(int defPlayer) {
    return (getAgility());
  }

  /**
   * Getter Nama Normal Attack.
   * @return nama Normal Attack.
   */
  public String getAttackName() {
    return attackName;
  }

  /**
   * Getter Nama Special Attack
   * @return nama Special Attack.
   */
  public String getSpecialAttackName() {
    return specialAttackName;
  }
}
