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
  protected int agility;
  protected int intelligence;
  protected int defense;
  protected int experience;

  /**
   * Constructor untuk Actor.
   * @param actorName Nama dari Actor.
   * @param actorMap Kode Map dari Actor.
   * @param posX Posisi X dari Actor.
   * @param posY Posisi Y dari Actor.
   */
  public Actor(String actorName,int actorMap,int posX,int posY) {
    this.actorName = actorName;
    this.mapID = actorMap;
    this.actorRow = posX;
    this.actorColumn = posY;
    health = 0;
    strength = 0;
    defense = 0;
    agility  = 0;
    intelligence = 0;
    experience = 0;
  }

  /**
   * Getter dari Nama Actor.
   * @return String berupa Nama Actor.
   */
  public String getActorName() {
    return actorName;
  }

  /**
   * Getter dari Kode Map.
   * @return Int berupa Kode Map.
   */
  public int getMapID() {
    return mapID;
  }

  /**
   * Getter dari Ordinat Actor.
   * @return Int berupa Ordinat dari Actor.
   */
  public int getActorRow() {
    return actorRow;
  }

  /**
   * Getter dari Absis Actor.
   * @return Int berupa Absis dari Actor.
   */
  public int getActorColumn() {
    return actorColumn;
  }

  /**
   * Getter dari Health Point Actor.
   * @return Int berupa Health Point dari Actor.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Getter dari Strength (STR) dari Actor.
   * @return Int berupa Strength dari Actor.
   */
  public int getStrength() {
    return strength;
  }

  /**
   * Getter dari Defense (DEF) dari Actor.
   * @return Int berupa Defense dari Actor.
   */
  public int getDefense() {
    return defense;
  }

  /**
   * Getter dari Agility (AGI) dari Actor.
   * @return Int breupa Agility dari Actor.
   */
  public int getAgility() {
    return agility;
  }

  /**
   * Getter dari Intelligence (INT) dari Actor.
   * @return Int berupa Intelligence dari Actor.
   */
  public int getIntelligence() {
    return intelligence;
  }

  /**
   * Getter dari Experience (EXP) dari Actor.
   * @return Int berupa Experience dari Actor.
   */
  public int getExperience() {
    return experience;
  }

  /**
   * Setter dari Kode Map.
   * @param mapID Kode Map baru.
   */
  public void setMapID(int mapID) {
    this.mapID = mapID;
  }

  /**
   * Setter dari Ordinat Actor.
   * @param actorRow Ordinat baru Actor.
   */
  public void setActorRow(int actorRow) {
    this.actorRow = actorRow;
  }

  /**
   * Setter dari Absis Actor.
   * @param actorColumn Absis baru Actor.
   */
  public void setActorColumn(int actorColumn) {
    this.actorColumn = actorColumn;
  }

  /**
   * Setter dari Health Point Actor.
   * @param health Health Point baru Actor.
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Setter dari Strength Actor.
   * @param strength Strength baru Actor.
   */
  public void setStrength(int strength) {
    this.strength = strength;
  }

  /**
   * Setter dari Defense (DEF) Actor.
   * @param defense Defense baru Actor.
   */
  public void setDefense(int defense) {
    this.defense = defense;
  }

  /**
   * Setter dari Agility (AGI) Actor.
   * @param agility Agility baru Actor.
   */
  public void setAgility(int agility) {
    this.agility = agility;
  }

  /**
   * Setter dari Intelligence (INT) Actor.
   * @param intelligence Intelligence baru Actor.
   */
  public void setIntelligence(int intelligence) {
    this.intelligence = intelligence;
  }

  /**
   * Prosedur Bergerak dari Actor.
   * @param direction arah dari pergerakan Actor.
   */
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
