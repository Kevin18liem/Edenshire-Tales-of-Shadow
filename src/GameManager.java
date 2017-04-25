import static java.lang.Math.abs;

import character.Dragon;
import character.Goblin;
import character.GolemKnight;
import character.Monster;
import character.Necromancer;
import character.OrcWarchief;
import character.People;
import character.Player;
import character.Wolf;
import character.dialogue.Dialogue;
import character.skill.Skill;
import character.skill.Skillset;
import infrastructure.Cell;
import infrastructure.Gate;
import infrastructure.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import quest.Mission;
import quest.Quest;



/**
 * Created by edwin on 18/04/17.
 */
public class GameManager {
  private Vector<Map> maps;
  private Player player;
  private int currentMapId;
  private Vector<Monster> monsters;
  private Vector<People> peoples;
  private Vector<Quest> quests;
  private Map currentMap;

  public Vector<Map> getMaps() {
    return maps;
  }

  public Player getPlayer() {
    return player;
  }

  public int getCurrentMapId() {
    return currentMapId;
  }

  public Vector<Monster> getMonsters() {
    return monsters;
  }

  public Vector<People> getPeoples() {
    return peoples;
  }

  public Vector<Quest> getQuests() {
    return quests;
  }

  public Map getCurrentMap() {
    return currentMap;
  }

  /**
   * Constructor Game Manager.
   * @param playerName Nama dari Player.
   * @param playerSkillset Skillset pilihan Player.
   */
  public GameManager(String playerName,String playerSkillset) {
    initiateMap();
    initiatePlayer(playerName,playerSkillset);
    initiateQuest();
    inititatePeople();
    initiateMonster();
    moveTo(14,1,2);
  }

  /**
   * Prosedur inisialisasi Map dari file eksternal.
   */
  public void initiateMap() {
    try {
      maps = new Vector<Map>();
      int row;
      int column;
      int mapId;
      int questReq;
      int missionReq;
      String mapName;
      String buffer;
      Cell[][] cells;
      Vector<Gate> gates;
      Scanner readMap = new Scanner(new FileInputStream("src/map.txt"));
      mapId = readMap.nextInt();
      while (mapId != -1) {
        buffer = readMap.nextLine();
        mapName = readMap.nextLine();
        row = readMap.nextInt();
        column = readMap.nextInt();
        questReq = readMap.nextInt();
        missionReq = readMap.nextInt();
        cells = new Cell[row][column];
        for (int i = 0; i < row; i++) {
          buffer = readMap.next();
          for (int j = 0; j < column; j++) {
            cells[i][j] = new Cell(i, j, buffer.charAt(j));
          }
        }
        gates = new Vector<Gate>();
        for (int i = 0; i < 4; i++) {
          int mapIdTes = readMap.nextInt();
          if (mapIdTes != -1) {
            gates.addElement(new Gate(readMap.nextInt(), readMap.nextInt(), mapIdTes));
          } else {
            gates.addElement(new Gate(0, 0, -1));
          }
        }
        maps.addElement(new Map(mapName, maps.size(), cells, row, column,
            gates,questReq,missionReq));
        mapId = readMap.nextInt();
      }
      currentMapId = 0;
      currentMap = new Map(maps.get(currentMapId));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Prosedur inisialisasi Player dari file eksternal.
   * @param playerName Nama dari Player.
   * @param playerSkillset Skillset pilihan Player.
   */
  public void initiatePlayer(String playerName,String playerSkillset) {
    try {
      int effectValue;
      int playerRow;
      int playerColumn;
      String buffer;
      Scanner readPlayer = new Scanner(new FileInputStream("src/player.txt"));
      playerRow = readPlayer.nextInt();
      playerColumn = readPlayer.nextInt();
      Scanner readSkillset = new Scanner(new FileInputStream("src/skillset.txt"));
      String skillsetName;
      String skillDesc;
      String skillEffect;
      String skillName;
      skillsetName = readSkillset.nextLine();
      while (!skillsetName.equals(playerSkillset)) {
        while (!skillsetName.equals("#")) {
          skillsetName = readSkillset.nextLine();
        }
        skillsetName = readSkillset.nextLine();
      }
      Skillset skillset = new Skillset(skillsetName);
      skillName = readSkillset.nextLine();
      while (!skillName.equals("#")) {
        skillDesc = readSkillset.nextLine();
        skillset.addSkill(new Skill(skillName, skillDesc));
        skillName = readSkillset.nextLine();
      }
      player = new Player(playerName, 0, playerRow, playerColumn,skillset);
      currentMap.setCellType(player.getActorRow(), player.getActorColumn(), 'P');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Prosedur inisialisasi Quest dari file eksternal.
   */
  public void initiateQuest() {
    try {
      int questId;
      int missionTarget;
      int unlockedSkill;
      String questName;
      String buffer;
      String missionName;
      String missionType;
      String missionIns;
      String missionMonster;
      quests = new Vector<Quest>();
      Vector<Mission> missions;
      Vector<Integer> actorIds;
      Scanner readQuest = new Scanner(new FileInputStream("src/quest.txt"));
      questId = readQuest.nextInt();
      while (questId != -1) {
        buffer = readQuest.nextLine();
        questName = readQuest.nextLine();
        buffer = readQuest.nextLine();
        missions = new Vector<Mission>();
        while (!buffer.equals("#")) {
          while (!buffer.equals(".")) {
            actorIds = new Vector<Integer>();
            missionName = buffer;
            unlockedSkill = readQuest.nextInt();
            buffer = readQuest.nextLine();
            missionType = readQuest.nextLine();
            missionIns = readQuest.nextLine();
            missionMonster = readQuest.nextLine();
            while (readQuest.hasNextInt()) {
              actorIds.addElement(readQuest.nextInt());
            }
            missionTarget = actorIds.get(actorIds.size() - 1);
            actorIds.removeElementAt(actorIds.size() - 1);
            missions.addElement(new Mission(missionName, missionType, missionIns,
                actorIds,missionTarget,missionMonster,unlockedSkill));
            buffer = readQuest.nextLine();
            buffer = readQuest.nextLine();
          }
          buffer = readQuest.nextLine();
        }
        quests.addElement(new Quest(questName, missions));
        questId = readQuest.nextInt();
      }
      quests.get(0).startQuest();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Prosedur inisialisasi People dari file eksternal.
   */
  public void inititatePeople() {
    try {
      peoples = new Vector<People>();
      int peopleId;
      int mapId;
      int peopleRow;
      int peopleColumn;
      String buffer;
      String peopleName;
      Random row = new Random();
      Random column = new Random();
      Scanner readPeople = new Scanner(new FileInputStream("src/people.txt"));
      peopleId =  readPeople.nextInt();
      while (peopleId != -1) {
        buffer = readPeople.nextLine();
        peopleName = readPeople.nextLine();
        mapId = readPeople.nextInt();
        buffer = readPeople.nextLine();
        Vector<Dialogue> dialogueSet = new Vector<Dialogue>();
        buffer = readPeople.nextLine();
        while (!buffer.equals("#")) {
          Vector<String> dialogues = new Vector<String>();
          while (!buffer.equals(".")) {
            dialogues.addElement(buffer);
            buffer = readPeople.nextLine();
          }
          dialogueSet.addElement(new Dialogue(dialogues));
          buffer = readPeople.nextLine();
        }
        do {
          peopleRow = row.nextInt(maps.get(mapId).getRow());
          peopleColumn = column.nextInt(maps.get(mapId).getColumn());
        } while (cellOccupied(mapId,peopleRow,peopleColumn)
          || maps.get(mapId).getCell(peopleRow,peopleColumn).getType() != 'r');
        //System.out.println("Set to map "+mapId+" "+peopleRow+","+peopleColumn);
        peoples.addElement(new People(peopleName,mapId,peopleRow,peopleColumn,dialogueSet));
        peopleId = readPeople.nextInt();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Fungsi untuk mencari tahu apakah sebuah Cell sudah terisi atau belum.
   * @param mapId ID dari map yang ingin dicek.
   * @param row Baris dari Cell yang ingin dicek.
   * @param column Kolom dari Cell yang ingin dicek.
   * @return Iya/tidaknya sebuah Cell sudah terisi.
   */
  public boolean cellOccupied(int mapId,int row,int column) {
    if (mapId == currentMapId && player.getActorRow() == row && player.getActorColumn() == column) {
      return false;
    } else {
      boolean found = false;
      int i = 0;
      while (!found && i < peoples.size() - 1) {
        if (peoples.get(i).getMapId() == mapId
            && peoples.get(i).getActorRow() == row
            && peoples.get(i).getActorColumn() == column) {
          found = true;
        } else {
          i++;
        }
      }
      if (found) {
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Prosedur inisialisasi Monster dari file eksternal.
   */
  public void initiateMonster() {
    try {
      int monsterId;
      int mapId;
      int row;
      int column;
      int health;
      int strength;
      int defense;
      int agility;
      int intelligence;
      int exp;
      String buffer;
      String monsterName;
      monsters = new Vector<Monster>();
      Scanner readMonster = new Scanner(new FileInputStream("src/monster.txt"));
      monsterId = readMonster.nextInt();
      while (monsterId != -1) {
        monsterName = readMonster.next();
        buffer = readMonster.nextLine();
        mapId = readMonster.nextInt();
        row = readMonster.nextInt();
        column = readMonster.nextInt();
        switch (monsterName) {
          case "Dragon":
            monsters.addElement(new Dragon(mapId, row, column));
            break;
          case "Goblin":
            monsters.addElement(new Goblin(mapId, row, column));
            break;
          case "GolemKnight":
            monsters.addElement(new GolemKnight(mapId, row, column));
            break;
          case "Necromancer":
            monsters.addElement(new Necromancer(mapId, row, column));
            break;
          case "OrcWarchief":
            monsters.addElement(new OrcWarchief(mapId, row, column));
            break;
          case "Wolf":
            monsters.addElement(new Wolf(mapId, row, column));
            break;
          default:
            assert false;
        }
        //System.out.println("added "+monsterName+" to "+mapId+" "+row+","+column);
        monsterId = readMonster.nextInt();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Prosedur untuk me-render Game.
   */
  public void renderGame() {
    Map toRender = new Map(maps.get(currentMapId));
    toRender.setCellType(player.getActorRow(),player.getActorColumn(),'P');
    for (People people:peoples) {
      if (people.getMapId() == currentMapId) {
        toRender.setCellType(people.getActorRow(),people.getActorColumn(),'!');
      }
    }
    for (Monster monster:monsters) {
      if (monster.getMapId() == currentMapId && monster.getHealth() > 0) {
        toRender.setCellType(monster.getActorRow(),monster.getActorColumn(),monster.getType());
      }
    }
    toRender.renderMap();
  }

  /**
   * Fungsi untuk mencari tahu apakah battle bisa dilakukan.
   * @return Indeks monster yang akan diajak battle. Jika tidak ada bernilai -1.
   */
  public int readyBattle() {
    int i = 0;
    boolean found = false;
    while (!found && i < monsters.size()) {
      Monster monster = monsters.get(i);
      if (monster.getMapId() == currentMapId && monster.getActorRow() == player.getActorRow() /*&&
          monster.getActorColumn() == player.getActorColumn() && monster.getHealth() > 0*/) {
        found = true;
      } else {
        i++;
      }
    }
    if (found) {
      return i;
    } else {
      return -1;
    }
  }

  /**
   * Prosedur untuk melakukan loop input/output antara game dan user.
   * @param input String yang berisi perintah user.
   */
  public void runGame(String input) {
    //System.out.print("> ");
    //Scanner in = new Scanner(System.in);
    //String input = in.nextLine();
    //while (!input.equals("quit")) {
    if (input.equals("stats")) {
      viewStats();
    } else if (input.equals("skill")) {
      viewSkills();
    } else if (input.equals("talk")) {
      talkNpc();
    } else if (input.equals("quest")) {
      viewQuest();
    } else if (input.equals("moveNpc")) {
      moveNpc();
    } else if (isMovementInput(input)) {
      handleMovement(input);
      int monsterId = readyBattle();
      /*if (monsterId != -1) {
        battleMode(monsters.get(monsterId));
      }
      renderGame();*/
    } else {
      renderGame();
      //}
      //System.out.print("> ");
      //input = in.nextLine();
    }
  }

  /**
   * Prosedur untuk mencetak status Player.
   */
  public void viewStats() {
    System.out.println("Name        : " + player.getActorName());
    System.out.println("Level       : " + player.getLevel());
    System.out.println("Health      : " + player.getHealth());
    System.out.println("Strength    : " + player.getStrength());
    System.out.println("Defense     : " + player.getDefense());
    System.out.println("Agility     : " + player.getAgility());
    System.out.println("Intelligence: " + player.getIntelligence());
    System.out.println("Exp         : " + player.getExperience() + "/" + player.getLevel() * 150);
  }

  /**
   * Prosedur Untuk mencetak Skill Player.
   */
  public void viewSkills() {
    for (int i = 0; i < player.getSkillset().getSkills().size(); i++) {
      System.out.print(player.getSkillset().getSkills().get(i).getSkillName() + " [");
      if (player.getSkillset().getSkills().get(i).isUnlocked()) {
        System.out.print("x]: ");
      } else {
        System.out.print(" ]: ");
      }
      System.out.println(player.getSkillset().getSkills().get(i).getSkillDesc());
    }
  }

  /**
   * Fungsi untuk melakukan dialog dengan People.
   * @return Vektor string dialog People. Jika tidak ada, Vektor kosong.
   */
  public Vector<String> talk() {
    int id = nearbyPeopleId();
    Vector<String> toReturn = new Vector<String>();
    if (id != -1) {
      int i = 0;
      for (String lines:peoples.get(id).getDialogueLines()) {
        if (!lines.equals("null")) {
          if (i % 2 == 0) {
            toReturn.addElement(peoples.get(id).getActorName() + " : " + lines);
          } else {
            toReturn.addElement("You : " + lines);
          }
        }
        i++;
      }
    }
    return toReturn;
  }

  /**
   * Prosedur untuk Pencetakan Setelah Berbicara dengan NPC.
   */
  public void afterTalk() {
    int id = nearbyPeopleId();
    int i;
    if (id != -1) {
      for (Quest quest : quests) {
        if (quest.isActive() && quest.getCurrentMission().getType().equals("talk")) {
          if (id == quest.getCurrentMission().getActorId().get(0)) {
            i = 0;
            Vector<Integer> actorIds = quest.getCurrentMission().getActorId();
            while (i < actorIds.size()) {
              peoples.get(actorIds.get(i)).setDialogueId(actorIds.get(i + 1));
              i = i + 2;
            }
            int result = quest.progressQuest();
            if (result != -1) {
              player.getSkillset().unlockSkill();
            }
          }
        }
      }
    }
  }

  /**
   * Prosedur untuk mencetak dialog dengan People.
   */
  public void talkNpc() {
    Vector<String> dialogues = talk();
    if (dialogues.size() != 0) {
      System.out.println(dialogues.size());
      for (String lines : dialogues) {
        Scanner input = new Scanner(System.in);
        System.out.print(lines);
        String enter = input.nextLine();
      }
      System.out.println();
    } else {
      System.out.println("No one here");
    }
  }

  /**
   * Fungsi Pengecekan NPC Terdekat.
   * @return ID dari NPC terdekat.
   */
  public int nearbyPeopleId() {
    int playerRow = player.getActorRow();
    int playerColumn = player.getActorColumn();
    int deltaRow;
    int deltaColumn;
    boolean found = false;
    int i = 0;
    while (!found && i < peoples.size()) {
      deltaRow = abs(peoples.get(i).getActorRow() - playerRow);
      deltaColumn = abs(peoples.get(i).getActorColumn() - playerColumn);
      if (peoples.get(i).getMapId() == currentMapId
          && ((deltaRow == 0 && deltaColumn == 1)
          || (deltaRow == 1 && deltaColumn == 0))) {
        found = true;
      } else {
        i = i + 1;
      }
    }
    if (found) {
      return i;
    } else {
      return -1;
    }
  }

  /**
   * Prosedur untuk Menampilkan Quest yang Tersedia.
   */
  public void viewQuest() {
    for (Quest quest:quests) {
      if (quest.isActive()) {
        if (quest.getCurrentMission().getType().equals("talk")) {
          System.out.println(quest.getQuestName() + " - "
              + quest.getCurrentMission().getMissionName()
              + ": " + quest.getCurrentMission().getInstruction());
        } else {
          System.out.println(quest.getQuestName() + " - "
              + quest.getCurrentMission().getMissionName()
              + ": " + quest.getCurrentMission().getInstruction()
              + " (" + quest.getCurrentMission().getCount() + "/"
              + quest.getCurrentMission().getTarget() + ")");
        }
      } else if (quest.isComplete()) {
        System.out.println(quest.getQuestName() + " -- Completed");
      }
    }
  }

  /**
   * Prosedur untuk Menggerakkan Semua NPC.
   */
  public void moveNpc() {
    for (People toMove:peoples) {
      if (toMove.getMapId() == currentMapId) {
        toMove.move(currentMap);
      }
    }
    for (Monster toMove:monsters) {
      if (toMove.getMapId() == currentMapId && toMove.getHealth() > 0) {
        switch (toMove.getType()) {
          case 'A':
            toMove.moveDjikstra(player.getActorRow(),player.getActorColumn(),currentMap);
            break;
          case 'M':
            toMove.moveRandom(currentMap);
            break;
          case 'B':
            toMove.moveDjikstra(player.getActorRow(),player.getActorColumn(),currentMap);
            break;
          default:
            assert false;
        }
      }
    }
  }

  /**
   * Fungsi untuk Memeriksa apakah Input merupakan gerakan.
   * @param input string Input masukan pengguna.
   * @return boolean apakah string input merupakan gerakan atau bukan.
   */
  public boolean isMovementInput(String input) {
    return (input.equals("w") || input.equals("s") || input.equals("a") || input.equals("d"));
  }

  /**
   * Fungsi untk memeriksa apakah Input merupakan Gerakan.
   * @param input char Input masukan pengguna.
   * @return boolean apakah char input merupakan gerakan atau bukan.
   */
  public boolean isMovementInput(char input) {
    return (input == 'w' || input == 's' || input == 'a' || input == 'd');
  }

  /**
   * Prosedur untuk Menangani Gerakan.
   * @param input String masukan pengguna.
   */
  public void handleMovement(String input) {
    char playerCellType = maps.get(currentMapId).getCell(player.getActorRow(),
        player.getActorColumn()).getType();
    if (isMovementInput(playerCellType)) {
      char movement = input.charAt(0);
      if (movement == playerCellType) {
        int gateId = -1;
        switch (playerCellType) {
          case 'w':
            gateId = 0;
            break;
          case 's':
            gateId = 1;
            break;
          case 'a':
            gateId = 2;
            break;
          case 'd':
            gateId = 3;
            break;
          default:
            assert false;
        }
        int targetMapId = maps.get(currentMapId).getGates().get(gateId).getMapIdTarget();
        int playerRow = maps.get(currentMapId).getGates().get(gateId).getRowTarget();
        int playerColumn = maps.get(currentMapId).getGates().get(gateId).getColumnTarget();
        moveTo(targetMapId,playerRow,playerColumn);
      } else {
        currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'w');
        moveNormally(input);
      }
    } else {
      currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'r');
      moveNormally(input);
    }
  }

  /**
   * Prosedur Pemindah Map.
   * @param mapId Kode Map yang ingin dituju.
   * @param row Posisi Ordinat dari Player.
   * @param column Posisi Absis dari Player.
   */
  public void moveTo(int mapId,int row,int column) {
    Map target = maps.get(mapId);
    if (target.getMissionReq() == -1
        || quests.get(target.getQuestReq()).getMission(target.getMissionReq()).isDone()) {
      currentMapId = mapId;
      currentMap = new Map(maps.get(currentMapId));
      player.setActorRow(row);
      player.setActorColumn(column);
      currentMap.setCellType(player.getActorRow(), player.getActorColumn(), 'P');
      if (currentMapId == 0) {
        player.setHealth(player.getStrength() * 5);
        initiateMonster();
      }
      System.out.println(maps.get(currentMapId).getMapName());
      for (People people:peoples) {
        if (people.getMapId() == currentMapId) {
          currentMap.setCellType(people.getActorRow(),people.getActorColumn(),'!');
        }
      }
      for (Monster monster:monsters) {
        if (monster.getMapId() == currentMapId && monster.getHealth() > 0) {
          currentMap.setCellType(monster.getActorRow(),monster.getActorColumn(),monster.getType());
        }
      }
    }
  }

  /**
   * Prosedur Penggerak Player.
   * @param input string berupa masukan gerakan dari pengguna.
   */
  public void moveNormally(String input) {
    switch (input) {
      case "w":
        if (isFreeBlock(player.getActorRow() - 1, player.getActorColumn())) {
          player.move("up");
        }
        break;
      case "s":
        if (isFreeBlock(player.getActorRow() + 1, player.getActorColumn())) {
          player.move("down");
        }
        break;
      case "a":
        if (isFreeBlock(player.getActorRow(), player.getActorColumn() - 1)) {
          player.move("left");
        }
        break;
      case "d":
        if (isFreeBlock(player.getActorRow(), player.getActorColumn() + 1)) {
          player.move("right");
        }
        break;
      default:
        assert false;
    }
    currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'P');
  }

  /**
   * Fungsi Pengecek Sebuah Blok.
   * @param row Posisi Ordinat pada Map.
   * @param column Posisi Absis pada Map.
   * @return boolean apakah petak bukan merupakan jalan ataupun NPC.
   */
  public boolean isFreeBlock(int row,int column) {
    char cellType = currentMap.getCell(row,column).getType();
    return (cellType != 'x' && cellType != '!');
  }

  /**
   * Fungi Getter Monster ID.
   * @param row Posisi Ordinat dari Monster.
   * @param column Posisi Absis dari Mosnter.
   * @return int berupa ID dari Monster pada posisi row, column.
   */
  public int getMonsterId(int row, int column) {
    boolean found = false;
    int i = 0;
    while (!found && i < monsters.size()) {
      if (monsters.get(i).getMapId() == currentMapId
          && monsters.get(i).getActorRow() == row
          && monsters.get(i).getActorColumn() == column) {
        found = true;
      } else {
        i = i + 1;
      }
    }
    if (found) {
      return i;
    } else {
      return -1;
    }
  }

  /**
   * Battle Mode Swing.
   * @param enemy Monster yang akan dihadapi.
   */
  public void battleMode(Monster enemy) {
    Random attackCode;
    attackCode = new Random();
    int attackType;
    attackType = 0;
    int damageToPlayer;
    int damageToMonster;
    damageToMonster = 0;
    damageToPlayer = 0;
    while (player.getHealth() > 0 && enemy.getHealth() > 0) {
      System.out.println(player.getActorName() + ": " + player.getHealth());
      System.out.println(enemy.getActorName() + ": " + enemy.getHealth());
      attackType = attackCode.nextInt(1);
      if (player.getAgility() < enemy.getAgility()) {
        System.out.println("Enemy's turn");
        if (attackType == 0) {
          damageToPlayer = enemy.attack(player.getDefense());
        } else if (attackType == 1) {
          damageToPlayer = enemy.specialAttack(player.getDefense());
        }
        player.setHealth(player.getHealth() - damageToPlayer);
        System.out.println(player.getActorName() + ": " + player.getHealth());
        System.out.println(enemy.getActorName() + ": " + enemy.getHealth());
        System.out.println("Your turn");
        System.out.print("> ");
        if (player.getHealth() > 0) {
          damageToMonster = player.attack(inputAttackKey(), enemy.getDefense());
        }
        enemy.setHealth(enemy.getHealth() - damageToMonster);
        System.out.println(player.getActorName() + ": " + player.getHealth());
        System.out.println(enemy.getActorName() + ": " + enemy.getHealth());
      } else {
        damageToMonster = player.attack(inputAttackKey(), enemy.getDefense());
        enemy.setHealth(enemy.getHealth() - damageToMonster);
        if (enemy.getHealth() > 0) {
          if (attackType == 0) {
            damageToPlayer = enemy.attack(player.getDefense());
          } else {
            damageToPlayer = enemy.specialAttack(player.getDefense());
          }
          player.setHealth(player.getHealth() - damageToPlayer);
        }
      }
    }
    if (enemy.getHealth() <= 0) {
      player.gainExp(enemy.getExperience());
      currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'r');
      for (Quest quest:quests) {
        if (quest.isActive() && quest.getCurrentMission().getType().equals("kill")) {
          if (enemy.getActorName().equals(quest.getCurrentMission().getMonster())) {
            int i = 0;
            Vector<Integer> actorIds = quest.getCurrentMission().getActorId();
            while (i < actorIds.size()) {
              peoples.get(actorIds.get(i)).setDialogueId(actorIds.get(i + 1));
              i = i + 2;
            }
            int result = quest.progressQuest();
            if (result != -1) {
              player.getSkillset().unlockSkill();
            }
            System.out.println(quest.getCurrentMission().getCount());
          }
        }
      }
    } else {
      System.out.println("Game Over");
    }
  }

  /**
   * Fungsi Penerima Input Key Gerakan.
   * @return Char yang dibaca dari pengguna.
   */
  public char inputAttackKey() {
    Scanner key = new Scanner(System.in);
    return key.next().charAt(0);
  }

  /**
   * Battle Mode.
   * @param enemy Musuh yang akan dihadapi.
   * @param input Char gerakan yang akan dilakukan.
   */
  public void battleModeTest(Monster enemy, char input) {
    Random attackCode;
    attackCode = new Random();
    int attackType;
    attackType = 0;
    int damageToPlayer;
    int damageToMonster;
    damageToMonster = 0;
    damageToPlayer = 0;

    //System.out.println(player.getActorName()+": "+player.getHealth());
    //System.out.println(enemy.getActorName()+": "+enemy.getHealth());
    attackType = attackCode.nextInt(1);
    if (player.getAgility() < enemy.getAgility()) {
      //System.out.println("Enemy's turn");
      if (attackType == 0) {
        damageToPlayer = enemy.attack(player.getDefense());
      } else if (attackType == 1) {
        damageToPlayer = enemy.specialAttack(player.getDefense());
      }
      player.setHealth(player.getHealth() - damageToPlayer);
      // System.out.println(player.getActorName()+": "+player.getHealth());
      // System.out.println(enemy.getActorName()+": "+enemy.getHealth());
      // System.out.println("Your turn");
      // System.out.print("> ");
      if (player.getHealth() > 0) {
        damageToMonster = player.attack(input, enemy.getDefense());
      }
      enemy.setHealth(enemy.getHealth() - damageToMonster);
      // System.out.println(player.getActorName()+": "+player.getHealth());
      // System.out.println(enemy.getActorName()+": "+enemy.getHealth());
    } else {
      damageToMonster = player.attack(input, enemy.getDefense());
      enemy.setHealth(enemy.getHealth() - damageToMonster);
      if (enemy.getHealth() > 0) {
        if (attackType == 0) {
          damageToPlayer = enemy.attack(player.getDefense());
        } else {
          damageToPlayer = enemy.specialAttack(player.getDefense());
        }
        player.setHealth(player.getHealth() - damageToPlayer);
      }
    }
  }

  /**
   * Tampilan Setelah BattleMode.
   * @param enemy Monster yang dihadapi.
   */
  public void battleModeAfter(Monster enemy) {
    if (enemy.getHealth() <= 0) {
      player.gainExp(enemy.getExperience());
      currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'r');
      for (Quest quest:quests) {
        if (quest.isActive() && quest.getCurrentMission().getType().equals("kill")) {
          if (enemy.getActorName().equals(quest.getCurrentMission().getMonster())) {
            int i = 0;
            Vector<Integer> actorIds = quest.getCurrentMission().getActorId();
            while (i < actorIds.size()) {
              peoples.get(actorIds.get(i)).setDialogueId(actorIds.get(i + 1));
              i = i + 2;
            }
            int result = quest.progressQuest();
            if (result != -1) {
              player.getSkillset().unlockSkill();
            }
            System.out.println(quest.getCurrentMission().getCount());
          }
        }
      }
      //player.setActorRow(player.getActorRow()+1);
      enemy.setActorRow(-1);
      enemy.setActorColumn(-1);
    } else {
      //System.out.println("Game Over");
    }
  }

}
