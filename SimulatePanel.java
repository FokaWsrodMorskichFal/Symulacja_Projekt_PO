package mainPack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;




@SuppressWarnings("serial")
public class SimulatePanel extends JPanel implements Runnable{

	public ArrayList<ArrayList<Point>> pointBodyMatrix;
	public ArrayList<ArrayList<Point>> pointSatMatrix;
	
	public ArrayList<Double> coefsList;
	
	static final int r = 10; 
	
	public ArrayList<Body> listBody;
	public ArrayList<Body> copylistBody;
	
	public ArrayList<Body> satList;
	public ArrayList<Body> copySatList;
	
	
	int counter=0;
	int dlbody;
	int dlsat;
	int czas=3;
	int smuga=63;
	double seconds = 0.00;
    int minutes = 0;
	
	boolean smuga_bool=false;
	
	public SimulatePanel(ArrayList<Body> bodyList, ArrayList<Double> coefsList) {
		
		satList = new ArrayList<Body>();
		copySatList = new ArrayList<Body>();
		
		listBody = new ArrayList<Body>();
		copylistBody = new ArrayList<Body>();

		listBody=bodyList;
		copylistBody=bodyList;
		
		dlbody=listBody.size();
		dlsat=satList.size();
		
		pointSatMatrix = new ArrayList<ArrayList<Point>>();
		pointBodyMatrix = new ArrayList<ArrayList<Point>>();
		
		for(int i=0; i<10; i++) {
			pointSatMatrix.add(new ArrayList<Point>());
			for(int j=0; j<smuga;j++) {
				pointSatMatrix.get(i).add(new Point(0, 0));
			}
		}
		for(int i=0; i<dlbody; i++) {
			pointBodyMatrix.add(new ArrayList<Point>());
			for(int j=0; j<smuga;j++) {
				pointBodyMatrix.get(i).add(new Point(0, 0));
			}
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
		
		if(counter==smuga) {
			counter=0;
		}
		
		for(int i = 0; i < listBody.size();i++) {
			
			//rysowanie aktualnych po³o¿eñ cia³
			
			g2d.setColor(listBody.get(i).getColor());
			
			g2d.fillOval( (int) (listBody.get(i).getX()), (int) (listBody.get(i).getY()) , r, r);
			
			//Czêœæ odpowiadaj¹ca za bie¿¹ce zapisywanie historii punktów do macierzy
			//o wymiarach "liczba cia³" x "smuga"
			
			pointBodyMatrix.get(i).set(counter, pointBodyMatrix.get(i).get(counter).aktual((int) (listBody.get(i).getX()), (int) (listBody.get(i).getY())));
			
			//jeœli smuga jest w³¹czona to j¹ wyœwietlamy
			if(smuga_bool==true) {
				for(int j=counter+1; j<smuga+counter+1;j++) {
					if(j<smuga){
						Color kolorek=new Color(listBody.get(i).getColor().getRed(),listBody.get(i).getColor().getGreen(),listBody.get(i).getColor().getBlue(), 2*(j-counter));//180+(j-counter)
						g2d.setColor(kolorek);
						g2d.fillOval( (int) (pointBodyMatrix.get(i).get(j).x), (int) (pointBodyMatrix.get(i).get(j).y) , r, r);//*((255-2*(j-counter))/(255))
					}else{
						Color kolorek=new Color(listBody.get(i).getColor().getRed(),listBody.get(i).getColor().getGreen(),listBody.get(i).getColor().getBlue(), 2*(j-counter));//180+(j-counter)//2*(j-counter)
						g2d.setColor(kolorek);
						g2d.fillOval( (int) (pointBodyMatrix.get(i).get(j-smuga).x), (int) (pointBodyMatrix.get(i).get(j-smuga).y) , r, r);
					}
				}
			}
			
			g2d.setColor(Color.white);
			g2d.drawString(listBody.get(i).getName(), (int) (listBody.get(i).getX())+5+r, (int) (listBody.get(i).getY()+5+r));	
		}
		
		g2d.setColor(Color.white);
		
		g2d.setFont(new Font(null, 0, 16));
        g2d.drawString(String.format("%02d", minutes) + ":" + String.format("%02d", (int) seconds)+ ":" + String.format("%02d", (int) (100*(seconds -((int) seconds)))), 0, 16);
        g2d.setFont(new Font(null, 0, 12));
        
			
			
		
		if(satList.size() != 0) {
				
			for(int i = 0; i < satList.size(); i++) {
				g2d.setColor(satList.get(i).getColor());
				
				g2d.fillOval( (int) (satList.get(i).getX()), (int) (satList.get(i).getY()) , r-2, r-2);
				
				pointSatMatrix.get(i).set(counter, pointSatMatrix.get(i).get(counter).aktual((int) (satList.get(i).getX()), (int) (satList.get(i).getY())));
				
				if(smuga_bool==true) {
					for(int j=counter+1; j<smuga+counter+1;j++) {
						if(j<smuga){
							Color kolorek=new Color(satList.get(i).getColor().getRed(),satList.get(i).getColor().getGreen(),satList.get(i).getColor().getBlue(), 2*(j-counter));//180+(j-counter)
							g2d.setColor(kolorek);
							g2d.fillOval( (int) (pointSatMatrix.get(i).get(j).x), (int) (pointSatMatrix.get(i).get(j).y) , r, r);//*((255-2*(j-counter))/(255))
						}else{
							Color kolorek=new Color(satList.get(i).getColor().getRed(),satList.get(i).getColor().getGreen(),satList.get(i).getColor().getBlue(), 2*(j-counter));//180+(j-counter)//2*(j-counter)
							g2d.setColor(kolorek);
							g2d.fillOval( (int) (pointSatMatrix.get(i).get(j-smuga).x), (int) (pointSatMatrix.get(i).get(j-smuga).y) , r, r);
						}
					}
				}
				
				g2d.setColor(Color.white);
				g2d.drawString(satList.get(i).getName(), (int) (satList.get(i).getX())+3+r, (int) (satList.get(i).getY()+3+r));
			}
		}
		counter++;
	}
		
	
	Timer timer = new Timer();
    TimerTask taskTimer = new TimerTask() {
            public void run () {
            	while(true) {
	            	try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	                seconds += 0.01;
	                //System.out.println("SEKUNDY " + seconds);
	                if(seconds > 60) {
	                    minutes ++;
	                    seconds = 0.0;
	                }
            	}
            }
    	};
	

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(18/czas);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=0; i<listBody.size(); i++) {
				//this.listBody.get(i).next(copylistBody, coefsList, i);
				this.listBody.set(i, this.listBody.get(i).next(copylistBody, coefsList, i));
			}
			
			if(satList.size() != 0) {
				
				copySatList = satList;
				for(int i = 0 ; i < satList.size(); i++) {
					
					//System.out.println(satList.get(i).getX()+" "+satList.get(i).getY());
					this.satList.set(i, this.satList.get(i).nextSat(copylistBody, coefsList, copySatList, i));
					//System.out.println(satList.get(i).getX()+" "+satList.get(i).getY());
				}
			}
			
			for(int i=0; i<listBody.size();i++) {
				copylistBody.set(i, listBody.get(i));
			}
			
		repaint();
		}
	}

	public double getSeconds() {
		return seconds;
	}

	public void setSeconds(double seconds) {
		this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
}








	
	
	
	
	
	
	
	
	
	
	

