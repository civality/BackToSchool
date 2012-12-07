package minigames.tiles;
import java.awt.*;

import javax.swing.*;

import main.BackToSchool;
import main.Player;
import main.Day;

public class TilePuzzle 
{
	
	public static void main(String[] args)
	{
		CardLayout layout = new CardLayout();
		JPanel cards = new JPanel(layout);
		BackToSchool frame = new BackToSchool(layout, cards);
		frame.setContentPane(new PuzzlePanel(new Player(), new Day(9), frame));
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
