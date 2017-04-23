package Infrastructure;

/**
 * Created by edwin on 18/04/17.
 */
public class Cell implements Renderable{
  private int cellRow;
  private int cellColumn;
  private char type;

  /**
   * Constructor Cell.
   * @param absis Posisi Absis.
   * @param ordinat Posisi Ordinat.
   * @param type Tipe dari Cell.
   */
  public Cell(int absis,int ordinat,char type) {
    this.cellRow = absis;
    this.cellColumn = ordinat;
    this.type = type;
  }

  /**
   * Getter Ordinat Cell.
   * @return nilai Ordinat dari Cell.
   */
  public int getCellRow() {
    return cellRow;
  }

  /**
   * Getter Absis Cell.
   * @return nilai Absis dari Cell.
   */
  public int getCellColumn() {
    return cellColumn;
  }

  /**
   * Getter Type Cell.
   * @return Tipe dari Cell.
   */
  public char getType() {
    return type;
  }

  /**
   * Setter Ordinat Cell.
   * @param cellRow nilai Ordinat baru Cell.
   */
  public void setCellRow(int cellRow) {
    this.cellRow = cellRow;
  }

  /**
   * Setter Absis Cell.
   * @param cellColumn nilai Absis baru Cell.
   */
  public void setCellColumn(int cellColumn) {
    this.cellColumn = cellColumn;
  }

  /**
   * Setter Type Cell.
   * @param type Tipe dari Cell.
   */
  public void setType(char type) {
    this.type = type;
  }

  /**
   * Prosedur Mencetak Cell.
   */
  public void render() {
    if(type == 'r' ||
       type == 'w' ||
       type == 's' ||
       type == 'a' ||
       type == 'd') {
      System.out.print(" ");
    } else {
      assert (type == 'x' || type == 'P' || type == 'M' || type == 'A'
        || type == '!');
      System.out.print(type);
    }
  }
}
