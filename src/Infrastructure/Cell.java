package Infrastructure;

/**
 * Created by edwin on 18/04/17.
 */
public class Cell implements Renderable{
  private int cellRow;
  private int cellColumn;
  private char type;

  public Cell(int absis,int ordinat,char type) {
    this.cellRow = absis;
    this.cellColumn = ordinat;
    this.type = type;
  }

  public int getCellRow() {
    return cellRow;
  }

  public int getCellColumn() {
    return cellColumn;
  }

  public char getType() {
    return type;
  }

  public void setCellRow(int cellRow) {
    this.cellRow = cellRow;
  }

  public void setCellColumn(int cellColumn) {
    this.cellColumn = cellColumn;
  }

  public void setType(char type) {
    this.type = type;
  }

  public void render() {
    if(type == 'r') {
      System.out.print(" ");
    } else {
      System.out.print(type);
    }
  }
}
