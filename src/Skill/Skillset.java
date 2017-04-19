package Skill;

import java.util.Vector;

/**
 * Created by edwin on 18/04/17.
 */
public class Skillset {
  String skillsetName;
  Vector<Skill> skills;

  public Skillset(String skillsetName) {
    this.skillsetName = skillsetName;
    skills = new Vector<Skill>();
  }

  public String getSkillsetName() {
    return skillsetName;
  }

  public Vector<Skill> getSkills() {
    return skills;
  }

  public void addSkill(Skill newskill) {
    skills.addElement(newskill);
  }

  public void unlockSkill() {
    int skillIdx = skills.size() -1;
    skills.get(skillIdx).unlock();
  }
}