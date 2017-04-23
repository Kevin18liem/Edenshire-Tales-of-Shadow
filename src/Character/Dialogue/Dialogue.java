package Character.Dialogue;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by USER on 4/19/2017.
 */
public class Dialogue {
  private Vector<String> dialogues;

  public Dialogue(Vector<String> dialogues) {
    this.dialogues = dialogues;
  }

  public Vector<String> getDialogues() {
    return dialogues;
  }

  public int getDialogueSize() {
    return dialogues.size();
  }
}
