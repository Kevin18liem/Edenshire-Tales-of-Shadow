package Dialogue;

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
  
  public void talk() {
    Scanner input = new Scanner(System.in);
    for(String dialogue:dialogues) {
      if (!dialogue.equals("null")) {
        System.out.println(dialogue);
        String enter = input.nextLine();
      }
    }
  }
}
