package mainPack;

import java.awt.Color;
import java.util.ArrayList;

public class Body {
	
	String name;
	double mass;
	double charge;
	Color color;
	double x;
	double y;
	double vx;
	double vy;
	double dt=0.1;
	int j;
	
	
	public Body(String n, double m, double charge, Color c, double xx, double yy, double vxx, double vyy, int j) {
		
		this.j=j;
		this.charge=charge;
		if(n == null) {
			name = "Obiekt" + Integer.toString(j+1);
			
		}else{	
			name = n;
		}
		mass = m;
		
		if(c == null) {
			color = new Color((j+1)*20, (j+1)*20, (j+1)*20);
		}else {
			color =  c;
		}
		
		x = xx;
		y = yy;
		vx = vxx;
		vy = vyy;
		
	}

	
	
	public Body next(ArrayList<Body> listBody, ArrayList<Double> coefsList, int to) {

		int dl=listBody.size();
		int czlony=coefsList.size()/2;
		
		
		double x=0;
		double y=0;
		double vx=0;
		double vy=0;
		double F=0;
		double Fx=0;
		double Fy=0;
		for(int i=0; i<dl; i++) {
			if(i!=to) {
				Double r=Math.pow(Math.pow(listBody.get(i).getX()-listBody.get(to).getX(), 2)+Math.pow(listBody.get(i).getY()-listBody.get(to).getY(), 2), 0.5);
				for(int j=0;j<czlony;j++) {
					F=+listBody.get(i).charge*listBody.get(to).charge*coefsList.get(j)*Math.pow(r, coefsList.get(j+1));
					
				}
				
				//System.out.println("F");
				//System.out.println(F);
				Fy=Fy+F*((listBody.get(i).getY()-listBody.get(to).getY())/r);
				Fx=Fx+F*((listBody.get(i).getX()-listBody.get(to).getX())/r);
				
				F=0;
			}
		}
		
		
		
		double ax=Fx/listBody.get(to).mass;
		double ay=Fy/listBody.get(to).mass;
		
		x=listBody.get(to).x+listBody.get(to).vx*dt+0.5*ax*dt*dt;
		
		y=listBody.get(to).y+listBody.get(to).vy*dt+0.5*ay*dt*dt;
		
		vx=listBody.get(to).vx+ax*dt;
		
		vy=listBody.get(to).vy+ay*dt;
	
		
		
		
		
		Body tmp=new Body(listBody.get(to).name, listBody.get(to).mass, listBody.get(to).charge, listBody.get(to).color, x, y, vx, vy, listBody.get(to).j);
		
		return tmp;
	}
	
	
	public Body nextSat(ArrayList<Body> listBody, ArrayList<Double> coefsList, ArrayList<Body> satlist, int to) {

		int dl=listBody.size();
		int czlony=coefsList.size()/2;
		
		
		double x=0;
		double y=0;
		double vx=0;
		double vy=0;
		double F=0;
		double Fx=0;
		double Fy=0;
		for(int i=0; i<dl; i++) {
		
			Double r=Math.pow(Math.pow(listBody.get(i).getX()-satlist.get(to).getX(), 2)+Math.pow(listBody.get(i).getY()-satlist.get(to).getY(), 2), 0.5);
			for(int j=0;j<czlony;j++) {
				F=+listBody.get(i).charge*satlist.get(to).charge*coefsList.get(j)*Math.pow(r, coefsList.get(j+1));
					
			}
			
			System.out.println("F");
			System.out.println(F);
			Fy=Fy+F*((listBody.get(i).getY()-satlist.get(to).getY())/r);
			Fx=Fx+F*((listBody.get(i).getX()-satlist.get(to).getX())/r);
				
			F=0;
			
		}
		
		
		
		double ax=Fx/satlist.get(to).mass;
		double ay=Fy/satlist.get(to).mass;
		
		x=satlist.get(to).x+satlist.get(to).vx*dt+0.5*ax*dt*dt;
		
		y=satlist.get(to).y+satlist.get(to).vy*dt+0.5*ay*dt*dt;
		
		vx=satlist.get(to).vx+ax*dt;
		
		vy=satlist.get(to).vy+ay*dt;
	
		
		
		
		
		Body tmp=new Body(satlist.get(to).name, satlist.get(to).mass, satlist.get(to).charge, satlist.get(to).color, x, y, vx, vy, satlist.get(to).j);
		
		return tmp;
	}
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getMass() {
		return mass;
	}


	public void setMass(double mass) {
		this.mass = mass;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public double getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public double getVx() {
		return vx;
	}


	public void setVx(double vx) {
		this.vx = vx;
	}


	public double getVy() {
		return vy;
	}


	public void setVy(double vy) {
		this.vy = vy;
	}
};