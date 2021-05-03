package mainPack;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tlo extends JPanel {
	
	Image bgImage;
	BufferedImage bImage;
	
	public Tlo(String path) {
		File inputFile = new File(path);
		try {
			bImage = ImageIO.read(inputFile);
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		bgImage = (Image) bImage;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(bgImage, 0, 0, null);
	}
}