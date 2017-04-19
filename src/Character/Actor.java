package Character;

/**
 * Created by edwin on 18/04/17.
 */
public abstract class Actor implements Movement {
  protected String actorName;
  protected int mapID;
  protected int actorRow;
  protected int actorColumn;
  protected int health;
  protected int strength;
  protected int defense;

  public Actor(String actorName,int actorMap,int posX,int posY) {
    this.actorName = actorName;
    this.mapID = actorMap;
    this.actorRow = posX;
    this.actorColumn = posY;
    health = 0;
    strength = 0;
    defense = 0;
  }

  public String getActorName() {
    return actorName;
  }

  public int getMapID() {
    return mapID;
  }

  public int getActorRow() {
    return actorRow;
  }

  public int getActorColumn() {
    return actorColumn;
  }

  public int getHealth() {
    return health;
  }

  public int getStrength() {
    return strength;
  }

  public int getDefense() {
    return defense;
  }

  public void setMapID(int mapID) {
    this.mapID = mapID;
  }

  public void setActorRow(int actorRow) {
    this.actorRow = actorRow;
  }

  public void setActorColumn(int actorColumn) {
    this.actorColumn = actorColumn;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }

  public void move(String direction) {

    switch (direction) {
      case "up":
        actorRow = actorRow - 1;
        break;
      case "down":
        actorRow = actorRow + 1;
        break;
      case "left":
        actorColumn = actorColumn -1;
        break;
      case "right":
        actorColumn = actorColumn + 1;
        break;
    }
  }
}
