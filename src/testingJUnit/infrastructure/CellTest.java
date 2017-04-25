package infrastructure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by ClementAndreas on 4/26/2017.
 */
public class CellTest {

  @Test
  public void getCellRow() throws Exception {
    Cell cellTest = new Cell(0,0,'r');
    assertEquals(0,cellTest.getCellRow());
  }

  @Test
  public void getCellColumn() throws Exception {
    Cell cellTest = new Cell(0,0,'r');
    assertEquals(0,cellTest.getCellColumn());
  }

  @Test
  public void getType() throws Exception {
    Cell cellTest = new Cell(0,0,'r');
    assertEquals('r',cellTest.getType());
  }

  @Test
  public void setCellRow() throws Exception {
    Cell cellTest = new Cell(0,0,'r');
    cellTest.setCellRow(1);
    assertEquals(1,cellTest.getCellRow());
  }

  @Test
  public void setCellColumn() throws Exception {
    Cell cellTest = new Cell(0,0,'r');
    cellTest.setCellColumn(1);
    assertEquals(1,cellTest.getCellColumn());
  }

  @Test
  public void setType() throws Exception {
    Cell cellTest = new Cell(0,0,'r');
    cellTest.setType('P');
    assertEquals('P', cellTest.getType());
  }
}