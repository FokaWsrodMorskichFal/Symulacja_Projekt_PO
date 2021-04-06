package mainPack;

import java.awt.Color;

public class Body {
	
	String name;
	double mass;
	Color color;
	int x;
	int y;
	double vx;
	double vy;
	
	
	public Body(String n, double m, Color c, int xx, int yy, double vxx, double vyy, int j) {
		
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


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
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