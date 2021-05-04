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

	/*
 		String name;
		double mass;
		double charge;
		Color color;
		int x;
		int y;
		double vx;
		double vy;
	*/
	
	public Body next(ArrayList<Body> listBody, ArrayList<Double> coefsList, int to) {

		int dl=listBody.size();
		int czlony=coefsList.size()/2;
		//ArrayList<Body>
		
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
					/*System.out.println("charge "+i);
					System.out.println(listBody.get(i).charge);
					
					System.out.println("charge "+ to);
					System.out.println(listBody.get(to).charge);
					
					System.out.println("coefs "+j);
					System.out.println(coefsList.get(j));
					
					System.out.println("r");
					System.out.println(r);
					
					System.out.println("coefs "+j+1);
					System.out.println(coefsList.get(j+1));
					*/
				}
				
				//System.out.println("F");
				//System.out.println(F);
				Fy=Fy+F*((listBody.get(i).getY()-listBody.get(to).getY())/r);
				Fx=Fx+F*((listBody.get(i).getX()-listBody.get(to).getX())/r);
				/*System.out.println("sin");
				System.out.println((listBody.get(i).getY()-listBody.get(to).getY())/r);
				System.out.println("cos");
				System.out.println((listBody.get(i).getX()-listBody.get(to).getX())/r);
				
				System.out.println("dx");
				System.out.println(listBody.get(i).getX()-listBody.get(to).getX());
				System.out.println("dy");
				System.out.println(listBody.get(i).getY()-listBody.get(to).getY());
				System.out.println("r");
				System.out.println(r);
				*/
				F=0;
			}
		}
		
		//System.out.println("Fx");
		//System.out.println(Fx);
		//System.out.println("Fy");
		//System.out.println(Fy);
		
		double ax=Fx/listBody.get(to).mass;
		double ay=Fy/listBody.get(to).mass;
		//listBody.get(to).setX((int) (listBody.get(to).x+listBody.get(to).vx*dt+0.5*ax*dt*dt));
		x=listBody.get(to).x+listBody.get(to).vx*dt+0.5*ax*dt*dt;
		//listBody.get(to).setY((int) (listBody.get(to).y+listBody.get(to).vy*dt+0.5*ay*dt*dt));
		y=listBody.get(to).y+listBody.get(to).vy*dt+0.5*ay*dt*dt;
		//listBody.get(to).setVx(listBody.get(to).vx+ax*dt);
		vx=listBody.get(to).vx+ax*dt;
		//listBody.get(to).setVy(listBody.get(to).vy+ay*dt);
		vy=listBody.get(to).vy+ay*dt;
	
		System.out.println("*****Cia³o "+listBody.get(to).name+"*****");
		
		System.out.println("vxp");
		System.out.println(listBody.get(to).vx);
		System.out.println("vxk");
		System.out.println(vx);
		System.out.println("dvx");
		System.out.println(vx-listBody.get(to).vx);
		System.out.println("vyp");
		System.out.println(listBody.get(to).vy);
		System.out.println("vyk");
		System.out.println(vy);
		System.out.println("dvy");
		System.out.println(vy-listBody.get(to).vy);
		
		System.out.println(vx);
		System.out.println(vy);
		
		/*
		if(to==0) {
		System.out.println("Cia³o "+to);
		System.out.println(x);
		System.out.println(y);
		
		System.out.println(ax);
		System.out.println(ay);
		System.out.println(czlony);
		}
		*/
		
		Body tmp=new Body(listBody.get(to).name, listBody.get(to).mass, listBody.get(to).charge, listBody.get(to).color, x, y, vx, vy, listBody.get(to).j);
		
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