import Character.Player;
import Infrastructure.Cell;
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

	public GameManager(String playerName,String playerSkillset) {
		try {
			maps = new Vector<Map>();
			int row, column, effectValue, playerAbsis, playerOrdinat;
			String mapName, toPreviousMap, toNextMap, buffer, skillsetName, skillName, skillDesc, skillEffect;
			Cell[][] cells;
			Scanner readMap = new Scanner(new FileInputStream("src/map.txt"));
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
				buffer = readMap.nextLine();
				toPreviousMap = readMap.nextLine();
				toNextMap = readMap.nextLine();
				maps.addElement(new Map(mapName, maps.size(), cells, row, column, toPreviousMap, toNextMap));
				mapName = readMap.nextLine();
			}
			currentMapID = 0;
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
			playerAbsis = maps.get(0).getEntrance().getCellRow();
			playerOrdinat = maps.get(0).getEntrance().getCellColumn();
			player = new Player(playerName, 0, playerAbsis, playerOrdinat, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void renderGame() {
		Map currentMap = new Map(maps.get(currentMapID));
		currentMap.setCellType(player.getActorRow(),player.getActorColumn(),'P');
		currentMap.renderMap();
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

	public void handleMovement(String input) {
		char playerCellType = maps.get(currentMapID).getCell(player.getActorRow(),player.getActorColumn()).getType();
		if(playerCellType == '+'){
			if(input.equals(maps.get(currentMapID).getToPreviousMap())) {
				if(currentMapID != 0) {
					moveToPreviousMap();
				}
			} else {
				moveNormally(input);
			}
		} else if(playerCellType == '*') {
			if(input.equals(maps.get(currentMapID).getToNextMap())) {
				if(currentMapID != maps.size()-1) {
					moveToNextMap();
				}
			} else {
				moveNormally(input);
			}
		} else {
			moveNormally(input);
		}
	}

	public void moveToNextMap() {
		currentMapID = currentMapID + 1;
		System.out.println(maps.get(currentMapID).getMapName());
		player.setActorRow(maps.get(currentMapID).getEntrance().getCellRow());
		player.setActorColumn(maps.get(currentMapID).getEntrance().getCellColumn());
	}

	public void moveToPreviousMap() {
		currentMapID = currentMapID - 1;
		System.out.println(maps.get(currentMapID).getMapName());
		player.setActorRow(maps.get(currentMapID).getExit().getCellRow());
		player.setActorColumn(maps.get(currentMapID).getExit().getCellColumn());
	}

	public void moveNormally(String input) {
		switch (input) {
			case "w":
				if (maps.get(currentMapID).getCell(player.getActorRow() - 1, player.getActorColumn()).getType() != 'x') {
					player.move("up");
				}
				break;
			case "s":
				if (maps.get(currentMapID).getCell(player.getActorRow() + 1, player.getActorColumn()).getType() != 'x') {
					player.move("down");
				}
				break;
			case "a":
				if (maps.get(currentMapID).getCell(player.getActorRow(), player.getActorColumn() - 1).getType() != 'x') {
					player.move("left");
				}
				break;
			case "d":
				if (maps.get(currentMapID).getCell(player.getActorRow(), player.getActorColumn() + 1).getType() != 'x') {
					player.move("right");
				}
				break;
		}
	}
}
