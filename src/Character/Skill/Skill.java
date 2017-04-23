package Character.Skill;

/**
 * Created by edwin on 18/04/17.
 */
public class Skill {
  private String skillName;
  private String skillDesc;
  private boolean Unlocked;

  public Skill(String skillName, String skillDesc) {
    this.skillName = skillName;
    this.skillDesc = skillDesc;
    Unlocked = false;

  }

  public String getSkillName() {
    return skillName;
  }

  public String getSkillDesc() {
    return skillDesc;
  }

  public boolean isUnlocked() {
    return Unlocked;
  }

  public void unlock() {
    Unlocked = true;
  }
}
