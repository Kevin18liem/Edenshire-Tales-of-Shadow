package quest;

import java.util.Vector;

/**
 * Created by Edwin.
 */
public class Quest {
  private String questName;
  private Vector<Mission> missions;
  private int currentMission;
  private boolean isActive;
  private boolean isComplete;

  /**
   * Constructor quest.
   * @param questName Nama dari quest.
   * @param missions Kumpulan Mission dari quest.
   */
  public Quest(String questName,Vector<Mission> missions) {
    this.questName = questName;
    this.missions = missions;
    isActive = false;
    isComplete = false;
    currentMission = 0;
  }

  /**
   * Getter Nama quest.
   * @return Nama dari quest.
   */
  public String getQuestName() {
    return questName;
  }

  /**
   * Getter isComplete.
   * @return nilai boolean apakah quest sudah diselesaikan.
   */
  public boolean isComplete() {
    return isComplete;
  }

  /**
   * Getter isActive.
   * @return nilai boolean apakah quest sudah aktif.
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * Getter Mission dari quest.
   * @return Mission yang sedang berlangsung.
   */
  public Mission getCurrentMission() {
    return missions.get(currentMission);
  }

  /**
   * Getter spesifik Mission dari quest.
   * @param missionId Indeks Mission.
   * @return Mission spesifik dari quest.
   */
  public Mission getMission(int missionId) {
    return missions.get(missionId);
  }

  /**
   * Prosedur Mengaktifkan quest.
   */
  public void startQuest() {
    isActive = true;
  }

  /**
   * Prosedur Penanda Perkembangan quest.
   */
  public int progressQuest() {
    int skill = -1;
    missions.get(currentMission).addCount();
    if (missions.get(currentMission).getTarget() == missions.get(currentMission).getCount()) {
      missions.get(currentMission).setDone();
      skill = missions.get(currentMission).getUnlockedSkill();
      if (currentMission + 1 == missions.size()) {
        isActive = false;
        isComplete = true;
      } else {
        currentMission = currentMission + 1;
      }
    }
    return skill;
  }
}
