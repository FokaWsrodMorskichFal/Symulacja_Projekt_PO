package mainPack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;




@SuppressWarnings("serial")
public class SimulatePanel extends JPanel implements Runnable{

	public ArrayList<Point> pointList;
	public ArrayList<Double> coefsList;
	static final int r = 10; 
	ArrayList<Body> listBody;
	ArrayList<Body> copylistBody;
	int dl;
	int czas=3;
	
	public SimulatePanel(ArrayList<Body> bodyList, ArrayList<Double> coefsList) {
		
		dl=bodyList.size();
		listBody = new ArrayList<Body>();
		copylistBody = new ArrayList<Body>();
		for(int i=0; i<dl;i++) {
			listBody.add(bodyList.get(i));
		}
		for(int i=0; i<dl;i++) {
			copylistBody.add(bodyList.get(i));
		}
		this.coefsList = coefsList;
		this.setBackground(Color.black);
		
	}
	
	public void aktual(int czas) {
		this.czas=czas;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke bs1 = new BasicStroke(2);
		g2d.setStroke(bs1);
		
		
		
		for(int i = 0; i < listBody.size();i++) {
			
			g2d.setColor(listBody.get(i).getColor());
			
			g2d.fillOval( (int) (listBody.get(i).getX()), (int) (listBody.get(i).getY()) , r, r);
			
			g2d.setColor(Color.white);
			g2d.drawString(listBody.get(i).getName(), (int) (listBody.get(i).getX())+5+r, (int) (listBody.get(i).getY()+5+r));
			
		}
		
		
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(18/czas);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=0; i<dl; i++) {
				//this.listBody.get(i).next(copylistBody, coefsList, i);
				this.listBody.set(i, this.listBody.get(i).next(copylistBody, coefsList, i));
			}
			for(int i=0; i<dl;i++) {
				copylistBody.set(i, listBody.get(i));
			}
		repaint();
		}
	}
}






