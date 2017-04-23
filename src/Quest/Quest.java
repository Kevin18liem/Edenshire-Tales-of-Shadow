package Quest;

import java.util.Vector;

/**
 * Created by USER on 4/19/2017.
 */
public class Quest {
  private String questName;
  private Vector<Mission> missions;
  private int currentMission;
  private boolean isActive;
  private boolean isComplete;

  /**
   * Constructor Quest.
   * @param questName Nama dari Quest.
   * @param missions Kumpulan Mission dari Quest.
   */
  public Quest(String questName,Vector<Mission> missions) {
    this.questName = questName;
    this.missions = missions;
    isActive = false;
    isComplete = false;
    currentMission = 0;
  }

  /**
   * Getter Nama Quest.
   * @return Nama dari Quest.
   */
  public String getQuestName() {
    return questName;
  }

  /**
   * Getter isComplete.
   * @return nilai boolean apakah Quest sudah diselesaikan.
   */
  public boolean isComplete() {
    return isComplete;
  }

  /**
   * Getter isActive.
   * @return nilai boolean apakah Quest sudah aktif.
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * Getter Mission dari Quest.
   * @return Kumpulan Mission dari Quest.
   */
  public Mission getCurrentMission() {
    return missions.get(currentMission);
  }

  /**
   * Prosedur Mengaktifkan Quest.
   */
  public void startQuest() {
    isActive = true;
  }

  /**
   * Prosedur Penanda Perkembangan Quest.
   */
  public void progressQuest() {
    missions.get(currentMission).addCount();
    if (missions.get(currentMission).getTarget() == missions.get(currentMission).getCount()) {
      if (currentMission + 1 == missions.size()) {
        isActive = false;
        isComplete = true;
      } else {
        currentMission = currentMission + 1;
      }
    }
  }
}
