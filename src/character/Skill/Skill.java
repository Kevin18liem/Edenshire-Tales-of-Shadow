package character.Skill;

/**
 * Created by edwin on 18/04/17.
 */
public class Skill {
  private String skillName;
  private String skillDesc;
  private boolean Unlocked;

  /**
   * Constructor Skill.
   * @param skillName Nama dari Skill.
   * @param skillDesc Deskripsi dari Skill.
   */
  public Skill(String skillName, String skillDesc) {
    this.skillName = skillName;
    this.skillDesc = skillDesc;
    Unlocked = false;

  }

  /**
   * Getter dari Nama Skill.
   * @return Nama dari Skill.
   */
  public String getSkillName() {
    return skillName;
  }

  /**
   * Getter dari Deskripsi Skill.
   * @return Deskripsi dari Skill.
   */
  public String getSkillDesc() {
    return skillDesc;
  }

  /**
   * Getter dari Unlocked.
   * @return nilai boolean apakah skill sudah di unlock atau tidak.
   */
  public boolean isUnlocked() {
    return Unlocked;
  }

  /**
   * Prosedur Unlock Skill.
   */
  public void unlock() {
    Unlocked = true;
  }
}
