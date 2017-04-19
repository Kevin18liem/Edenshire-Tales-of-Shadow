package Infrastructure;

/**
 * Created by edwin on 18/04/17.
 */
public class Map {
	private String mapName;
	private int mapID;
	private int row;
	private int column;
	private Cell[][] cells;
	private String toPreviousMap;
	private String toNextMap;
	private Cell entrance;
	private Cell exit;

	public Map(String mapName, int mapID, Cell[][] cells,int row,int column,String toPreviousMap,String toNextMap) {
		this.mapName = mapName;
		this.mapID = mapID;
		this.row = row;
		this.column = column;
		this.cells = new Cell[row][column];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				this.cells[i][j] = new Cell(i,j,cells[i][j].getType());
				if(this.cells[i][j].getType() == '+') {
					entrance = this.cells[i][j];
				}
				if(this.cells[i][j].getType() == '*') {
					exit = this.cells[i][j];
				}
			}
		}
		this.toPreviousMap = toPreviousMap;
		this.toNextMap = toNextMap;
	}

	public Map(Map oldMap) {
		this.mapName = oldMap.mapName;
		this.mapID = oldMap.mapID;
		this.row = oldMap.row;
		this.column = oldMap.column;
		this.cells = new Cell[row][column];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				this.cells[i][j] = new Cell(i,j,oldMap.cells[i][j].getType());
			}
		}
		this.toPreviousMap = oldMap.toPreviousMap;
		this.toNextMap = oldMap.toNextMap;
	}

	public String getMapName() {
		return mapName;
	}

	public int getMapID() {
		return mapID;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public Cell getCell(int row,int column) {
		return cells[row][column];
	}

	public String getToPreviousMap() {
		return toPreviousMap;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Cell getEntrance() {
		return entrance;
	}

	public Cell getExit() {
		return exit;
	}

	public String getToNextMap() {
		return toNextMap;
	}

	public void setCellType(int row,int column,char type) {
		cells[row][column].setType(type);
	}

	public void renderMap() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				cells[i][j].render();
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
