package Character;

import Infrastructure.Map;
import java.util.Random;

/**
 * Created by USER on 4/22/2017.
 */

public class People extends Actor implements Movement{
  private int dialogID;
  public People(String peopleName, int mapID, int posX, int posY, int dialogID) {
    super(peopleName, mapID, posX, posY);
    this.dialogID = dialogID;
  }

  public int getDialogID() {
    return dialogID;
  }

  public void setDialogID(int dialogID) {
    this.dialogID = dialogID;
  }

  public boolean isValid(char typeCell) {
    return ! (typeCell == 'x' || typeCell == 'w' || typeCell == 'a' || typeCell == 'd'
        || typeCell == 's' || typeCell == 'P' || typeCell == 'A'  || typeCell == 'M' || typeCell == '!');
  }

  public void move(Map currentMap) {
    Random direction = new Random();
    char typeCell;
    int newPosColumn;
    int newPosRow;
    int moveTo;
    newPosColumn = getActorColumn();
    newPosRow = getActorRow();
    moveTo = direction.nextInt() % 4 + 1;
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
    //currentMap.setCellType(getActorRow(),getActorColumn(),'!');
    //currentMap.setCellType(newPosRow,newPosColumn,'r');
    setActorColumn(newPosColumn);
    setActorRow(newPosRow);
  }
}
