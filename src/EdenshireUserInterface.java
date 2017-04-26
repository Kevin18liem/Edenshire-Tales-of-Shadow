/**
 * Created by Trevin Matthew on 4/18/2017.
 */

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.*;

public class EdenshireUserInterface implements Runnable {

  private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
  private static final String MOVE_UP = "move up";
  private static final String MOVE_DOWN = "move down";
  private static final String MOVE_RIGHT = "move right";
  private static final String MOVE_LEFT = "move left";
  private static final String START_TALK = "start talk";
  private static final String USE_SKILL_NORMAL = "use normal skill";
  private static final String USE_SKILL_1 = "use skill 1";
  private static final String USE_SKILL_2 = "use skill 2";
  private static final String USE_SKILL_3 = "use skill 3";
  private  static final String NEXT_PAGE = "next page";
  private  static final String PREVIOUS_PAGE = "previous page";
  private Color frames = new Color(138,133,131);
  private Color panels = new Color(238,215,161);
  private int status = 1;
  private int talkstatus = 0;
  private int idmonster;
  private int cektalk;
  private static Integer i;
  private Vector<String>  dialogue;
  private GameManager gameinput;
  private Thread gameloop;

  JFrame mainWindow = new JFrame("Edenshire : The Tales of Shadows");

  /**
   * Konstruktor EdenshireUserInterface menginisiasi atribut pada kelas EdenshireUserInterface.
   * @param gameInput Merupakan Game Manager yang mengatur semua kelas.
   */
  EdenshireUserInterface(GameManager gameInput) {
    mainWindow.setSize(1366, 720);
    mainWindow.getContentPane().setBackground(new Color(71, 92, 108));
    mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
    mainWindow.setLayout(new GridBagLayout());
    this.gameinput = gameInput;
    printscreen(mainWindow);
    mainWindow.setVisible(true);
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameloop = new Thread(this);
    gameloop.start();
  }

  /**
   * Untuk menjalankan Thread.
   */
  public void run() {
    long beforeTime;
    long timeDiff;
    long sleepTime;
    beforeTime = System.currentTimeMillis();
    while (true) {
      printscreen(mainWindow);
      timeDiff = System.currentTimeMillis() - beforeTime;
      sleepTime = (20 - timeDiff) * 90;
      if (sleepTime <= 0) {
        sleepTime = 5;
      }
      try {
        Thread.sleep(sleepTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      beforeTime = System.currentTimeMillis();
    }
  }

  /**
   * printScreen digunakan untuk menampilkan seluruh tampilan.
   * @param mainWindow sebagai parameter yang digunakan untuk menampilkan tampilan ke layar.
   */
  public void printscreen(JFrame mainWindow) {
    GridBagConstraints constraints = new GridBagConstraints();
    JPanel sideBar = new JPanel();
    sideBar.setBackground(new Color(85,85,85));
    sideBar.setLayout(new GridLayout(2,1));
    constraints.ipady = 0;
    constraints.weighty = 1;
    constraints.anchor = GridBagConstraints.FIRST_LINE_START;
    constraints.insets = new Insets(10,10,10,5);
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.ipadx = 15;
    constraints.ipady = 720;
    mainWindow.add(sideBar,constraints);
    JLabel labelQuest = new JLabel();
    JPanel sideBarStat = new JPanel();
    sideBarStat.setBackground(panels);
    sideBarStat.setBorder(BorderFactory.createLineBorder(frames,10));
    sideBarStat.setLayout(new GridLayout(2,1));
    constraints.ipady = 0;
    constraints.weighty = 1;
    constraints.anchor = GridBagConstraints.PAGE_START;
    sideBar.add(sideBarStat,constraints);
    JPanel sideBarStatPic =  new JPanel();
    ImageIcon image = new ImageIcon("src/image/icon2.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    sideBarStatPic.setBackground(panels);
    sideBarStatPic.add(label);
    sideBarStat.add(sideBarStatPic);
    JPanel sideBarStatInfo = new JPanel();
    sideBarStatInfo.setLayout(new GridLayout(4,2));
    sideBarStatInfo.setBackground(panels);
    sideBarStat.add(sideBarStatInfo);
    JPanel sideBarStatInfoNamePic = new JPanel();
    ImageIcon nameicon = new ImageIcon("src/image/icontest.png");
    JLabel iconname = new JLabel("NAMA", nameicon, JLabel.CENTER);
    sideBarStatInfoNamePic.setBackground(panels);
    sideBarStatInfoNamePic.add(iconname);
    sideBarStatInfo.add(sideBarStatInfoNamePic);
    JPanel sideBarStatInfoName = new JPanel();
    sideBarStatInfoName.setBackground(panels);
    sideBarStatInfoName.setLayout(new GridLayout(1,1));
    String nama = new String();
    nama = getNameInfo();
    JLabel labelName = new JLabel();
    labelName.setText(nama);
    labelName.setHorizontalAlignment(JLabel.LEFT);
    labelName.setVerticalAlignment(JLabel.CENTER);
    labelName.setBackground(panels);
    labelName.setOpaque(true);
    sideBarStatInfoName.add(labelName);
    sideBarStatInfo.add(sideBarStatInfoName);
    JPanel sideBarStatInfoHpPic = new JPanel();
    ImageIcon hpIcon = new ImageIcon("src/image/icontest.png");
    JLabel iconHp = new JLabel("HP", hpIcon, JLabel.CENTER);
    sideBarStatInfoHpPic.setBackground(panels);
    sideBarStatInfoHpPic.add(iconHp);
    sideBarStatInfo.add(sideBarStatInfoHpPic);
    JPanel sideBarStatInfoHp = new JPanel();
    sideBarStatInfoHp.setBackground(panels);
    sideBarStatInfoHp.setLayout(new GridLayout(1,1));
    JLabel labelHp = new JLabel();
    String hp = new String();
    hp = gethpinfo();
    labelHp.setText(hp);
    labelHp.setHorizontalAlignment(JLabel.LEFT);
    labelHp.setVerticalAlignment(JLabel.CENTER);
    labelHp.setBackground(panels);
    labelHp.setOpaque(true);
    sideBarStatInfoHp.add(labelHp);
    sideBarStatInfo.add(sideBarStatInfoHp);
    JPanel sideBarStatInfoLevelPic = new JPanel();
    ImageIcon levelicon = new ImageIcon("src/image/icontest.png");
    JLabel iconlevel = new JLabel("LEVEL", levelicon, JLabel.CENTER);
    sideBarStatInfoLevelPic.setBackground(panels);
    sideBarStatInfoLevelPic.add(iconlevel);
    sideBarStatInfo.add(sideBarStatInfoLevelPic);
    JPanel sideBarStatInfoLevel = new JPanel();
    sideBarStatInfoLevel.setBackground(panels);
    sideBarStatInfoLevel.setLayout(new GridLayout(1,1));
    JLabel labelLevel = new JLabel();
    String level = new String();
    level = getLevelInfo();
    labelLevel.setText(level);
    labelLevel.setHorizontalAlignment(JLabel.LEFT);
    labelLevel.setVerticalAlignment(JLabel.CENTER);
    labelLevel.setBackground(panels);
    labelLevel.setOpaque(true);
    sideBarStatInfoLevel.add(labelLevel);
    sideBarStatInfo.add(sideBarStatInfoLevel);
    JPanel sideBarStatInfoExpPic = new JPanel();
    ImageIcon expIcon = new ImageIcon("src/image/icontest.png");
    JLabel iconExp = new JLabel("EXP", expIcon, JLabel.CENTER);
    sideBarStatInfoExpPic.setBackground(panels);
    sideBarStatInfoExpPic.add(iconExp);
    sideBarStatInfo.add(sideBarStatInfoExpPic);
    JPanel sideBarStatInfoExp = new JPanel();
    sideBarStatInfoExp.setBackground(panels);
    sideBarStatInfoExp.setLayout(new GridLayout(1,1));
    JLabel labelExp = new JLabel();
    String exp = new String();
    exp = getexpinfo();
    labelExp.setText(exp);
    labelExp.setHorizontalAlignment(JLabel.LEFT);
    labelExp.setVerticalAlignment(JLabel.CENTER);
    labelExp.setBackground(panels);
    labelExp.setOpaque(true);
    sideBarStatInfoExp.add(labelExp);
    sideBarStatInfo.add(sideBarStatInfoExp);
    JPanel sideBarSkill = new JPanel();
    sideBarSkill.setBackground(panels);
    sideBarSkill.setBorder(BorderFactory.createLineBorder(frames,10));
    sideBarSkill.setLayout(new GridLayout(7,2));
    constraints.ipady = 0;
    constraints.weighty = 1;
    constraints.anchor = GridBagConstraints.PAGE_END;
    sideBar.add(sideBarSkill,constraints);
    JPanel sideBarSkillInfoStrPic = new JPanel();
    ImageIcon strIcon = new ImageIcon("src/image/icontest.png");
    JLabel iconStr = new JLabel("STR", strIcon, JLabel.CENTER);
    sideBarSkillInfoStrPic.setBackground(panels);
    sideBarSkillInfoStrPic.add(iconStr);
    sideBarSkill.add(sideBarSkillInfoStrPic);
    JPanel sideBarSkillInfoStr = new JPanel();
    sideBarSkillInfoStr.setBackground(Color.red);
    sideBarSkillInfoStr.setLayout(new GridLayout(1,1));
    JLabel labelStr = new JLabel();
    String str = new String();
    str = getstrinfo();
    labelStr.setText(str);
    labelStr.setHorizontalAlignment(JLabel.LEFT);
    labelStr.setVerticalAlignment(JLabel.CENTER);
    labelStr.setBackground(panels);
    labelStr.setOpaque(true);
    sideBarSkillInfoStr.add(labelStr);
    sideBarSkill.add(sideBarSkillInfoStr);
    JPanel sideBarSkillInfoAgiPic = new JPanel();
    ImageIcon agiIcon = new ImageIcon("src/image/icontest.png");
    JLabel iconAgi = new JLabel("AGI", agiIcon, JLabel.CENTER);
    sideBarSkillInfoAgiPic.setBackground(panels);
    sideBarSkillInfoAgiPic.add(iconAgi);
    sideBarSkill.add(sideBarSkillInfoAgiPic);
    JPanel sideBarSkillInfoAgi = new JPanel();
    sideBarSkillInfoAgi.setBackground(panels);
    sideBarSkillInfoAgi.setLayout(new GridLayout(1,1));
    JLabel labelAgi = new JLabel();
    String agi = new String();
    agi = getagiinfo();
    labelAgi.setText(agi);
    labelAgi.setHorizontalAlignment(JLabel.LEFT);
    labelAgi.setVerticalAlignment(JLabel.CENTER);
    labelAgi.setBackground(panels);
    labelAgi.setOpaque(true);
    sideBarSkillInfoAgi.add(labelAgi);
    sideBarSkill.add(sideBarSkillInfoAgi);
    JPanel sideBarSkillInfoIntPic = new JPanel();
    ImageIcon intIcon = new ImageIcon("src/image/icontest.png");
    JLabel iconInt = new JLabel("INT", intIcon, JLabel.CENTER);
    sideBarSkillInfoIntPic.setBackground(panels);
    sideBarSkillInfoIntPic.add(iconInt);
    sideBarSkill.add(sideBarSkillInfoIntPic);
    JPanel sideBarSkillInfoInt = new JPanel();
    sideBarSkillInfoInt.setBackground(panels);
    sideBarSkillInfoInt.setLayout(new GridLayout(1,1));
    JLabel labelInt = new JLabel();
    String intel = new String();
    intel = getintinfo();
    labelInt.setText(intel);
    labelInt.setHorizontalAlignment(JLabel.LEFT);
    labelInt.setVerticalAlignment(JLabel.CENTER);
    labelInt.setBackground(panels);
    labelInt.setOpaque(true);
    sideBarSkillInfoInt.add(labelInt);
    sideBarSkill.add(sideBarSkillInfoInt);
    JPanel sideBarSkillInfoDefPic = new JPanel();
    ImageIcon defIcon = new ImageIcon("src/image/icontest.png");
    JLabel iconDef = new JLabel("DEF", defIcon, JLabel.CENTER);
    sideBarSkillInfoDefPic.setBackground(panels);
    sideBarSkillInfoDefPic.add(iconDef);
    sideBarSkill.add(sideBarSkillInfoDefPic);
    JPanel sideBarSkillInfoDef = new JPanel();
    sideBarSkillInfoDef.setBackground(panels);
    sideBarSkillInfoDef.setLayout(new GridLayout(1,1));
    JLabel labelDef = new JLabel();
    String def = new String();
    def = getdefinfo();
    labelDef.setText(def);
    labelDef.setHorizontalAlignment(JLabel.LEFT);
    labelDef.setVerticalAlignment(JLabel.CENTER);
    labelDef.setBackground(panels);
    labelDef.setOpaque(true);
    sideBarSkillInfoDef.add(labelDef);
    sideBarSkill.add(sideBarSkillInfoDef);
    JPanel sideBarSkillInfoSkillOnePic = new JPanel();
    ImageIcon soneIcon = new ImageIcon("src/image/icontest.png");
    JLabel iconS1 = new JLabel("SKILL 1(W)", soneIcon, JLabel.CENTER);
    sideBarSkillInfoSkillOnePic.setBackground(panels);
    sideBarSkillInfoSkillOnePic.add(iconS1);
    sideBarSkill.add(sideBarSkillInfoSkillOnePic);
    JPanel sideBarSkillInfoSkillOne = new JPanel();
    sideBarSkillInfoSkillOne.setBackground(panels);
    sideBarSkillInfoSkillOne.setLayout(new GridLayout(1,1));
    JLabel labelS1 = new JLabel();
    String sone = new String();
    sone = getS1Info();
    labelS1.setText(sone);
    labelS1.setHorizontalAlignment(JLabel.LEFT);
    labelS1.setVerticalAlignment(JLabel.CENTER);
    labelS1.setBackground(panels);
    labelS1.setOpaque(true);
    sideBarSkillInfoSkillOne.add(labelS1);
    sideBarSkill.add(sideBarSkillInfoSkillOne);
    JPanel sideBarSkillInfoSkillTwoPic = new JPanel();
    ImageIcon stwoicon = new ImageIcon("src/image/icontest.png");
    JLabel iconS2 = new JLabel("SKILL 2(E)", stwoicon, JLabel.CENTER);
    sideBarSkillInfoSkillTwoPic.setBackground(panels);
    sideBarSkillInfoSkillTwoPic.add(iconS2);
    sideBarSkill.add(sideBarSkillInfoSkillTwoPic);
    JPanel sideBarSkillInfoSkillTwo = new JPanel();
    sideBarSkillInfoSkillTwo.setBackground(panels);
    sideBarSkillInfoSkillTwo.setLayout(new GridLayout(1,1));
    String stwo = new String();
    JLabel labelS2 = new JLabel();
    stwo = getS2Info();
    labelS2.setText(stwo);
    labelS2.setHorizontalAlignment(JLabel.LEFT);
    labelS2.setVerticalAlignment(JLabel.CENTER);
    labelS2.setBackground(panels);
    labelS2.setOpaque(true);
    sideBarSkillInfoSkillTwo.add(labelS2);
    sideBarSkill.add(sideBarSkillInfoSkillTwo);
    JPanel sideBarSkillInfoSkillThreePic = new JPanel();
    ImageIcon sthreeicon = new ImageIcon("src/image/icontest.png");
    JLabel iconS3 = new JLabel("SKILL 3(R)", sthreeicon, JLabel.CENTER);
    sideBarSkillInfoSkillThreePic.setBackground(panels);
    sideBarSkillInfoSkillThreePic.add(iconS3);
    sideBarSkill.add(sideBarSkillInfoSkillThreePic);
    JPanel sideBarSkillInfoSkillThree = new JPanel();
    sideBarSkillInfoSkillThree.setBackground(panels);
    sideBarSkillInfoSkillThree.setLayout(new GridLayout(1,1));
    JLabel labelS3 = new JLabel();
    String sthree = new String();
    sthree = getS3Info();
    labelS3.setText(sthree);
    labelS3.setHorizontalAlignment(JLabel.LEFT);
    labelS3.setVerticalAlignment(JLabel.CENTER);
    labelS3.setBackground(panels);
    labelS3.setOpaque(true);
    sideBarSkillInfoSkillThree.add(labelS3);
    sideBarSkill.add(sideBarSkillInfoSkillThree);
    JPanel mainBar = new JPanel();
    mainBar.setBorder(BorderFactory.createLineBorder(frames,5));
    constraints.ipady = 0;
    constraints.weighty = 1;
    constraints.anchor = GridBagConstraints.PAGE_START;
    constraints.insets = new Insets(5,10,5,10);
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.ipadx = 800;
    constraints.ipady = 100;
    mainBar.setLayout(new CardLayout());
    JPanel mainBarMap = new JPanel();
    mainBarMap.setBackground(panels);
    mainBarMap.setLayout(new GridLayout(1,1));
    mainBar.add(mainBarMap);
    JLabel mapMatrix = new JLabel();
    mapMatrix.setHorizontalAlignment(JLabel.CENTER);
    mapMatrix.setVerticalAlignment(JLabel.TOP);
    mapMatrix.setMaximumSize(new Dimension(100,400));
    mapMatrix.setMinimumSize(new Dimension(10,400));
    mapMatrix.setFont(new Font("Consolas", Font.PLAIN, 19));
    mapMatrix.setText(mapUpdate());
    mainBarMap.add(mapMatrix);
    JPanel mainBarBattleScene = new JPanel();
    mainBarBattleScene.setBackground(Color.BLACK);
    mainBarBattleScene.setLayout(new GridLayout(2,1));

    JPanel mainBarBattleScenePlayer = new JPanel();
    mainBarBattleScenePlayer.setLayout(new GridLayout(1,1));
    JLabel battlePlayerInfo = new JLabel();
    battlePlayerInfo.setText(gethpinfo());
    mainBarBattleScenePlayer.add(battlePlayerInfo);
    mainBarBattleScene.add(mainBarBattleScenePlayer);

    JPanel mainBarBattleSceneEnemy = new JPanel();
    mainBarBattleSceneEnemy.setBackground(Color.LIGHT_GRAY);
    mainBarBattleSceneEnemy.setLayout(new GridLayout(1,1));
    JLabel battleEnemyInfo = new JLabel();
    mainBarBattleSceneEnemy.add(battleEnemyInfo);
    mainBarBattleScene.add(mainBarBattleSceneEnemy);

    mainBar.add(mainBarBattleScene);

    mainWindow.add(mainBar,constraints);

    if (status == 1) {
      mainBarMap.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
      mainBarMap.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
      mainBarMap.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
      mainBarMap.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
      mainBarMap.getInputMap(IFW).put(KeyStroke.getKeyStroke("G"), START_TALK);

      mainBarMap.getActionMap().put(MOVE_UP, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          gameinput.runGame("w");
          idmonster = gameinput.readyBattle();
          if (idmonster != -1) {
            status = 2;
          }
          mapMatrix.setText(mapUpdate());
        }
      });
      mainBarMap.getActionMap().put(MOVE_DOWN, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          gameinput.runGame("s");
          idmonster = gameinput.readyBattle();
          if (idmonster != -1) {
            status = 2;
          }
          mapMatrix.setText(mapUpdate());
        }
      });
      mainBarMap.getActionMap().put(MOVE_RIGHT, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          gameinput.runGame("d");
          idmonster = gameinput.readyBattle();
          if (idmonster != -1) {
            status = 2;
          }
          mapMatrix.setText(mapUpdate());
        }
      });
      mainBarMap.getActionMap().put(MOVE_LEFT, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          gameinput.runGame("a");
          idmonster = gameinput.readyBattle();
          if (idmonster != -1) {
            status = 2;
          }
          mapMatrix.setText(mapUpdate());
        }
      });
      mainBarMap.getActionMap().put(START_TALK, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // cek bisa mulai talk
          dialogue = gameinput.talk();
          if (dialogue.size() != 0) {
            talkstatus = 1;
          }
          // else pesan error
        }
      });
    } else if (status == 2) {
      ((CardLayout) mainBar.getLayout()).previous(mainBar);
      while ((gameinput.getPlayer().getHealth() > 0)
              && (gameinput.getMonsters().get(idmonster).getHealth() > 0)) {
        mainBarBattleScene.getInputMap(IFW).put(KeyStroke.getKeyStroke("Q"), USE_SKILL_NORMAL);
        mainBarBattleScene.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), USE_SKILL_1);
        mainBarBattleScene.getInputMap(IFW).put(KeyStroke.getKeyStroke("E"), USE_SKILL_2);
        mainBarBattleScene.getInputMap(IFW).put(KeyStroke.getKeyStroke("R"), USE_SKILL_3);
        mainBarBattleScene.getActionMap().put(USE_SKILL_NORMAL, new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            gameinput.battleModeTest(gameinput.getMonsters().get(gameinput.readyBattle()),'Q');
            //print semua data
            battlePlayerInfo.setText(gethpinfo());
            battleEnemyInfo.setText(
                    String.valueOf(gameinput.getMonsters()
                            .get(gameinput.readyBattle()).getHealth()));
          }
        });
        mainBarBattleScene.getActionMap().put(USE_SKILL_1, new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            gameinput.battleModeTest(gameinput.getMonsters().get(gameinput.readyBattle()),'W');
            //print semua data
            battlePlayerInfo.setText(gethpinfo());
            battleEnemyInfo.setText(
                    String.valueOf(gameinput.getMonsters()
                            .get(gameinput.readyBattle()).getHealth()));
          }
        });

        mainBarBattleScene.getActionMap().put(USE_SKILL_2, new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            gameinput.battleModeTest(gameinput.getMonsters().get(gameinput.readyBattle()),'E');
            //print semua data
            battlePlayerInfo.setText(gethpinfo());
            battleEnemyInfo.setText(
                    String.valueOf(gameinput.getMonsters()
                            .get(gameinput.readyBattle()).getHealth()));
          }
        });

        mainBarBattleScene.getActionMap().put(USE_SKILL_3, new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            gameinput.battleModeTest(gameinput.getMonsters().get(gameinput.readyBattle()),'R');
            //print semua data
            battlePlayerInfo.setText(gethpinfo());
            battleEnemyInfo.setText(
                    String.valueOf(gameinput.getMonsters()
                            .get(gameinput.readyBattle()).getHealth()));
          }
        });
      }
      gameinput.battleModeAfter(gameinput.getMonsters().get(gameinput.readyBattle()));
      if (gameinput.getPlayer().getHealth() == 0) {
        mainWindow.dispose();
      }
      labelQuest.setText("<html>" + getQuestInfo() + "</html>");
      status = 1;
      ((CardLayout) mainBar.getLayout()).next(mainBar);
    }
    JPanel infoBar = new JPanel();
    infoBar.setBackground(new Color(85,85,85));
    infoBar.setLayout(new GridLayout(2,1));
    constraints.ipady = 0;
    constraints.weighty = 1;
    constraints.anchor = GridBagConstraints.FIRST_LINE_END;
    constraints.insets = new Insets(10,5,10,10);
    constraints.gridx = 3;
    constraints.gridy = 0;
    constraints.ipadx = 0;
    constraints.ipady = 720;
    mainWindow.add(infoBar,constraints);

    JPanel infoBarMap = new JPanel();
    infoBarMap.setBackground(panels);
    infoBarMap.setBorder(BorderFactory.createLineBorder(frames,10));
    ImageIcon mapimage = new ImageIcon("src/image/mapicon.png");
    JLabel maplabel = new JLabel("", mapimage, JLabel.CENTER);
    infoBarMap.add(maplabel);
    constraints.ipady = 0;
    constraints.weighty = 1;
    constraints.anchor = GridBagConstraints.PAGE_START;
    infoBar.add(infoBarMap,constraints);

    JPanel infoBarQuest  = new JPanel();
    infoBarQuest.setBackground(panels);
    infoBarQuest.setBorder(BorderFactory.createLineBorder(frames,10));
    constraints.ipady = 0;
    constraints.weighty = 1;
    constraints.anchor  = GridBagConstraints.PAGE_END;
    infoBarQuest.setLayout(new GridLayout(1,1));
    String quest = new String();
    quest = getQuestInfo();
    labelQuest.setText("<html>" + quest + "</html>");
    labelQuest.setHorizontalAlignment(JLabel.LEFT);
    labelQuest.setVerticalAlignment(JLabel.TOP);
    labelQuest.setBackground(panels);
    labelQuest.setOpaque(true);
    infoBarQuest.add(labelQuest);

    infoBar.add(infoBarQuest,constraints);
    JPanel scenesBar  = new JPanel();
    scenesBar.setBackground(panels);
    scenesBar.setLayout(new GridLayout(1,2));
    constraints.ipady = 0;
    constraints.weighty = 1;
    constraints.anchor = GridBagConstraints.PAGE_END;
    constraints.insets = new Insets(5,3,5,3);
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.ipadx = 250;
    constraints.ipady = 10;
    mainWindow.add(scenesBar,constraints);
    JPanel scenesBarDes = new JPanel();
    scenesBarDes.setBackground(panels);
    scenesBarDes.setBorder(BorderFactory.createLineBorder(frames,7));
    constraints.ipadx = 0;
    constraints.weightx = 1;
    constraints.anchor  = GridBagConstraints.LINE_START;
    scenesBarDes.setLayout(new GridLayout(1,1));
    JLabel labelDes = new JLabel();
    labelDes.setHorizontalAlignment(JLabel.LEFT);
    labelDes.setVerticalAlignment(JLabel.TOP);
    labelDes.setBackground(panels);
    labelDes.setOpaque(true);
    scenesBarDes.add(labelDes);
    scenesBar.add(scenesBarDes,constraints);
    JPanel scenesBarScene = new JPanel();
    scenesBarScene.setBackground(panels);
    scenesBarScene.setBorder(BorderFactory.createLineBorder(frames,7));
    scenesBarScene.setLayout(new CardLayout());
    constraints.ipadx = 0;
    constraints.weightx = 1;
    constraints.anchor  = GridBagConstraints.LINE_END;
    JPanel scenesBarScenePlayer = new JPanel();
    scenesBarScenePlayer.setLayout(new GridLayout(1,2));
    JPanel dialoguePlayerPic = new JPanel();
    ImageIcon imagePlayerDialogue = new ImageIcon("src/image/icon2.png");
    JLabel labelPlayerDialogue = new JLabel("", imagePlayerDialogue, JLabel.CENTER);
    dialoguePlayerPic.setBackground(panels);
    dialoguePlayerPic.add(labelPlayerDialogue);
    scenesBarScenePlayer.add(dialoguePlayerPic);
    JPanel dialoguePlayerBox  = new JPanel();
    dialoguePlayerBox.setLayout(new GridLayout(1,1));
    JLabel labelPlayerDialogueBox = new JLabel();
    labelPlayerDialogueBox.setMinimumSize(new Dimension(130,150));
    labelPlayerDialogueBox.setMaximumSize(new Dimension(130,150));
    labelPlayerDialogueBox.setHorizontalAlignment(JLabel.LEFT);
    labelPlayerDialogueBox.setVerticalAlignment(JLabel.TOP);
    labelPlayerDialogueBox.setBackground(panels);
    labelPlayerDialogueBox.setOpaque(true);
    dialoguePlayerBox.add(labelPlayerDialogueBox);
    scenesBarScenePlayer.add(dialoguePlayerBox);
    scenesBarScene.add(scenesBarScenePlayer, "PLAYER DIALOGUE");
    JPanel scenesBarSceneActor = new JPanel();
    scenesBarSceneActor.setLayout(new GridLayout(1,2));
    JPanel dialogueActorPic = new JPanel();
    ImageIcon imageActorDialogue = new ImageIcon("src/image/icon2.png");
    JLabel labelActorDialogue = new JLabel("", imageActorDialogue, JLabel.CENTER);
    dialogueActorPic.setBackground(panels);
    dialogueActorPic.add(labelActorDialogue);
    scenesBarSceneActor.add(dialogueActorPic);
    JPanel dialogueActorBox  = new JPanel();
    dialogueActorBox.setLayout(new GridLayout(1,1));
    JLabel labelActorDialogueBox = new JLabel();
    labelActorDialogueBox.setMinimumSize(new Dimension(130,150));
    labelActorDialogueBox.setMaximumSize(new Dimension(130,150));
    labelActorDialogueBox.setHorizontalAlignment(JLabel.LEFT);
    labelActorDialogueBox.setVerticalAlignment(JLabel.TOP);
    labelActorDialogueBox.setBackground(panels);
    labelActorDialogueBox.setOpaque(true);
    dialogueActorBox.add(labelActorDialogueBox);
    scenesBarSceneActor.add(dialogueActorBox);
    scenesBarScene.add(scenesBarSceneActor,"NPC DIALOGUE");
    scenesBar.add(scenesBarScene,constraints);
    if (talkstatus == 1) {
      i = 0 ;
      labelPlayerDialogueBox.setText("<html>" + dialogue.get(0) + "</html>");
      labelActorDialogueBox.setText("<html>" + dialogue.get(0) + "</html>");
      i++;
      while (i < dialogue.size()) {
        scenesBarScene.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), PREVIOUS_PAGE);
        scenesBarScene.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), NEXT_PAGE);
        scenesBarScene.getActionMap().put(PREVIOUS_PAGE, new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (i >= 0) {
              labelPlayerDialogueBox.setText("<html>" + dialogue.get(i) + "</html>");
              labelActorDialogueBox.setText("<html>" + dialogue.get(i) + "</html>");
              ((CardLayout) scenesBarScene.getLayout()).previous(scenesBarScene);
              i--;
            }
          }
        });
        scenesBarScene.getActionMap().put(NEXT_PAGE, new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (i <= dialogue.size()) {
              labelPlayerDialogueBox.setText("<html>" + dialogue.get(i) + "</html>");
              labelActorDialogueBox.setText("<html>" + dialogue.get(i) + "</html>");
              ((CardLayout) scenesBarScene.getLayout()).next(scenesBarScene);
              i++;
            }
          }
        });
      }
      gameinput.afterTalk();
      labelQuest.setText("<html>" + getQuestInfo() + "</html>");
      talkstatus = 0;
    }
    if (status == 1) {
      gameinput.runGame("moveNPC");
      idmonster = gameinput.readyBattle();
      if (idmonster != -1) {
        status = 2;
      }
    }
    mapMatrix.setText(mapUpdate());
  }

  /**
   * Getter nama player.
   * @return String merupakan nama dari player.
   */
  public String getNameInfo() {
    return gameinput.getPlayer().getActorName();
  }

  /**
   * Getter untuk Heatl Point player.
   * @return String merupakan Health Point dari player.
   */
  public String gethpinfo() {
    return String.valueOf(gameinput.getPlayer().getHealth());
  }

  /**
   * Getter untuk level dari player.
   * @return String yang merupakan info level dari player.
   */
  public String getLevelInfo() {
    return String.valueOf(gameinput.getPlayer().getLevel());
  }

  /**
   * Getter untuk info EXP dari player.
   * @return String yang merupakan info EXP player.
   */
  public String getexpinfo() {
    return String.valueOf(gameinput.getPlayer().getExperience());
  }

  /**
   * Getter untuk info STR dari player.
   * @return String yang merupakan info STR player.
   */
  public String getstrinfo() {
    return String.valueOf(gameinput.getPlayer().getStrength());
  }

  /**
   * Getter untuk info AGI dari player.
   * @return String yang merupakan info AGI player.
   */
  public String getagiinfo() {
    return String.valueOf(gameinput.getPlayer().getAgility());
  }

  /**
   * Getter untuk info INT dari player.
   * @return String yang merupakan info INT player.
   */
  public String getintinfo() {
    return String.valueOf(gameinput.getPlayer().getIntelligence());
  }

  /**
   * Getter untuk info Defend dari player.
   * @return String yang merupakan info Defend player.
   */
  public String getdefinfo() {
    return String.valueOf(gameinput.getPlayer().getDefense());
  }

  /**
   * Getter untuk info Skill ke -1 dari player.
   * @return String yang merupakan info skill ke -1 player.
   */
  public String getS1Info() {
    return gameinput.getPlayer().getSkillset().getSkills().get(0).getSkillName();
  }

  /**
   * Getter untuk info Skill ke -2 dari player.
   * @return String yang merupakan info skill ke -2 player.
   */
  public String getS2Info() {
    return gameinput.getPlayer().getSkillset().getSkills().get(1).getSkillName();
  }

  /**
   * Getter untuk info Skill ke -3 dari player.
   * @return String yang merupakan info skill ke -3 player.
   */
  public String getS3Info() {
    return gameinput.getPlayer().getSkillset().getSkills().get(2).getSkillName();
  }

  /**
   * Getter untuk info quest yang dijalankan player.
   * @return String yang merupakan info quest player.
   */
  public String getQuestInfo() {
    return gameinput.getQuests().get(0).getCurrentMission().getInstruction();
  }

  /**
   * Membuat Peta permainan.
   * @return String yang merupakan peta.
   */
  public String mapUpdate() {
    String matrix = new String();
    String currentString = new String();
    matrix = "<html>";
    int maxrow =  gameinput.getCurrentMap().getRow();
    int maxcol =  gameinput.getCurrentMap().getColumn();
    for (int i = 0;i < maxrow;i++) {
      for (int j = 0;j < maxcol; j++) {
        char charmap = gameinput.getCurrentMap().getCell(i,j).getType();
        currentString = String.valueOf(charmap);
        matrix = matrix + currentString + " ";
      }
      matrix = matrix + "<br>";
    }
    matrix = matrix + "</html>";
    return matrix;
  }
}

