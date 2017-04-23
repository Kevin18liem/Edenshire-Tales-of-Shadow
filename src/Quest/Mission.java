package Quest;

import java.util.Vector;

/**
 * Created by edwin on 23/04/17.
 */
public class Mission {
  private String missionName;
  private String type;
  private String instruction;
  private String monster;
  private Vector<Integer> actorId;
  private boolean isDone;
  private int count;
  private int target;

  public Mission(String missionName,String type,String instruction,Vector<Integer> actorId,int target,String actorName) {
    this.missionName = missionName;
    this.type = type;
    this.instruction = instruction;
    this.actorId = actorId;
    this.monster = actorName;
    isDone = false;
    this.target = target;
    count = 0;
  }

  public String getInstruction() {
    return instruction;
  }

  public String getType() {
    return type;
  }

  public Vector<Integer> getActorId() {
    return actorId;
  }

  public String getMissionName() {
    return missionName;
  }

  public String getMonster() {
    return monster;
  }

  public int getTarget() {
    return target;
  }

  public int getCount() {
    return count;
  }

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean done) {
    isDone = done;
  }

  public void addCount() {
    count++;
  }
}
