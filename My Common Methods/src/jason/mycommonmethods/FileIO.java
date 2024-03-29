package jason.mycommonmethods;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class FileIO {
	
	public static Clip playClip(Object requestor, String fileName) {
		Clip clip = null;
		try {
			URL url = requestor.getClass().getResource(fileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch (IOException e) {
			String message = "The file " + fileName + " could not be opened.";
			JOptionPane.showMessageDialog(null, message);
		}
		catch (UnsupportedAudioFileException e) {
			String message = "The file " + fileName + " is not a valid audio file.";
			JOptionPane.showMessageDialog(null, message);
		}
		catch (LineUnavailableException e) {
			String message = "The resources are not available to open " + fileName;
			JOptionPane.showMessageDialog(null, message);
		}
		return clip;
	}

}
