package character;

import infrastructure.Map;
import java.util.Random;

/**
 * Created by ClementAndreas.
 */
public abstract class Monster extends Actor implements Movement {

  private char type;
  private int [][] currentPath;

  /**
   * Contructor Monster.
   * @param monsterName Nama Monster.
   * @param monsterMapId Kode Map Monster.
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
                 int monsterMapId,
                 int monsterX,
                 int monsterY,
                 int monsterHealth,
                 int monsterStrength,
                 int monsterDefense,
                 int monsterAgility,
                 int monsterIntelligence,
                 int monsterExperience,
                 char monsterType) {
    super(monsterName, monsterMapId, monsterX, monsterY);
    health = monsterHealth;
    strength = monsterStrength;
    defense = monsterDefense;
    agility = monsterAgility;
    intelligence = monsterIntelligence;
    experience = monsterExperience;
    type = monsterType;
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
    solved = track(currentMap, posXPlayer, posYPlayer,
      actorRow, actorColumn);
    assert solved;
  }

  /**
   * Fungsi Pengecek Jalan Valid.
   * @param currentMap Peta yang ditempati saat ini.
   * @param posXMonster Posisi Ordinat dari Monster.
   * @param posYMonster Posisi Absis dari Monster.
   * @return boolean apakah gerakan valid atau tidak.
   */
  public boolean isValid(Map currentMap, int posXMonster, int posYMonster) {
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
    return currentMap.getCell(posXMonster,posYMonster).getType() != 'x'
      && currentMap.getCell(posXMonster,posYMonster).getType() != 'w'
      && currentMap.getCell(posXMonster,posYMonster).getType() != 'a'
      && currentMap.getCell(posXMonster,posYMonster).getType() != 's'
      && currentMap.getCell(posXMonster,posYMonster).getType() != 'd'
      && currentMap.getCell(posXMonster,posYMonster).getType() != 'M'
      && currentMap.getCell(posXMonster,posYMonster).getType() != '!'
      && currentMap.getColumn() - 1 != posYMonster
      && currentMap.getRow() - 1 != posXMonster
      && posYMonster != 0 && posXMonster != 0;
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
    if (!isValid(currentMap, posXMonster, posYMonster)) {
      return false;
    }
    assert (isValid(currentMap, posXMonster, posYMonster)) : "Gerakan Tidak Valid";
    if (isDestination(posXPlayer,posYPlayer,posXMonster,posYMonster)) {
      currentPath[posXMonster][posYMonster] = 3;
      return true;
    } else {
      currentPath[posXMonster][posYMonster] = 2;
    }
    if (track(currentMap,posXPlayer,posYPlayer,
        posXMonster - 1,posYMonster)) {
      currentPath[posXMonster - 1][posYMonster] = 3;
      return true;
    } else if (track(currentMap,posXPlayer,posYPlayer,
        posXMonster, posYMonster + 1)) {
      currentPath[posXMonster][posYMonster + 1] = 3;
      return true;
    } else if (track(currentMap,posXPlayer,posYPlayer,
        posXMonster + 1, posYMonster)) {
      currentPath[posXMonster + 1][posYMonster] = 3;
      return true;
    } else if (track(currentMap,posXPlayer,posYPlayer,
        posXMonster,posYMonster - 1)) {
      currentPath[posXMonster][posYMonster - 1] = 3;
      return true;
    }
    return false;
  }

  /**
   * Fungsi Pengecek Sekitar.
   * @param posXPlayer Posisi Ordinat dari Player.
   * @param posYPlayer Posisi Absis dari Player.
   * @param currentMap Peta yang ditempati saat ini.
   * @return nilai posisi player dibandingkan monster.
   */
  public int checkSurrounding(int posXPlayer, int posYPlayer, Map currentMap) {
    if (actorRow - 1 == posXPlayer && actorColumn == posYPlayer) {
      return 1;
    } else if (actorRow == posXPlayer && actorColumn + 1 == posYPlayer) {
      return 2;
    } else if (actorRow + 1 == posXPlayer && actorColumn == posYPlayer) {
      return 3;
    } else if (actorRow == posXPlayer && actorColumn - 1 == posYPlayer) {
      return 4;
    } else if (actorRow - 1 == posXPlayer && actorColumn - 1 == posYPlayer) {
      return 5;
    } else if (actorRow - 1 == posXPlayer && actorColumn + 1 == posYPlayer) {
      return 6;
    } else if (actorRow + 1 == posXPlayer && actorColumn + 1 == posYPlayer) {
      return 7;
    } else if (actorRow + 1 == posXPlayer && actorColumn - 1 == posYPlayer) {
      return 8;
    }
    return 0;
  }

  /**
   * Prosedur Bergerak.
   * @param posXPlayer Posisi Ordinat dari Player.
   * @param posYPlayer Posisi Absis dari Player.
   * @param currentMap Peta yang ditempati saat ini.
   */
  public void moveDjikstra(int posXPlayer, int posYPlayer, Map currentMap) {
    pursue(currentMap, posXPlayer, posYPlayer);
    currentMap.setCellType(actorRow, actorColumn, 'r');
    if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 0) {
      int direction;
      Random way = new Random();
      direction = way.nextInt(4);
      if (direction == 0 && currentPath[actorRow - 1][actorColumn] == 3
          && actorRow - 1 != 0) {
        actorRow = actorRow - 1;
      }
      if (direction == 1 && currentPath[actorRow][actorColumn + 1] == 3
          && actorColumn + 1 != currentMap.getColumn() - 1) {
        actorColumn = actorColumn + 1;
      }
      if (direction == 2 && currentPath[actorRow + 1][actorColumn] == 3
          && actorRow + 1 != currentMap.getRow() - 1) {
        actorRow = actorRow + 1;
      }
      if (direction == 3 && currentPath[actorRow][actorColumn - 1] == 3
          && actorColumn - 1 != 0) {
        actorColumn = actorColumn - 1;
      }
    } else {
      if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 1
          && actorRow - 1 != 0) {
        actorRow = actorRow - 1;
      } else if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 2
          && actorColumn + 1 != currentMap.getColumn() - 1) {
        actorColumn = actorColumn + 1;
      } else if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 3
          && actorRow + 1 != currentMap.getRow() - 1) {
        actorRow = actorRow + 1;
      } else if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 4
          && actorColumn - 1 != 0) {
        actorColumn = actorColumn - 1;
      } else if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 5) {
        if (isAvailable(currentMap,actorRow - 1, actorColumn)) {
          actorRow = actorRow - 1;
        } else if (isAvailable(currentMap, actorRow, actorColumn - 1)) {
          actorColumn = actorColumn - 1;
        }
      } else if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 6) {
        if (isAvailable(currentMap, actorRow - 1, actorColumn)) {
          actorRow = actorRow - 1;
        } else if (isAvailable(currentMap, actorRow, actorColumn + 1)) {
          actorColumn = actorColumn + 1;
        }
      } else if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 7) {
        if (isAvailable(currentMap,actorRow + 1, actorColumn)) {
          actorRow = actorRow + 1;
        } else if (isAvailable(currentMap, actorRow, actorRow + 1)) {
          actorColumn = actorColumn + 1;
        }
      } else if (checkSurrounding(posXPlayer, posYPlayer, currentMap) == 8) {
        if (isAvailable(currentMap, actorRow + 1, actorColumn)) {
          actorRow = actorRow + 1;
        } else if (isAvailable(currentMap, actorRow, actorColumn - 1)) {
          actorColumn = actorColumn - 1;
        }
      }
    }
    currentMap.setCellType(actorRow, actorColumn, 'A');
  }

  /**
   * Prosedur Penggerak Random.
   * @param currentMap Peta yang ditempati saat ini.
   */
  public void moveRandom(Map currentMap) {
    int direction;
    Random way = new Random();
    direction = way.nextInt(4);
    currentMap.setCellType(actorRow, actorColumn, 'r');
    if (direction == 0 && isInRange(currentMap,actorRow - 1,actorColumn)
        && isAvailable(currentMap, actorRow - 1, actorColumn)) {
      actorRow = actorRow - 1;
    }
    if (direction == 1 && isInRange(currentMap,actorRow,actorColumn + 1)
        && isAvailable(currentMap, actorRow, actorColumn + 1)) {
      actorColumn = actorColumn + 1;
    }
    if (direction == 2 && isInRange(currentMap,actorRow + 1,actorColumn)
        && isAvailable(currentMap, actorRow + 1, actorColumn)) {
      actorRow = actorRow + 1;
    }
    if (direction == 3 && isInRange(currentMap,actorRow,actorColumn - 1)
        && isAvailable(currentMap, actorRow, actorColumn - 1)) {
      actorColumn = actorColumn - 1;
    }
    currentMap.setCellType(actorRow, actorColumn, 'M');
    System.out.println(actorRow + " " + actorColumn);
  }

  /**
   * Fungsi Pengecek Posisi dengan Tujuan.
   * @param posXPlayer Posisi Ordinat dari Player.
   * @param posYPlayer Posisi Absis dari Player.
   * @param posXMonster Posisi Ordinat dari Monster.
   * @param posYMonster Posisi Absis dari Monster.
   * @return boolean apakah Monster berada pada posisi yang sama dengan player.
   */
  private boolean isDestination(int posXPlayer,
                                int posYPlayer,
                                int posXMonster,
                                int posYMonster) {
    return (posXMonster == posXPlayer) && (posYMonster == posYPlayer);
  }

  public char getType() {
    return type;
  }

  public abstract int attack(int x);

  public abstract int specialAttack(int x);
}
