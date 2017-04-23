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

  public Quest(String questName,Vector<Mission> missions) {
    this.questName = questName;
    this.missions = missions;
    isActive = false;
    isComplete = false;
    currentMission = 0;
  }

  public String getQuestName() {
    return questName;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public boolean isActive() {
    return isActive;
  }

  public Mission getCurrentMission() {
    return missions.get(currentMission);
  }

  public void startQuest() {
    isActive = true;
  }

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
