package Skill;

/**
 * Created by edwin on 18/04/17.
 */
public class Skill {
	private String skillName;
	private String skillDesc;
	private String skillEffect;
	private int effectValue;
	private boolean Unlocked;

	public Skill(String skillName, String skillDesc, String skillEffect, int effectValue) {
		this.skillName = skillName;
		this.skillDesc = skillDesc;
		this.skillEffect = skillEffect;
		this.effectValue = effectValue;
		Unlocked = false;

	}

	public String getSkillName() {
		return skillName;
	}

	public String getSkillDesc() {
		return skillDesc;
	}

	public String getSkillEffect() {
		return skillEffect;
	}

	public int getEffectValue() {
		return effectValue;
	}

	public boolean isUnlocked() {
		return Unlocked;
	}

	public void unlock() {
		Unlocked = true;
	}
}
