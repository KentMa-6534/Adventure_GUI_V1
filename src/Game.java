import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.NoRouteToHostException;
import java.nio.channels.AcceptPendingException;
import java.security.PublicKey;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Game {

	JFrame gameWindow = new JFrame(); // Application window
	Container gameContainer = gameWindow.getContentPane(); // Container for elements
	JPanel titlePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel; // Displays panel on screen
	JLabel titleLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName; // Displays text on screen
	Font titleFont = new Font("Times New Roman", Font.BOLD, 90); // Creates customized font
	Font startButtonFont = new Font("Times New Roman", Font.BOLD, 45);
	Font gameFont = new Font("Times New Roman", Font.PLAIN, 28);
	JButton startButton, choice1, choice2, choice3, choice4;
	JTextArea mainTextArea;
	int monsterHP;
	int monsterDamage;
	int playerHP;
	int playerDamage;
	int silverRing;
	String weapon, position;

	TitleScreenHandler tsHandler = new TitleScreenHandler();
	ChoiceHandler cHandler = new ChoiceHandler();

	public static void main(String[] args) {

		new Game();
	}

	public Game() {
		// JFrame
		gameWindow.setSize(800, 600); // Sets size of window
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Creates an exit button on window
		gameWindow.getContentPane().setBackground(Color.black); // Creates black background on window
		gameWindow.setLayout(null); // Disables default layout with null

		// JPanel
		titlePanel = new JPanel();
		titlePanel.setBounds(100, 100, 600, 150); // (x,y) is the position in pixels from the upper-left of the window.
		titlePanel.setBackground(Color.black); // (width, height) is the size of the title panel.
		titleLabel = new JLabel("ADVENTURE");
		titleLabel.setForeground(Color.white); // Adds the white text over the blue panel. Set foreground after making
												// new JLabel.
		titleLabel.setFont(titleFont); // Sets font for title label.

		// JPanel
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(Color.BLACK);

		// JButton
		startButton = new JButton("START");
		startButton.setBackground(Color.BLACK);
		startButton.setForeground(Color.WHITE);
		startButton.setFont(startButtonFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);

		// Adds JComponents, JPanel to Container.
		startButtonPanel.add(startButton);
		titlePanel.add(titleLabel);
		gameContainer.add(titlePanel);
		gameContainer.add(startButtonPanel);
		gameWindow.setVisible(true); // Makes sure your window is visible

	}

	public void createGameScreen() {
		titlePanel.setVisible(false);
		startButtonPanel.setVisible(false);

		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100, 100, 600, 150);
		mainTextPanel.setBackground(Color.BLACK);
		gameContainer.add(mainTextPanel);

		mainTextArea = new JTextArea();
		mainTextArea.setBounds(100, 100, 600, 250);
		mainTextArea.setBackground(Color.BLACK);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(gameFont);
		mainTextArea.setLineWrap(true);
		mainTextPanel.add(mainTextArea);

		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(250, 350, 300, 150);
		choiceButtonPanel.setBackground(Color.BLACK);
		choiceButtonPanel.setLayout(new GridLayout(4, 1));

		gameContainer.add(choiceButtonPanel);

		choice1 = new JButton("Choice 1");
		choice1.setBackground(Color.black);
		choice1.setForeground(Color.white);
		choice1.setFont(gameFont);
		choice1.setFocusPainted(false);
		choice1.addActionListener(cHandler);
		choice1.setActionCommand("c1");
		choiceButtonPanel.add(choice1);

		choice2 = new JButton("Choice 2");
		choice2.setBackground(Color.black);
		choice2.setForeground(Color.white);
		choice2.setFont(gameFont);
		choice2.setFocusPainted(false);
		choice2.addActionListener(cHandler);
		choice2.setActionCommand("c2");
		choiceButtonPanel.add(choice2);

		choice3 = new JButton("Choice 3");
		choice3.setBackground(Color.black);
		choice3.setForeground(Color.white);
		choice3.setFont(gameFont);
		choice3.setFocusPainted(false);
		choice3.addActionListener(cHandler);
		choice3.setActionCommand("c3");
		choiceButtonPanel.add(choice3);

		choice4 = new JButton("Choice 4");
		choice4.setBackground(Color.black);
		choice4.setForeground(Color.white);
		choice4.setFont(gameFont);
		choice4.setFocusPainted(false);
		choice4.addActionListener(cHandler);
		choice4.setActionCommand("c4");
		choiceButtonPanel.add(choice4);

		playerPanel = new JPanel();
		playerPanel.setBounds(100, 15, 600, 50);
		playerPanel.setBackground(Color.black);
		playerPanel.setLayout(new GridLayout(1, 4));

		gameContainer.add(playerPanel);

		hpLabel = new JLabel("HP: ");
		hpLabel.setFont(gameFont);
		hpLabel.setForeground(Color.white);
		playerPanel.add(hpLabel);

		hpLabelNumber = new JLabel();
		hpLabelNumber.setFont(gameFont);
		hpLabelNumber.setForeground(Color.white);
		playerPanel.add(hpLabelNumber);

		weaponLabel = new JLabel("Weapon: ");
		weaponLabel.setFont(gameFont);
		weaponLabel.setForeground(Color.white);
		playerPanel.add(weaponLabel);

		weaponLabelName = new JLabel();
		weaponLabelName.setFont(gameFont);
		weaponLabelName.setForeground(Color.white);
		playerPanel.add(weaponLabelName);

		playerSetup();

	}

	public void playerSetup() {
		playerHP = 15;
		monsterHP = 20;
		weapon = "Knife";
		silverRing = 0;
		weaponLabelName.setText(weapon);
		hpLabelNumber.setText("" + playerHP);

		townGate();
	}

	public void townGate() {
		position = "townGate";
		mainTextArea.setText("You are at the gate of the town.\nA guard is standing in front of you.\n\nWhat do you want to do?");
		choice1.setText("Talk to the guard");
		choice2.setText("Attack the guard");
		choice3.setText("Leave");
		choice4.setVisible(false);
	}
	
	private void ending() {
		position = "ending";
		mainTextArea.setText("Guard: I see that you have obtained a Silver Ring.\nWelcome to the town!\nTHE END");
		choice1.setText("Play Again");
		choice2.setText("Exit");
		choice3.setVisible(false);
		choice4.setVisible(false);
		
	}

	public void crossRoad() {
		position = "crossRoad";
		mainTextArea.setText("You're at a crossroad. If you go south, you \nwill go back into the town.");
		choice1.setText("Go north");
		choice2.setText("Go south");
		choice3.setText("Go east");
		choice4.setText("Go west");
	}
	
	public void north() {
		position = "north";
		mainTextArea.setText("There is a river. You drink the water and rest at the riverside.\nYou have recovered 1 HP.");
		if(playerHP<15) {
			playerHP++;
			hpLabelNumber.setText("" + playerHP);
		}
		else {
			hpLabelNumber.setText("" + playerHP);
		}
		choice1.setText(">");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}
	
	public void east() {
		position = "east";
		mainTextArea.setText("You walked into the forest and found a Long Sword!\nYou equipped the Long Sword.");
		weapon = "Long Sword";
		weaponLabelName.setText(weapon);
		choice1.setText(">");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}
	
	
	public void west() {
		position = "west";
		mainTextArea.setText("You encountered a goblin!");
		choice1.setText("Fight");
		choice2.setText("Run");
		choice3.setVisible(false);
		choice4.setVisible(false);
	}
	
	public void fight() {
		position = "fight";
		mainTextArea.setText("Goblin HP: " + monsterHP);
		choice1.setText("Attack");
		choice2.setText("Run");
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void playerAttack() {
		position = "playerAttack";
		playerDamage = 0;
		if(playerHP<1) {
			gameOver();
		}
		else {
			if(weapon.equals("Knife")) {
				playerDamage = new java.util.Random().nextInt(5);
			}
			else if(weapon.equals("Long Sword")) {
				playerDamage = new java.util.Random().nextInt(8);
			}
			mainTextArea.setText("You attacked the monster and dealt " + playerDamage + " damage!");
			monsterHP = monsterHP - playerDamage;
			
			choice1.setText(">");
			choice2.setVisible(false);
			choice3.setVisible(false);
			choice4.setVisible(false);
		}

	}
	public void monsterAttack() {
		position = "monsterAttack";
		monsterDamage = 0;
		if (monsterHP<1) {
			win();
		}
		else {
		monsterDamage = 0;
		
		monsterDamage = new java.util.Random().nextInt(4);
		mainTextArea.setText("The monster attacked you and dealt " + monsterDamage + " damage!");
		playerHP = playerHP - monsterDamage;
		hpLabelNumber.setText("" + playerHP);
		
		choice1.setText(">");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		}	
	}
	private void win() {
		// TODO Auto-generated method stub
		position = "win";
		mainTextArea.setText("You killed the monster!\nThe monster dropped an item...\nYou obtained a Silver Ring!!");
		silverRing = 1;
		choice1.setText(">");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void guardDialogue() {
		position = "guardDialogue";
		if(silverRing == 1) {
			ending();
		}
		else {
		mainTextArea.setText(
				"Guard: Hello there, stranger.\nSorry but we cannot let you enter our town unless\nyou have a Silver Ring.");
		choice1.setText(">");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		}
	}

	public void attackGuard() {
		position = "attackGuard";
		mainTextArea.setText(
				"You tried to attack the guard, but he fought back for 3 damage. Maybe you shouldn't attack him.");
		playerHP = playerHP - 3;
		if (playerHP <= 0) {
			gameOver();
		}
		hpLabelNumber.setText("" + playerHP);
		choice1.setText(">");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void gameOver() {
		position = "gameOver";
		mainTextArea.setText("You are dead....\nGAME OVER");
		choice1.setText(">");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public class TitleScreenHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			createGameScreen();
		}
	}

	public class ChoiceHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String yourChoice = event.getActionCommand();
			switch (position) {
			case "townGate":
				switch (yourChoice) {
				case "c1":
					guardDialogue();
					break;
				case "c2":
					attackGuard();
					break;
				case "c3":
					crossRoad();
					choice4.setVisible(true);
					break;
				}
				break;
			case "guardDialogue":
				switch (yourChoice) {
				case "c1":
					townGate();
					choice2.setVisible(true);
					choice3.setVisible(true);
					break;
				}
				break;
			case "attackGuard":
				switch (yourChoice) {
				case "c1":
					townGate();
					choice2.setVisible(true);
					choice3.setVisible(true);
					break;
				}
				break;
				
			case "crossRoad":
				switch (yourChoice) {
				case "c1":
					north();
					break;
				case "c2":
					townGate();
					choice4.setVisible(false);
					break;
				case "c3":
					east();
					break;
				case "c4":
					west();
					break;
				}
				break;
				
			case "north":
				switch (yourChoice) {
				case "c1":
					crossRoad();
					choice2.setVisible(true);
					choice3.setVisible(true);
					choice4.setVisible(true);
					break;				
				}
				break;
			case "east":
				switch (yourChoice) {
				case "c1":
					crossRoad();
					choice2.setVisible(true);
					choice3.setVisible(true);
					choice4.setVisible(true);
					break;				
				}
				break;	
			case "west":
				switch (yourChoice) {
				case "c1":
					fight();
					break;				
				case "c2":
					crossRoad();
					choice2.setVisible(true);
					choice3.setVisible(true);
					choice4.setVisible(true);
					break;
				}
				break;	
			case "fight":
				switch (yourChoice) {
				case "c1":
					playerAttack();
					break;
				case "c2":
					crossRoad();
					choice2.setVisible(true);
					choice3.setVisible(true);
					choice4.setVisible(true);
					break;
				default:
					break;
				}
				break;
			case "playerAttack":
				switch(yourChoice) {
				case "c1":
					monsterAttack();
					break;
				}
				break;
			case "monsterAttack":
				switch (yourChoice) {
				case "c1": 
					fight();
					choice2.setVisible(true);
					break;
				}
				break;
			case "win":
				switch (yourChoice){
				case "c1":
					crossRoad();
					choice2.setVisible(true);
					choice3.setVisible(true);
					choice4.setVisible(true);
					break;
				}
				break;
			case "gameOver":
				switch (yourChoice) {
				case "c1":
					playerSetup();
					choice2.setVisible(true);
					choice3.setVisible(true);
					break;
				}
				break;
			case "ending":
				switch (yourChoice) {
				case "c1": 
					playerSetup();
					choice2.setVisible(true);
					choice3.setVisible(true);
					break;
				case "c2":
					System.exit(0);
					break;
				}
				break;
			}

		}
	}
}
