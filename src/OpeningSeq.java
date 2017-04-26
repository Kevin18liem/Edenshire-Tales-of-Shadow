import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by user on 4/25/2017.
 */
public class OpeningSeq implements  ActionListener {
  JTextField name;
  JRadioButton warriorButton;
  JRadioButton rogueButton;
  JRadioButton mageButton;
  ButtonGroup playstyleGroup;
  JButton submitButton;

  public static String inputName = new String();
  public static String inputPlaystyle = new String();

  public String getInputName() {
    return inputName;
  }

  public String getInputPlaystyle() {
    return inputName;
  }

  /**
   * Konstruktor yang melakukan inisiasi.
   */
  OpeningSeq() {
    JFrame mainOpening = new JFrame("Eedenshire : The Tales of Shadows");
    mainOpening.getContentPane().setBackground(new Color(85, 85, 85));
    mainOpening.setExtendedState(Frame.MAXIMIZED_BOTH);
    mainOpening.setLayout(new GridLayout(1,1));

    JPanel mainScreen = new JPanel();
    mainScreen.setBackground(new Color(85, 85, 85));
    mainScreen.setLayout(new GridLayout(2,1));
    mainOpening.add(mainScreen);

    JPanel titleScreen = new JPanel();
    titleScreen.setBackground(Color.BLACK);
    titleScreen.setLayout(new GridLayout(1,1));
    mainScreen.add(titleScreen);

    ImageIcon titleImagepic = new ImageIcon("src/image/title.jpg");
    JLabel titleImage = new JLabel("",titleImagepic,JLabel.CENTER);
    titleScreen.add(titleImage);

    JPanel registerScreen = new JPanel();
    registerScreen.setBackground(Color.BLUE);
    registerScreen.setLayout(new GridLayout(3,1));
    mainScreen.add(registerScreen);

    JPanel nameScreen = new JPanel();
    nameScreen.setBackground(Color.BLACK);
    registerScreen.add(nameScreen);

    name = new JTextField();
    name.setPreferredSize(new Dimension(400,50));
    nameScreen.add(name);

    JPanel playstyleScreen  = new JPanel();
    playstyleScreen.setBackground(Color.BLACK);
    registerScreen.add(playstyleScreen);

    warriorButton = new JRadioButton("Warrior");
    warriorButton.setPreferredSize(new Dimension(100,25));
    warriorButton.setBackground(Color.BLACK);
    warriorButton.setForeground(Color.WHITE);
    rogueButton = new JRadioButton("Rogue");
    rogueButton.setPreferredSize(new Dimension(100,25));
    rogueButton.setBackground(Color.BLACK);
    rogueButton.setForeground(Color.WHITE);
    mageButton = new JRadioButton("Mage");
    mageButton.setPreferredSize(new Dimension(100,25));
    mageButton.setBackground(Color.BLACK);
    mageButton.setForeground(Color.WHITE);

    playstyleGroup = new ButtonGroup();
    playstyleGroup.add(warriorButton);
    playstyleGroup.add(rogueButton);
    playstyleGroup.add(mageButton);

    playstyleScreen.add(warriorButton);
    playstyleScreen.add(rogueButton);
    playstyleScreen.add(mageButton);

    JPanel submitScreen = new JPanel();
    submitScreen.setBackground(Color.BLACK);
    registerScreen.add(submitScreen);

    submitButton = new JButton("START GAME");
    submitButton.setPreferredSize(new Dimension(400,75));
    submitScreen.add(submitButton);

    submitButton.addActionListener(this);

    mainOpening.setVisible(true);
    mainOpening.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Memproses input aksi dari pengguna.
   * @param e sebagai input key terhadap aksi yang diambil oleh pengguna.
   */
  public void actionPerformed(ActionEvent e) {
    inputName = name.getText();
    if (warriorButton.isSelected()) {
      inputPlaystyle = "Warrior";
      GameManager eedenshire = new GameManager(inputName,inputPlaystyle);
      EdenshireUserInterface uinterface = new EdenshireUserInterface(eedenshire);
    }
    if (rogueButton.isSelected()) {
      inputPlaystyle = "Rogue";
      GameManager eedenshire = new GameManager(inputName,inputPlaystyle);
      EdenshireUserInterface uinterface = new EdenshireUserInterface(eedenshire);
    }
    if (mageButton.isSelected()) {
      inputPlaystyle = "Mage";
      GameManager eedenshire = new GameManager(inputName,inputPlaystyle);
      EdenshireUserInterface uinterface = new EdenshireUserInterface(eedenshire);
    }
  }
}

