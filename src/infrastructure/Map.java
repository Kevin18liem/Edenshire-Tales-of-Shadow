package infrastructure;

import java.util.Vector;

/**
 * Created by Kevin.
 */
public class Map {
  private String mapName;
  private int mapId;
  private int row;
  private int column;
  private Cell[][] cells;
  private Vector<Gate> gates;
  private int questReq;
  private int missionReq;

  /**
   * Constructor Map.
   * @param mapName Nama dari Map.
   * @param mapId Kode Map.
   * @param cells Matriks Map.
   * @param row Ukuran Ordinat.
   * @param column Ukuran Absis.
   * @param gates Kumpulan Gerbang Perpindahan.
   * @param questReq quest yang harus dijalani sebagai syarat akses Map
   * @param missionReq Misi bagian quest yang harus dijalani sebagai syarat akses Map
   */
  public Map(String mapName,
             int mapId,
             Cell[][] cells,
             int row,
             int column,
             Vector<Gate> gates,
             int questReq,
             int missionReq) {
    this.mapName = mapName;
    this.mapId = mapId;
    this.row = row;
    this.column = column;
    this.cells = new Cell[row][column];
    int i;
    int j;
    for (i = 0; i < row; i++) {
      for (j = 0; j < column; j++) {
        this.cells[i][j] = new Cell(i, j, cells[i][j].getType());
      }
    }
    this.gates = gates;
    this.questReq = questReq;
    this.missionReq = missionReq;
  }

  /**
   * Copy Constructor Map.
   * @param oldMap Map lama.
   */
  public Map(Map oldMap) {
    this.mapName = oldMap.mapName;
    this.mapId = oldMap.mapId;
    this.row = oldMap.row;
    this.column = oldMap.column;
    this.cells = new Cell[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        this.cells[i][j] = new Cell(i, j, oldMap.cells[i][j].getType());
      }
    }
    this.gates = oldMap.gates;

  }

  /**
   * Getter Nama Map.
   * @return Nama dari Map.
   */
  public String getMapName() {
    return mapName;
  }

  /**
   * Getter Kode Map.
   * @return Kode dari Map.
   */
  public int getMapId() {
    return mapId;
  }

  /**
   * Getter Matriks Map.
   * @return Matriks dari Map.
   */
  public Cell[][] getCells() {
    return cells;
  }

  /**
   * Getter Cell dari Map.
   * @param row Posisi Ordinat dalam Matriks.
   * @param column Posisi Absis dalam Matriks.
   * @return Cell pada posisi row, column.
   */
  public Cell getCell(int row,int column) {
    return cells[row][column];
  }

  /**
   * Getter Ukuran Tinggi Map.
   * @return Ukuran Tinggi dari Map.
   */
  public int getRow() {
    return row;
  }

  /**
   * Getter Ukuran Lebar Map.
   * @return Ukuran Lebar dari Map.
   */
  public int getColumn() {
    return column;
  }

  /**
   * Getter dari Gerbang Perpindahan.
   * @return Kumpulan Gerbang Perpindahan.
   */
  public Vector<Gate> getGates() {
    return gates;
  }

  /**
   * Getter dari quest Syarat Akses Map.
   * @return Indeks quest.
   */
  public int getQuestReq() {
    return questReq;
  }

  /**
   * Getter dari Mission Syarat Akses Map.
   * @return Indeks Mission.
   */
  public int getMissionReq() {
    return missionReq;
  }

  /**
   * Setter Cell Type.
   * @param row Posisi Ordinat dalam Map.
   * @param column Posisi Absis dalam Map.
   * @param type Tipe yang akan diisikan.
   */
  public void setCellType(int row,int column,char type) {
    cells[row][column].setType(type);
  }

  /**
   * Prosedur Pencetakan Map.
   */
  public void renderMap() {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        cells[i][j].render();
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}
