import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by edwin on 18/04/17.
 */
public class Main {
	public static void main(String args[]) throws FileNotFoundException {
		/*Scanner input = new Scanner(System.in);
		System.out.println("=====================");
		System.out.println("Welcome to Eedenshire");
		System.out.println("=====================");
		System.out.println();
		System.out.print("Enter your name: ");
		String playerName = input.nextLine();
		System.out.println("Hi, "+playerName+"!");
		System.out.print("What do you like to play as? ");
		String playerSkillset = input.nextLine();
		System.out.println("Skillset chosen: "+playerSkillset);*/
		GameManager Eedenshire = new GameManager("Edwin","Mage");
		Eedenshire.renderGame();
		Eedenshire.runGame();
	}
}
