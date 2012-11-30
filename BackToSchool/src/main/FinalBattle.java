package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class FinalBattle extends JPanel {	
	// global variables
	JButton button1;
	boolean attackPressed;
	ImageIcon background;
	Graphics graphics;
	ImageIcon splashWin;
	ImageIcon splashLost;
	BackToSchool frame;
	JButton exit;
	Random r;

	// Student variable
	ImageIcon student;
	ImageIcon backpack;
	int studentX;
	int studentY;
	int backpackX;
	int backpackY;
	int playerHealth;
	JLabel playerHealthLabel;
	JLabel creativityLabel;
	JLabel quantReasoningLabel;
	JLabel scientRigorLabel;
	Timer timer;
	ImageIcon pencil;
	ImageIcon pencil2;
	Player player;

	// Attack Menu variables
	JLabel defaultAttackLabel;
	JLabel specializedAttackLabel;
	JButton optionAButton;
	JButton optionBButton;
	ImageIcon A;
	ImageIcon B;
	boolean optionA;
	boolean optionB;
	boolean specialAttack;
	String bossChosen;

	// Boss variables
	ImageIcon humBoss;
	ImageIcon sciBoss;
	ImageIcon mathBoss;
	ImageIcon sciAttack;
	ImageIcon scribble;
	JButton humBossButton;
	JButton sciBossButton;
	JButton mathBossButton;
	ImageIcon humAttack1;
	ImageIcon humAttack2;
	ImageIcon humAttack3;
	int attackX;
	int attackY;
	int humBossX;
	int humBossY;
	int sciBossX;
	int sciBossY;
	int mathBossX;
	int mathBossY;
	int xSpeed;
	double ySpeed;
	int humBossHealth;
	int sciBossHealth;
	int mathBossHealth;
	Timer bossTimer;
	JLabel bossHealthLabel;
	JLabel bossTypeLabel;
	JLabel bossNameLabel;
	JLabel bossSpecialAttackLabel;
	JLabel bossSpecialDefenseLabel;
	boolean anyBossTurn;
	boolean humBossTurn;
	boolean sciBossTurn;
	boolean mathBossTurn;
	String lastBossToGo;
	ImageIcon explosion;
	ImageIcon explosion2;

	public FinalBattle(Player player)
	{
		this.player = player;
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background
		background = new ImageIcon("art/battle/battle.jpg");
		playerHealth=100;
		mathBossHealth=100;
		sciBossHealth=100;
		humBossHealth=100;
		optionA=true;
		optionB=false;
		bossChosen="";
		xSpeed=5;// speed for movement
		ySpeed=1;
		anyBossTurn=false;
		humBossTurn=false;
		sciBossTurn=false;
		mathBossTurn=false;
		r = new Random();

		//adding the attack button
		button1 = new JButton(new ImageIcon("art/buttons/attack_btn.jpg"));
		//button1.addActionListener(new ButtonListener());
		button1.setBounds(420,510,100,30);
		button1.addActionListener(new AttackButtonListener());

		//attacking menu
		defaultAttackLabel = new JLabel("Default Attack");
		defaultAttackLabel.setBounds(450,405,100,30);
		specializedAttackLabel = new JLabel("Special Attack");
		specializedAttackLabel.setBounds(450,455,160,30);
		scribble = new ImageIcon("art/battle/scribble_sprite.png");

		//adding option A button
		optionAButton = new JButton();
		optionAButton.addActionListener(new AButtonListener());
		optionAButton.setIcon(new ImageIcon("art/battle/A_sprite.png"));
		optionAButton.setBounds(388,398,50,40); 
		optionAButton.setBackground(null);
		optionAButton.setOpaque(false);
		optionAButton.setBorder(null);

		// adding option B button
		optionBButton = new JButton();
		optionBButton.addActionListener(new BButtonListener());
		optionBButton.setIcon(new ImageIcon("art/battle/B_sprite.png"));
		optionBButton.setBounds(388,450,50,40);
		optionBButton.setBackground(null);
		optionBButton.setOpaque(false);
		optionBButton.setBorder(null);
		
		exit = new JButton("Exit");
		exit.addActionListener(new exitButtonListener());
		exit.setBounds(350,400,100,50); 
		exit.setVisible(false);
		exit.setBackground(null);
		exit.setOpaque(false);

		setLayout(null);

		//----------------------Player Variables--------------------------------//
		student = new ImageIcon("art/characters/student_leftside.png"); // loading image
		studentX=600;// x coordinate for student
		studentY=200;// y coordinate for student
		backpackX=600;
		backpackY=200;
		attackPressed=false;

		// initializing variables
		playerHealthLabel = new JLabel(playerHealth+"%");
		playerHealthLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		creativityLabel = new JLabel("Creativity: "+player.getCreativity());
		quantReasoningLabel = new JLabel("Quantative Reasoning: "+player.getQuantReasoning());
		scientRigorLabel = new JLabel("Scientific Rigor: "+player.getSciRigor());
		backpack = new ImageIcon("art/battle/backpack.png");
		
		pencil = new ImageIcon("art/battle/pencil.png");
		pencil2 = new ImageIcon("art/battle/pencil.png");

		//setting location of statistics
		playerHealthLabel.setBounds(670,340,100,100);
		creativityLabel.setBounds(620,380,160,100);
		quantReasoningLabel.setBounds(620,420,160,100);
		scientRigorLabel.setBounds(620,460,160,100);
		//---------------------End of Player Variables---------------------------//

		//---------------------Bosses Variables------------------------------------//
		humBoss = new ImageIcon("art/battle/Finalhumboss.png");
		humBossButton = new JButton();
		humBossButton.addActionListener(new HumButtonListener());
		humBossButton.setIcon(humBoss);
		humBossButton.setBounds(0,0,140,140); 
		humBossButton.setBackground(null);
		humBossButton.setOpaque(false);
		humBossButton.setBorder(null);
		humAttack1=new ImageIcon("art/battle/hum_attack1.png");
		humAttack2=new ImageIcon("art/battle/hum_attack2.png");
		humAttack3=new ImageIcon("art/battle/hum_attack3.png");

		sciBoss = new ImageIcon("art/battle/Finalscience_boss.png");
		sciAttack = new ImageIcon("art/battle/scalpels.png");
		sciBossButton = new JButton();
		sciBossButton.addActionListener(new ScienceButtonListener());
		sciBossButton.setIcon(sciBoss);
		sciBossButton.setBounds(0,110,130,140); 
		sciBossButton.setBackground(null);
		sciBossButton.setOpaque(false);
		sciBossButton.setBorder(null);

		mathBoss = new ImageIcon("art/battle/Finalmath_boss.png");
		mathBossButton = new JButton();
		mathBossButton.addActionListener(new MathButtonListener());
		mathBossButton.setIcon(mathBoss);
		mathBossButton.setBounds(0,250,130,140); 
		mathBossButton.setBackground(null);
		mathBossButton.setOpaque(false);
		mathBossButton.setBorder(null);

		mathBossX=0;
		mathBossY=250;

		bossHealthLabel = new JLabel();
		bossTypeLabel = new JLabel();
		bossNameLabel = new JLabel();
		bossSpecialAttackLabel = new JLabel();
		bossSpecialDefenseLabel = new JLabel();
		
		bossHealthLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		bossHealthLabel.setBounds(140,340,100,100);
		bossNameLabel.setBounds(90,410,200,30);
		bossTypeLabel.setBounds(90,440,100,30);
		bossSpecialAttackLabel.setBounds(90,470,200,30);
		bossSpecialDefenseLabel.setBounds(90,500,250,30);

		explosion = new ImageIcon("art/battle/explosion.png");
		explosion2 = new ImageIcon("art/battle/explosion.png");
		lastBossToGo="";
		//--------------------End of Bosses Variables
		
		splashLost = new ImageIcon("art/battle/Lost.png");
		splashWin = new ImageIcon("art/battle/Win.png");

		// adding components to the jpanel
		this.add(bossSpecialAttackLabel);
		this.add(bossSpecialDefenseLabel);
		this.add(bossNameLabel);
		this.add(bossTypeLabel);
		this.add(bossHealthLabel);
		this.add(button1);
		this.add(optionAButton);
		this.add(optionBButton);
		this.add(humBossButton);
		this.add(sciBossButton);
		this.add(mathBossButton);
		this.add(bossHealthLabel);
		this.add(playerHealthLabel);
		this.add(creativityLabel);
		this.add(quantReasoningLabel);
		this.add(scientRigorLabel);
		this.add(specializedAttackLabel);
		this.add(exit);
		this.add(defaultAttackLabel);

		setVisible(true);
	}
	
	protected void sendFrame(BackToSchool frame) 
	{
		this.frame = frame;
	}

	// paint the images and graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics=g;
		background.paintIcon(this,g,0,0);
		student.paintIcon(this, g, studentX, studentY);
		
		if(attackPressed){
		
			if(specialAttack)
			{
				pencil.paintIcon(this,g,backpackX-15,backpackY);
				pencil2.paintIcon(this,g,backpackX+5,backpackY+40);
				
				if(backpackX<230){
					if(bossChosen.equals("Science")){
						explosion.paintIcon(this, g, 180, 100);
						explosion2.paintIcon(this, g, 180, 140);
					}
					else if(bossChosen.equals("Math")){
						explosion.paintIcon(this, g, 200, 280);
						explosion2.paintIcon(this, g, 180, 240);
					}
					else if(bossChosen.equals("Humanities")){
						explosion.paintIcon(this, g, 220, 0);
						explosion2.paintIcon(this, g, 230, 40);
					}
				}
			}
			else
			{
				backpack.paintIcon(this, g, backpackX,backpackY);
				
				if(backpackX<230){
					if(bossChosen.equals("Science"))
						explosion.paintIcon(this, g, 180, 100);
					else if(bossChosen.equals("Math"))
						explosion.paintIcon(this, g, 180, 250);
					else if(bossChosen.equals("Humanities"))
						explosion.paintIcon(this, g, 200, 0);
				}
			}
		}

		if(optionA)
			scribble.paintIcon(this, g, 395, 400);
		else if(optionB)
			scribble.paintIcon(this,g,395,452);

		if(anyBossTurn&&mathBossTurn)
			mathBossButton.setBounds(mathBossX,mathBossY,130,140); 
		else if(anyBossTurn&&sciBossTurn)
		{
			sciAttack.paintIcon(this,g,attackX,attackY);
		}
		else if(anyBossTurn&&humBossTurn)
		{
			humAttack1.paintIcon(this,g,attackX,attackY);
			humAttack1.paintIcon(this,g,attackX+30,attackY+30);
			humAttack1.paintIcon(this,g,attackX+60,attackY);
			student.paintIcon(this, g, studentX, studentY);
		}
		
		if(mathBossHealth<=0&&humBossHealth<=0&&sciBossHealth<=0){
			splashWin.paintIcon(this, g, 0, 0);
			exit.setVisible(true);
			bossSpecialAttackLabel.setVisible(false);
			bossSpecialDefenseLabel.setVisible(false);
			bossNameLabel.setVisible(false);
			bossTypeLabel.setVisible(false);
			bossHealthLabel.setVisible(false);
			button1.setVisible(false);
			optionAButton.setVisible(false);
			optionBButton.setVisible(false);
			humBossButton.setVisible(false);
			sciBossButton.setVisible(false);
			mathBossButton.setVisible(false);
			creativityLabel.setVisible(false);
			quantReasoningLabel.setVisible(false);
			scientRigorLabel.setVisible(false);
			specializedAttackLabel.setVisible(false);
			defaultAttackLabel.setVisible(false);
			optionAButton.setVisible(false);
			playerHealthLabel.setVisible(false);
			
			
		}
		else if(playerHealth<=0){
			splashLost.paintIcon(this,g,0,0);
			bossSpecialAttackLabel.setVisible(false);
			bossSpecialDefenseLabel.setVisible(false);
			bossNameLabel.setVisible(false);
			bossTypeLabel.setVisible(false);
			bossHealthLabel.setVisible(false);
			button1.setVisible(false);
			optionAButton.setVisible(false);
			optionBButton.setVisible(false);
			playerHealthLabel.setVisible(false);
			optionBButton.setVisible(false);
			humBossButton.setVisible(false);
			sciBossButton.setVisible(false);
			mathBossButton.setVisible(false);
			creativityLabel.setVisible(false);
			quantReasoningLabel.setVisible(false);
			scientRigorLabel.setVisible(false);
			specializedAttackLabel.setVisible(false);
			defaultAttackLabel.setVisible(false);
			
			exit.setVisible(true);
		}
	}

	public void update() {
		if(anyBossTurn &&(sciBossHealth>0||mathBossHealth>0||humBossHealth>0))
			moveBoss();
		else
			movePlayer();
	}

	public void whosTurn(){
		if(lastBossToGo.equals("Humanities"))
		{
			if(sciBossHealth>0)
				sciBossTurn=true;
			else if(mathBossHealth>0)
				mathBossTurn=true;
			else if(humBossHealth>0)
				humBossTurn=true;
		}
		else if(lastBossToGo.equals("Science"))
		{
			if(mathBossHealth>0)
				mathBossTurn=true;
			else if(humBossHealth>0)
				humBossTurn=true;
			else if(sciBossHealth>0)
				sciBossTurn=true;
		}
		else if(lastBossToGo.equals("Math")||lastBossToGo.equals(""))
		{
			if(humBossHealth>0)
				humBossTurn=true;
			else if(sciBossHealth>0)
				sciBossTurn=true;
			else if(mathBossHealth>0)
				mathBossTurn=true;
		}
	}
	public void moveBoss(){
		if(mathBossTurn){

			mathBossX += xSpeed;

			mathBossY -= ySpeed;
			
			// if student touches the boss , tell him to go the other direction
			if (mathBossX > 500) 
			{
				playerHealth-=(21-(player.getQuantReasoning()-r.nextInt(5)));

				if(playerHealth<0)
					playerHealthLabel.setText("0%");
				else
					playerHealthLabel.setText(playerHealth+"%");
				xSpeed = -xSpeed;
				ySpeed = -ySpeed;
			}
			// if the student reaches to the origin, make him stop
			else if(mathBossX < 4)
			{
				bossTimer.stop();
				xSpeed=5;
				ySpeed=1;
				anyBossTurn=false;
				mathBossTurn=false;
				bossChosen="Humanities";
				bossHealthLabel.setText("Choose");
				bossTypeLabel.setText("a boss");
				bossNameLabel.setText("to attack");
				bossSpecialAttackLabel.setText("");
				bossSpecialDefenseLabel.setText("");
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(0,250,130,140); 
				lastBossToGo="Math";
			}
		}
		else if(humBossTurn){
			attackY += 1;

			if(attackY>50)
			{
				playerHealth-=(21-(player.getCreativity()-r.nextInt(5)));
				
				if(playerHealth<0)
					playerHealthLabel.setText("0%");
				else
					playerHealthLabel.setText(playerHealth+"%");
				bossTimer.stop();
				xSpeed=5;
				anyBossTurn=false;
				humBossTurn=false;
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(0,250,130,140); 
				lastBossToGo="Humanities";
				bossHealthLabel.setText("Choose");
				bossTypeLabel.setText("a boss");
				bossNameLabel.setText("to attack");
				bossSpecialAttackLabel.setText("");
				bossSpecialDefenseLabel.setText("");
			}
		}
		else if(sciBossTurn)
		{
			attackX+=12;

			if(attackX>800)
			{
				playerHealth-=(21-(player.getSciRigor()-r.nextInt(5)));
				if(playerHealth<0)
					playerHealthLabel.setText("0%");
				else
					playerHealthLabel.setText(playerHealth+"%");
				bossTimer.stop();
				sciBossTurn=false;
				anyBossTurn=false;
				bossChosen="Humanities";
				bossHealthLabel.setText("Choose");
				bossTypeLabel.setText("a boss");
				bossNameLabel.setText("to attack");
				bossSpecialAttackLabel.setText("");
				bossSpecialDefenseLabel.setText("");
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(0,250,130,140); 
				lastBossToGo="Science";
			}
		}
		
		repaint();
	}

	public void movePlayer(){
		if(bossChosen.equals("Science"))
			backpackY -= 0.5;
		else if(bossChosen.equals("Math"))
			backpackY += 1;
		else if(bossChosen.equals("Humanities"))
			backpackY -= 2;

		backpackX -= xSpeed;


		//if the student reaches to the origin, make him stop
		if(backpackX < 190)
		{
			if(specialAttack)
			{
				if(bossChosen.equals("Science"))
					sciBossHealth-=((player.getSciRigor()*15)+r.nextInt(5));
				else if(bossChosen.equals("Math"))
					mathBossHealth-=((player.getQuantReasoning()*15)+r.nextInt(5));
				else if(bossChosen.equals("Humanities"))
					humBossHealth-=	((player.getCreativity()*15)+r.nextInt(5));
			}
			else
			{
				if(bossChosen.equals("Science"))
					sciBossHealth-=18;
				else if(bossChosen.equals("Math"))
					mathBossHealth-=18;
				else if(bossChosen.equals("Humanities"))
					humBossHealth-=18;
			}


			if(bossChosen.equals("Science")){
				if(sciBossHealth<=0){
					bossHealthLabel.setText("0%");
				}
				else
					bossHealthLabel.setText(sciBossHealth+"%");// inflict damage on boss's health
				
			}
			else if(bossChosen.equals("Math")){
				if(mathBossHealth<=0){
					bossHealthLabel.setText("0%");
				}
				else
					bossHealthLabel.setText(mathBossHealth+"%");// inflict damage on boss's health	
			}
			else if(bossChosen.equals("Humanities")){
				if(humBossHealth<=0){
					bossHealthLabel.setText("0%");
				}
				else
					bossHealthLabel.setText(humBossHealth+"%");// inflict damage on boss's health
				
			}

			//repaint();
			timer.stop();
			backpackX=600;
			backpackY=200;
			xSpeed=5;
			ySpeed=1;
			attackPressed=false;
			anyBossTurn=true;
			bossChosen="";

			ActionListener updateTask = new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					update();   // update the (x, y) position
					repaint();  // Refresh the JFrame, callback paintComponent()
				}
			};
			whosTurn();
			if(sciBossHealth>0||mathBossHealth>0||humBossHealth>0){
				if(mathBossTurn){
					humBossButton.setBounds(0,0,140,140); 
					sciBossButton.setBounds(0,110,130,140); 
					mathBossButton.setBounds(100,250,130,140); 
					bossHealthLabel.setText(mathBossHealth+"%");
					bossTypeLabel.setText("Type: Math");
					bossNameLabel.setText("Name: Number of Doom");
					bossSpecialAttackLabel.setText("Special Attack: Number Cruncher");
					bossSpecialDefenseLabel.setText("Special Defense: Bias Data");
				}
				else if(sciBossTurn){
					humBossButton.setBounds(0,0,140,140); 
					sciBossButton.setBounds(100,110,130,140); 
					mathBossButton.setBounds(0,250,130,140); 
					bossHealthLabel.setText(sciBossHealth+"%");
					bossTypeLabel.setText("Type: Science");
					bossNameLabel.setText("Name: Froggerhut");
					bossSpecialAttackLabel.setText("Special Attack: Shooting Scalpels");
					bossSpecialDefenseLabel.setText("Special Defense: Preservation");
					attackX=60;
					attackY=240;
				}
				else if(humBossTurn)
				{
					humBossButton.setBounds(100,0,140,140); 
					sciBossButton.setBounds(0,110,130,140); 
					mathBossButton.setBounds(0,250,130,140); 
					attackX=600;
					attackY=0;
					bossHealthLabel.setText(humBossHealth+"%");
					bossTypeLabel.setText("Type: Humanities");
					bossNameLabel.setText("Name: Shakespeare's Ghost");
					bossSpecialAttackLabel.setText("Special Attack: Artistic Squeeze");
					bossSpecialDefenseLabel.setText("Special Defense: Long Live the King!");
				}

				bossTimer = new Timer(15, updateTask);
				bossTimer.start();
			}
			else{
				// display You Win!
				repaint();
			}
		}
	}

	// action listener for the Humanities Boss Button
	private class HumButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed && humBossHealth>0){
				humBossButton.setBounds(100,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(0,250,130,140);  
				bossChosen="Humanities";
				bossHealthLabel.setText(humBossHealth+"%");
				bossTypeLabel.setText("Type: Humanities");
				bossNameLabel.setText("Name: Shakespeare's Ghost");
				bossSpecialAttackLabel.setText("Special Attack: Artistic Squeeze");
				bossSpecialDefenseLabel.setText("Special Defense: Long Live the King!");
			}
		}
	}

	// action listener for the Science Boss Button
	private class ScienceButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed  && sciBossHealth>0){
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(100,110,130,140); 
				mathBossButton.setBounds(0,250,130,140);  
				bossChosen="Science";
				bossHealthLabel.setText(sciBossHealth+"%");
				bossTypeLabel.setText("Type: Science");
				bossNameLabel.setText("Name: Froggerhut");
				bossSpecialAttackLabel.setText("Special Attack: Shooting Scalpels");
				bossSpecialDefenseLabel.setText("Special Defense: Preservation");
			}
		}
	}

	// action listener for the Math Boss Button
	private class MathButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed  && mathBossHealth>0){
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(100,250,130,140); 
				bossChosen="Math";
				bossHealthLabel.setText(mathBossHealth+"%");
				bossTypeLabel.setText("Type: Math");
				bossNameLabel.setText("Name: Number of Doom");
				bossSpecialAttackLabel.setText("Special Attack: Number Cruncher");
				bossSpecialDefenseLabel.setText("Special Defense: Bias Data");
			}
		}
	}

	// action listener for the Option A Button
	private class AButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				optionA=true;
				optionB=false;
				specialAttack=false;
				repaint();
			}
		}
	}

	// action listener for the Option B Button
	private class BButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				optionA=false;
				optionB=true;
				specialAttack=true;
				repaint();
			}
		}
	}

	// action listener for the exit Button
		private class exitButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				frame.switchPanel(BackToSchool.Screen.CAMPUS);
			}
		}
		
	// action listener for the Attack Button
	private class AttackButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!bossChosen.equals("") && !anyBossTurn){
				ActionListener updateTask = new ActionListener() {

					public void actionPerformed(ActionEvent evt) {
						update();   // update the (x, y) position
						repaint();  // Refresh the JFrame, callback paintComponent()
					}
				};
				// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
				if(playerHealth>0 && !attackPressed && (sciBossHealth>0||mathBossHealth>0||humBossHealth>0)){
					attackPressed=true;
					timer = new Timer(20, updateTask);
					timer.start();
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Back To School: Battle Mode");
		FinalBattle fBattle= new FinalBattle(new Player());
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(fBattle);
		frame.pack();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FinalBattle(new Player()); // Let the constructor do the job
			}
		});
		frame.setVisible(true);
	}


}
