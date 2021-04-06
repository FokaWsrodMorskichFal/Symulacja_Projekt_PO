package projekt.symulacja;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class SimulatePanel extends JPanel {

	static final int r = 10; 
	ArrayList<Body> listBody;
	
	
	public SimulatePanel(ArrayList<Body> bodyList) {
		
		this.listBody = bodyList;
		this.setBackground(Color.black);
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke bs1 = new BasicStroke(2);
		g2d.setStroke(bs1);
		
		
		
		for(int i = 0; i < listBody.size();i++) {
			
			g2d.setColor(listBody.get(i).getColor());
			
			g2d.fillOval(listBody.get(i).getX(),listBody.get(i).getY() , r, r);
			
			g2d.setColor(Color.white);
			g2d.drawString(listBody.get(i).getName(), listBody.get(i).getX()+5+r, listBody.get(i).getY()+5+r);
			
		}
		
		
	}
}
