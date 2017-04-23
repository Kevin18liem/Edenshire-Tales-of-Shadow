package Character;

/**
 * Created by ClementAndreas on 4/23/2017.
 */
public class Dragon extends Monster {
  protected String attackName;
  protected String specialAttackName;

  /**
   * Constructor Dragon.
   * @param monsterMapId Kode Map dari Monster.
   * @param monsterX Posisi Ordinat dari Monster.
   * @param monsterY Posisi Absis dari Monster.
   */
  public Dragon(int monsterMapId, int monsterX, int monsterY) {
    super("Nefarian", monsterMapId, monsterX, monsterY, 250, 50, 30, 35, 40, 1000, 'M');
    attackName = "Cleave";
    specialAttackName = "Shsdowflame Breath";
  }

  /**
   * Normal Attack Calculator Dragon.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Normal Attack Dragon.
   */
  public int attack(int defPlayer) {
    if (getAgility() > defPlayer) {
      return (getAgility() - defPlayer);
    }
    return 1;
  }

  /**
   * Special Attack Calculator Dragon.
   * @param defPlayer Defense dari Player.
   * @return nilai Damage Special Attack Dragon.
   */
  public int specialAttack(int defPlayer) {
    return getIntelligence();
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
