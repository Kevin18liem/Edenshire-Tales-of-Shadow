package Character;

/**
 * Created by ClementAndreas on 4/23/2017.
 */
public class Wolf extends Monster{
  protected String attackName;
  protected String specialAttackName;

  /**
   * Constructor Wolf.
   * @param monsterMapId Kode Map dari Monster.
   * @param monsterX Posisi Ordinat dari Monster.
   * @param monsterY Posisi Absis dari Monster.
   */
  public Wolf(int monsterMapId, int monsterX, int monsterY) {
    super("Wolf", monsterMapId, monsterX, monsterY, 15, 2, 3, 10, 2, 40, 'A');
    attackName = "Bite";
    specialAttackName = "Swift Strike";
  }

  /**
   * Normal Attack Calculator Wolf.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Normal Attack Wolf.
   */
  public int attack(int defPlayer) {
    if (getAgility() > defPlayer) {
      return (getAgility() - defPlayer);
    }
    return 1;
  }

  /**
   * Special Attack Calculator Wolf.
   * @param defPlayer Defense dari Player.
   * @return nilai Special Attack Wolf.
   */
  public int specialAttack(int defPlayer) {
    return attack(defPlayer);
  }

  /**
   * Getter Nama Normal Attack.
   * @return nama Normal Attack.
   */
  public String getAttackName() {
    return attackName;
  }

  /**
   * Getter dari Special Attack.
   * @return nama Special Attack.
   */
  public String getSpecialAttackName() {
    return specialAttackName;
  }
}
