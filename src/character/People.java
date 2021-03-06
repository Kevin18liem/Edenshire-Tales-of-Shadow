package character;

import character.dialogue.Dialogue;
import infrastructure.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Kevin.
 */

public class People extends Actor implements Movement {
  private Vector<Dialogue> dialogue;
  private int dialogueId;

  /**
   * Constructor NPC People.
   * @param peopleName Nama dari NPC People.
   * @param mapId Kode Map dari NPC People.
   * @param posX Posisi Ordinat dari NPC People.
   * @param posY Posisi Absis dari NPC People.
   * @param dialogue Rangkaian Dialog dari NPC People.
   */
  public People(String peopleName, int mapId, int posX, int posY, Vector<Dialogue> dialogue) {
    super(peopleName, mapId, posX, posY);
    this.dialogue = dialogue;
    dialogueId = 0;
  }

  /**
   * Getter dialog People.
   * @return Kumpulan String dialog People.
   */
  public Vector<String> getDialogueLines() {
    return dialogue.get(dialogueId).getDialogues();
  }

  /**
   * Setter ID Dialog
   * @param dialogueId ID Dialog.
   */
  public void setDialogueId(int dialogueId) {
    this.dialogueId = dialogueId;
  }

  /**
   * Fungsi Pengecek Jalan Valid.
   * @param typeCell Tipe dari Sel yang dilalui.
   * @return boolean apakah gerakan valid atau tidak.
   */
  public boolean isValid(char typeCell) {
    return ! (typeCell == 'x' || typeCell == 'w' || typeCell == 'a'
      || typeCell == 'd' || typeCell == 's' || typeCell == 'P'
      || typeCell == 'A'  || typeCell == 'M' || typeCell == '!');
  }

  /**
   * Prosedur Pencari Jalan.
   * @param currentMap Peta yang ditempati saat ini.
   */
  public void move(Map currentMap) {
    Random direction = new Random();
    char typeCell;
    int newPosColumn;
    int newPosRow;
    int moveTo;
    newPosColumn = getActorColumn();
    newPosRow = getActorRow();
    moveTo = direction.nextInt(4) + 1;
    if (moveTo == 1) {
      typeCell = currentMap.getCell(newPosRow - 1,newPosColumn).getType();
      if (isValid(typeCell)) {
        newPosRow = newPosRow - 1;
      }
    } else if (moveTo == 2) {
      typeCell = currentMap.getCell(newPosRow,newPosColumn + 1).getType();
      if (isValid(typeCell)) {
        newPosColumn = newPosColumn + 1;
      }
    } else if (moveTo == 3) {
      typeCell = currentMap.getCell(newPosRow + 1,newPosColumn).getType();
      if (isValid(typeCell)) {
        newPosRow = newPosRow + 1;
      }
    } else if (moveTo == 4) {
      typeCell = currentMap.getCell(newPosRow,newPosColumn - 1).getType();
      if (isValid(typeCell)) {
        newPosColumn = newPosColumn - 1;
      }
    }
    currentMap.setCellType(getActorRow(),getActorColumn(),'r');
    currentMap.setCellType(newPosRow,newPosColumn,'!');
    setActorColumn(newPosColumn);
    setActorRow(newPosRow);
  }

  /**
   * Prosedur Pencetak Dialog NPC People.
   */
  public void talk() {
    System.out.println();
    int i = 0;
    Scanner input = new Scanner(System.in);
    for (String lines:dialogue.get(dialogueId).getDialogues()) {
      if (!lines.equals("null")) {
        if (i % 2 == 0) {
          System.out.print(actorName + "  : ");
        } else {
          assert i % 2 != 0;
          System.out.print("You   : ");
        }
        System.out.print(lines);
        String enter = input.nextLine();
      }
      i = i + 1;
    }
    System.out.println();
  }
}
