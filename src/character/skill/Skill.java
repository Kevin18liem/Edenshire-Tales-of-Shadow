package character.skill;

/**
 * Created by edwin on 18/04/17.
 */
public class Skill {
  private String skillName;
  private String skillDesc;
  private boolean unlocked;

  /**
   * Constructor skill.
   * @param skillName Nama dari skill.
   * @param skillDesc Deskripsi dari skill.
   */
  public Skill(String skillName, String skillDesc) {
    this.skillName = skillName;
    this.skillDesc = skillDesc;
    unlocked = false;

  }

  /**
   * Getter dari Nama skill.
   * @return Nama dari skill.
   */
  public String getSkillName() {
    return skillName;
  }

  /**
   * Getter dari Deskripsi skill.
   * @return Deskripsi dari skill.
   */
  public String getSkillDesc() {
    return skillDesc;
  }

  /**
   * Getter dari unlocked.
   * @return nilai boolean apakah skill sudah di unlock atau tidak.
   */
  public boolean isUnlocked() {
    return unlocked;
  }

  /**
   * Prosedur Unlock skill.
   */
  public void unlock() {
    unlocked = true;
  }
}
