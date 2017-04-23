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

  /**
   * Constructor Mission.
   * @param missionName Nama dari Mission.
   * @param type Tipe dari Mission.
   * @param instruction Instruksi dari Mission.
   * @param actorId ID monster yang harus dikalahkan.
   * @param target NPC yang akan terpengaruh oleh quest.
   * @param actorName Nama Monster yang harus dikalahkan.
   */
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

  /**
   * Getter Mission Instruction.
   * @return Instruksi dari Mission.
   */
  public String getInstruction() {
    return instruction;
  }

  /**
   * Getter Mission Type.
   * @return Tipe dari Mission.
   */
  public String getType() {
    return type;
  }

  /**
   * Getter ActorId.
   * @return Kumpulan Id Actor.
   */
  public Vector<Integer> getActorId() {
    return actorId;
  }

  /**
   * Getter Mission Name.
   * @return Nama dari Mission.
   */
  public String getMissionName() {
    return missionName;
  }

  /**
   * Getter Monster Name.
   * @return Nama dari Monster.
   */
  public String getMonster() {
    return monster;
  }

  /**
   * Getter Target Amount.
   * @return jumlah Target yang diminta.
   */
  public int getTarget() {
    return target;
  }

  /**
   * Getter Count.
   * @return jumlah yang telah dilakukan.
   */
  public int getCount() {
    return count;
  }

  /**
   * Getter isDone.
   * @return nilai boolean apakah Mission sudah selesai.
   */
  public boolean isDone() {
    return isDone;
  }

  /**
   * Setter Done.
   * @param done nilai boolean yang akan dimasukkan.
   */
  public void setDone(boolean done) {
    isDone = done;
  }

  /**
   * Setter Count.
   */
  public void addCount() {
    count++;
  }
}
