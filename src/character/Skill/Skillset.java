package character.Skill;

import java.util.Vector;

/**
 * Created by edwin on 18/04/17.
 */
public class Skillset {
  String skillsetName;
  Vector<Skill> skills;

  /**
   * Constructor SkillSet.
   * @param skillsetName Nama Skill Set berdasarkan PlayStyle.
   */
  public Skillset(String skillsetName) {
    this.skillsetName = skillsetName;
    skills = new Vector<Skill>();
  }

  /**
   * Getter dari Nama Skill Set.
   * @return Nama dari Skill Set.
   */
  public String getSkillsetName() {
    return skillsetName;
  }

  /**
   * Getter dari Skill.
   * @return Kumpulan Skill dari PlayStyle tertentu.
   */
  public Vector<Skill> getSkills() {
    return skills;
  }

  /**
   * Prosedur Menambahkan Skill pada Skill Set.
   * @param newskill Skill yang akan ditambahkan.
   */
  public void addSkill(Skill newskill) {
    skills.addElement(newskill);
  }

  /**
   * Prosedur Unlock Skill pada Skill Set PlayStyle tertentu.
   */
  public void unlockSkill() {
    int i = 0;
    while (skills.get(i).isUnlocked()) {
      i = i + 1;
    }
    skills.get(i).unlock();
  }
}