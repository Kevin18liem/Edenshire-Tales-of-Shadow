package Infrastructure;

/**
 * Created by edwin on 21/04/17.
 */
public class Gate {
  private int rowTarget;
  private int columnTarget;
  private int mapIDTarget;

  public Gate(int rowTarget,int columnTarget,int mapIDTarget) {
    this.rowTarget = rowTarget;
    this.columnTarget = columnTarget;
    this.mapIDTarget = mapIDTarget;
  }

  public int getRowTarget() {
    return rowTarget;
  }

  public int getColumnTarget() {
    return columnTarget;
  }

  public int getMapIDTarget() {
    return mapIDTarget;
  }
}
