package character.dialogue;

import java.util.Vector;

/**
 * Created by USER on 4/19/2017.
 */
public class Dialogue {
  private Vector<String> dialogues;

  /**
   * Constructor Dialog.
   * @param dialogues Kumpulan Dialog NPC.
   */
  public Dialogue(Vector<String> dialogues) {
    this.dialogues = dialogues;
  }

  /**
   * Getter Dialog.
   * @return Kumpulan Dialog NPC.
   */
  public Vector<String> getDialogues() {
    return dialogues;
  }

  /**
   * Getter Count Dialog.
   * @return Count dari Dialog NPC.
   */
  public int getDialogueSize() {
    return dialogues.size();
  }

  /**
   * Setter Dialog
   * @param dialogues Dialog baru NPC.
   */
  public void setDialogues(Vector<String> dialogues) {
    this.dialogues = dialogues;
  }
}
