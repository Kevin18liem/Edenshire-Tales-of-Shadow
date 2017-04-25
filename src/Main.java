import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by edwin on 18/04/17.
 */
public class Main {
  /**
   * Main Program.
   * @param args Input argumen.
   * @throws FileNotFoundException Exception IO, File Eksternal.
   */
  public static void main(String[] args) throws FileNotFoundException {
    GameManager eedenshire = new GameManager("Edwin","Warrior");
    System.out.print("> ");
    Scanner input = new Scanner(System.in);
    String in = input.nextLine();
    while (!in.equals("quit")) {
      eedenshire.runGame(in);
      System.out.print("> ");
      in = input.nextLine();
    }
  }
}
