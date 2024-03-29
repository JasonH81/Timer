package jason.mycomponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Font SMALL_FONT = new Font(Font.DIALOG, Font.PLAIN, 12);
	private static final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 36);
	
	private int initialScore = 0;
	private int score = 0;
	private JLabel scoreLabel = new JLabel("0");
	
	public ScorePanel(int initialScore, Color color) {
		this.initialScore = initialScore;
		score = initialScore;
		setBackground(color);
		
		JLabel scoreTitleLabel = new JLabel("Score: ");
		scoreTitleLabel.setFont(SMALL_FONT);
		add(scoreTitleLabel);
		scoreLabel.setFont(BIG_FONT);
		add(scoreLabel);
	}
	
	public void addToScore(int points) {
		score += points;
		scoreLabel.setText(score + "");
	}
	
	public void reset() { 
		score = initialScore;
		scoreLabel.setText(score + "");
	}
}
