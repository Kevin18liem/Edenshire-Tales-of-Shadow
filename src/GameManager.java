import character.Dialogue.Dialogue;
import character.Monster;
import character.Dragon;
import character.Goblin;
import character.GolemKnight;
import character.Necromancer;
import character.OrcWarchief;
import character.Wolf;
import character.People;
import character.Player;
import Infrastructure.Cell;
import Infrastructure.Gate;
import Infrastructure.Map;
import Quest.Mission;
import Quest.Quest;
import character.Skill.Skillset;
import character.Skill.Skill;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.Math.abs;

/**
 * Created by edwin on 18/04/17.
 */
public class GameManager {
  private Vector<Map> maps;
  private Player player;
  private int currentMapID;
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

  public int getCurrentMapID() {
    return currentMapID;
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

  public GameManager(String playerName,String playerSkillset) {
    initiateMap();
    initiatePlayer(playerName,playerSkillset);
    initiateQuest();
    inititatePeople();
    initiateMonster();
    moveTo(14,1,2);
  }

  public void initiateMap() {
    try {
      maps = new Vector<Map>();
      int row, column,mapId,questReq,missionReq;
      String mapName, buffer;
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
          int mapID = readMap.nextInt();
          if (mapID != -1) {
            gates.addElement(new Gate(readMap.nextInt(), readMap.nextInt(), mapID));
          } else {
            gates.addElement(new Gate(0, 0, -1));
          }
        }
        maps.addElement(new Map(mapName, maps.size(), cells, row, column, gates,questReq,missionReq));
        mapId = readMap.nextInt();
      }
      currentMapID = 14;
      currentMap = new Map(maps.get(currentMapID));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void initiatePlayer(String playerName,String playerSkillset) {
    try {
      int effectValue, playerRow, playerColumn;
      String buffer, skillsetName, skillName, skillDesc, skillEffect;
      Scanner readPlayer = new Scanner(new FileInputStream("src/player.txt"));
      playerRow = readPlayer.nextInt();
      playerColumn = readPlayer.nextInt();
      Scanner readSkillset = new Scanner(new FileInputStream("src/skillset.txt"));
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

  public void initiateQuest() {
    try {
      int questId,missionTarget,unlockedSkill;
      String questName, buffer, missionName, missionType, missionIns, missionMonster;
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
            missions.addElement(new Mission(missionName, missionType, missionIns, actorIds,missionTarget,missionMonster,unlockedSkill));
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

  public void inititatePeople() {
    try {
      peoples = new Vector<People>();
      int peopleId,mapId,peopleRow,peopleColumn;
      String buffer,peopleName;
      Random row = new Random();
      Random column = new Random();
      Scanner readPeople = new Scanner(new FileInputStream("src/people.txt"));
      peopleId =  readPeople.nextInt();
      while(peopleId != -1) {
        buffer = readPeople.nextLine();
        peopleName = readPeople.nextLine();
        mapId = readPeople.nextInt();
        buffer = readPeople.nextLine();
        Vector<Dialogue> dialogueSet = new Vector<Dialogue>();
        buffer = readPeople.nextLine();
        while(!buffer.equals("#")) {
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
        } while (cellOccupied(mapId,peopleRow,peopleColumn) ||
                  maps.get(mapId).getCell(peopleRow,peopleColumn).getType() != 'r');
        //System.out.println("Set to map "+mapId+" "+peopleRow+","+peopleColumn);
        peoples.addElement(new People(peopleName,mapId,peopleRow,peopleColumn,dialogueSet));
        peopleId = readPeople.nextInt();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean cellOccupied(int mapId,int row,int column) {
    if (mapId == currentMapID && player.getActorRow() == row && player.getActorColumn() == column) {
      return false;
    } else {
      boolean found = false;
      int i = 0;
      while (!found && i < peoples.size() - 1) {
        if (peoples.get(i).getMapID() == mapId &&
          peoples.get(i).getActorRow() == row &&
          peoples.get(i).getActorColumn() == column) {
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

  public void initiateMonster() {
    try {
      int monsterId,mapId,row,column,health,strength,defense,agility,intelligence,exp;
      String buffer,monsterName;
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
        }
        System.out.println("added "+monsterName+" to "+mapId+" "+row+","+column);
        monsterId = readMonster.nextInt();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void renderGame() {
    Map toRender = new Map(maps.get(currentMapID));
    toRender.setCellType(player.getActorRow(),player.getActorColumn(),'P');
    for (People people:peoples) {
      if (people.getMapID() == currentMapID) {
        toRender.setCellType(people.getActorRow(),people.getActorColumn(),'!');
      }
    }
    for (Monster monster:monsters) {
      if (monster.getMapID() == currentMapID && monster.getHealth() > 0) {
        toRender.setCellType(monster.getActorRow(),monster.getActorColumn(),monster.getType());
      }
    }
    toRender.renderMap();
  }

  public int readyBattle() {
    int i = 0;
    boolean found = false;
    while (!found && i < monsters.size()) {
      Monster monster = monsters.get(i);
      if (monster.getMapID() == currentMapID && monster.getActorRow() == player.getActorRow() &&
          monster.getActorColumn() == player.getActorColumn()) {
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

  public void runGame(String input) {
    //System.out.print("> ");
    //Scanner in = new Scanner(System.in);
    //String input = in.nextLine();
    //while (!input.equals("quit")) {
    if(input.equals("stats")){
      viewStats();
    } else if (input.equals("skill")) {
      viewSkills();
    } else if (input.equals("talk")) {
      talkNPC();
    } else if (input.equals("quest")) {
      viewQuest();
    } else if (input.equals("moveNPC")) {
      moveNPC();
    } else if (isMovementInput(input)) {
      handleMovement(input);
      int monsterId = readyBattle();
      if (monsterId != -1) {
        battleMode(monsters.get(monsterId));
      }
      renderGame();
    } else {
      renderGame();
      //}
      //System.out.print("> ");
      //input = in.nextLine();
    }
  }

  public void viewStats() {
    System.out.println("Name        : " + player.getActorName());
    System.out.println("Level       : " + player.getLevel());
    System.out.println("Health      : " + player.getHealth());
    System.out.println("Strength    : " + player.getStrength());
    System.out.println("Defense     : " + player.getDefense());
    System.out.println("Agility     : " + player.getAgility());
    System.out.println("Intelligence: " + player.getIntelligence());
    System.out.println("Exp         : " + player.getExperience()+"/"+player.getLevel()*150);
  }

  public void viewSkills() {
    for(int i = 0; i < player.getSkillset().getSkills().size(); i++) {
      System.out.print(player.getSkillset().getSkills().get(i).getSkillName()+" [");
      if (player.getSkillset().getSkills().get(i).isUnlocked()) {
        System.out.print("x]: ");
      } else {
        System.out.print(" ]: ");
      }
      System.out.println(player.getSkillset().getSkills().get(i).getSkillDesc());
    }
  }

  public void talkNPC() {
    int id = nearbyPeopleId();
    if (id != -1) {
      peoples.get(id).talk();
    } else {
      System.out.println("No people nearby");
    }
    for(Quest quest:quests) {
      if (quest.isActive() && quest.getCurrentMission().getType().equals("talk")) {
        if (id == quest.getCurrentMission().getActorId().get(0)) {
          int i = 0;
          Vector<Integer> actorIds = quest.getCurrentMission().getActorId();
          while (i<actorIds.size()) {
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

  public int nearbyPeopleId() {
    int playerRow = player.getActorRow();
    int playerColumn = player.getActorColumn();
    int deltaRow,deltaColumn;
    boolean found = false;
    int i = 0;
    while(!found && i<peoples.size()) {
      deltaRow = abs(peoples.get(i).getActorRow() - playerRow);
      deltaColumn = abs(peoples.get(i).getActorColumn() - playerColumn);
      if(peoples.get(i).getMapID() == currentMapID &&
          ((deltaRow == 0 && deltaColumn == 1) ||
           (deltaRow == 1 && deltaColumn == 0)
          )
        ) {
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

  public void viewQuest() {
    for (Quest quest:quests) {
      if (quest.isActive()) {
        if (quest.getCurrentMission().getType().equals("talk")) {
          System.out.println(quest.getQuestName() + " - " + quest.getCurrentMission().getMissionName() +
            ": " + quest.getCurrentMission().getInstruction());
        } else {
          System.out.println(quest.getQuestName() + " - " + quest.getCurrentMission().getMissionName() +
            ": " + quest.getCurrentMission().getInstruction()+" ("+quest.getCurrentMission().getCount()+"/"+
            quest.getCurrentMission().getTarget()+")");
        }
      } else if (quest.isComplete()) {
        System.out.println(quest.getQuestName()+" -- Completed");
      }
    }
  }

  public void moveNPC() {
    for (People toMove:peoples) {
      if (toMove.getMapID() == currentMapID) {
        toMove.move(currentMap);
      }
    }
    for (Monster toMove:monsters) {
      if (toMove.getMapID() == currentMapID && toMove.getHealth() > 0) {
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
        }
      }
    }
  }

  public boolean isMovementInput(String input) {
    return (input.equals("w") || input.equals("s") || input.equals("a") || input.equals("d"));
  }

  public boolean isMovementInput(char input) {
    return (input == 'w' || input == 's' || input == 'a' || input == 'd');
  }

  public void handleMovement(String input) {
    char playerCellType = maps.get(currentMapID).getCell(player.getActorRow(),player.getActorColumn()).getType();
    if(isMovementInput(playerCellType)){
      char movement = input.charAt(0);
      if(movement == playerCellType) {
        int gateID = -1;
        switch(playerCellType){
          case 'w':
            gateID = 0;
            break;
          case 's':
            gateID = 1;
            break;
          case 'a':
            gateID = 2;
            break;
          case 'd':
            gateID = 3;
            break;
        }
        int targetMapID = maps.get(currentMapID).getGates().get(gateID).getMapIDTarget();
        int playerRow = maps.get(currentMapID).getGates().get(gateID).getRowTarget();
        int playerColumn = maps.get(currentMapID).getGates().get(gateID).getColumnTarget();
        moveTo(targetMapID,playerRow,playerColumn);
      } else {
        currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'w');
        moveNormally(input);
      }
    } else {
      currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'r');
      moveNormally(input);
    }
  }

  public void moveTo(int mapID,int row,int column) {
    Map target = maps.get(mapID);
    if (target.getMissionReq() == -1 || quests.get(target.getQuestReq()).getMission(target.getMissionReq()).isDone()) {
      currentMapID = mapID;
      currentMap = new Map(maps.get(currentMapID));
      player.setActorRow(row);
      player.setActorColumn(column);
      currentMap.setCellType(player.getActorRow(), player.getActorColumn(), 'P');
      if (currentMapID == 0) {
        player.setHealth(player.getStrength() * 5);
        initiateMonster();
      }
      System.out.println(maps.get(currentMapID).getMapName());
      for (People people:peoples) {
        if (people.getMapID() == currentMapID) {
          currentMap.setCellType(people.getActorRow(),people.getActorColumn(),'!');
        }
      }
      for (Monster monster:monsters) {
        if (monster.getMapID() == currentMapID && monster.getHealth() > 0) {
          currentMap.setCellType(monster.getActorRow(),monster.getActorColumn(),monster.getType());
        }
      }
    }
  }

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
    }
    currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'P');
  }

  public boolean isFreeBlock(int row,int column) {
    char cellType = currentMap.getCell(row,column).getType();
    return (cellType != 'x' && cellType != '!');
  }

  public int getMonsterId(int row, int column) {
    boolean found = false;
    int i = 0;
    while (!found && i < monsters.size()) {
      if (monsters.get(i).getMapID() == currentMapID &&
          monsters.get(i).getActorRow() == row &&
          monsters.get(i).getActorColumn() == column) {
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
      System.out.println(player.getActorName()+": "+player.getHealth());
      System.out.println(enemy.getActorName()+": "+enemy.getHealth());
      attackType = attackCode.nextInt(1);
      if (player.getAgility() < enemy.getAgility()) {
        System.out.println("Enemy's turn");
        if (attackType == 0) {
          damageToPlayer = enemy.attack(player.getDefense());
        } else if (attackType == 1) {
          damageToPlayer = enemy.specialAttack(player.getDefense());
        }
        player.setHealth(player.getHealth() - damageToPlayer);
        System.out.println(player.getActorName()+": "+player.getHealth());
        System.out.println(enemy.getActorName()+": "+enemy.getHealth());
        System.out.println("Your turn");
        System.out.print("> ");
        if (player.getHealth() > 0) {
          damageToMonster = player.attack(inputAttackKey(), enemy.getDefense());
        }
        enemy.setHealth(enemy.getHealth() - damageToMonster);
        System.out.println(player.getActorName()+": "+player.getHealth());
        System.out.println(enemy.getActorName()+": "+enemy.getHealth());
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
      for(Quest quest:quests) {
        if (quest.isActive() && quest.getCurrentMission().getType().equals("kill")) {
          if (enemy.getActorName().equals(quest.getCurrentMission().getMonster())) {
            int i = 0;
            Vector<Integer> actorIds = quest.getCurrentMission().getActorId();
            while (i<actorIds.size()) {
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

  public char inputAttackKey() {
    Scanner key = new Scanner(System.in);
    return key.next().charAt(0);
  }
}
