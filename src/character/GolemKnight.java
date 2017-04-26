package character;

/**
 * Created by ClementAndreas.
 */
public class GolemKnight extends Monster {
  protected String attackName;
  protected String specialAttackName;

  /**
   * Constructor Golem Knight.
   * @param monsterMapId Kode Map dari Monster.
   * @param monsterX Posisi Ordinat dari Player.
   * @param monsterY Posisi Absis dari Player.
   */
  public GolemKnight(int monsterMapId, int monsterX, int monsterY) {
    super("Golem Knight", monsterMapId, monsterX, monsterY, 1500, 300, 100,  75, 50, 5000, 'B');
    attackName = "Kor'kron Annihilator";
    specialAttackName = "Echo Slam";
  }

  /**
   * Normal Atack Calculator Golem Knight.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Normal Attack Golem Knight.
   */
  public int attack(int defPlayer) {
    if (getAgility() > defPlayer) {
      return 2 * (getAgility() - defPlayer);
    }
    assert (getAgility() - defPlayer <= 0) : "Defense Player > Agility Monster";
    return 1;
  }

  /**
   * Special Attack Calculator Golem Knight.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Special Attack Golem Knight.
   */
  public int specialAttack(int defPlayer) {
    return 4 * getAgility();
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
