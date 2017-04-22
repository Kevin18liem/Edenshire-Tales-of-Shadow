package Character;

import java.util.Random;
import Infrastructure.Map;
/**
 * Created by ClementAndreas on 4/22/2017.
 */
public class Monster extends Actor implements Movement{

  private int [][] currentPath;
  /**
   * Contructor Monster.
   * @param monsterName Nama Monster.
   * @param monsterMapID Kode Map Monster.
   * @param monsterX Ordinat Monster.
   * @param monsterY Absis Monster.
   * @param monsterHealth Health Point (HP) Monster.
   * @param monsterStrength Strength (STR) Monster.
   * @param monsterDefense Defense (DEF) Monster.
   * @param monsterAgility Agility (AGI) Monster.
   * @param monsterIntelligence Intelligence (INT) Monster.
   * @param monsterExperience Experience (EXP) Monster.
   */
  public Monster(String monsterName,
                 int monsterMapID,
                 int monsterX,
                 int monsterY,
                 int monsterHealth,
                 int monsterStrength,
                 int monsterDefense,
                 int monsterAgility,
                 int monsterIntelligence,
                 int monsterExperience) {
    super(monsterName, monsterMapID, monsterX, monsterY);
    health = monsterHealth;
    strength = monsterStrength;
    defense = monsterDefense;
    agility = monsterAgility;
    intelligence = monsterIntelligence;
    experience = monsterExperience;
  }

  /**
   * Prosedur Mengejar Player.
   * @param currentMap Peta yang ditempati saat ini.
   * @param posXPlayer Posisi Ordinat dari Player.
   * @param posYPlayer Posisi Absis dari Player.
   */
  public void pursue(Map currentMap, int posXPlayer, int posYPlayer) {
    currentPath = new int[currentMap.getRow()][currentMap.getColumn()];
    boolean solved;
    int i,j;
    solved = track(currentMap, posXPlayer, posYPlayer,
      actorRow, actorColumn);
//    for (i = 0; i < currentMap.getRow(); i++) {
//      for (j = 0; j < currentMap.getColumn(); j++) {
//        System.out.print(currentPath[i][j] + " ");
//        if (j == currentMap.getColumn()-1) {
//          System.out.println("\n");
//        }
//      }
//    }
//    System.out.print(currentPath.length + " " + currentPath[0].length);
  }

  /**
   * Fungsi Pengecek Jalan Valid.
   * @param currentMap Peta yang ditempati saat ini.
   * @param posXMonster Posisi Ordinat dari Monster.
   * @param posYMonster Posisi Absis dari Monster.
   * @return boolean apakah gerakan valid atau tidak.
   */
  public boolean isValid(Map currentMap, int posXMonster, int posYMonster) {
//    if (isInRange(currentMap,posXMonster,posYMonster)) {
//      System.out.println("inRange Bener");
//      if (isAvailable(currentMap,posXMonster,posYMonster)) {
//        System.out.println("isAvailable Bener");
//        if (!isPassed(posXMonster,posYMonster)) {
//          System.out.println("isPassed Bener");
//          return true;
//        }
//      }
//    }
//    return false;
    if (isInRange(currentMap,posXMonster,posYMonster)
     && isAvailable(currentMap, posXMonster, posYMonster)
     && !isPassed(posXMonster,posYMonster)) {
     return true;
   }
   return false;
  }

  /**
   * Fungsi Pengecek Jalan dapat Dilewati.
   * @param currentMap Peta yang ditempati saat ini.
   * @param posXMonster Posisi Ordinat dari Monster.
   * @param posYMonster Posisi Absis dari Monster.
   * @return boolean apakah gerakan dapat dilakukan atau tidak.
   */
  public boolean isAvailable(Map currentMap, int posXMonster, int posYMonster) {
    return currentMap.getCell(posXMonster,posYMonster).getType() != 'x' &&
      currentMap.getCell(posXMonster,posYMonster).getType() != 'w' &&
      currentMap.getCell(posXMonster,posYMonster).getType() != 'a' &&
      currentMap.getCell(posXMonster,posYMonster).getType() != 's' &&
      currentMap.getCell(posXMonster,posYMonster).getType() != 'd' &&
      currentMap.getCell(posXMonster,posYMonster).getType() != 'M';
  }

  /**
   * Fungsi Pengecek Jalan Sudah Dilewati.
   * @param posXMonster Posisi Ordinat dari Player.
   * @param posYMonster Posisi Absis dari Player.
   * @return boolean apakah jalan sudah dilewati.
   */
  public boolean isPassed(int posXMonster, int posYMonster) {
    return currentPath[posXMonster][posYMonster] == 2;
  }

  /**
   * Fungsi Pengecek Koordinat dalam Ordinat.
   * @param currentMap Peta yang ditempati saat ini.
   * @param posXMonster Posisi Ordinat dari Monster.
   * @return boolean apakah koordinat terdapat dalam baris yang valid.
   */
  public boolean inRow(Map currentMap, int posXMonster) {
    return (posXMonster >= 0 && posXMonster < currentMap.getRow());
  }

  /**
   * Fungsi Pengecek Koordinat dalam Absis.
   * @param currentMap Peta yang ditempati saat ini.
   * @param posYMonster Posisi Absis dari Monster.
   * @return boolean apakah koordinat terdapat dalam kolom yang valid.
   */
  public boolean inColumn(Map currentMap, int posYMonster) {
    return (posYMonster >= 0 && posYMonster < currentMap.getColumn());
  }

  /**
   * Fungsi Pengecek Koordinat dalam Ukuran Peta.
   * @param currentMap Peta yang ditempati saat ini.
   * @param posXMonster Posisi Ordinat dari Monster.
   * @param posYMonster Posisi Absis dari Monster.
   * @return boolean apakah posisi dari Monster terdapat dalam peta.
   */
  public boolean isInRange(Map currentMap, int posXMonster, int posYMonster) {
    return inRow(currentMap, posXMonster) && inColumn(currentMap,posYMonster);
  }

  /**
   * Fungsi Rekursif Pencari Jalan Djikstra.
   * @param currentMap Peta yang ditempati saat ini.
   * @param posXPlayer Posisi Ordinat dari Player.
   * @param posYPlayer Posisi Absis dari Player.
   * @param posXMonster Posisi Ordinat dari Monster.
   * @param posYMonster Posisi Absis dari Monster.
   * @return boolean apakah perjalanan dapat menuju tujuan.
   */
  public boolean track(Map currentMap,
                       int posXPlayer,
                       int posYPlayer,
                       int posXMonster,
                       int posYMonster) {
//    System.out.println(posXMonster + " " + posYMonster);
    if (!isValid(currentMap, posXMonster, posYMonster)) {
      return false;
    }
    if (isDestination(posXPlayer,posYPlayer,posXMonster,posYMonster)) {
      currentPath[posXMonster][posYMonster] = 3;
      return true;
    } else {
      currentPath[posXMonster][posYMonster] = 2;
    }
    if (track(currentMap,posXPlayer,posYPlayer,
      posXMonster-1,posYMonster)) {
      currentPath[posXMonster-1][posYMonster] = 3;
      return true;
    } else if (track(currentMap,posXPlayer,posYPlayer,
      posXMonster,posYMonster+1)) {
      currentPath[posXMonster][posYMonster+1] = 3;
      return true;
    } else if (track(currentMap,posXPlayer,posYPlayer,
      posXMonster+1, posYMonster)) {
      currentPath[posXMonster+1][posYMonster] = 3;
      return true;
    } else if (track(currentMap,posXPlayer,posYPlayer,
      posXMonster,posYMonster-1)) {
      currentPath[posXMonster][posYMonster-1] = 3;

      return true;
    }
    return false;
  }

  /**
   * Prosedur Bergerak.
   * @param posXPlayer Posisi Ordinat dari Player.
   * @param posYPlayer Posisi Absis dari Player.
   * @param currentMap Peta yang ditempati saat ini.
   */
  public void move(int posXPlayer, int posYPlayer, Map currentMap) {
    pursue(currentMap,posXPlayer,posYPlayer);
    System.out.println(actorRow + " " + actorColumn);
    int i,j;
    for (i = 0; i < currentMap.getRow(); i++) {
      for (j = 0; j < currentMap.getColumn(); j++) {
        System.out.print(currentPath[i][j] + " ");
        if (j == currentMap.getColumn()-1) {
          System.out.print("\n");
        }
      }
    }
    moveRandom(posXPlayer,posYPlayer,currentMap);
//    int direction;
//    Random way = new Random();
//    while (actorRow != posXPlayer || actorColumn != posYPlayer) {
//      direction = way.nextInt(4);
//      currentMap.setCellType(actorRow,actorColumn,'r');
//      if (direction == 0 && currentPath[actorRow - 1][actorColumn] == 3) {
//        actorRow = actorRow - 1;
//      } else if (direction == 1  && currentPath[actorRow][actorColumn + 1] == 3) {
//        actorColumn = actorColumn + 1;
//      } else if (direction == 2  && currentPath[actorRow + 1][actorColumn] == 3) {
//        actorRow = actorRow + 1;
//      } else if (direction == 3  && currentPath[actorRow][actorColumn - 1] == 3) {
//        actorColumn = actorColumn - 1;
//      }
//      currentMap.setCellType(actorRow,actorColumn,'A');
//    }
//    System.out.println(actorRow + " " + actorColumn);
  }

  /**
   * Prosedur Penggerak Random.
   * @param posXPlayer Posisi Ordinat dari Player.
   * @param posYPlayer Posisi Absis dari Player.
   * @param currentMap Peta yang ditempati saat ini.
   */
  public void moveRandom(int posXPlayer, int posYPlayer, Map currentMap) {
    int direction;
    Random way = new Random();
    while (actorRow != posXPlayer || actorColumn != posYPlayer) {
      direction = way.nextInt(4);
      currentMap.setCellType(actorRow,actorColumn,'r');
      if (direction == 0 && currentPath[actorRow - 1][actorColumn] == 3) {
        actorRow = actorRow - 1;
      } else if (direction == 1  && currentPath[actorRow][actorColumn + 1] == 3) {
        actorColumn = actorColumn + 1;
      } else if (direction == 2  && currentPath[actorRow + 1][actorColumn] == 3) {
        actorRow = actorRow + 1;
      } else if (direction == 3  && currentPath[actorRow][actorColumn - 1] == 3) {
        actorColumn = actorColumn - 1;
      }
      currentMap.setCellType(actorRow,actorColumn,'A');
    }
    System.out.println(actorRow + " " + actorColumn);
  }

  /**
   * Fungsi Pengecek Posisi dengan Tujuan.
   * @param posXPlayer Posisi Ordinat dari Player.
   * @param posYPlayer Posisi Absis dari Player.
   * @param posXMonster Posisi Ordinat dari Monster.
   * @param posYMonster Posisi Absis dari Monster.
   * @return boolean apakah Monster sudah berada dalam posisi yang
   * sama dengan player.
   */
  private boolean isDestination(int posXPlayer,
                                int posYPlayer,
                                int posXMonster,
                                int posYMonster) {
    return (posXMonster == posXPlayer) && (posYMonster == posYPlayer);
  }

}