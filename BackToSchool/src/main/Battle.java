package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Battle extends JPanel {
	// global variables
	JButton button1;
	boolean attackPressed;
	ImageIcon background;
	BackToSchool frame;
	ImageIcon lostScreen;
	ImageIcon winScreen;
	JButton exit;
	boolean gameOver=false;
	Random r;

	// Student variables
	ImageIcon student;
	ImageIcon backpack;
	ImageIcon pencil1;
	ImageIcon pencil2;
	int backpackX;
	int backpackY;
	int studentX;
	int studentY;
	int playerHealth;
	JLabel playerHealthLabel;
	JLabel creativityLabel;
	JLabel quantReasoningLabel;
	JLabel scientRigorLabel;
	Timer timer;
	Player player;
	boolean drawConfused1;
	boolean drawConfused2;
	ImageIcon confusedStudent1;
	ImageIcon confusedStudent2;
	boolean setDown;

	// Attack Menu variables
	JLabel defaultAttackLabel;
	JLabel specializedAttackLabel;
	JButton optionAButton;
	JButton optionBButton;
	ImageIcon scribble;
	ImageIcon A;
	ImageIcon B;
	boolean optionA;
	boolean optionB;
	boolean specialAttack;

	// Boss variables
	ImageIcon boss;
	ImageIcon attack;
	ImageIcon humAttack1;
	ImageIcon humAttack2;
	ImageIcon humAttack3;
	ImageIcon explosion;
	ImageIcon explosion2;
	int attackX;
	int attackY;
	int bossX;
	int bossY;
	int xSpeed;
	int bossHealth;
	String bossSubject;
	Timer bossTimer;
	JLabel bossHealthLabel;
	JLabel bossSpecialAttackLabel;
	JLabel bossSpecialDefenseLabel;
	JLabel bossType;
	JLabel bossName;
	//JLabel bossStory;
	boolean bossTurn;
	Graphics graphics;
	double earnedPercentage;

	public Battle(Player player, String classSubject){
		this.setPreferredSize(new Dimension(800, 600));// setting the size
		this.setBackground(Color.white);// color of background

		r = new Random();
		bossSubject=classSubject;
		this.player = player;
		//playerHealth = 0;
		playerHealth=100;
		bossHealth=100;
		//bossHealth = 0;
		background = new ImageIcon("art/battle/battle.jpg");
		specialAttack=false;
		setDown=false;

		//adding the attack button
		button1 = new JButton(new ImageIcon("art/buttons/attack_btn.jpg"));
		button1.addActionListener(new AttackButtonListener());
		button1.setBounds(420,510,100,30);
		
		exit = new JButton("Exit");
		exit.addActionListener(new exitButtonListener());
		exit.setBounds(350,400,100,50); 
		exit.setVisible(false);
		exit.setBackground(null);
		exit.setOpaque(false);
		//exit.setBorder(null);

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

		//attacking menu
		defaultAttackLabel = new JLabel("Default Attack");
		defaultAttackLabel.setBounds(450,405,100,30);
		specializedAttackLabel = new JLabel("Special Attack");
		specializedAttackLabel.setBounds(450,455,160,30);
		scribble = new ImageIcon("art/battle/scribble_sprite.png");
		// by default make option A
		optionA=true;
		optionB=false;

		setLayout(null);

		//----------------------Player Variables--------------------------------//
		student = new ImageIcon("art/characters/student_leftside.png"); // loading image
		backpack = new ImageIcon("art/battle/backpack.png");
		pencil1 = new ImageIcon("art/battle/pencil.png");
		pencil2 = new ImageIcon("art/battle/pencil.png");
		confusedStudent1 = new ImageIcon("art/characters/confused_student.png");
		confusedStudent2 = new ImageIcon("art/characters/confused_student2.png");
		
		drawConfused1=false;
		drawConfused2=false;
		studentX=600;// x coordinate for student
		studentY=200;// y coordinate for student
		backpackX=600;
		backpackY=200;
		attackPressed=false;

		// initializing variables
		playerHealthLabel = new JLabel(playerHealth+"%");
		creativityLabel = new JLabel("Creativity: "+player.getCreativity());
		quantReasoningLabel = new JLabel("Quantative Reasoning: "+player.getQuantReasoning());
		scientRigorLabel = new JLabel("Scientific Rigor: "+player.getSciRigor());


		//setting location of statistics
		playerHealthLabel.setBounds(670,340,100,100);
		playerHealthLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		creativityLabel.setBounds(620,380,160,100);
		quantReasoningLabel.setBounds(620,420,200,100);
		scientRigorLabel.setBounds(620,460,160,100);
		//---------------------End of Player Variables---------------------------//

		//----------------------- Boss Variables----------------------------------//
		bossTurn=false;
		xSpeed=5;// speed for movement
		bossX=0;// x coordinate for boss
		bossY=0;// y coordinate for boss

		bossHealthLabel = new JLabel(bossHealth+"%");
		bossHealthLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		if(bossSubject.equals("Humanities")){
			attackY=0;
			attackX=600;
			bossName = new JLabel("Boss: Shakespeare's Ghost");
			bossType = new JLabel("Type: Humanities");
			bossSpecialAttackLabel = new JLabel("Special Attack: Artistic Squeeze");
			bossSpecialDefenseLabel = new JLabel("Special Defense: Long Live the King!");
			boss = new ImageIcon("art/battle/humboss.png");
			humAttack1=new ImageIcon("art/battle/hum_attack1.png");
			humAttack2=new ImageIcon("art/battle/hum_attack2.png");
			humAttack3=new ImageIcon("art/battle/hum_attack3.png");
			attackY=0;
			attackX=600;
			//bossStory = new JLabel("Hello");
		}
		else if(bossSubject.equals("Science"))
		{
			bossName = new JLabel("Boss: Froggerhut");
			attack = new ImageIcon("art/battle/scalpels.png");
			bossType = new JLabel("Type: Science");
			bossSpecialAttackLabel = new JLabel("Special Attack: Shooting Scalpels");
			bossSpecialDefenseLabel = new JLabel("Special Defense: Preservation");
			boss = new ImageIcon("art/battle/science_boss.png");
			//bossStory = new JLabel("");
		}
		else if(bossSubject.equals("Math"))
		{
			bossName = new JLabel("Boss: Number of Doom");
			bossType = new JLabel("Type: Math");
			boss = new ImageIcon("art/battle/math_boss.png");
			bossSpecialAttackLabel = new JLabel("Special Attack: Number Cruncher");
			bossSpecialDefenseLabel = new JLabel("Special Defense: Bias Data");
			//bossStory = new JLabel("");
		}
		
		explosion = new ImageIcon("art/battle/explosion.png");
		explosion2 = new ImageIcon("art/battle/explosion.png");

		//setting location of statistics
		bossHealthLabel.setBounds(140,340,100,100);
		bossName.setBounds(90,410,200,30);
		bossType.setBounds(90,440,100,30);
		bossSpecialAttackLabel.setBounds(90,470,250,30);
		bossSpecialDefenseLabel.setBounds(90,500,250,30);
		//bossStory.setBounds(100,500,100,10);

		//--------------------- End of Boss Variables-----------------------------//

		lostScreen = new ImageIcon("art/battle/Lost.jpg");
		winScreen = new ImageIcon("art/battle/Win.jpg");
		// adding components to the jpanel
		//
		this.add(bossName);
		this.add(bossType);
		this.add(bossSpecialAttackLabel);
		this.add(bossSpecialDefenseLabel);
		//this.add(bossStory);
		this.add(button1);
		this.add(optionAButton);
		this.add(optionBButton);
		this.add(bossHealthLabel);
		this.add(playerHealthLabel);
		this.add(creativityLabel);
		this.add(quantReasoningLabel);
		this.add(scientRigorLabel);
		this.add(specializedAttackLabel);
		this.add(defaultAttackLabel);
		
		this.add(exit);

		setVisible(true);
	}		

	protected void sendFrame(BackToSchool frame) 
	{
		this.frame = frame;
	}

	private void moveBoss(){
		if(bossHealth>0){
			// -------------------Science boss atack-----------------------//
			if(bossSubject.equals("Science"))
			{
				attackX+=8;

				if(attackX>700)
				{
					drawConfused1=false;
					drawConfused2=false;
					playerHealth-=(21-(player.getSciRigor()-r.nextInt(5)));

					playerHealthLabel.setText(playerHealth+"%");// inflict damage on players health
					bossTimer.stop();
					bossTurn=false;
				}
				else if(attackX>460 && attackX<480){
					drawConfused1=true;
					drawConfused2=false;
				}
				else if(attackX>=600)
				{
					drawConfused1=false;
					drawConfused2=true;
				}
			}
			else if(bossSubject.equals("Math"))
			{
				bossX += xSpeed;
				// if student touches the boss , tell him to go the other direction
				if (bossX > 350) 
				{
					drawConfused1=true;
					drawConfused2=false;
					playerHealth-=(21-(player.getQuantReasoning()-r.nextInt(5)));
					playerHealthLabel.setText(playerHealth+"%");
					xSpeed = -xSpeed;
				}
				// if the student reaches to the origin, make him stop
				else if(bossX < 4)
				{
					drawConfused1=false;
					drawConfused2=false;
					bossTimer.stop();
					bossX=0;
					xSpeed=5;
					bossTurn=false;
				}
				else if(bossX>330 && bossX<350)
				{
					drawConfused1=false;
					drawConfused2=true;
				}
			}
			else if(bossSubject.equals("Humanities"))
			{
				attackY += 1;

				if(attackY>50)
				{
					drawConfused1=false;
					drawConfused2=false;
					playerHealth-=(21-(player.getCreativity()-r.nextInt(5)));
					//playerHealth-=100;
					playerHealthLabel.setText(playerHealth+"%");
					bossTimer.stop();
					xSpeed=5;
					bossTurn=false;
				}
				else if(attackY>10){
					drawConfused2=true;
					drawConfused1=false;
				}
			}

			if (playerHealth <= 0)
			{
				// BATTLE END (Loss)
				playerHealthLabel.setText("0%");
				//System.out.println("You were vanquished by the "+ this.bossSubject + " midterm...");
				repaint();
			}


		}
		//-------------------- end of Science boss attack-----------------//
	}

	private void movePlayer(){
		backpackX -= xSpeed;
		backpackY -= 1;
		
		if(specialAttack)
		{
			backpackX-=6;
		}

		//if backpack reaces boss
		if(backpackX < 190)
		{
			if(specialAttack){
				if(bossSubject=="Science")
					bossHealth-=((player.getSciRigor()*15)+r.nextInt(5));
				else if(bossSubject=="Math")
					bossHealth-=((player.getQuantReasoning()*15)+r.nextInt(5));
				else if(bossSubject=="Humanities")
					bossHealth-=((player.getCreativity()*15)+r.nextInt(5));
			}
			else
				bossHealth-=18;// contains no luck

			if(bossHealth<0)
				bossHealthLabel.setText("0%");
			else
				bossHealthLabel.setText(bossHealth+"%");

			timer.stop();
			setDown=false;
			backpackX=600;
			backpackY=200;
			xSpeed=5;
			attackPressed=false;
			bossTurn=true;

			ActionListener updateTask = new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					update();   // update the (x, y) position
					repaint();  // Refresh the JFrame, callback paintComponent()
				}
			};

			if(bossSubject.equals("Science")){
				attackX=60;
				attackY=240;
			}
			if(bossSubject.equals("Humanities")){
				attackX=600;
				attackY=0;
			}

			if(bossHealth>0){
				bossTimer = new Timer(15, updateTask);
				bossTimer.start();
			}
			else
			{
				//System.out.println("You dominated the "+ this.bossSubject + " midterm!");
				repaint();
			}
		}	
	}

	private void update() {
		if(bossTurn)
			moveBoss();
		else
			movePlayer();
	}

	// paint the images and graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics=g;
		background.paintIcon(this,g,0,0);
		
		if(drawConfused1)
		{
			confusedStudent1.paintIcon(this, g, studentX, studentY);
		}
		else if(drawConfused2)
		{
			confusedStudent2.paintIcon(this, g, studentX, studentY);
		}
		else{
			student.paintIcon(this, g, studentX, studentY);
		}
		
		boss.paintIcon(this, g, bossX, bossY);
		
		if(attackPressed){
			if(!specialAttack){
				backpack.paintIcon(this, g, backpackX,backpackY);
				//System.out.println(backpackX);
				if(backpackX<230)
					explosion.paintIcon(this,g,180,100);
			}
			else
			{
				pencil1.paintIcon(this,g,backpackX-40,200);
				pencil2.paintIcon(this,g,backpackX-10,250);
				
				if(backpackX<210)
				{
					explosion2.paintIcon(this,g,150,180);
					explosion.paintIcon(this,g,170,230);
				}
			}
		}

		if(optionA)
			scribble.paintIcon(this, g, 395, 400);
		else if(optionB)
			scribble.paintIcon(this,g,395,452);

		if(bossHealth>0){
			if(bossTurn && bossSubject.equals("Science"))
			{
				attack.paintIcon(this,g,attackX,attackY);
			}
			if(bossTurn && bossSubject.equals("Humanities"))
			{
				humAttack1.paintIcon(this,g,attackX,attackY);
				humAttack1.paintIcon(this,g,attackX+30,attackY+30);
				humAttack1.paintIcon(this,g,attackX+60,attackY);
				student.paintIcon(this, g, studentX, studentY);
				
			}
		}
		
		if(playerHealth<=0){
			lostScreen.paintIcon(this, g, 0, 0);
			earnedPercentage=.25;
			exit.setVisible(true);
			optionAButton.setVisible(false);
			optionBButton.setVisible(false);
			bossHealthLabel.setVisible(false);
			bossName.setVisible(false);
			bossType.setVisible(false);
			bossSpecialAttackLabel.setVisible(false);
			bossSpecialDefenseLabel.setVisible(false);
			button1.setVisible(false);
			playerHealthLabel.setVisible(false);
			creativityLabel.setVisible(false);
			quantReasoningLabel.setVisible(false);
			scientRigorLabel.setVisible(false);
			specializedAttackLabel.setVisible(false);
			defaultAttackLabel.setVisible(false);
		
		}
		else if(bossHealth<=0)
		{
			winScreen.paintIcon(this, g, 0, 0);
			earnedPercentage=0.5;
			optionAButton.setVisible(false);
			optionBButton.setVisible(false);
			bossHealthLabel.setVisible(false);
			bossName.setVisible(false);
			bossType.setVisible(false);
			bossSpecialAttackLabel.setVisible(false);
			bossSpecialDefenseLabel.setVisible(false);
			button1.setVisible(false);
			playerHealthLabel.setVisible(false);
			creativityLabel.setVisible(false);
			quantReasoningLabel.setVisible(false);
			scientRigorLabel.setVisible(false);
			specializedAttackLabel.setVisible(false);
			defaultAttackLabel.setVisible(false);
			
			exit.setVisible(true);	
		}
	}


	// action listener for the attack button
	private class AttackButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event){
			ActionListener updateTask = new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					update();   // update the (x, y) position
					repaint();  // Refresh the JFrame, callback paintComponent()
				}
			};
			// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
			if(playerHealth>0 && !attackPressed && !bossTurn){
				attackPressed=true;
				timer = new Timer(20, updateTask);
				timer.start();
			}
		}

	}

	// action listener for the Option A Button
	private class AButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!attackPressed){
				//System.out.println("Pressed A");
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
				//System.out.println("Pressed B");
				optionA=false;
				optionB=true;
				specialAttack=true;
				repaint();
			}
		}
	}
	
	public void increaseStats(){
		if(bossSubject.equals("Science"))
			player.increaseSciRigor(earnedPercentage);
		else if(bossSubject.equals("Humanities"))
			player.increaseCreativit(earnedPercentage);
		else if(bossSubject.equals("Math"))
			player.increaseQuantReasoning(earnedPercentage);
	}
	
	// action listener for the exit Button
		private class exitButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				increaseStats();
				frame.switchPanel(BackToSchool.Screen.CAMPUS);
			}
		}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Back To School: Battle Mode");
		Battle battle = new Battle(new Player(),"Humanities");
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(battle);
		frame.setContentPane(battle);
		frame.pack();

		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Battle(new Player(),"Humanities"); // Let the constructor do the job
			}
		});*/
		frame.setVisible(true);
	}

}
