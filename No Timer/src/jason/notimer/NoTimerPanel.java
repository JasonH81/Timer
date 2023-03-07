package jason.notimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NoTimerPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final String ALARM_FILE = "/alarm.wav";
	
	private int width = 150;
	private int height = 24;
	private String timeString = "00:00:00";
	private long time = 10;
	private Thread timerThread;
	
	public NoTimerPanel(long time, Font font) {
		setTime(time);
		setFont(font);
		height = font.getSize();
		FontMetrics fm = getFontMetrics(font);
		width = fm.stringWidth(timeString);
	}
	
	public void start() {
		stop();
		timerThread = new Thread(this);
		timerThread.start();
	}
	
	public void stop() {
		if (timerThread!=null) {
		timerThread.interrupt();
		timerThread = null;
		}
	}
	
	public void run() {
		while(time>0) {
			time-=1;
			setTime(time);
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				return;
			}
		}
		timesUp();
	}
	
	protected void timesUp() {
		try {
			URL url = getClass().getResource(ALARM_FILE);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			String message = "Time is up!";
			JOptionPane.showMessageDialog(this, message);
			clip.stop();
		}
		catch (IOException e) {
			String message = "The file " + ALARM_FILE + " could not be opened.";
			JOptionPane.showMessageDialog(this, message);
		}
		catch (UnsupportedAudioFileException e) {
			String message = "The file " + ALARM_FILE + " is not a valid audio file.";
			JOptionPane.showMessageDialog(this, message);
		}
		catch (LineUnavailableException e) {
			String message = "The resources are not available to open " + ALARM_FILE;
			JOptionPane.showMessageDialog(this, message);
		}
	}
	
	public void setTime(long time) {
		this.time = time;
		long h = time/3600;
		long m = (time/60) % 60;
		long s = time%60;
		timeString = String.format("%02d:%02d:%02d", h, m, s);
		repaint();
	}
	
	//test
	public long getTime() {
		return time;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString(timeString, 0, height);
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}

}
