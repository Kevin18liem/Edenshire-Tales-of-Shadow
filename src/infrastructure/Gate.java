package infrastructure;

/**
 * Created by edwin.
 */

public class Gate {
  private int rowTarget;
  private int columnTarget;
  private int mapIdTarget;

  /**
   * Constructor Gate.
   * @param rowTarget Posisi Ordinat dari Map Tujuan.
   * @param columnTarget Posisi Absis dari Map Tujuan.
   * @param mapIdTarget Kode dari Map Tujuan.
   */
  public Gate(int rowTarget,int columnTarget,int mapIdTarget) {
    this.rowTarget = rowTarget;
    this.columnTarget = columnTarget;
    this.mapIdTarget = mapIdTarget;
  }

  /**
   * Getter Ordinat Map Tujuan.
   * @return Ordinat dari Map Tujuan.
   */
  public int getRowTarget() {
    return rowTarget;
  }

  /**
   * Getter Absis Map Tujuan.
   * @return Absis dari Map Tujuan.
   */
  public int getColumnTarget() {
    return columnTarget;
  }

  /**
   * Getter Kode Map Tujuan.
   * @return Kode Map Tujuan.
   */
  public int getMapIdTarget() {
    return mapIdTarget;
  }
}
