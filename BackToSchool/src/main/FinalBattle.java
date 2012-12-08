package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import main.Battle.W;

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
	boolean drawConfused1;
	boolean drawConfused2;
	ImageIcon confusedStudent1;
	ImageIcon confusedStudent2;
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
	JLabel freezeAttackLabel;
	JButton optionAButton;
	JButton optionBButton;
	JButton optionCButton;
	ImageIcon A;
	ImageIcon B;
	boolean optionA;
	boolean optionB;
	boolean optionC;
	boolean specialAttack;
	boolean freezeAttack;
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
	//JLabel bossSpecialDefenseLabel;
	boolean anyBossTurn;
	boolean humBossTurn;
	boolean sciBossTurn;
	boolean mathBossTurn;
	ImageIcon explosion;
	ImageIcon explosion2;
	int mathFreezeRounds;
	int humFreezeRounds;
	int sciFreezeRounds;
	boolean humBossFreeze;
	boolean mathBossFreeze;
	boolean sciBossFreeze;

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
		optionC=false;
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
		button1.setBounds(420,530,100,30);
		button1.addActionListener(new AttackButtonListener());

		//-----------------attacking menu--------------------
		scribble = new ImageIcon("art/battle/scribble_sprite.png");

		//adding option A button
		optionAButton = new JButton();
		optionAButton.addActionListener(new AButtonListener());
		optionAButton.setIcon(new ImageIcon("art/battle/A_sprite.png"));
		optionAButton.setBounds(388,380,50,40); 
		optionAButton.setBackground(null);
		optionAButton.setOpaque(false);
		optionAButton.setBorder(null);
		defaultAttackLabel = new JLabel("Default Attack");
		defaultAttackLabel.setBounds(450,385,100,30);

		// adding option B button
		optionBButton = new JButton();
		optionBButton.addActionListener(new BButtonListener());
		optionBButton.setIcon(new ImageIcon("art/battle/B_sprite.png"));
		optionBButton.setBounds(388,425,50,40);
		optionBButton.setBackground(null);
		optionBButton.setOpaque(false);
		optionBButton.setBorder(null);
		optionBButton.setVisible(false);
		specializedAttackLabel = new JLabel("Special Attack");
		specializedAttackLabel.setBounds(450,430,160,30);
		specializedAttackLabel.setVisible(false);
		
		// adding option B button
		optionCButton = new JButton();
		optionCButton.addActionListener(new CButtonListener());
		optionCButton.setIcon(new ImageIcon("art/battle/B_sprite.png"));
		optionCButton.setBounds(388,470,50,40);
		optionCButton.setBackground(null);
		optionCButton.setOpaque(false);
		optionCButton.setBorder(null);
		optionCButton.setVisible(false);
		freezeAttackLabel = new JLabel("Freeze Attack");
		freezeAttackLabel.setBounds(452,475,160,30);
		freezeAttackLabel.setVisible(false);
		freezeAttack=false;
		specialAttack=false;
		//----------------------- end of attack menu--------------------

		exit = new JButton(new ImageIcon("art/buttons/exit_btn.jpg"));
		exit.addActionListener(new exitButtonListener());
		exit.setBounds(350,400,100,30); 
		exit.setVisible(false);

		// key binding for hack
		InputMap myInputMap = new InputMap();
		ActionMap myActionMap = new ActionMap();
		W w = new W();

		myInputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "w");
		myActionMap = this.getActionMap();
		myActionMap.put("w", w);

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

		confusedStudent1 = new ImageIcon("art/characters/confused_student.png");
		confusedStudent2 = new ImageIcon("art/characters/confused_student2.png");

		drawConfused1=false;
		drawConfused2=false;

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
		//bossSpecialDefenseLabel = new JLabel();

		bossHealthLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		bossHealthLabel.setBounds(140,340,100,100);
		bossNameLabel.setBounds(90,410,200,30);
		bossTypeLabel.setBounds(90,440,100,30);
		bossSpecialAttackLabel.setBounds(90,470,200,30);
		//bossSpecialDefenseLabel.setBounds(90,500,250,30);

		explosion = new ImageIcon("art/battle/explosion.png");
		explosion2 = new ImageIcon("art/battle/explosion.png");

		mathFreezeRounds=0;
		humFreezeRounds=0;
		sciFreezeRounds=0;
		
		humBossFreeze=false;
		mathBossFreeze=false;
		sciBossFreeze=false;

		//--------------------End of Bosses Variables

		splashLost = new ImageIcon("art/battle/Lost.jpg");
		splashWin = new ImageIcon("art/battle/Win.jpg");

		// adding components to the jpanel
		this.add(bossSpecialAttackLabel);
		//this.add(bossSpecialDefenseLabel);
		this.add(bossNameLabel);
		this.add(bossTypeLabel);
		this.add(bossHealthLabel);
		this.add(button1);
		this.add(optionAButton);
		this.add(optionBButton);
		this.add(optionCButton);
		this.add(humBossButton);
		this.add(sciBossButton);
		this.add(mathBossButton);
		this.add(bossHealthLabel);
		this.add(playerHealthLabel);
		this.add(creativityLabel);
		this.add(quantReasoningLabel);
		this.add(scientRigorLabel);
		this.add(specializedAttackLabel);
		this.add(freezeAttackLabel);
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
			else if(freezeAttack)
			{
				if(bossChosen.equals("Science"))
					sciBossButton.setIcon(new ImageIcon("art/battle/Finalscience_boss_freeze.png"));
				else if(bossChosen.equals("Math"))
					mathBossButton.setIcon(new ImageIcon("art/battle/Finalmath_boss_freeze.png"));
				else if(bossChosen.equals("Humanities"))
					humBossButton.setIcon(new ImageIcon("art/battle/Finalhumboss_freeze.png"));
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
			scribble.paintIcon(this, g, 395, 382);
		else if(optionB)
			scribble.paintIcon(this,g,395,428);
		else if(optionC)
			scribble.paintIcon(this,g,395,474);

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
			//bossSpecialDefenseLabel.setVisible(false);
			bossNameLabel.setVisible(false);
			bossTypeLabel.setVisible(false);
			bossHealthLabel.setVisible(false);
			button1.setVisible(false);
			optionAButton.setVisible(false);
			optionBButton.setVisible(false);
			optionCButton.setVisible(false);
			freezeAttackLabel.setVisible(false);
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
			//bossSpecialDefenseLabel.setVisible(false);
			bossNameLabel.setVisible(false);
			bossTypeLabel.setVisible(false);
			bossHealthLabel.setVisible(false);
			button1.setVisible(false);
			optionAButton.setVisible(false);
			optionBButton.setVisible(false);
			playerHealthLabel.setVisible(false);
			optionCButton.setVisible(false);
			freezeAttackLabel.setVisible(false);
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
		if(humBossFreeze)
		{
			if(humFreezeRounds>0)
				humFreezeRounds--;
			else if(humFreezeRounds==0){
				humBossFreeze=false;
				humBossButton.setIcon(new ImageIcon("art/battle/Finalhumboss.png"));
			}
		}
		if(mathBossFreeze)
		{
			if(mathFreezeRounds>0)
				mathFreezeRounds--;
			else if(mathFreezeRounds==0){
				mathBossFreeze=false;	
				mathBossButton.setIcon(new ImageIcon("art/battle/Finalmath_boss.png"));
			}
		}
		if(sciBossFreeze)
		{
			if(sciFreezeRounds>0)
				sciFreezeRounds--;
			else if(sciFreezeRounds==0){
				sciBossFreeze=false;
				sciBossButton.setIcon(new ImageIcon("art/battle/Finalscience_boss.png"));
			}
		}
		
		if(humBossHealth<0)
			humBossButton.setIcon(new ImageIcon("art/battle/Finalhumboss.png"));
		else if(mathBossHealth<0)
			mathBossButton.setIcon(new ImageIcon("art/battle/Finalmath_boss.png"));
		else if(sciBossHealth<0)
			sciBossButton.setIcon(new ImageIcon("art/battle/Finalscience_boss.png"));
		
		if(humBossHealth>0&&!humBossFreeze)
			humBossTurn=true;
		else if(sciBossHealth>0&&!sciBossFreeze)
			sciBossTurn=true;
		else if(mathBossHealth>0&&!mathBossFreeze)
			mathBossTurn=true;
	}

	public class W extends AbstractAction{
		public void actionPerformed(ActionEvent e)
		{
			mathBossHealth=0;
			humBossHealth=0;
			sciBossHealth=0;
			repaint();
		}
	}

	public void setHitOrMiss()
	{
		int num = r.nextInt(11);

		// High Hit
		if(num<=5)
		{
			if(sciBossTurn)
				playerHealth-=(15-(player.getSciRigor()));
			else if(mathBossTurn)
				playerHealth-=(15-(player.getQuantReasoning()));
			else if(humBossTurn)
				playerHealth-=(15-(player.getCreativity()));
			// Set high hit image to visible(true)
			System.out.println("High Hit");
		}
		// Low Hit
		else if(num>5 && num<=8)
		{
			if(sciBossTurn)
				playerHealth-=(10-(player.getSciRigor()));
			else if(mathBossTurn)
				playerHealth-=(10-(player.getQuantReasoning()));
			else if(humBossTurn)
				playerHealth-=(10-(player.getCreativity()));

			// Set low hit image to visible(true)
			System.out.println("Low Hit");
		}
		// Miss
		else if(num>8){
			// Set miss image to visible(true)
			System.out.println("Miss");
		}
	}

	public void redrawAttackMenu(){
		optionA=true;
		optionB=false;
		optionC=false;
		specialAttack=false;
		freezeAttack=false;
		if(anyBossTurn){
			if(humBossTurn){
				if(player.getCreativity()>2.4){
					optionBButton.setVisible(true);
					specializedAttackLabel.setVisible(true);
					
					if(player.getCreativity()>3.9){
						optionCButton.setVisible(true);
						freezeAttackLabel.setVisible(true);
					}
					else{
						optionCButton.setVisible(false);
						freezeAttackLabel.setVisible(false);
					}
				}	
				else
				{
					optionBButton.setVisible(false);
					specializedAttackLabel.setVisible(false);
					optionCButton.setVisible(false);
					freezeAttackLabel.setVisible(false);
				}
			}
			else if(mathBossTurn)
			{
				if(player.getQuantReasoning()>2.4){
					optionBButton.setVisible(true);
					specializedAttackLabel.setVisible(true);
					
					if(player.getQuantReasoning()>3.9){
						optionCButton.setVisible(true);
						freezeAttackLabel.setVisible(true);
					}
					else{
						optionCButton.setVisible(false);
						freezeAttackLabel.setVisible(false);
					}
				}	
				else
				{
					optionCButton.setVisible(false);
					freezeAttackLabel.setVisible(false);
					optionBButton.setVisible(false);
					specializedAttackLabel.setVisible(false);
				}
			}
			else if (sciBossTurn)
			{
				if(player.getSciRigor()>2.4){
					optionBButton.setVisible(true);
					specializedAttackLabel.setVisible(true);
					
					if(player.getSciRigor()>3.9){
						optionCButton.setVisible(true);
						freezeAttackLabel.setVisible(true);
					}
					else{
						optionCButton.setVisible(false);
						freezeAttackLabel.setVisible(false);
					}
				}	
				else
				{
					optionCButton.setVisible(false);
					freezeAttackLabel.setVisible(false);
					optionBButton.setVisible(false);
					specializedAttackLabel.setVisible(false);
				}
			}
		}
		else{
			optionBButton.setVisible(false);
			specializedAttackLabel.setVisible(false);
			optionCButton.setVisible(false);
			freezeAttackLabel.setVisible(false);
		}


	}

	public void moveBoss(){
		redrawAttackMenu();
		if(mathBossTurn&&!mathBossFreeze){
			mathBossX += xSpeed;

			mathBossY -= ySpeed;

			// if student touches the boss , tell him to go the other direction
			if (mathBossX > 500) 
			{
				drawConfused1=true;
				drawConfused2=false;
				setHitOrMiss();

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
				drawConfused1=false;
				drawConfused2=false;
				bossTimer.stop();
				anyBossTurn=false;
				redrawAttackMenu();
				xSpeed=5;
				ySpeed=1;
				mathBossTurn=false;
				bossChosen="Humanities";
				bossHealthLabel.setText("Choose");
				bossTypeLabel.setText("a boss");
				bossNameLabel.setText("to attack");
				bossSpecialAttackLabel.setText("");
				//bossSpecialDefenseLabel.setText("");
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(0,250,130,140); 

				// Set hit and miss images to visible(false)
			}
			else if(mathBossX>450 && mathBossX<480)
			{
				drawConfused1=false;
				drawConfused2=true;
			}
		}
		else if(humBossTurn&&!humBossFreeze){
			attackY += 1;
			if(attackY>50)
			{

				drawConfused1=false;
				drawConfused2=false;
				setHitOrMiss();

				if(playerHealth<0)
					playerHealthLabel.setText("0%");
				else
					playerHealthLabel.setText(playerHealth+"%");

				xSpeed=5;
				anyBossTurn=true;
				humBossTurn=false;
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(0,250,130,140); 

				bossHealthLabel.setText("Choose");
				bossTypeLabel.setText("a boss");
				bossNameLabel.setText("to attack");
				bossSpecialAttackLabel.setText("");

				if(sciBossHealth>0&&!sciBossFreeze)
				{					
					sciBossTurn=true;
					humBossButton.setBounds(0,0,140,140); 
					sciBossButton.setBounds(100,110,130,140); 
					mathBossButton.setBounds(0,250,130,140); 
					bossHealthLabel.setText(sciBossHealth+"%");
					bossTypeLabel.setText("Type: Science");
					bossNameLabel.setText("Name: Froggerhut");
					bossSpecialAttackLabel.setText("Special Attack: Shooting Scalpels");
					//bossSpecialDefenseLabel.setText("Special Defense: Preservation");

					attackX=60;
					attackY=240;
					moveBoss();
				}
				else if(mathBossHealth>0&&!mathBossFreeze)
				{
					mathBossTurn=true;
					humBossButton.setBounds(0,0,140,140); 
					sciBossButton.setBounds(0,110,130,140); 
					mathBossButton.setBounds(100,250,130,140); 
					bossHealthLabel.setText(mathBossHealth+"%");
					bossTypeLabel.setText("Type: Math");
					bossNameLabel.setText("Name: Number of Doom");
					bossSpecialAttackLabel.setText("Special Attack: Number Cruncher");
					moveBoss();
				}
				else{
					anyBossTurn=false;
					bossTimer.stop();
					redrawAttackMenu();	
				}
			}
			else if(attackY>10){
				drawConfused2=true;
				drawConfused1=false;
			}

			// Set hit and miss images to visible(false)
		}
		else if(sciBossTurn&&!sciBossFreeze)
		{
			attackX+=12;

			if(attackX>800)
			{
				drawConfused1=false;
				drawConfused2=false;
				setHitOrMiss();
				if(playerHealth<0)
					playerHealthLabel.setText("0%");
				else
					playerHealthLabel.setText(playerHealth+"%");

				sciBossTurn=false;
				anyBossTurn=true;
				bossChosen="Humanities";
				bossHealthLabel.setText("Choose");
				bossTypeLabel.setText("a boss");
				bossNameLabel.setText("to attack");
				bossSpecialAttackLabel.setText("");
				humBossButton.setBounds(0,0,140,140); 
				sciBossButton.setBounds(0,110,130,140); 
				mathBossButton.setBounds(0,250,130,140); 

				if(mathBossHealth>0&&!mathBossFreeze){
					mathBossTurn=true;
					humBossButton.setBounds(0,0,140,140); 
					sciBossButton.setBounds(0,110,130,140); 
					mathBossButton.setBounds(100,250,130,140); 
					bossHealthLabel.setText(mathBossHealth+"%");
					bossTypeLabel.setText("Type: Math");
					bossNameLabel.setText("Name: Number of Doom");
					bossSpecialAttackLabel.setText("Special Attack: Number Cruncher");
					moveBoss();
				}
				else{
					anyBossTurn=false;
					bossTimer.stop();
					redrawAttackMenu();
				}
			}
			else if(attackX>550 && attackX<580){
				drawConfused1=true;
				drawConfused2=false;
			}
			else if(attackX>=600)
			{
				drawConfused1=false;
				drawConfused2=true;
			}

			// Set hit and miss images to visible(false)
		}
		else
		{
			anyBossTurn=false;
			bossTimer.stop();
			redrawAttackMenu();
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
			else if(freezeAttack)
			{
				if(bossChosen.equals("Science")){
					sciBossHealth-=((player.getSciRigor()*15)+r.nextInt(5));
					sciFreezeRounds=2;
					sciBossFreeze=true;
				}
				else if(bossChosen.equals("Math")){
					mathBossHealth-=((player.getQuantReasoning()*15)+r.nextInt(5));
					mathBossFreeze=true;
					mathFreezeRounds=2;
				}
				else if(bossChosen.equals("Humanities")){
					humBossHealth-=	((player.getCreativity()*15)+r.nextInt(5));
					humFreezeRounds=2;
					humBossFreeze=true;
				}
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
					//bossSpecialDefenseLabel.setText("Special Defense: Bias Data");
				}
				else if(sciBossTurn){
					humBossButton.setBounds(0,0,140,140); 
					sciBossButton.setBounds(100,110,130,140); 
					mathBossButton.setBounds(0,250,130,140); 
					bossHealthLabel.setText(sciBossHealth+"%");
					bossTypeLabel.setText("Type: Science");
					bossNameLabel.setText("Name: Froggerhut");
					bossSpecialAttackLabel.setText("Special Attack: Shooting Scalpels");
					//bossSpecialDefenseLabel.setText("Special Defense: Preservation");
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
					//bossSpecialDefenseLabel.setText("Special Defense: Long Live the King!");
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

				if(player.getCreativity()>2.4){
					optionBButton.setVisible(true);
					specializedAttackLabel.setVisible(true);
					
					if(player.getCreativity()>3.9){
						optionCButton.setVisible(true);
						freezeAttackLabel.setVisible(true);
					}
					else
					{
						optionCButton.setVisible(false);
						freezeAttackLabel.setVisible(false);
					}
				}	
				else
				{
					optionBButton.setVisible(false);
					specializedAttackLabel.setVisible(false);
					optionCButton.setVisible(false);
					freezeAttackLabel.setVisible(false);
				}
				//bossSpecialDefenseLabel.setText("Special Defense: Long Live the King!");
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

				if(player.getSciRigor()>2.4){
					optionBButton.setVisible(true);
					specializedAttackLabel.setVisible(true);
					
					if(player.getSciRigor()>3.9){
						optionCButton.setVisible(true);
						freezeAttackLabel.setVisible(true);
					}
					else
					{
						optionCButton.setVisible(false);
						freezeAttackLabel.setVisible(false);
					}
				}	
				else
				{
					optionBButton.setVisible(false);
					specializedAttackLabel.setVisible(false);
					optionCButton.setVisible(false);
					freezeAttackLabel.setVisible(false);
				}
				//bossSpecialDefenseLabel.setText("Special Defense: Preservation");
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

				if(player.getQuantReasoning()>2.4){
					optionBButton.setVisible(true);
					specializedAttackLabel.setVisible(true);
					
					if(player.getQuantReasoning()>3.9){
						optionCButton.setVisible(true);
						freezeAttackLabel.setVisible(true);
					}
					else
					{
						optionCButton.setVisible(false);
						freezeAttackLabel.setVisible(false);
					}
				}
				else
				{
					optionBButton.setVisible(false);
					specializedAttackLabel.setVisible(false);
					optionCButton.setVisible(false);
					freezeAttackLabel.setVisible(false);
				}

				//bossSpecialDefenseLabel.setText("Special Defense: Bias Data");
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
				optionC=false;
				specialAttack=false;
				freezeAttack=false;
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
				optionC=false;
				specialAttack=true;
				freezeAttack=false;
				repaint();
			}
		}
	}
	
	// action listener for the Option B Button
		private class CButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(!attackPressed){
					optionA=false;
					optionB=false;
					freezeAttack=true;
					optionC=true;
					specialAttack=false;
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
