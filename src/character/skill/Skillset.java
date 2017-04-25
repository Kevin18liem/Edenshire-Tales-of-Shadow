package character.skill;

import java.util.Vector;

/**
 * Created by edwin on 18/04/17.
 */
public class Skillset {
  String skillsetName;
  Vector<Skill> skills;

  /**
   * Constructor SkillSet.
   * @param skillsetName Nama skill Set berdasarkan PlayStyle.
   */
  public Skillset(String skillsetName) {
    this.skillsetName = skillsetName;
    skills = new Vector<Skill>();
  }

  /**
   * Getter dari Nama skill Set.
   * @return Nama dari skill Set.
   */
  public String getSkillsetName() {
    return skillsetName;
  }

  /**
   * Getter dari skill.
   * @return Kumpulan skill dari PlayStyle tertentu.
   */
  public Vector<Skill> getSkills() {
    return skills;
  }

  /**
   * Prosedur Menambahkan skill pada skill Set.
   * @param newskill skill yang akan ditambahkan.
   */
  public void addSkill(Skill newskill) {
    skills.addElement(newskill);
  }

  /**
   * Prosedur Unlock skill pada skill Set PlayStyle tertentu.
   */
  public void unlockSkill() {
    int i = 0;
    while (skills.get(i).isUnlocked()) {
      i = i + 1;
    }
    skills.get(i).unlock();
  }
}