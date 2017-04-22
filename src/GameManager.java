import Character.Monster;
import Character.People;
import Character.Player;
import Infrastructure.Cell;
import Infrastructure.Gate;
import Infrastructure.Map;
import Skill.Skillset;
import Skill.Skill;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by edwin on 18/04/17.
 */
public class GameManager {
  private Vector<Map> maps;
  private Skillset skillset;
  private Player player;
  private int currentMapID;
  private Vector<Monster> monsters;
  private Vector<People> peoples;
  private Map currentMap;

  public GameManager(String playerName,String playerSkillset) {
    try {
      maps = new Vector<Map>();
      monsters = new Vector<Monster>();
      peoples = new Vector<People>();
      int row, column, effectValue, playerRow, playerColumn;
      String mapName, buffer, skillsetName, skillName, skillDesc, skillEffect;
      Cell[][] cells;
      Vector<Gate> gates;
      Scanner readMap = new Scanner(new FileInputStream("src/map.txt"));
      playerRow = readMap.nextInt();
      playerColumn = readMap.nextInt();
      buffer = readMap.nextLine();
      mapName = readMap.nextLine();
      while (!mapName.equals("#")) {
        row = readMap.nextInt();
        column = readMap.nextInt();
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
          if(mapID != -1) {
            gates.addElement(new Gate(readMap.nextInt(), readMap.nextInt(), mapID));
          } else {
            gates.addElement(new Gate(0,0,-1));
          }
        }
        buffer = readMap.nextLine();
        maps.addElement(new Map(mapName, maps.size(), cells, row, column,gates));
        mapName = readMap.nextLine();
      }
      currentMapID = 0;
      currentMap = new Map(maps.get(currentMapID));
      Scanner readSkillset = new Scanner(new FileInputStream("src/skillset.txt"));
      skillsetName = readSkillset.nextLine();
      while (!skillsetName.equals(playerSkillset)) {
        while (!skillsetName.equals("#")) {
          skillsetName = readSkillset.nextLine();
        }
        skillsetName = readSkillset.nextLine();
      }
      skillset = new Skillset(skillsetName);
      skillName = readSkillset.nextLine();
      while (!skillName.equals("#")) {
        skillDesc = readSkillset.nextLine();
        skillEffect = readSkillset.nextLine();
        effectValue = readSkillset.nextInt();
        buffer = readSkillset.nextLine();
        skillset.addSkill(new Skill(skillName, skillDesc, skillEffect, effectValue));
        skillName = readSkillset.nextLine();
      }
      player = new Player(playerName, 0, playerRow, playerColumn, 1);
      currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'P');
      //monsters.addElement(new Monster("Monster",0,3,3,10,10,10,10,10,10));
      monsters.addElement(new Monster("Tes",0,2,3,10,10,10,10,10,10));
      //peoples.addElement(new People("Orang",0,2,2,-1));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void renderGame() {
    System.out.println("Copy: ");
    currentMap.renderMap();
    System.out.println("Real: ");
    Map toRender = new Map(maps.get(currentMapID));
    toRender.setCellType(player.getActorRow(),player.getActorColumn(),'P');
    if (monsters.get(0).getHealth() > 0) {
      toRender.setCellType(monsters.get(0).getActorRow(), monsters.get(0).getActorColumn(), 'A');
      //currentMap.setCellType(monsters.get(1).getActorRow(), monsters.get(1).getActorColumn(), 'M');
    }
    //toRender.setCellType(peoples.get(0).getActorRow(),peoples.get(0).getActorColumn(),'!');
    toRender.renderMap();
  }

  public void runGame() {
    System.out.print("> ");
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    while (!input.equals("quit")) {
      if(input.equals("stats")){
        viewStats();
      } else if(input.equals("skill")) {
        viewSkills();
      } else if(isMovementInput(input)) {
        handleMovement(input);
        renderGame();
      } else {
        renderGame();
      }
      System.out.print("> ");
      input = in.nextLine();
    }
  }

  public void viewStats() {
    System.out.println("Name      : " + player.getActorName());
    System.out.println("Level     : " + player.getLevel());
    System.out.println("Health    : " + player.getHealth());
    System.out.println("Strength  : " + player.getStrength());
    System.out.println("Defense   : " + player.getDefense());
  }

  public void viewSkills() {
    for(int i = 0; i < skillset.getSkills().size(); i++) {
      System.out.print(skillset.getSkills().get(i).getSkillName()+" [");
      if (skillset.getSkills().get(i).isUnlocked()) {
        System.out.print("x]: ");
      } else {
        System.out.print(" ]: ");
      }
      System.out.println(skillset.getSkills().get(i).getSkillDesc());
    }
  }

  public boolean isMovementInput(String input) {
    return (input.equals("w") || input.equals("s") || input.equals("a") || input.equals("d"));
  }

  public boolean isMovementInput(char input) {
    return (input == 'w' || input == 's' || input == 'a' || input == 'd');
  }

  public void handleMovement(String input) {
    int initialPlayerRow = player.getActorRow();
    int initialPlayerColumn = player.getActorColumn();
    //monsters.get(1).moveRandom(currentMap);
    //peoples.get(0).move(currentMap);
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
        moveNormally(input);
      }
    } else {
      moveNormally(input);
    }
    if (player.getActorRow() != initialPlayerRow || player.getActorColumn() != initialPlayerColumn) {
      currentMap.setCellType(initialPlayerRow,initialPlayerColumn,'r');
      currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'P');
    }
    monsters.get(0).moveDjikstra(player.getActorRow(),player.getActorColumn(),currentMap);
  }

  public void moveTo(int mapID,int row,int column) {
    currentMapID = mapID;
    currentMap = new Map(maps.get(currentMapID));
    player.setActorRow(row);
    player.setActorColumn(column);
    System.out.println(maps.get(currentMapID).getMapName());
  }

  public void moveNormally(String input) {
    switch (input) {
      case "w":
        if (isFreeBlock(player.getActorRow() - 1,player.getActorColumn())) {
          player.move("up");
        }
        break;
      case "s":
        if (isFreeBlock(player.getActorRow() + 1,player.getActorColumn())) {
          player.move("down");
        }
        break;
      case "a":
        if (isFreeBlock(player.getActorRow(),player.getActorColumn() - 1)) {
          player.move("left");
        }
        break;
      case "d":
        if (isFreeBlock(player.getActorRow(),player.getActorColumn() + 1)) {
          player.move("right");
        }
        break;
    }
  }

  public boolean isFreeBlock(int row,int column) {
    char cellType = currentMap.getCell(row,column).getType();
    System.out.println(cellType);
    return (cellType != 'x' && cellType != '!' && cellType != 'A');
  }
}
