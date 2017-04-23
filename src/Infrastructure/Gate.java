package Infrastructure;

/**
 * Created by edwin on 21/04/17.
 */
public class Gate {
  private int rowTarget;
  private int columnTarget;
  private int mapIDTarget;

  /**
   * Constructor Gate.
   * @param rowTarget Posisi Ordinat dari Map Tujuan.
   * @param columnTarget Posisi Absis dari Map Tujuan.
   * @param mapIDTarget Kode dari Map Tujuan.
   */
  public Gate(int rowTarget,int columnTarget,int mapIDTarget) {
    this.rowTarget = rowTarget;
    this.columnTarget = columnTarget;
    this.mapIDTarget = mapIDTarget;
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
  public int getMapIDTarget() {
    return mapIDTarget;
  }
}
